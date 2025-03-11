package org.metro.model;

public class TauModel {
    private String matau; // Mã tàu
    private int soghe; // Số ghế trên tàu
    private String trangthaitau; // Trạng thái (Đang vận hành, bảo trì)

    public TauModel(String matau, int soghe, String trangthaitau) {
        this.matau = matau;
        this.soghe = soghe;
        this.trangthaitau = trangthaitau;
    }

    public String getMatau() { return matau; }

    public int getSoghe() { return soghe; }

    public String getTrangthaitau() { return trangthaitau; }

    public void setMatau(String matau) { this.matau = matau; }

    public void setSoghe(int soghe) { this.soghe = soghe; }

    public void setTrangthaitau(String trangthaitau) { this.trangthaitau = trangthaitau; }
}
