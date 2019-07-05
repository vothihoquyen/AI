package model;

public class OCo {
	private int viTri;
	private int giaTri;
	private boolean isQuan;

	public OCo(int viTri, int giaTri, boolean isQuan) {
		this.viTri = viTri;
		this.giaTri = giaTri;
		this.isQuan = isQuan;
	}

	public int getGiaTri() {
		return giaTri;
	}

	public void setGiaTri(int giaTri) {
		this.giaTri = giaTri;
	}

	public boolean isQuan() {
		return isQuan;
	}

	public void setQuan(boolean isQuan) {
		this.isQuan = isQuan;
	}

	public int getViTri() {
		return viTri;
	}

	public void setViTri(int viTri) {
		this.viTri = viTri;
	}

	public String toString() {
		String quan = "";
		if (isQuan) {
			quan = " là dân";
		} else {
			quan = " là quan";
		}
		return "Vị trí: " + viTri + quan;
	}
}
