package org.metro.service;

import org.metro.DAO.TuyenDAO;
import org.metro.model.TuyenDuongModel;
import org.metro.service.TramService;

import java.util.List;

import static java.io.IO.print;

public class TuyenDuongService {
    private static TuyenDAO tuyenDAO = new TuyenDAO();
    // Không lưu trữ tramService dưới dạng static instance để tránh vấn đề cache

    public static List<TuyenDuongModel> getAll() {
        return tuyenDAO.selectAll();
    }

    public static boolean insert(TuyenDuongModel tuyenDuong) {
        return tuyenDAO.insert(tuyenDuong) > 0;
    }

    public static boolean update(TuyenDuongModel tuyenDuong) {
        return tuyenDAO.update(tuyenDuong) > 0;
    }

    public static boolean delete(int matuyen) {
        return tuyenDAO.delete(matuyen) > 0;
    }

    public static TuyenDuongModel getById(int matuyen) {
        return tuyenDAO.selectById(matuyen);
    }

    public static List<TuyenDuongModel> search(String inpStr, String type) {
        return tuyenDAO.search(inpStr, type);
    }
    
    // Nguyễn Thanh Hiền thêm ngày 24/04/2025
    public static String getHuongDiDisplay(int matuyen, boolean isHuongDi) {
        TramService tramService = new TramService();
        TuyenDuongModel tuyen = getById(matuyen);
        if (tuyen == null) {
            System.err.println("Không tìm thấy tuyến đường với mã: " + matuyen);
            return "Tuyến " + matuyen + " không xác định";
        }
        String tenTramDau = tramService.getTenTramById(tuyen.getTramdau());
        String tenTramCuoi = tramService.getTenTramById(tuyen.getTramdich());

        return isHuongDi ? (tenTramDau + " - " + tenTramCuoi) : (tenTramCuoi + " - " + tenTramDau);
    }
}