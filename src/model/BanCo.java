package model;

import java.util.ArrayList;

import controller.Result;

public class BanCo {
	private OCo[] oCo;
	private ArrayList<Result> results;

	public BanCo() {
		results = new ArrayList<>();
		oCo = new OCo[14];
		for (int i = 0; i < 12; i++) {
			if (i % 6 != 0) {
				oCo[i] = new OCo(i, 5, false);
			} else if (i == 0) {
				oCo[i] = new OCo(i, 10, true);
			} else {
				oCo[i] = new OCo(i, 10, true);
			}
		}
		oCo[12] = new OCo(12, 0, false);
		oCo[13] = new OCo(13, 0, false);
	}

	public int hanhDong(int hanhDong, int viTri) {
		if (hanhDong == 1) {
			return quaTrai(viTri);
		} else {
			return quaPhai(viTri);
		}
	}

	public int anQuan(int hanhDong, int viTri) {
		if (hanhDong == 1) {
			return anBenTrai(viTri);
		} else {
			return anBenPhai(viTri);
		}
	}
	
	public OCo[] adapter(OCo[] oCo) {
		OCo[] s = new OCo[oCo.length];
		for (int i = 0; i < s.length; i++) {
			s[i] = new OCo(i, oCo[i].getGiaTri(), oCo[i].isQuan());
		}
		return s;
	}

	// tráº£ vá»� vá»‹ trĂ­ sau khi Ä‘i qua trĂ¡i
	public int quaTrai(int viTri) {
		int giaTri = oCo[viTri].getGiaTri();
		oCo[viTri].setGiaTri(0);
		Result r = new Result(adapter(oCo));
		results.add(r);
		while (giaTri != 0) {
			viTri++;
			if (viTri == 12) {
				viTri = 0;
			}
			oCo[viTri].setGiaTri(oCo[viTri].getGiaTri() + 1);
			giaTri--;
			Result result = new Result(adapter(oCo));
			results.add(result);
		}
		viTri++;
		if (viTri == 12) {
			viTri = 0;
		}
		if (oCo[viTri].getGiaTri() != 0 && viTri % 6 != 0) {
			return quaTrai(viTri);
		} else {
			return viTri--;
		}
	}

	// vi tri Ä‘Ă³ cĂ³ Äƒn Ä‘Æ°á»£c hay ko
	public boolean anDuocKhong(int viTri) {
		if (oCo[viTri].getGiaTri() == 0 && (viTri != 0 || viTri != 6)) {
			return true;
		}
		return false;
	}

	// tráº£ vá»� vá»‹ trĂ­ sau khi Ä‘i qua pháº£i
	public int quaPhai(int viTri) {
		int giaTri = oCo[viTri].getGiaTri();
		oCo[viTri].setGiaTri(0);
		Result r = new Result(adapter(oCo));
		results.add(r);
		while (giaTri != 0) {
			viTri--;
			if (viTri == -1) {
				viTri = 11;
			}
			oCo[viTri].setGiaTri(oCo[viTri].getGiaTri() + 1);
			giaTri--;
			Result result = new Result(adapter(oCo));
			results.add(result);
		}
		viTri--;
		if (viTri == -1) {
			viTri = 11;
		}
		if (oCo[viTri].getGiaTri() != 0 && viTri % 6 != 0) {
			return quaPhai(viTri);
		} else {
			return viTri--;
		}
	}

	public int anBenTrai(int viTri) {
		if (oCo[viTri].getGiaTri() == 0 && viTri % 6 != 0) {
			viTri++;
			if (viTri == 12) {
				viTri = 0;
			}
			if (oCo[viTri].getGiaTri() != 0) {
				int diemSo = oCo[viTri].getGiaTri();
				oCo[viTri].setGiaTri(0);
				Result result = new Result(adapter(oCo));
				results.add(result);
				viTri++;
				if (viTri == 12) {
					viTri = 0;
				}
				if (oCo[viTri].getGiaTri() == 0 && viTri % 6 != 0) {
					return diemSo + anBenTrai(viTri);
				} else {
					return diemSo;
				}
			}
		}
		return 0;
	}

	public int anBenPhai(int viTri) {
		if (oCo[viTri].getGiaTri() == 0 && viTri % 6 != 0) {
			viTri--;
			if (viTri == -1) {
				viTri = 11;
			}
			if (oCo[viTri].getGiaTri() != 0) {
				int diemSo = oCo[viTri].getGiaTri();
				oCo[viTri].setGiaTri(0);
				Result result = new Result(adapter(oCo));
				results.add(result);
				viTri--;
				if (viTri == -1) {
					viTri = 11;
				}
				if (oCo[viTri].getGiaTri() == 0 && viTri % 6 != 0) {
					return diemSo + anBenPhai(viTri);
				} else {
					return diemSo;
				}
			}
		}
		return 0;
	}

