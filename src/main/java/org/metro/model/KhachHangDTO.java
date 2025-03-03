package org.metro.model;

public class KhachHangDTO {
    private int id;
    private String ten;
    private String soDienThoai;
    private String routeID;

    public KhachHangDTO(int id, String ten, String soDienThoai, String routeID) {
        this.id = id;
        this.ten = ten;
        this.soDienThoai = soDienThoai;
        this.routeID = routeID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getRouteID() {
        return routeID;
    }

    public void setRouteID(String routeID) {
        this.routeID = routeID;
    }
}
