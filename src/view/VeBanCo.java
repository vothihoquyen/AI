package view;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import model.OCo;

public class VeBanCo {
	private int player1 = 1, player2 = 2;
	public static int WIDTH = 100, HEIGHT = 100, HEIGHT_QUAN = 200, X = 100, Y = 100;
	public static final int[] boundX = { 25, 50, 35, 35, 60, 30, 60, 50, 75, 25, 60, 30, 55, 65, 27, 37, 52, 33, 73, 51,
			43, 68, 44, 24, 67, 25, 70, 64, 29, 46, 35, 25, 64, 53, 71, 47, 26, 68, 35, 57, 23, 45, 67, 31, 56, 68, 71,
			23, 46, 33, 56, 78, 34, 48, 67, 52, 29, 45, 69, 51, 48, 33, 74, 39, 26, 57, 48, 58, 72, 64, 45, 56 };
	public static final int[] boundY = { 65, 35, 45, 60, 50, 29, 47, 76, 46, 56, 34, 40, 35, 33, 45, 76, 25, 34, 59, 47,
			38, 68, 52, 67, 43, 28, 36, 57, 73, 56, 39, 47, 61, 26, 47, 57, 53, 74, 29, 55, 24, 30, 60, 50, 75, 25, 60,
			30, 55, 65, 27, 37, 52, 33, 73, 51, 43, 68, 44, 24, 67, 25, 70, 64, 29, 46, 35, 25, 64, 46, 33, 56, 78, 34,
			48, 67, 52, 29 };
	private Oco[] ocos;
	private OCo[] oCo;

	public VeBanCo(OCo[] oCo) {
		this.oCo = oCo;
		createBanco();
	}

	public void createBanco() {
		ocos = new Oco[14];
		for (int i = 1; i < 6; i++) {
			ocos[i] = new Oco(i * WIDTH + X, Y * 2, WIDTH + 2, HEIGHT + 2, oCo[i].getViTri(), oCo[i].getGiaTri(),
					oCo[i].isQuan(), player1);
		}
		ocos[7] = new Oco(X + WIDTH * (5), Y * 3, WIDTH + 2, HEIGHT, oCo[7].getViTri(), oCo[7].getGiaTri(),
				oCo[7].isQuan(), player2);
		ocos[8] = new Oco(X + WIDTH * (4), Y * 3, WIDTH + 2, HEIGHT, oCo[8].getViTri(), oCo[8].getGiaTri(),
				oCo[8].isQuan(), player2);
		ocos[9] = new Oco(X + WIDTH * (3), Y * 3, WIDTH + 2, HEIGHT, oCo[9].getViTri(), oCo[9].getGiaTri(),
				oCo[9].isQuan(), player2);
		ocos[10] = new Oco(X + WIDTH * (2), Y * 3, WIDTH + 2, HEIGHT, oCo[10].getViTri(), oCo[10].getGiaTri(),
				oCo[10].isQuan(), player2);
		ocos[11] = new Oco(X + WIDTH * (1), Y * 3, WIDTH + 2, HEIGHT, oCo[11].getViTri(), oCo[11].getGiaTri(),
				oCo[11].isQuan(), player2);
		ocos[0] = new Oco(X, Y * 2, WIDTH + 2, HEIGHT_QUAN, oCo[0].getViTri(), oCo[0].getGiaTri(), oCo[0].isQuan(), 0);
		ocos[6] = new Oco(WIDTH * 6 + X, Y * 2, WIDTH, HEIGHT_QUAN, oCo[6].getViTri(), oCo[6].getGiaTri(),
				oCo[6].isQuan(), 0);
		ocos[12] = new Oco(WIDTH * 3 + X, Y / 2, WIDTH, HEIGHT, oCo[12].getViTri(), oCo[12].getGiaTri(),
				oCo[12].isQuan(), 0);
		ocos[13] = new Oco(WIDTH * 3 + X, 50 + Y * 4, WIDTH, HEIGHT, oCo[13].getViTri(), oCo[13].getGiaTri(),
				oCo[13].isQuan(), 0);
	}

	public void draw(Graphics2D g2d) {
		for (int i = 0; i < ocos.length; i++) {
			ocos[i].draw(g2d);
		}
	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {
		for (int i = 0; i < ocos.length; i++) {
			ocos[i].mouseEntered(e);
		}
	}

	public void mouseExited(MouseEvent e) {
		for (int i = 0; i < ocos.length; i++) {
			ocos[i].mouseExited(e);
		}
	}

	public void mousePressed(MouseEvent e) {
		for (int i = 0; i < ocos.length; i++) {
			ocos[i].mousePressed(e);
		}
	}

	public void mouseReleased(MouseEvent e) {
		for (int i = 0; i < ocos.length; i++) {
			ocos[i].mouseReleased(e);
		}
	}

	public void mouseDragged(MouseEvent e) {
		for (int i = 0; i < ocos.length; i++) {
			ocos[i].mouseDragged(e);
		}
	}

	public void mouseMoved(MouseEvent e) {
		mouseEntered(e);
		mouseExited(e);
	}

	public void setoCo(OCo[] oCo) {
		this.oCo = oCo;
	}
}