	public boolean finish() {
		if (oCo[0].getGiaTri() == 0 && oCo[6].getGiaTri() == 0) {
			return true;
		}
		if (oCo[12].getGiaTri() == 0) {
			if (kiemTraHetQuan(1)) {
				return true;

			}
		}
		if (oCo[13].getGiaTri() == 0) {
			if (kiemTraHetQuan(2)) {
				return true;
			}
		}
		return false;
	}

	public boolean kiemTra(int viTri, int player) {
		if (player == 1) {
			if (viTri != 1 && viTri != 2 && viTri != 3 && viTri != 4 && viTri != 5) {
				return false;
			}
			if (oCo[viTri].getGiaTri() == 0 || viTri % 6 == 0) {
				return false;
			}
		} else {
			if (viTri == 1 || viTri == 2 || viTri == 3 || viTri == 4 || viTri == 5) {
				return false;
			}
			if (oCo[viTri].getGiaTri() == 0 || viTri % 6 == 0) {
				return false;
			}
		}
		return true;
	}

	public int themDiemSo(int player) {
		int diemSo = 0;
		if (player == 1) {
			for (int i = 1; i < 6; i++) {
				diemSo += oCo[i].getGiaTri();
				oCo[i].setGiaTri(0);
			}
			setDiemNguoiChoi1(diemSo);
		} else {
			for (int i = 7; i < 12; i++) {
				diemSo += oCo[i].getGiaTri();
				oCo[i].setGiaTri(0);
			}
			setDiemNguoiChoi2(diemSo);
		}
		return diemSo;
	}

	public boolean kiemTraHetQuan(int player) {
		if (player == 1) {
			int diemSo = 0;
			for (int i = 1; i < 6; i++) {
				diemSo += oCo[i].getGiaTri();
			}
			if (diemSo == 0) {
				return true;
			}
		} else {
			int diemSo = 0;
			for (int i = 7; i < 12; i++) {
				diemSo += oCo[i].getGiaTri();
			}
			if (diemSo == 0) {
				return true;
			}
		}
		return false;
	}

	public int raiQuan(int player) {
		if (player == 1) {
			int diemSo = oCo[12].getGiaTri();
			if (diemSo <= 5) {
				for (int i = 1; i <= diemSo; i++) {
					oCo[i].setGiaTri(1);
				}
				oCo[12].setGiaTri(0);
				Result r = new Result(adapter(oCo));
				results.add(r);
				return diemSo;
			} else {
				for (int i = 1; i < 6; i++) {
					oCo[i].setGiaTri(1);
				}
				oCo[12].setGiaTri(diemSo-5);
				Result r = new Result(adapter(oCo));
				results.add(r);
				return 5;
			}
		} else {
			int diemSo = oCo[13].getGiaTri();
			if (diemSo <= 5) {
				for (int i = 7; i < 7 + diemSo; i++) {
					oCo[i].setGiaTri(1);
				}
				oCo[13].setGiaTri(0);
				Result r = new Result(adapter(oCo));
				results.add(r);
				return diemSo;
			} else {
				for (int i = 7; i < 12; i++) {
					oCo[i].setGiaTri(1);
				}
				oCo[13].setGiaTri(diemSo-5);
				Result r = new Result(adapter(oCo));
				results.add(r);
				return 5;
			}
		}
	}

	public void setDiemNguoiChoi1(int diemSo) {
		int a = oCo[12].getGiaTri();
		oCo[12].setGiaTri(diemSo + a);
		Result result = new Result(oCo);
		results.add(result);
	}

	public void setDiemNguoiChoi2(int diemSo) {
		int a = oCo[13].getGiaTri();
		oCo[13].setGiaTri(diemSo+a);
		Result result = new Result(oCo);
		results.add(result);
	}

	public void printBanCo() {
		System.out.print("  |");
		for (int i = 1; i < 6; i++) {
			System.out.print(oCo[i].getGiaTri() + "|");
		}
		System.out.println();
		System.out.print(oCo[0].getGiaTri() + "|-|-|-|-|-|");
		System.out.print(oCo[6].getGiaTri());
		System.out.println();
		System.out.print("  |");
		for (int i = 12 - 1; i >= 7; i--) {
			System.out.print(oCo[i].getGiaTri() + "|");
		}
		System.out.println();
		System.out.println();
		System.out.println();
	}

	public OCo[] getOCo() {
		return oCo;
	}

	public void setOCo(OCo[] oCo) {
		this.oCo = oCo;
	}

	public ArrayList<Result> getResults() {
		return results;
	}

	public void setResults(ArrayList<Result> results) {
		this.results = results;
	}

}
