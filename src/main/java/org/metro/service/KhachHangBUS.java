package org.metro.service;

import org.metro.dao.KhachHangDAO;
import org.metro.model.KhachHangDTO;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class KhachHangBUS {
    private static ArrayList<KhachHangDTO> allCustomers = new ArrayList<>();
    private static KhachHangDAO khachHangDAO = new KhachHangDAO();

    // Thêm phương thức này để load dữ liệu từ DB
    public static void loadData() {
        allCustomers = new ArrayList<>(khachHangDAO.getAll());
    }

    // Đảm bảo phương thức search làm việc với dữ liệu đã được load
    public static ArrayList<KhachHangDTO> search(String txt, String type) {
        // Nếu allCustomers trống, load dữ liệu
        if (allCustomers.isEmpty()) {
            loadData();
        }

        if (txt == null || txt.trim().isEmpty()) {
            return new ArrayList<>(allCustomers);
        }

        String lowerTxt = txt.toLowerCase();
        return allCustomers.stream()
                .filter(kh -> {
                    switch (type) {
                        case "Tất cả":
                            return String.valueOf(kh.getMaKh()).contains(lowerTxt) ||
                                    kh.getTenKh().toLowerCase().contains(lowerTxt) ||
                                    kh.getSdt().contains(lowerTxt) ||
                                    kh.getMaTuyen().toLowerCase().contains(lowerTxt);
                        case "ID":
                            return String.valueOf(kh.getMaKh()).contains(lowerTxt);
                        case "Tên khách hàng":
                            return kh.getTenKh().toLowerCase().contains(lowerTxt);
                        case "Số điện thoại":
                            return kh.getSdt().contains(lowerTxt);
                        case "Tuyến":
                            return kh.getMaTuyen().toLowerCase().contains(lowerTxt);
                        default:
                            return false;
                    }
                })
                .collect(Collectors.toCollection(ArrayList::new));
    }

    // Thêm các phương thức quản lý dữ liệu khách hàng
    public static boolean insert(KhachHangDTO kh) {
        if (khachHangDAO.insert(kh) > 0) {
            allCustomers.add(kh);
            loadData();
            return true;
        }
        return false;
    }

    public static boolean update(KhachHangDTO kh) {
        if (khachHangDAO.update(kh) > 0) {
            // Cập nhật danh sách allCustomers
            for (int i = 0; i < allCustomers.size(); i++) {
                if (allCustomers.get(i).getMaKh() == kh.getMaKh()) {
                    allCustomers.set(i, kh);
                    break;
                }
            }
            loadData();
            return true;
        }
        return false;
    }

    public static boolean delete(int maKh) {
        if (khachHangDAO.delete(maKh) > 0) {
            // Xóa khỏi danh sách allCustomers
            allCustomers.removeIf(kh -> kh.getMaKh() == maKh);
            loadData();
            return true;
        }
        return false;
    }

    public static KhachHangDTO getById(int maKh) {
        return khachHangDAO.selectById(maKh);
    }
}
