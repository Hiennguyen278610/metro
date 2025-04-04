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
    public static List<NhanVienModel> searchByKeyWord(String key,String word) {
        getListnv();
        List<NhanVienModel> lst = new ArrayList<>();
        word = word.toLowerCase();
        switch (key) {
            case "----":
                for (NhanVienModel nvm : listnv) {
                    if (String.valueOf(nvm.getManv()).toLowerCase().contains(word) ||
                            String.valueOf(nvm.getTennv()).toLowerCase().contains(word) ||
                            nvm.getGioitinh().toLowerCase().contains(word) ||
                            nvm.getChucvu().toLowerCase().contains(word)) {
                        lst.add(nvm);
                    }
                }
                break;
            case "mã":
                for (NhanVienModel nvm : listnv) {
                    if (String.valueOf(nvm.getManv()).toLowerCase().contains(word)) {
                        lst.add(nvm);
                    }
                }
                break;
            case "tên":
                for (NhanVienModel nvm : listnv) {
                    if (String.valueOf(nvm.getTennv()).toLowerCase().contains(word)) {
                        lst.add(nvm);
                    }
                }
                break;
            case "số điện thoại":
                for (NhanVienModel nvm : listnv) {
                    if (nvm.getSdtnv().toLowerCase().contains(word)) {
                        lst.add(nvm);
                    }
                }
                break;
            case "giới tính":
                for (NhanVienModel nvm : listnv) {
                    if (nvm.getGioitinh().toLowerCase().contains(word)) {
                        lst.add(nvm);
                    }
                }
                break;
            case "chức vụ":
                for (NhanVienModel nvm : listnv) {
                    if (nvm.getChucvu().toLowerCase().contains(word)) {
                        lst.add(nvm);
                    }
                }
            default:
                System.out.println("Khong the tim kiem");
                break;
        }
        System.out.println("danh sach sau tim kiem: " + lst.size());
        return lst;
    }
    public static NhanVienModel getById(int maKh) {
        return nvd.selectById(maKh);
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


}
