package org.metro.model;

public class KhachHangModal {
    private int maKh;
    private String tenKh;
    private String sdt;
    private int solan;

    public KhachHangModal() {}

    public KhachHangModal(int maKh, String tenKh, String sdt, int solan) {
        this.maKh = maKh;
        this.tenKh = tenKh;
        this.sdt = sdt;
        this.solan = solan;
    }

    public int getMaKh() {
        return maKh;
    }

    public void setMaKh(int maKh) {
        this.maKh = maKh;
    }

    public String getTenKh() {
        return tenKh;
    }

    public void setTenKh(String tenKh) {
        this.tenKh = tenKh;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public int getSolan() {
        return solan;
    }

    public void setSolan(int solan) {
        this.solan = solan;
    }

    @Override
    public String toString() {
        return "KhachHangDTO{" +
                "maKh=" + maKh +
                ", tenKh='" + tenKh + '\'' +
                ", sdt='" + sdt + '\'' +
                ", solan=" + solan +
                '}';
    }
}
