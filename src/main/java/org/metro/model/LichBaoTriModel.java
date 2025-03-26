package org.metro.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LichBaoTriModel {
    private int mabaotri;
    private int matau;
    private LocalDateTime ngaybaotri;
    private String trangthaibaotri;
    private DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    public LichBaoTriModel() {
    }

    public LichBaoTriModel(int mabaotri, int matau, LocalDateTime ngaybaotri, String trangthaibaotri) {
        this.mabaotri = mabaotri;
        this.matau = matau;
        this.ngaybaotri = ngaybaotri;
        this.trangthaibaotri = trangthaibaotri;
    }

    public String convertLocalDateTime() {
        return this.ngaybaotri.format(formatTime);
    }

    public int getMabaotri() {
        return mabaotri;
    }

    public void setMabaotri(int mabaotri) {
        this.mabaotri = mabaotri;
    }

    public int getMatau() {
        return matau;
    }

    public void setMatau(int matau) {
        this.matau = matau;
    }

    public LocalDateTime getNgaybaotri() {
        return ngaybaotri;
    }

    public void setNgaybaotri(LocalDateTime ngaybaotri) {
        this.ngaybaotri = ngaybaotri;
    }

    public String getTrangthaibaotri() {
        return trangthaibaotri;
    }

    public void setTrangthaibaotri(String trangthaibaotri) {
        this.trangthaibaotri = trangthaibaotri;
    }
}
