package org.metro.model;

public class NhanVienDTO {
    private int manv;
    private String tennv;
    private String sdtnv;
    private String gioitinh;
    private String chucvu;

    public NhanVienDTO(int manv, String tennv,String gioitinh, String sdtnv,String chucvu) {
        this.manv = manv;
        this.tennv = tennv;
        this.gioitinh = gioitinh;
        this.sdtnv = sdtnv;
        this.chucvu = chucvu;
    }

    public int getManv() { return manv; }

    public void setManv(int manv) { this.manv = manv; }

    public String getTennv() { return tennv; }

    public void setTennv(String tennv) { this.tennv = tennv; }

    public String getSdtnv() { return sdtnv; }

    public void setSdtnv(String sdtnv) { this.sdtnv = sdtnv; }

    public String getGioitinh() {
        return gioitinh;
    }
    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }
    public String getChucvu() {
        return chucvu;
    }
    public void setChucvu(String chucvu) {
        this.chucvu = chucvu;
    }
}
