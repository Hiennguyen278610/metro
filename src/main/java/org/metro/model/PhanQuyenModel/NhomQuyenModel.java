package org.metro.model.PhanQuyenModel;

public class NhomQuyenModel {
    private int manhomquyen;
    private String tennhomquyen;

    public NhomQuyenModel() {}

    public NhomQuyenModel(int manhomquyen, String tennhomquyen) {
        this.manhomquyen = manhomquyen;
        this.tennhomquyen = tennhomquyen;
    }

    //constructor cho manhomquyen tu dung tang
    public NhomQuyenModel(String tennhomquyen) {
        this.tennhomquyen = tennhomquyen;
    }

    public int getManhomquyen() { return manhomquyen; }

    public String getTennhomquyen() { return tennhomquyen; }

    public void setManhomquyen(int manhomquyen) { this.manhomquyen = manhomquyen; }

    public void setTennhomquyen(String tennhomquyen) { this.tennhomquyen = tennhomquyen; }
}
