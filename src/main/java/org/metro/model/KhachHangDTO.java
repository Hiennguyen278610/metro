package org.metro.model;

import java.time.LocalDateTime;

public class KhachHangDTO {
    private int maKh;           // Tương ứng với `makh`
    private String tenKh;       // Tương ứng với `tenkh`
    private String sdt;         // Tương ứng với `sdt`
    private String maTuyen;     // Tương ứng với `matuyen`
    private LocalDateTime ngayThamGia;  // Tương ứng với `ngaythamgia`

    // Constructor đầy đủ
    public KhachHangDTO(int maKh, String tenKh, String sdt, String maTuyen, LocalDateTime ngayThamGia) {
        this.maKh = maKh;
        this.tenKh = tenKh;
        this.sdt = sdt;
        this.maTuyen = maTuyen;
        this.ngayThamGia = ngayThamGia;
    }

    // Getter và Setter
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

    public String getMaTuyen() {
        return maTuyen;
    }

    public void setMaTuyen(String maTuyen) {
        this.maTuyen = maTuyen;
    }

    public LocalDateTime getNgayThamGia() {
        return ngayThamGia;
    }

    public void setNgayThamGia(LocalDateTime ngayThamGia) {
        this.ngayThamGia = ngayThamGia;
    }
}