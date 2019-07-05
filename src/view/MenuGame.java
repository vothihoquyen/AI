package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class MenuGame extends JPanel implements Runnable, MouseListener, MouseMotionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int X = 315, Y = 150, WIDTH = 300, HEIGHT = 70;
	private ButtonRectangle easy, normal, hard;
	private Thread thread;

	public MenuGame() {
		easy = new ButtonRectangle(X, Y + 100, WIDTH, HEIGHT, "easyDefault.png", "easyEntered.png", "easyPressed.png",
				1);
		normal = new ButtonRectangle(X, Y + 200, WIDTH, HEIGHT, "normalDefault.png", "normalEntered.png",
				"normalPressed.png", 2);
		hard = new ButtonRectangle(X, Y + 300, WIDTH, HEIGHT, "hardDefault.png", "hardEntered.png", "hardPressed.png",
				3);

		addMouseListener(this);
		addMouseMotionListener(this);
		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		while (true) {
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
		easy.draw(g2d);
		normal.draw(g2d);
		hard.draw(g2d);
		g2d.drawImage(loadImage("tieude.png"), 280, 40, null);
		g2d.setColor(Color.decode("#832411"));
		g2d.setFont(new Font("Courier New", Font.CENTER_BASELINE, 20));
		String[] authors = { "Võ Thị Hồ Quyên", "Nguyễn Tuấn Vũ", "Hoàng Thị Cẩm Hồng", "Võ Nguyễn Hữu Nhân" };
		for (int i = 0; i < authors.length; i++) {
			g2d.drawString(authors[i], 70, 575 + 20 * i);
		}
		g2d.drawImage(loadImage("bamboo.png"), 750, -50, 450, 700, null);
		g2d.drawImage(loadImage("bamboo1.png"), -270, -50, 450, 700, null);
	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		easy.mouseMoved(e);
		normal.mouseMoved(e);
		hard.mouseMoved(e);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		easy.mouseEntered(e);
		normal.mouseEntered(e);
		hard.mouseEntered(e);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		easy.mouseExited(e);
		normal.mouseExited(e);
		hard.mouseExited(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		easy.mousePressed(e);
		normal.mousePressed(e);
		hard.mousePressed(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		easy.mouseReleased(e);
		normal.mouseReleased(e);
		hard.mouseReleased(e);
	}

	public Image loadImage(String fileName) {
		return new ImageIcon(getClass().getResource("/images/" + fileName)).getImage();
	}

}
