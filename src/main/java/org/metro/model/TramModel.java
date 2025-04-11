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

    public TramModel(String tentram, int x, int y) {
        this.tentram = tentram;
        this.x = x;
        this.y = y;
    }

    public TramModel(int matram, String tentram, int x, int y) {
        this.matram = matram;
        this.tentram = tentram;
        this.x = x;
        this.y = y;
    }

    public int getMatram() {
        return matram;
    }

    public void setMatram(int matram) {
        this.matram = matram;
    }

    public String getTentram() {
        return tentram;
    }

    public void setTentram(String tentram) {
        this.tentram = tentram;
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

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    @Override
    public String toString() {
        return "Trạm " + this.getMatram() + " - " + this.getTentram();
    }
}
