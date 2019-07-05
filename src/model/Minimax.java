package model;

import java.util.ArrayList;

public class Minimax {

	public int[] minimax(BanCo banCo, int doSau, int nguoiChoi) {
		if (nguoiChoi == 1) {
			if (banCo.kiemTraHetQuan(nguoiChoi)) {
				banCo.raiQuan(nguoiChoi);
			}
		} else {
			if (banCo.kiemTraHetQuan(nguoiChoi)) {
				banCo.raiQuan(nguoiChoi);
			}
		}
		OCo[] oCo = banCo.getOCo();
		int[] valueRoot = new int[14];
		for (int i = 0; i < 14; i++) {
			valueRoot[i] = oCo[i].getGiaTri();
		}
		ArrayList<Integer> moves = generateMoves(banCo, nguoiChoi);
		// nguoi choi 1 la max, 2 la min
		int bestScore = (nguoiChoi == 1) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
		int currentScore;
		int bestLocation = -1;
		int hanhDong = 0;
		// tra ve ham danh gia
		if (doSau == 0 || banCo.finish()) {
			if (banCo.finish()) {
				banCo.themDiemSo(1);
				banCo.themDiemSo(2);
			}
			bestScore = oCo[12].getGiaTri() - oCo[13].getGiaTri();
		} else {
			for (int i = 0; i < moves.size(); i++) {
				// thuc hien ham left or right
				for (int k = 1; k < 3; k++) {
					int score = 0;
					int location = banCo.hanhDong(k, moves.get(i));
					if (banCo.anDuocKhong(location)) {
						score = banCo.anQuan(k, location);
					}

					if (nguoiChoi == 1) { // max nguoiChoi
						banCo.setDiemNguoiChoi1(score);
						currentScore = minimax(banCo, doSau - 1, 2)[0];
						if (currentScore > bestScore) {
							bestScore = currentScore;
							bestLocation = moves.get(i);
							hanhDong = k;
						}
					} else { // min nguoiChoi
						banCo.setDiemNguoiChoi2(score);
						currentScore = minimax(banCo, doSau - 1, 1)[0];
						if (currentScore < bestScore) {
							bestScore = currentScore;
							bestLocation = moves.get(i);
							hanhDong = k;
						}
					}

					// khoi phuc trang thai ban co cu
					for (int j = 0; j < 14; j++) {
						if (j == 0 || j == 6) {
							oCo[j] = new OCo(j, valueRoot[j], true);
						} else {
							oCo[j] = new OCo(j, valueRoot[j], false);
						}
					}
				}
			}
		}
		return new int[] { bestScore, bestLocation, hanhDong };
	}

	// so buoc tiep theo co the co
	public ArrayList<Integer> generateMoves(BanCo banCo, int nguoiChoi) {
		OCo[] oCo = banCo.getOCo();
		ArrayList<Integer> result = new ArrayList<>();
		if (banCo.finish()) {
			return result;
		}
		if (nguoiChoi == 1) {
			for (int i = 1; i < 6; i++) {
				if (oCo[i].getGiaTri() != 0) {
					result.add(oCo[i].getViTri());
				}
			}
		} else {
			for (int i = 7; i < 12; i++) {
				if (oCo[i].getGiaTri() != 0) {
					result.add(oCo[i].getViTri());
				}
			}
		}
		return result;
	}

}
