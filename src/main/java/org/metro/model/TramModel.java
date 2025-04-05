package org.metro.model;

public class TramModel {
    private int matram; // Mã trạm
    private String tentram; // Tên trạm
    private String diachi; // Địa chỉ trạm
    private int x;
    private int y;

    public TramModel(int matram, String tentram, String diachi, int x, int y) {
        this.matram = matram;
        this.tentram = tentram;
        this.diachi = diachi;
        this.x = x;
        this.y = y;
    }

    public TramModel(int matram, String tentram, String diachi) {
        this.matram = matram;
        this.tentram = tentram;
        this.diachi = diachi;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getMatram() {
        return matram;
    }

    public String getTentram() {
        return tentram;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setMatram(int matram) {
        this.matram = matram;
    }

    public void setTentram(String tentram) {
        this.tentram = tentram;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }
}
