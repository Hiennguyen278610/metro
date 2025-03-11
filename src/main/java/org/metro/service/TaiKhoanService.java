package org.metro.service;

import org.metro.DAO.TaiKhoanDAO;
import org.metro.model.TaiKhoanModal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaiKhoanService {
    private static ArrayList<TaiKhoanModal> allAccounts = new ArrayList<>();
    private static final TaiKhoanDAO taikhoanDAO = new TaiKhoanDAO();

    // Load dữ liệu từ DB
    public static void loadData() {
        allAccounts = new ArrayList<>(taikhoanDAO.getAll());
    }

    // Tìm kiếm tài khoản theo mã nhân viên (ID)
    public static ArrayList<TaiKhoanModal> search(String txt, String type) {
        loadData(); // luôn load dữ liệu mới nhất
        if (txt == null || txt.trim().isEmpty()) {
            return new ArrayList<>(allAccounts);
        }
        String lowerTxt = txt.toLowerCase();
        return allAccounts.stream()
                .filter(tk -> String.valueOf(tk.getManv()).contains(lowerTxt))
                .collect(Collectors.toCollection(ArrayList::new));
    }


    public static boolean insert(TaiKhoanModal tk) {
        if (taikhoanDAO.insert(tk) > 0) {
            loadData();
            return true;
        }
        return false;
    }

    public static boolean update(TaiKhoanModal tk) {
        if (taikhoanDAO.update(tk) > 0) {
            loadData();
            return true;
        }
        return false;
    }

    public static boolean delete(int manv) {
        if (taikhoanDAO.delete(manv) > 0) {
            loadData();
            return true;
        }
        return false;
    }

    public static TaiKhoanModal getByID(int manv) {
        return taikhoanDAO.selectById(manv);
    }
}
