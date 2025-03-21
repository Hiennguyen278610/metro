package org.metro.service;

import org.metro.DAO.VeTauDAO;
import org.metro.model.VeTauModel;
import java.util.ArrayList;
import java.util.List;

public class VeTauService {
    private static VeTauDAO vetauDAO = new VeTauDAO();

    public static List<VeTauModel> getAll() {
        return vetauDAO.getAll();
    }

    public static boolean insert(VeTauModel vetau) {
        if (vetauDAO.insert(vetau) > 0) {
            return true;
        }
        return false;
    }

    public static boolean update(VeTauModel vetau) {
        if (vetauDAO.update(vetau) > 0) {
            return true;
        }
        return false;
    }

    public static boolean delete(int mave) {
        if (vetauDAO.delete(mave) > 0) {
            return true;
        }
        return false;
    }

    public static VeTauModel getById(int mave) {
        return vetauDAO.selectById(mave);
    }

    // Phương thức tìm kiếm
    public static List<VeTauModel> search(String inpStr, String type) {
        List<VeTauModel> result = new ArrayList<>();
        List<VeTauModel> allTickets = vetauDAO.getAll();

        if (inpStr == null || inpStr.trim().isEmpty()) {
            return allTickets;
        }

        String lowerInpStr = inpStr.toLowerCase();

        for (VeTauModel vetau : allTickets) {
            switch(type) {
                case "Tất cả":
                    if (String.valueOf(vetau.getMave()).contains(lowerInpStr) ||
                            String.valueOf(vetau.getMachuyen()).contains(lowerInpStr) ||
                            String.valueOf(vetau.getMakh()).contains(lowerInpStr) ||
                            String.valueOf(vetau.getGiave()).contains(lowerInpStr)) {
                        result.add(vetau);
                    }
                    break;
                case "Mã vé":
                    if (String.valueOf(vetau.getMave()).contains(lowerInpStr)) {
                        result.add(vetau);
                    }
                    break;
                case "Mã chuyến":
                    if (String.valueOf(vetau.getMachuyen()).contains(lowerInpStr)) {
                        result.add(vetau);
                    }
                    break;
                case "Mã khách hàng":
                    if (String.valueOf(vetau.getMakh()).contains(lowerInpStr)) {
                        result.add(vetau);
                    }
                    break;
                case "Giá vé":
                    if (String.valueOf(vetau.getGiave()).contains(lowerInpStr)) {
                        result.add(vetau);
                    }
                    break;
            }
        }

        return result;
    }
}