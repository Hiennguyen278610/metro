package org.metro.model;

public class NhomQuyenDTO {
    private int manhomquyen;
    private String tennhomquyen;

    public NhomQuyenDTO(int manhomquyen, String tennhomquyen) {
        this.manhomquyen = manhomquyen;
        this.tennhomquyen = tennhomquyen;
    }

    public int getManhomquyen() { return manhomquyen; }

    public String getTennhomquyen() { return tennhomquyen; }

    public void setManhomquyen(int manhomquyen) { this.manhomquyen = manhomquyen; }

    public void setTennhomquyen(String tennhomquyen) { this.tennhomquyen = tennhomquyen; }
}
