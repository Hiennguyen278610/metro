package org.metro.model;

import org.metro.model.PhanQuyenModel.NhomQuyenModel;

public class TaiKhoanModel {
    private int manv;
    private String matkhau;
    private int trangthai; // 1: Hoạt động, 0: Ngừng hoạt động
    private NhomQuyenModel nqm;

    public TaiKhoanModel() {}

    public TaiKhoanModel(int manv, String matkhau, int trangthai,NhomQuyenModel nqm) {
        this.manv = manv;
        this.matkhau = matkhau;
        this.trangthai = trangthai;
        this.nqm = nqm;
    }

    public TaiKhoanModel(int manv,int trangthai,NhomQuyenModel nqm) {
        this.manv = manv;
        this.trangthai = trangthai;
        this.nqm = nqm;
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

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

    public NhomQuyenModel getNqm() {
        return nqm;
    }

    public void setNqm(NhomQuyenModel nqm) {
        this.nqm = nqm;
    }

    public boolean isTrangThai() {
        return trangthai == 1;
    }
}
