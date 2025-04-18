package org.metro.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LichBaoTriModel {
    private int mabaotri;
    private int matau;
    private LocalDate ngaybaotri;
    private String trangthaibaotri;
    private LocalDateTime ngaytao;
    private double chiphibaotri;

    private DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public LichBaoTriModel(int mabaotri, int matau, LocalDate ngaybaotri, String trangthaibaotri,
            LocalDateTime ngaytao, double chiphibaotri) {
        this.mabaotri = mabaotri;
        this.matau = matau;
        this.ngaybaotri = ngaybaotri;
        this.trangthaibaotri = trangthaibaotri;
        this.ngaytao = ngaytao;
        this.chiphibaotri = chiphibaotri;
    }

    public LichBaoTriModel() {
    }

    public String convertLocalDate() {
        return this.ngaybaotri.format(formatTime);
    }

    public String convertLocalDateTime() {
        return this.ngaytao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
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

    public LocalDate getNgaybaotri() {
        return ngaybaotri;
    }

    public void setNgaybaotri(LocalDate ngaybaotri) {
        this.ngaybaotri = ngaybaotri;
    }

    public String getTrangthaibaotri() {
        return trangthaibaotri;
    }

    public void setTrangthaibaotri(String trangthaibaotri) {
        this.trangthaibaotri = trangthaibaotri;
    }

    public LocalDateTime getNgaytao() {
        return ngaytao;
    }

    public void setNgaytao(LocalDateTime ngaytao) {
        this.ngaytao = ngaytao;
    }

    public double getChiphibaotri() {
        return chiphibaotri;
    }

    public void setChiphibaotri(double chiphibaotri) {
        this.chiphibaotri = chiphibaotri;
    }
}
