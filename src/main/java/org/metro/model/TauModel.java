package org.metro.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TauModel {
    private String matau; // Mã tàu
    private int soghe; // Số ghế trên tàu
    private String trangthaitau; // Trạng thái (Đang vận hành, bảo trì)
    private LocalDate ngaynhap; // Ngày nhập tàu

    public TauModel(String matau, int soghe, String trangthaitau) {
        this.matau = matau;
        this.soghe = soghe;
        this.trangthaitau = trangthaitau;
    }

    public TauModel(String matau, int soghe, String trangthaitau, LocalDate ngaynhap) {
        this.matau = matau;
        this.soghe = soghe;
        this.trangthaitau = trangthaitau;
        this.ngaynhap = ngaynhap;
    }

    public String getNgaynhap() {
        DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return ngaynhap.format(FORMATTER);
    }

    public void setNgaynhap(LocalDate ngaynhap) {
        this.ngaynhap = ngaynhap;
    }

    public String getMatau() {
        return matau;
    }

    public int getSoghe() {
        return soghe;
    }

    public String getTrangthaitau() {
        return trangthaitau;
    }

    public void setMatau(String matau) {
        this.matau = matau;
    }

    public void setSoghe(int soghe) {
        this.soghe = soghe;
    }

    public void setTrangthaitau(String trangthaitau) {
        this.trangthaitau = trangthaitau;
    }

    @Override
    public String toString() {
        return "Tàu " + matau + ": " + trangthaitau;
    }
}
