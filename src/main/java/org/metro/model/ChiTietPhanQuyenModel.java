package org.metro.model;

public class ChiTietPhanQuyenModel {
    private int manhomquyen;
    private int machucnang;
    private String tenquyen;

    public ChiTietPhanQuyenModel(int manhomquyen, int machucnang, String tenquyen) {
        this.manhomquyen = manhomquyen;
        this.machucnang = machucnang;
        this.tenquyen = tenquyen;
    }

    public int getManhomquyen() { return manhomquyen; }

    public int getMachucnang() { return machucnang; }

    public String getTenquyen() { return tenquyen; }

    public void setManhomquyen(int manhomquyen) { this.manhomquyen = manhomquyen; }

    public void setMachucnang(int machucnang) { this.machucnang = machucnang; }

    public void setTenquyen(String tenquyen) { this.tenquyen = tenquyen; }
}
