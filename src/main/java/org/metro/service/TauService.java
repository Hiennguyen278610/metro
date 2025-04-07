package org.metro.service;

import org.metro.DAO.TauDAO;
import org.metro.model.TauModel;

import java.util.ArrayList;
import java.util.List;

public class TauService {
    private static final TauDAO tauDAO = new TauDAO();

    public static List<TauModel> getAll() {
        return tauDAO.selectAll();
    }

    public static boolean insert(TauModel tau) {
        return tauDAO.insert(tau) > 0;
    }

    public static boolean update(TauModel tau) {
        return tauDAO.update(tau) > 0;
    }

    public static boolean delete(String matau) {
        return tauDAO.delete(matau) > 0;
    }

    public static TauModel getById(String matau) {
        return tauDAO.selectById(matau);
    }

    public static List<TauModel> search(String inpStr, String type) {
        List<TauModel> result = new ArrayList<>();
        List<TauModel> allTau = tauDAO.selectAll();

        if (inpStr == null || inpStr.trim().isEmpty()) {
            return allTau;
        }

        String lowerInpStr = inpStr.toLowerCase();

        for (TauModel tau : allTau) {
            switch(type) {
                case "Tất cả":
                    if (tau.getMatau().toLowerCase().contains(lowerInpStr) ||
                            String.valueOf(tau.getSoghe()).contains(lowerInpStr) ||
                            tau.getTrangthaitau().toLowerCase().contains(lowerInpStr) ||
                            tau.getNgaynhap().toLowerCase().contains(lowerInpStr)) {
                        result.add(tau);
                    }
                    break;
                case "Mã tàu":
                    if (tau.getMatau().toLowerCase().contains(lowerInpStr)) {
                        result.add(tau);
                    }
                    break;
                case "Số ghế":
                    if (String.valueOf(tau.getSoghe()).contains(lowerInpStr)) {
                        result.add(tau);
                    }
                    break;
                case "Trạng thái":
                    if (tau.getTrangthaitau().toLowerCase().contains(lowerInpStr)) {
                        result.add(tau);
                    }
                    break;
                case "Ngày nhập":
                    if (tau.getNgaynhap().toLowerCase().contains(lowerInpStr)) {
                        result.add(tau);
                    }
                    break;
            }
        }
        return result;
    }
}