package org.metro.model;

public class TuyenDuongModel {
    private int matuyen; // Mã tuyến
    private int tramdau; // Trạm đầu
    private int tramdich; // Trạm đích
    private double thoigiandichuyen; // Thời gian đi giữa 2 trạm
    private String trangthaituyen;

    public TuyenDuongModel(int matuyen, int tramdau, int tramdich, double thoigiandichuyen, String trangthaituyen) {
        this.matuyen = matuyen;
        this.tramdau = tramdau;
        this.tramdich = tramdich;
        this.thoigiandichuyen = thoigiandichuyen;
        this.trangthaituyen = trangthaituyen;
    }

    public int getMatuyen() { return matuyen; }

    public int getTramdau() { return tramdau; }

    public int getTramdich() { return tramdich; }

    public double getThoigiandichuyen() { return thoigiandichuyen; }

    public String getTrangthaituyen() { return trangthaituyen; }

    public void setMatuyen(int matuyen) { this.matuyen = matuyen; }

    public void setTramdau(int tramdau) { this.tramdau = tramdau; }

    public void setTramdich(int tramdich) { this.tramdich = tramdich; }

    public void setThoigiandichuyen(double thoigiandichuyen) { this.thoigiandichuyen = thoigiandichuyen; }

    public void setTrangthaituyen(String trangthaituyen) { this.trangthaituyen = trangthaituyen; }
}
