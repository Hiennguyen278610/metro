package org.metro.service;

import org.metro.DAO.KhachHangDAO;
import org.metro.model.KhachHangModel;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class KhachHangService {
    private static ArrayList<KhachHangModel> allCustomers = new ArrayList<>();
    private static final KhachHangDAO khachHangDAO = new KhachHangDAO();

    // Load dữ liệu từ DB
    public static void loadData() {
        allCustomers = new ArrayList<>(khachHangDAO.getAll());
    }

    // Tìm kiếm khách hàng theo từ khóa và loại tìm kiếm
    public static ArrayList<KhachHangModel> search(String txt, String type) {
        loadData(); // đảm bảo dữ liệu mới nhất
        if (txt == null || txt.trim().isEmpty()) {
            return new ArrayList<>(allCustomers);
        }
        String lowerTxt = txt.toLowerCase();
        return allCustomers.stream().filter(kh -> {
            switch (type) {
                case "Tất cả":
                    return String.valueOf(kh.getMaKh()).contains(lowerTxt)
                            || kh.getTenKh().toLowerCase().contains(lowerTxt)
                            || kh.getSdt().contains(lowerTxt)
                            || String.valueOf(kh.getSolan()).contains(lowerTxt);
                case "ID":
                    return String.valueOf(kh.getMaKh()).contains(lowerTxt);
                case "Tên khách hàng":
                    return kh.getTenKh().toLowerCase().contains(lowerTxt);
                case "Số điện thoại":
                    return kh.getSdt().contains(lowerTxt);
                case "Số lần đi":
                    return String.valueOf(kh.getSolan()).contains(lowerTxt);
                default:
                    return false;
            }
        }).collect(Collectors.toCollection(ArrayList::new));
    }

    public static boolean insert(KhachHangModel kh) {
        if (khachHangDAO.insert(kh) > 0) {
            loadData();
            return true;
        }
        return false;
    }

    public static boolean update(KhachHangModel kh) {
        if (khachHangDAO.update(kh) > 0) {
            loadData();
            return true;
        }
        return false;
    }

    public static boolean delete(int maKh) {
        if (khachHangDAO.delete(maKh) > 0) {
            loadData();
            return true;
        }
        return false;
    }

    public static KhachHangModel getById(int maKh) {
        return khachHangDAO.selectById(maKh);
    }
}
