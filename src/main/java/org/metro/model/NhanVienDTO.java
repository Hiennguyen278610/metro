package org.metro.model;

public class NhanVienDTO {
    private int manv;
    private String tennv;
    private String sdtnv;

    public NhanVienDTO(int manv, String tennv, String sdtnv) {
        this.manv = manv;
        this.tennv = tennv;
        this.sdtnv = sdtnv;
    }

    public int getManv() { return manv; }

    public void setManv(int manv) { this.manv = manv; }

    public String getTennv() { return tennv; }

    public void setTennv(String tennv) { this.tennv = tennv; }

    public String getSdtnv() { return sdtnv; }

    public void setSdtnv(String sdtnv) { this.sdtnv = sdtnv; }
}
