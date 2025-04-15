package org.metro.service;

import org.metro.DAO.TaiKhoanDAO;
import org.metro.model.TaiKhoanModel;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaiKhoanService {
    private static ArrayList<TaiKhoanModel> allAccounts = new ArrayList<>();
    private static final TaiKhoanDAO taikhoanDAO = new TaiKhoanDAO();

    // Load dữ liệu từ DB
    public static List<TaiKhoanModel> loadData() {
        return taikhoanDAO.selectAll();
    }

    // Tìm kiếm tài khoản theo mã nhân viên (ID)
    public static ArrayList<TaiKhoanModel> search(String txt, String type) {
        loadData(); // luôn load dữ liệu mới nhất
        if (txt == null || txt.trim().isEmpty()) {
            return new ArrayList<>(allAccounts);
        }
        String lowerTxt = txt.toLowerCase();
        return allAccounts.stream()
                .filter(tk -> String.valueOf(tk.getManv()).contains(lowerTxt))
                .collect(Collectors.toCollection(ArrayList::new));
    }


    public static boolean insert(TaiKhoanModel tk) {
        if (taikhoanDAO.insert(tk) > 0) {
            loadData();
            return true;
        }
        return false;
    }

    public static boolean update(TaiKhoanModel tk) {
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

    public static TaiKhoanModel getByID(int manv) {
        return taikhoanDAO.selectById(manv);
    }
}
