package org.metro.model;

public class TaiKhoanDTO {
    private int manv;
    private String matkhau;
    private int manhomquyen;
    private int trangthai; // 1: Hoạt động, 0: Ngừng hoạt động

    public TaiKhoanDTO() {}

    public TaiKhoanDTO(int manv, String matkhau, int manhomquyen, int trangthai) {
        this.manv = manv;
        this.matkhau = matkhau;
        this.manhomquyen = manhomquyen;
        this.trangthai = trangthai;
    }

    public int getManv() {
        return manv;
    }

    public void setManv(int manv) {
        this.manv = manv;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public int getManhomquyen() {
        return manhomquyen;
    }

    public void setManhomquyen(int manhomquyen) {
        this.manhomquyen = manhomquyen;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

    @Override
    public String toString() {
        return "TaiKhoanDTO{" +
                "manv=" + manv +
                ", matkhau='" + matkhau + '\'' +
                ", manhomquyen=" + manhomquyen +
                ", trangthai=" + trangthai +
                '}';
    }

}
