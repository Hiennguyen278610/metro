package org.metro.model;

public class VeTauModal {
    private int mave; // Mã vé
    private int machuyen; // Mã chuyến tàu
    private int makh; // Mã khách hàng
    private int manv; // Mã nhân viên
    private double price; // Giá vé

    public VeTauModal(int mave, int machuyen, int makh, int manv, double price) {
        this.mave = mave;
        this.machuyen = machuyen;
        this.makh = makh;
        this.manv = manv;
        this.price = price;
    }

    public int getMave() {return mave; }

    public int getMachuyen() {return machuyen; }

    public int getMakh() {return makh; }

    public int getManv() {return manv; }

    public double getPrice() {return price; }

    public void setMave(int mave) {this.mave = mave; }

    public void setMachuyen(int machuyen) {this.machuyen = machuyen; }

    public void setMakh(int makh) {this.makh = makh; }

    public void setManv(int manv) {this.manv = manv; }

    public void setPrice(double price) {this.price = price; }
}
