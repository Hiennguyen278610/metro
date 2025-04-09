package org.metro.service;

import org.metro.DAO.TuyenDAO;
import org.metro.model.TuyenDuongModel;

import java.util.List;

public class TuyenDuongService {
    private static TuyenDAO tuyenDAO = new TuyenDAO();

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
        // Luoi qua, viec phan con lai di m
        return tuyenDAO.search(inpStr, type);
    }
}