package org.metro.service;

import org.metro.DAO.NhanVienDAO;
import org.metro.model.NhanVienModel;
import org.metro.view.Panel.NhanVien;

public class NhanVienService {
    private static NhanVien nv;
    private static NhanVienDAO nvd =  new NhanVienDAO();
    //goi cac ham trong dao
    public static boolean insert(NhanVienModel nvm) {
        if(nvd.insert(nvm) > 0) {
           if(nv != null) nv.reloadData();
           return true;
        }
        return false;
    }

    public static boolean update(NhanVienModel nvm) {
        return false;
    }

    public static boolean delete(int maNV) {
        System.out.println("Gọi phương thức delete() trong NhanVienService với maNV = " + maNV);
       if(nvd != null && nvd.delete(maNV) > 0) {
           if(nv != null) nv.reloadData();
           return true;
       }
       return false;
    }
}
