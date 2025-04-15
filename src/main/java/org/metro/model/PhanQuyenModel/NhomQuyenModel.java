package org.metro.model.PhanQuyenModel;

import java.util.Objects;

public class NhomQuyenModel {
    private int manhomquyen;
    private String tennhomquyen;

    public NhomQuyenModel() {}

    public NhomQuyenModel(int manhomquyen, String tennhomquyen) {
        this.manhomquyen = manhomquyen;
        this.tennhomquyen = tennhomquyen;
    }

    public NhomQuyenModel(int manhomquyen) {this.manhomquyen = manhomquyen;}

    //constructor cho manhomquyen tu dung tang
    public NhomQuyenModel(String tennhomquyen) {
        this.tennhomquyen = tennhomquyen;
    }

    public int getManhomquyen() { return manhomquyen; }

    public String getTennhomquyen() { return tennhomquyen; }

    public void setManhomquyen(int manhomquyen) { this.manhomquyen = manhomquyen; }

    public void setTennhomquyen(String tennhomquyen) { this.tennhomquyen = tennhomquyen; }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        NhomQuyenModel that = (NhomQuyenModel) o;
        return manhomquyen == that.manhomquyen && Objects.equals(tennhomquyen, that.tennhomquyen);
    }

    @Override
    public int hashCode() {
        return Objects.hash(manhomquyen, tennhomquyen);
    }

    @Override
    public String toString() {
       return tennhomquyen;
    }
}
