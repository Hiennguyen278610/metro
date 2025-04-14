package org.metro.service;

import org.metro.DAO.LichTrinhDAO;
import org.metro.model.LichTrinhModel;

import java.util.List;
import java.util.ArrayList;

public class LichTrinhService {
    private static LichTrinhDAO lichtrinhDAO = new LichTrinhDAO();

    public static List<LichTrinhModel> getAll() {return lichtrinhDAO.getAll();}

    public static boolean insert(LichTrinhModel lichtrinh) {return lichtrinhDAO.insert(lichtrinh) > 0;}

    public static boolean update(LichTrinhModel lichtrinh) {return lichtrinhDAO.update(lichtrinh) > 0;}

    public static boolean delete(int machuyen) {return lichtrinhDAO.delete(machuyen) > 0;}

    public static LichTrinhModel getById(int machuyen) {return lichtrinhDAO.selectById(machuyen);}

    public static List<LichTrinhModel> search(String inpStr, String type) {
        List<LichTrinhModel> allLichTrinh = lichtrinhDAO.getAll();
        List<LichTrinhModel> result = new ArrayList<>();

        if (inpStr == null || inpStr.trim().isEmpty()) {return allLichTrinh;}
        String lowerInpStr = inpStr.toLowerCase();

        for (LichTrinhModel lichtrinh : allLichTrinh) {
            switch(type) {
                case "Tất cả":
                    if (String.valueOf(lichtrinh.getMachuyen()).toLowerCase().contains(lowerInpStr) ||
                            String.valueOf(lichtrinh.getManv()).toLowerCase().contains(lowerInpStr) ||
                            String.valueOf(lichtrinh.getMatau()).toLowerCase().contains(lowerInpStr) ||
                            String.valueOf(lichtrinh.getMatuyen()).toLowerCase().contains(lowerInpStr) ||
                            (lichtrinh.getHuongdi() ? "Đi" : "Về").toLowerCase().contains(lowerInpStr) ||
                            lichtrinh.getTrangthai().toLowerCase().contains(lowerInpStr) ||
                            lichtrinh.getThoigiankhoihanh().toString().toLowerCase().contains(lowerInpStr)) {
                        result.add(lichtrinh);
                    }
                    break;
                case "Mã chuyến":
                    if (String.valueOf(lichtrinh.getMachuyen()).toLowerCase().contains(lowerInpStr)) {result.add(lichtrinh);}
                    break;
                case "Mã nhân viên":
                    if (String.valueOf(lichtrinh.getManv()).toLowerCase().contains(lowerInpStr)) {result.add(lichtrinh);}
                    break;
                case "Mã tàu":
                    if (String.valueOf(lichtrinh.getMatau()).toLowerCase().contains(lowerInpStr)) {result.add(lichtrinh);}
                    break;
                case "Mã tuyến":
                    if (String.valueOf(lichtrinh.getMatuyen()).toLowerCase().contains(lowerInpStr)) {result.add(lichtrinh);}
                    break;
                case "Hướng đi":
                    String huongdi = lichtrinh.getHuongdi() ? "Đi" : "Về";
                    if (huongdi.toLowerCase().contains(lowerInpStr)) {result.add(lichtrinh);}
                    break;
                case "Thời gian khởi hành":
                    if (lichtrinh.getThoigiankhoihanh().toString().toLowerCase().contains(lowerInpStr)) {result.add(lichtrinh);}
                    break;
                case "Trạng thái lịch trình":
                    if (lichtrinh.getTrangthai().toLowerCase().contains(lowerInpStr)) {result.add(lichtrinh);}
                    break;
                default:
                    System.out.println("Không thể tìm kiếm theo loại: " + type);
                    break;
            }
        }
        System.out.println("Số lượng tìm thấy: " + result.size());
        return result;
    }
}
