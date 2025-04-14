package org.metro.model;

import java.time.LocalDateTime;

public class LichTrinhModel {
    private int machuyen;
    private int manv;
    private int matau;
    private int matuyen;
    private boolean huongdi;
    private LocalDateTime tgkhoihanh;
    private LocalDateTime tgdenthucte;
    private String trangthailichtrinh;

    public LichTrinhModel(int machuyen, int manv, int matau, int matuyen, boolean huongdi, LocalDateTime tgkhoihanh, LocalDateTime tgdenthucte, String trangthailichtrinh) {
        this.machuyen = machuyen;
        this.manv = manv;
        this.matau = matau;
        this.matuyen = matuyen;
        this.huongdi = huongdi;
        this.tgkhoihanh = tgkhoihanh;
        this.tgdenthucte = tgdenthucte;
        this.trangthailichtrinh = trangthailichtrinh;
    }

    public int getMachuyen() {return machuyen;}
    public int getManv() {return manv;}
    public int getMatau() {return matau;}
    public int getMatuyen() {return matuyen;}
    public boolean isHuongdi() {return huongdi;}
    public LocalDateTime getTgkhoihanh() {return tgkhoihanh;}
    public LocalDateTime getTgdenthucte() {return tgdenthucte;}
    public String getTrangthailichtrinh() {return trangthailichtrinh;}

    public void setMachuyen(int machuyen) {this.machuyen = machuyen;}
    public void setManv(int manv) {this.manv = manv;}
    public void setMatau(int matau) {this.matau = matau;}
    public void setMatuyen(int matuyen) {this.matuyen = matuyen;}
    public void setHuongdi(boolean huongdi) {this.huongdi = huongdi;}
    public void setTgkhoihanh(LocalDateTime tgkhoihanh) {this.tgkhoihanh = tgkhoihanh;}
    public void setTgdenthucte(LocalDateTime tgdenthucte) {this.tgdenthucte = tgdenthucte;}
    public void setTrangthailichtrinh(String trangthailichtrinh) {this.trangthailichtrinh = trangthailichtrinh;}

    public boolean getHuongdi() {return huongdi;}
    public LocalDateTime getThoigiankhoihanh() {return tgkhoihanh;}
    public LocalDateTime getThoigianthucte() {return tgdenthucte;}
    public String getTrangthai() {return trangthailichtrinh;}

    @Override
    public String toString() {
        return "Chuyến số " + machuyen + ": " + trangthailichtrinh;
    }
}