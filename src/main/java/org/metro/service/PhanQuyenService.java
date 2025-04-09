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
    private static PhanQuyen pq;
    private static ChiTietQuyenDAO ctqDAO;
    private static NhomChucNangDAO ncnDAO;
    private static NhomQuyenDAO nqDAO;
    public PhanQuyenService() {
        this.ctqDAO = new ChiTietQuyenDAO();
        this.ncnDAO = new NhomChucNangDAO();
        this.nqDAO = new NhomQuyenDAO();
    }
    public static List<ChiTietPhanQuyenModel> getChiTietPhanQuyen(int manv){
           return ctqDAO.getQuyen(manv);
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
}
