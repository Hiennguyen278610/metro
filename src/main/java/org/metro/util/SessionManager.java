package org.metro.util;

import org.metro.model.PhanQuyenModel.ChiTietPhanQuyenModel;
import org.metro.model.TaiKhoanModel;
import org.metro.service.PhanQuyenService;
import org.metro.service.TaiKhoanService;

import java.util.List;

public class SessionManager {
    private static TaiKhoanModel currentUser;
    //tai khoan hien tai ung voi 1 list chi tiet quyen
    private static List<ChiTietPhanQuyenModel> listQuyenCurrentUser;
    private static PhanQuyenService pqs = new PhanQuyenService();
    public static void setCurrentUser(TaiKhoanModel user) {
        currentUser = user;
        if(user != null) {
            listQuyenCurrentUser = PhanQuyenService.getChiTietPhanQuyen(user.getManv());
            System.out.println(user.getManv() + " " + listQuyenCurrentUser.size());
        } else listQuyenCurrentUser = null;
    }

    //check quyen
    public static boolean checkQuyenCurrentUser(int machucnang,String hanhdong) {
        if(listQuyenCurrentUser == null || currentUser == null) return false;

        for(ChiTietPhanQuyenModel ctpqm : listQuyenCurrentUser) {
            if(ctpqm.getMachucnang() == machucnang && ctpqm.getTenquyen().equalsIgnoreCase(hanhdong)) {
                return true;
            }
        }
        return false;
    }

    //check xem chuc nang co it nhat 1 quyen khong de hien thi ra
    public static boolean checkAnyQuyenCurrentUser(int machucnang) {
        if(listQuyenCurrentUser == null || currentUser == null) return false;
        for(ChiTietPhanQuyenModel ctpqm : listQuyenCurrentUser) {
            if(ctpqm.getMachucnang() == machucnang) return true;
        }
        return false;
    }

    public static TaiKhoanModel getCurrentUser() {
        return currentUser;
    }
    public static void clearSession() {
        currentUser = null;
        listQuyenCurrentUser = null;
    }

    //reload lại mỗi khi sửa trong phân quyền
    public static void reloadQuyen() {
        if(currentUser != null) {
            int manhomquyen = TaiKhoanService.getByID(currentUser.getManv()).getNqm().getManhomquyen();
            listQuyenCurrentUser = pqs.getChiTietPhanQuyen(manhomquyen);
        }
    }
}
