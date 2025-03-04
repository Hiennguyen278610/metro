package org.metro.model;

public class TaiKhoanDTO {
    private int manv;
    private int maquyen;
    private String matkhau;
    private String trangthaitaikhoan;

    public TaiKhoanDTO(int manv, int maquyen, String matkhau, String trangthaitaikhoan) {
        this.manv = manv;
        this.maquyen = maquyen;
        this.matkhau = matkhau;
        this.trangthaitaikhoan = trangthaitaikhoan;
    }

    public int getManv() { return manv; }

    public int getMaquyen() { return maquyen; }

    public String getMatkhau() { return matkhau; }

    public String getTrangthaitaikhoan() { return trangthaitaikhoan; }

    public void setManv(int manv) { this.manv = manv; }

    public void setMaquyen(int maquyen) { this.maquyen = maquyen; }

    public void setMatkhau(String matkhau) { this.matkhau = matkhau; }

    public void setTrangthaitaikhoan(String trangthaitaikhoan) { this.trangthaitaikhoan = trangthaitaikhoan; }
}
