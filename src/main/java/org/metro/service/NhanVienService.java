package org.metro.service;

import org.metro.DAO.NhanVienDAO;
import org.metro.model.KhachHangModel;
import org.metro.model.LichBaoTriModel;
import org.metro.model.NhanVienModel;
import org.metro.view.Panel.NhanVien;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class NhanVienService {
    private static NhanVien nv;
    private static NhanVienDAO nvd =  new NhanVienDAO();
    private static List<NhanVienModel> listnv = new ArrayList<>();
    //goi cac ham trong dao
    public NhanVienService(NhanVien nv) {
        this.nv = nv;
    }

    public static List<NhanVienModel> getListnv() {
        listnv = nvd.selectAll();
        return listnv;
    }
    public static boolean insert(NhanVienModel nvm) {
        if(nvd.insert(nvm) > 0) {
           if(nv != null) nv.reloadData();
           return true;
        }
        return false;
    }

    public static boolean update(NhanVienModel nvm) {
        if(nvd != null && nvd.update(nvm) > 0) {
            if(nv != null) nv.reloadData();
            return true;
        }
        return false;
    }

    public static boolean delete(int maNV) {
       if(nvd != null && nvd.delete(maNV) > 0) {
           if(nv != null) nv.reloadData();
           return true;
       }
       return false;
    }

    public static List<NhanVienModel> searchByKeyWord(String key,String word) {
        getListnv();
        List<NhanVienModel> lst = new ArrayList<>();
        String lowWord = word.toLowerCase();
        for(NhanVienModel nvm : listnv) {
            boolean check = false;
            switch (key) {
                case "manv":
                    if(String.valueOf(nvm.getManv()).contains(lowWord)) {
                        check = true;
                    }
                    break;
                case "tennv":
                    if(String.valueOf(nvm.getTennv()).contains(lowWord)) {
                        check = true;
                    }
                    break;
                case "sodienthoai":
                    if(String.valueOf(nvm.getSdtnv()).contains(lowWord)) {
                        check = true;
                    }
                    break;
                case "gioitinh":
                    if(String.valueOf(nvm.getGioitinh()).contains(lowWord)) {
                        check = true;
                    }
                    break;
                case "chucvu":
                    if(String.valueOf(nvm.getChucvu()).contains(lowWord)) {
                        check = true;
                    }
                    break;
                case "----":
                    check = true;
                    break;
                default:
                    break;
            }
            if(check) {
                lst.add(nvm);
            }
        }
        if (lst.isEmpty()) {
            JOptionPane.showMessageDialog(null,"Không tìm thấy nhân viên","thông báo",JOptionPane.ERROR_MESSAGE);
        }
        return lst;
    }
    public static NhanVienModel getById(int maKh) {
        return nvd.selectById(maKh);
    }
}
