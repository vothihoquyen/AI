package model;

import java.util.ArrayList;
import java.util.Scanner;

import controller.Result;



public class Game {
	private BanCo banCo = new BanCo();
	private Scanner scanner;
	private Minimax minimax;
	private OCo[] oCo;
	private ArrayList<Result> results;

	public Game() {
		results = new ArrayList<>();
		scanner = new Scanner(System.in);
		minimax = new Minimax();
		oCo = banCo.getOCo();
	}

	public void play(boolean chon) {

		int player = 2;
		while (!banCo.finish()) {
			if (player == 1) {
				if (banCo.kiemTraHetQuan(player)) {
					System.out.println("Computer rải quân");
					banCo.raiQuan(player);
				}
			} else {
				if (banCo.kiemTraHetQuan(player)) {
					System.out.println("Người chơi rải quân");
					banCo.raiQuan(player);
				}
			}

			banCo.setResults(new ArrayList<>());
			System.out.println("Điểm computer: " + oCo[12].getGiaTri());
			System.out.println("Điểm người chơi: " + oCo[13].getGiaTri());
			banCo.printBanCo();
			int viTri = 0;
			if (chon) {
				if (player == 2) {
					System.out.print("Người chơi 2 chọn [Vị trí] = ");
					String s = scanner.nextLine();
					try {
						viTri = Integer.parseInt(s.substring(1, s.length()));
					} catch (NumberFormatException e) {
						e.printStackTrace();
						continue;
					}
					if (!banCo.kiemTra(viTri, player)) {
						System.err.println("Người chơi 2 không được chọn ô này");
						continue;
					}
					if (s.substring(0, 1).equals("l")) {
						int diemSo = banCo.anBenTrai(banCo.quaTrai(viTri));
						banCo.setDiemNguoiChoi2(diemSo);
					} else if (s.substring(0, 1).equals("r")) {
						int diemSo = banCo.anBenPhai(banCo.quaPhai(viTri));
						banCo.setDiemNguoiChoi2(diemSo);
					} else {
						continue;
					}
				} else {
					int[] a = new int[3];
					int bestLocation = 0;
					int bestdiemSo = Integer.MIN_VALUE;
					int huong = 0;
					a = minimax.minimax(adapter(banCo), 5, 1);
					if (a[0] > bestdiemSo) {
						bestdiemSo = a[0];
						bestLocation = a[1];
						huong = a[2];
					}
					System.out.println("Vi tri " + bestLocation + ", Huong " + huong);
					int diemSo = 0;
					if (a[2] == 1) {
						diemSo = banCo.anBenTrai(banCo.quaTrai(a[1]));
					} else {
						diemSo = banCo.anBenPhai(banCo.quaPhai(a[1]));
					}
					banCo.setDiemNguoiChoi1(diemSo);
				}
			} else {

				if (player == 2) {
					System.out.print("Người chơi 2 chọn [Vị trí] = ");
					String s = scanner.nextLine();
					try {
						viTri = Integer.parseInt(s.substring(1, s.length()));
					} catch (NumberFormatException e) {
						e.printStackTrace();
						continue;
					}
					if (!banCo.kiemTra(viTri, player)) {
						System.err.println("Người chơi 2 không được chọn ô này");
						continue;
					}
					if (s.substring(0, 1).equals("l")) {
						int diemSo = banCo.anBenTrai(banCo.quaTrai(viTri));
						banCo.setDiemNguoiChoi2(diemSo);
					} else if (s.substring(0, 1).equals("r")) {
						int diemSo = banCo.anBenPhai(banCo.quaPhai(viTri));
						banCo.setDiemNguoiChoi2(diemSo);
					} else {
						continue;
					}
				} else {

					System.out.print("Người chơi 1 chọn [Vị trí] = ");
					String s = scanner.nextLine();
					try {
						viTri = Integer.parseInt(s.substring(1, s.length()));
					} catch (NumberFormatException e) {
						e.printStackTrace();
						continue;
					}
					if (!banCo.kiemTra(viTri, player)) {
						System.err.println("Người chơi 2 không được chọn ô này");
						continue;
					}
					if (s.substring(0, 1).equals("l")) {
						int diemSo = banCo.anBenTrai(banCo.quaTrai(viTri));
						banCo.setDiemNguoiChoi1(diemSo);
					} else if (s.substring(0, 1).equals("r")) {
						int diemSo = banCo.anBenPhai(banCo.quaPhai(viTri));
						banCo.setDiemNguoiChoi1(diemSo);
					} else {
						continue;
					}
				}

			}

			results = banCo.getResults();
			printResult(results);

			if (player == 1) {
				player = 2;
			} else {
				player = 1;
			}
		}
		banCo.printBanCo();
		banCo.themDiemSo(1);
		banCo.themDiemSo(2);
		
		System.out.println("Điểm computer: " + oCo[12].getGiaTri());
		System.out.println("Điểm người chơi: " + oCo[13].getGiaTri());
		if (oCo[12].getGiaTri() > oCo[13].getGiaTri()) {
			System.out.println("Computer thắng");
		} else if (oCo[12].getGiaTri() < oCo[13].getGiaTri()) {
			System.out.println("Người chơi thắng");
		} else {
			System.out.println("Game hòa");
		}
		banCo.printBanCo();
	}

	public void printResult(ArrayList<Result> results) {
		for (Result result : results) {
			result.print();
		}
	}

	public BanCo adapter(BanCo banCo) {
		OCo[] oCo = banCo.getOCo();
		OCo[] s = new OCo[oCo.length];
		for (int i = 0; i < s.length; i++) {
			s[i] = new OCo(i, oCo[i].getGiaTri(), oCo[i].isQuan());
		}
		BanCo b = new BanCo();
		b.setOCo(s);
		return b;
	}

	public static void main(String[] args) {
		Game g = new Game();
		g.play(false);
	}
}
