package org.metro.service;

import org.metro.DAO.PhanQuyenDAO.ChiTietQuyenDAO;
import org.metro.DAO.PhanQuyenDAO.NhomChucNangDAO;
import org.metro.DAO.PhanQuyenDAO.NhomQuyenDAO;
import org.metro.model.PhanQuyenModel.ChiTietPhanQuyenModel;
import org.metro.model.PhanQuyenModel.NhomChucNangModel;
import org.metro.model.PhanQuyenModel.NhomQuyenModel;
import org.metro.model.TaiKhoanModel;
import org.metro.view.Panel.PhanQuyenPackage.PhanQuyen;

import java.util.List;


public class PhanQuyenService {
    private static ChiTietQuyenDAO ctqDAO;
    private static NhomChucNangDAO ncnDAO;
    private static NhomQuyenDAO nqDAO;
    public PhanQuyenService() {
        this.ctqDAO = new ChiTietQuyenDAO();
        this.ncnDAO = new NhomChucNangDAO();
        this.nqDAO = new NhomQuyenDAO();
    }

    //lay danh sach nhom quyen
    public List<NhomQuyenModel> getAllNhomquyen() {
        return nqDAO.selectAll();
    }
    public static List<ChiTietPhanQuyenModel> getChiTietPhanQuyen(int manv){
           return ctqDAO.getQuyen(manv);
    }
    public static List<ChiTietPhanQuyenModel> getChiTietPhanQuyenByManhomQuyen(int manhomquyen){
        return ctqDAO.getQuyenByNhomquyen(manhomquyen);
    }
    public static List<NhomChucNangModel> getNhomChucNang(){
        return ncnDAO.selectAll();
    }

    public static int insertNhomQuyen(NhomQuyenModel nqm) {
        int manhomquyen = nqDAO.insert(nqm);
        if(manhomquyen > 0) return manhomquyen;
        return 0;
    }

    public static boolean insertChiTietPhanQuyen(List<ChiTietPhanQuyenModel> listctpqm) {
        if(listctpqm.isEmpty()) {
            System.out.println("danh sách chức năng quyền rỗng");
            return false;
        }
        System.out.println("kich thuoc: "+listctpqm.size());
        for(ChiTietPhanQuyenModel ctpqm : listctpqm) {
            int row = ctqDAO.insert(ctpqm);
            if(row <= 0) {
                System.out.println("errors");
                listctpqm.clear();
                return false;
            }
        }
        return true;
    }

    public static boolean updateNhomQuyen(NhomQuyenModel nqm) {
        if(nqDAO.update(nqm) > 0) return true;
        return false;
    }
    public static boolean deleteChiTietPhanQuyen(int id) {
        if(ctqDAO.delete(id) > 0) return true;
        return false;
    }
    public static boolean updateChiTietNhomQuyen(ChiTietPhanQuyenModel chiTietPhanQuyenModel) {
        if(ctqDAO.update(chiTietPhanQuyenModel) > 0) return true;
        return false;
    }

    public static boolean deleteNhomquyen(int manhomquyen) {
        if(nqDAO.delete(manhomquyen) > 0) {
            return true;
        }
        return false;
    }
}
