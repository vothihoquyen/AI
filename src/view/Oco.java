package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

public class Oco {
	private int x, y, width, height;
	private int viTri, value;
	private boolean isQuan;
	private Image imageCurrent, imageDefault, imageEntered, imagePressed;
	private boolean entered;
	private int player;

	public Oco(int x, int y, int width, int height, int viTri, int value, boolean isQuan, int player) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.viTri = viTri;
		this.value = value;
		this.isQuan = isQuan;
		this.player = player;
		imageDefault = loadImage("default.png");
		imageEntered = loadImage("entered.png");
		imagePressed = loadImage("pressed.png");
		imageCurrent = imageDefault;
	}

	public void draw(Graphics2D g2d) {
		g2d.setColor(Color.decode("#015ea6"));
		g2d.setFont(new Font("Tomaha", Font.CENTER_BASELINE, 25));
		if (!isQuan && viTri != 12 && viTri != 13) {
			g2d.drawImage(imageCurrent, x, y, width, height, null);
			g2d.drawString(value + "", x + 40, y + 25);
		} else if (isQuan) {
			if (viTri == 0) {
				g2d.drawImage(loadImage("quanTrai.png"), x, y, width, height, null);
				g2d.drawString(value + "", x + VeBanCo.WIDTH - 30, y + VeBanCo.HEIGHT_QUAN - 15);
			} else {
				g2d.drawImage(loadImage("quanPhai.png"), x, y, width, height, null);
				g2d.drawString(value + "", x + 5, y + 30);
			}
		} else {
			g2d.drawImage(loadImage("score.png"), x - 30, y, width + 65, height, null);
			if (viTri == 12) {
				g2d.drawString(value + "", x + width, y + 59);
				g2d.setColor(Color.decode("#48423f"));
			} else {
				g2d.drawString(value + "", x - 23, y + 59);
				g2d.setColor(Color.decode("#48423f"));
			}
		}
		g2d.setColor(Color.decode("#48423f"));
		if (isQuan) {
			for (int i = 0; i < value; i++) {
				g2d.fillOval(x + VeBanCo.boundX[i], y + VeBanCo.boundY[i] + 35, 13, 10);
			}
		} else {
			for (int i = 0; i < value; i++) {
				g2d.fillOval(x + VeBanCo.boundX[i], y + VeBanCo.boundY[i], 13, 10);
			}
		}
		if (entered) {
			g2d.drawImage(loadImage("trai.png"), x + 2, y, 35, 30, null);
			g2d.drawImage(loadImage("phai.png"), x + 65, y, 35, 30, null);
		}
	}

	public void mouseEntered(MouseEvent e) {
		if (!isQuan && viTri != 12 && viTri != 13 && value > 0) {
			if (player == 1 && viTri < 6 && viTri > 0) {
				Point p = e.getPoint();
				int px = (int) p.getX();
				int py = (int) p.getY();
				if ((px >= x && px <= x + width) && (py >= y && py <= y + height)) {
					imageCurrent = imageEntered;
					entered = true;
				}
			}
			
			if (player == 2 && viTri < 12 && viTri > 6) {
				Point p = e.getPoint();
				int px = (int) p.getX();
				int py = (int) p.getY();
				if ((px >= x && px <= x + width) && (py >= y && py <= y + height)) {
					imageCurrent = imageEntered;
					entered = true;
				}
			}
		}
	}

	public void mouseExited(MouseEvent e) {
		if (!isQuan && viTri != 12 && viTri != 13 && value > 0) {
			if (player == 1 && viTri < 6 && viTri > 0) {
				Point p = e.getPoint();
				int px = (int) p.getX();
				int py = (int) p.getY();
				if ((px <= x || px >= x + width) || (py <= y || py >= y + height)) {
					imageCurrent = imageDefault;
					entered = false;
				}
			}
			if (player == 2 && viTri < 12 && viTri > 6) {
				Point p = e.getPoint();
				int px = (int) p.getX();
				int py = (int) p.getY();
				if ((px <= x || px >= x + width) || (py <= y || py >= y + height)) {
					imageCurrent = imageDefault;
					entered = false;
				}
			}
		}

	}

	public void mousePressed(MouseEvent e) {
		if (!isQuan && viTri != 12 && viTri != 13 && value > 0) {
			if (player == 1 && viTri < 6 && viTri > 0) {
				Point p = e.getPoint();
				int px = (int) p.getX();
				int py = (int) p.getY();
				if ((px >= x && px <= x + width) && (py >= y && py <= y + height)) {
					imageCurrent = imagePressed;
				}
			}
			if (player == 2 && viTri < 12 && viTri > 6) {
				Point p = e.getPoint();
				int px = (int) p.getX();
				int py = (int) p.getY();
				if ((px >= x && px <= x + width) && (py >= y && py <= y + height)) {
					imageCurrent = imagePressed;
				}
			}

		}
	}

	public void mouseReleased(MouseEvent e) {
		if (!isQuan && viTri != 12 && viTri != 13 && value > 0) {
			if (player == 1 && viTri > 0 && viTri < 6) {
				Point p = e.getPoint();
				int px = (int) p.getX();
				int py = (int) p.getY();
				if ((px >= x && px <= x + width) && (py >= y && py <= y + height)) {
					imageCurrent = imageEntered;
				} else {
					imageCurrent = imageDefault;
				}
			}
			if (player == 2 && viTri > 6 && viTri < 12) {
				Point p = e.getPoint();
				int px = (int) p.getX();
				int py = (int) p.getY();
				if ((px >= x && px <= x + width) && (py >= y && py <= y + height)) {
					imageCurrent = imageEntered;
				} else {
					imageCurrent = imageDefault;
				}
			}
		}
	}

	public void mouseDragged(MouseEvent e) {
	}

	public void mouseMoved(MouseEvent e) {
		if (!isQuan && viTri != 12 && viTri != 13 && value > 0) {
			mouseEntered(e);
			mouseExited(e);
		}
	}

	public Image loadImage(String fileName) {
		return new ImageIcon(getClass().getResource("/images/" + fileName)).getImage();
	}
}
