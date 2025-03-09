package org.metro.model;

public class TramDTO {
    private int matram; // Mã trạm
    private String tentram; // Tên trạm
    private String diachi; // Địa chỉ trạm

    public TramDTO(int matram, String tentram, String diachi) {
        this.matram = matram;
        this.tentram = tentram;
        this.diachi = diachi;
    }

    public int getMatram() { return matram; }

    public String getTentram() { return tentram; }

    public String getDiachi() { return diachi; }

    public void setMatram(int matram) { this.matram = matram; }

    public void setTentram(String tentram) { this.tentram = tentram; }

    public void setDiachi(String diachi) { this.diachi = diachi; }
}
