package org.metro.model;

public class VeTauModel {
    private int mave; // Mã vé
    private int machuyen; // Mã chuyến tàu
    private int makh; // Mã khách hàng
    private double giave; // Giá vé

    public VeTauModel(int mave, int machuyen, int makh, double giave) {
        this.mave = mave;
        this.machuyen = machuyen;
        this.makh = makh;
        this.giave = giave;
    }

    public int getMave() {return mave; }

    public int getMachuyen() {return machuyen; }

    public int getMakh() {return makh; }

    public double getGiave() {return giave; }

    public void setMave(int mave) {this.mave = mave; }

    public void setMachuyen(int machuyen) {this.machuyen = machuyen; }

    public void setMakh(int makh) {this.makh = makh; }

    public void setGiave(double giave) {this.giave = giave; }
}
