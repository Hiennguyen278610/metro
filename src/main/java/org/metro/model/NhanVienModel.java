package org.metro.model;

public class NhanVienModel {
    private int manv;
    private String tennv;
    private String sdtnv;
    private String gioitinh;
    private String chucvu;

    public NhanVienModel() {}

    public NhanVienModel(String tennv,String sdtnv,String gioitinh,String chucvu) {
        this.tennv = tennv;
        this.sdtnv = sdtnv;
        this.gioitinh = gioitinh;
        this.chucvu = chucvu;
    }

    public NhanVienModel(int manv, String tennv, String sdtnv, String gioitinh, String chucvu) {
        this.manv = manv;
        this.tennv = tennv;
        this.gioitinh = gioitinh;
        this.sdtnv = sdtnv;
        this.chucvu = chucvu;
    }

    public int getManv() { return manv; }

    public void setManv(int manv) { this.manv = manv; }

    public String getTennv() { return tennv; }

    public void setTennv(String tennv) { this.tennv = tennv;}

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

    @Override
    public String toString() {
        return this.tennv; // Hiển thị tên nhân viên trong combobox
    }
}
