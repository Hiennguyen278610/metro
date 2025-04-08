package org.metro.service;

import org.metro.DAO.PhanQuyenDAO.ChiTietQuyenDAO;
import org.metro.DAO.PhanQuyenDAO.NhomChucNangDAO;
import org.metro.model.PhanQuyenModel.ChiTietPhanQuyenModel;
import org.metro.model.PhanQuyenModel.NhomChucNangModel;
import org.metro.model.TaiKhoanModel;

import java.util.List;


public class PhanQuyenService {
    private static ChiTietQuyenDAO ctqDAO;
    private static NhomChucNangDAO ncnDAO;
    public PhanQuyenService() {
        this.ctqDAO = new ChiTietQuyenDAO();
        this.ncnDAO = new NhomChucNangDAO();
    }
    public static List<ChiTietPhanQuyenModel> getChiTietPhanQuyen(int manv){
           return ctqDAO.getQuyen(manv);
    }
    public static List<NhomChucNangModel> getNhomChucNang(){
        return ncnDAO.selectAll();
    }
}
