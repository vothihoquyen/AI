package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import model.BanCo;
import model.Minimax;

public class GamePanel extends JPanel implements Runnable, MouseListener, MouseMotionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int DELAY = 100;
	private Thread thread;
	private BanCo board;
	private VeBanCo banCo;
	// private AlphaBeta alphaBeta;
	private Minimax minimax;
	private int player = 2;
	private int index = 0;
	private boolean finish, click, raiQuan;
	private int y = -100, difficult;
	private int location, action;
	private boolean chon;

	public GamePanel(int difficult, boolean chon) {
		this.difficult = difficult;
		this.chon = chon;
		if (!chon) {
			player = 1;
		}
		board = new BanCo();
		banCo = new VeBanCo(board.getOCo());
		// alphaBeta = new AlphaBeta();
		minimax = new Minimax();

		addMouseListener(this);
		addMouseMotionListener(this);
		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		while (!board.finish()) {
			index = 0;
			board.setResults(new ArrayList<>());
			if (player == 1) {
				if (board.kiemTraHetQuan(player)) {
					board.raiQuan(player);
					raiQuan = true;
					banCo = new VeBanCo(board.getResults().get(index).getOCo());
					repaint();
					try {
						Thread.sleep(DELAY);
					} catch (InterruptedException e) {
						e.printStackTrace();
					} finally {
						raiQuan = false;
					}
				}
			} else {
				if (board.kiemTraHetQuan(player)) {
					board.raiQuan(player);
					raiQuan = true;
					banCo = new VeBanCo(board.getResults().get(index).getOCo());
					repaint();
					try {
						Thread.sleep(DELAY);
					} catch (InterruptedException e) {
						e.printStackTrace();
					} finally {
						raiQuan = false;
					}
				}
			}
			board.setResults(new ArrayList<>());
			if (chon) {
				if (player == 1) {
					int[] a = miniMax(board, difficult, player);
					int location = a[0];
					int action = a[1];
					int score = 0;
					if (action == 1) {
						score = board.anBenTrai(board.quaTrai(location));
					} else {
						score = board.anBenPhai(board.quaPhai(location));
					}
					board.setDiemNguoiChoi1(score);
					while (index < board.getResults().size()) {
						banCo = new VeBanCo(board.getResults().get(index).getOCo());
						repaint();
						index++;
						try {
							Thread.sleep(DELAY);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					player = 2;
				} else {
					if (click) {
						int score = 0;
						if (action == 1) {
							score = board.anBenTrai(board.quaTrai(location));
						} else {
							score = board.anBenPhai(board.quaPhai(location));
						}
						board.setDiemNguoiChoi2(score);
						while (index < board.getResults().size()) {
							banCo = new VeBanCo(board.getResults().get(index).getOCo());
							repaint();
							index++;
							try {
								Thread.sleep(DELAY);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						player = 1;
					}
					repaint();
				}
			} else {

				if (player == 1) {
					if (click) {
						int score = 0;
						if (action == 1) {
							score = board.anBenTrai(board.quaTrai(location));
						} else {
							score = board.anBenPhai(board.quaPhai(location));
						}
						board.setDiemNguoiChoi1(score);
						while (index < board.getResults().size()) {
							banCo = new VeBanCo(board.getResults().get(index).getOCo());
							repaint();
							index++;
							try {
								Thread.sleep(DELAY);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						player = 2;
					}
					repaint();
				} else {
					if (click) {
						int score = 0;
						if (action == 1) {
							score = board.anBenTrai(board.quaTrai(location));
						} else {
							score = board.anBenPhai(board.quaPhai(location));
						}
						board.setDiemNguoiChoi2(score);
						while (index < board.getResults().size()) {
							banCo = new VeBanCo(board.getResults().get(index).getOCo());
							repaint();
							index++;
							try {
								Thread.sleep(DELAY);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						player = 1;
					}
					repaint();
				}

			}
		}
		index = 0;
		board.setResults(new ArrayList<>());
		while (!finish) {
			board.themDiemSo(1);
			board.themDiemSo(2);
			banCo = new VeBanCo(board.getResults().get(index).getOCo());
			repaint();
			index++;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				finish = true;
			}
		}
		while (y < 450) {
			y += 1;
			repaint();
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		while (y > 200) {
			y -= 1;
			repaint();
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.drawImage(loadImage("bg.jpg"), 0, 0, 970, 650, null);
		banCo.draw(g2d);
		if (player == 1) {
			g2d.drawImage(loadImage("co1.png"), 340, 35, 70, 70, null);
		} else {
			g2d.drawImage(loadImage("co2.png"), 495, 435, 70, 70, null);
		}
		g2d.setColor(Color.decode("#c65500"));
		g2d.setFont(new Font("Tomaha", Font.CENTER_BASELINE, 25));
		if (raiQuan) {
			if (chon) {
				if (player == 1) {
					g2d.drawString("Computer rải quân", 340, 180);
				} else {
					g2d.drawString("Bạn rải quân", 375, 435);
				}
			} else {
				if (player == 1) {
					g2d.drawString("Player 1", 340, 180);
				} else {
					g2d.drawString("Player 2", 375, 435);
				}
			}
		} else {
			if (chon) {
				if (player == 1) {
					g2d.drawString("Lượt computer", 360, 180);
				} else {
					g2d.drawString("Lượt của bạn", 370, 435);
				}
			} else {
				if (player == 1) {
					g2d.drawString("Lượt player 1", 360, 180);
				} else {
					g2d.drawString("Lượt player 2", 370, 435);
				}
			}

		}
		g2d.drawImage(loadImage("bamboo.png"), 750, -50, 450, 700, null);
		g2d.drawImage(loadImage("bamboo1.png"), -270, -50, 450, 700, null);
		g2d.setColor(Color.decode("#e81f1f"));
		g2d.setFont(new Font("Viner Hand ITC", Font.CENTER_BASELINE, 100));
		if (finish) {
			g2d.drawImage(loadImage("fade.png"), 0, 0, 930, 650, null);
			int computer = board.getOCo()[12].getGiaTri();
			int player = board.getOCo()[13].getGiaTri();
			if (computer > player) {
				if (chon) {
					g2d.drawString("Computer win", 105, y);
				} else {
					g2d.drawString("Player1 win", 105, y);
				}
			} else if (computer < player) {
				if (chon) {
					g2d.drawString("You win", 240, y);
				} else {
					g2d.drawString("Player 2 win", 240, y);
				}
			} else {
				g2d.drawString("Hòa", 345, y);
			}
			g2d.drawImage(loadImage("menu.png"), 325, y + 60, 250, 150, null);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		banCo.mouseEntered(e);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		banCo.mouseExited(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		banCo.mousePressed(e);
		Point p = e.getPoint();
		int px = (int) p.getX();
		int py = (int) p.getY();
		
		// true la nguoi voi may
		if (chon == false) { 
			if (player == 1) {
				if ((px >= 602 && px <= 637) && (py >= 200 && py <= 230)) {
					location = 5;
					action = 2;
					click = true;
				}
				if ((px >= 665 && px <= 700) && (py >= 200 && py <= 230)) {
					location = 5;
					action = 1;
					click = true;
				}
				if ((px >= 502 && px <= 537) && (py >= 200 && py <= 230)) {
					location = 4;
					action = 2;
					click = true;
				}
				if ((px >= 565 && px <= 600) && (py >= 200 && py <= 230)) {
					location = 4;
					action = 1;
					click = true;
				}
				if ((px >= 402 && px <= 437) && (py >= 200 && py <= 230)) {
					location = 3;
					action = 2;
					click = true;
				}
				if ((px >= 465 && px <= 500) && (py >= 200 && py <= 230)) {
					location = 3;
					action = 1;
					click = true;
				}
				if ((px >= 302 && px <= 337) && (py >= 200 && py <= 230)) {
					location = 2;
					action = 2;
					click = true;
				}
				if ((px >= 365 && px <= 400) && (py >= 200 && py <= 230)) {
					location = 2;
					action = 1;
					click = true;
				}
				if ((px >= 265 && px <= 300) && (py >= 200 && py <= 230)) {
					location = 1;
					action = 2;
					click = true;
				}
				if ((px >= 265 && px <= 300) && (py >= 200 && py <= 230)) {
					location = 1;
					action = 1;
					click = true;
				}
			} else {
				if ((px >= 602 && px <= 637) && (py >= 300 && py <= 330)) {
					location = 7;
					action = 1;
					click = true;
				}
				if ((px >= 665 && px <= 700) && (py >= 300 && py <= 330)) {
					location = 7;
					action = 2;
					click = true;
				}
				if ((px >= 502 && px <= 537) && (py >= 300 && py <= 330)) {
					location = 8;
					action = 1;
					click = true;
				}
				if ((px >= 565 && px <= 600) && (py >= 300 && py <= 330)) {
					location = 8;
					action = 2;
					click = true;
				}
				if ((px >= 402 && px <= 437) && (py >= 300 && py <= 330)) {
					location = 9;
					action = 1;
					click = true;
				}
				if ((px >= 465 && px <= 500) && (py >= 300 && py <= 330)) {
					location = 9;
					action = 2;
					click = true;
				}
				if ((px >= 302 && px <= 337) && (py >= 300 && py <= 330)) {
					location = 10;
					action = 1;
					click = true;
				}
				if ((px >= 365 && px <= 400) && (py >= 300 && py <= 330)) {
					location = 10;
					action = 2;
					click = true;
				}
				if ((px >= 202 && px <= 237) && (py >= 300 && py <= 330)) {
					location = 11;
					action = 1;
					click = true;
				}
				if ((px >= 265 && px <= 300) && (py >= 300 && py <= 330)) {
					location = 11;
					action = 2;
					click = true;
				}
			}
			
		} else {
			if ((px >= 602 && px <= 637) && (py >= 300 && py <= 330)) {
				location = 7;
				action = 1;
				click = true;
			}
			if ((px >= 665 && px <= 700) && (py >= 300 && py <= 330)) {
				location = 7;
				action = 2;
				click = true;
			}
			if ((px >= 502 && px <= 537) && (py >= 300 && py <= 330)) {
				location = 8;
				action = 1;
				click = true;
			}
			if ((px >= 565 && px <= 600) && (py >= 300 && py <= 330)) {
				location = 8;
				action = 2;
				click = true;
			}
			if ((px >= 402 && px <= 437) && (py >= 300 && py <= 330)) {
				location = 9;
				action = 1;
				click = true;
			}
			if ((px >= 465 && px <= 500) && (py >= 300 && py <= 330)) {
				location = 9;
				action = 2;
				click = true;
			}
			if ((px >= 302 && px <= 337) && (py >= 300 && py <= 330)) {
				location = 10;
				action = 1;
				click = true;
			}
			if ((px >= 365 && px <= 400) && (py >= 300 && py <= 330)) {
				location = 10;
				action = 2;
				click = true;
			}
			if ((px >= 202 && px <= 237) && (py >= 300 && py <= 330)) {
				location = 11;
				action = 1;
				click = true;
			}
			if ((px >= 265 && px <= 300) && (py >= 300 && py <= 330)) {
				location = 11;
				action = 2;
				click = true;
			}
		}
		
		

		
		if (finish) {
			if ((px >= 350 && px <= 550) && (py >= 350 && py <= 400)) {
				restart();
			}
			if ((px >= 325 && px <= 575) && (py >= 260 && py <= 410)) {
				MainFrame.startActivity(new MenuGame());
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		banCo.mouseReleased(e);
		click = false;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		banCo.mouseDragged(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		banCo.mouseMoved(e);
	}

	public int[] miniMax(BanCo b, int difficult, int player) {
		BanCo boa = new BanCo();
		boa.setOCo(b.getOCo());
		int max = Integer.MIN_VALUE;
		int location = -1;
		int action = 0;
		int[] tmp = new int[3];
		tmp = minimax.minimax(boa, difficult, player);
		if (tmp[0] > max) {
			max = tmp[0];
			location = tmp[1];
			action = tmp[2];
		}
		return new int[] { location, action };
	}

	public void restart() {
		finish = false;
		click = false;
		raiQuan = false;
		board = new BanCo();
		banCo = new VeBanCo(board.getOCo());
		// alphaBeta = new AlphaBeta();
		minimax = new Minimax();
		player = 2;
		addMouseListener(this);
		addMouseMotionListener(this);
		repaint();
		thread = new Thread(this);
		thread.start();
	}

	public Image loadImage(String fileName) {
		return new ImageIcon(getClass().getResource("/images/" + fileName)).getImage();
	}
}
