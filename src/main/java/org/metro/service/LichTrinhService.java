package org.metro.service;

import org.metro.DAO.LichTrinhDAO;
import org.metro.model.LichTrinhModel;

import java.util.List;

public class LichTrinhService {
    private static LichTrinhDAO lichtrinhDAO = new LichTrinhDAO();

    public static List<LichTrinhModel> getAll() {
        return lichtrinhDAO.getAll();
    }

    public static boolean insert(LichTrinhModel lichtrinh) {
        if (lichtrinhDAO.insert(lichtrinh) > 0) {
            return true;
        }
        return false;
    }

    public static boolean update(LichTrinhModel lichtrinh) {
        if (lichtrinhDAO.update(lichtrinh) > 0) {
            return true;
        }
        return false;
    }

    public static boolean delete(int machuyen) {
        if (lichtrinhDAO.delete(machuyen) > 0) {
            return true;
        }
        return false;
    }

    public static LichTrinhModel getById(int machuyen) {
        return lichtrinhDAO.selectById(machuyen);
    }

    public static List<LichTrinhModel> search(String inpStr, String type) {
        List<LichTrinhModel> result = lichtrinhDAO.getAll();
        List<LichTrinhModel> allLichTrinh = lichtrinhDAO.getAll();

        if (inpStr == null || inpStr.trim().isEmpty()) {
            return allLichTrinh;
        }

        String lowerInpStr = inpStr.toLowerCase();

        for (LichTrinhModel lichtrinh : allLichTrinh) {
            switch(type) {
                case "Tất cả":
                    if (String.valueOf(lichtrinh.getMachuyen()).contains(lowerInpStr) ||
                            String.valueOf(lichtrinh.getManv()).contains(lowerInpStr) ||
                            String.valueOf(lichtrinh.getMatau()).contains(lowerInpStr) ||
                            String.valueOf(lichtrinh.getMatuyen()).contains(lowerInpStr)) {
                        result.add(lichtrinh);
                    }
                    break;
                case "Mã chuyến":
                    if (String.valueOf(lichtrinh.getMachuyen()).contains(lowerInpStr)) {
                        result.add(lichtrinh);
                    }
                    break;
                case "Mã nhân viên":
                    if (String.valueOf(lichtrinh.getManv()).contains(lowerInpStr)) {
                        result.add(lichtrinh);
                    }
                    break;
                case "Mã tàu":
                    if (String.valueOf(lichtrinh.getMatau()).contains(lowerInpStr)) {
                        result.add(lichtrinh);
                    }
                    break;
                case "Mã tuyến":
                    if (String.valueOf(lichtrinh.getMatuyen()).contains(lowerInpStr)) {
                        result.add(lichtrinh);
                    }
                    break;
            }
        }
        return result;
    }
}
