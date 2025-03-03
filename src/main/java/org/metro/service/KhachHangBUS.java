package org.metro.service;

import org.metro.model.KhachHangDTO;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class KhachHangBUS {
    private static ArrayList<KhachHangDTO> allCustomers = new ArrayList<>();

    // Thêm dữ liệu mẫu
    static {
        allCustomers.add(new KhachHangDTO(1, "Nguyễn Văn A", "0123456789", "Tuyến 1"));
        allCustomers.add(new KhachHangDTO(2, "Trần Thị B", "0987654321", "Tuyến 2"));
    }

    public static ArrayList<KhachHangDTO> search(String txt, String type) {
        if (txt == null || txt.trim().isEmpty()) {
            return new ArrayList<>(allCustomers);
        }

        String lowerTxt = txt.toLowerCase();
        return allCustomers.stream()
                .filter(kh -> {
                    switch (type) {
                        case "Tất cả":
                            return String.valueOf(kh.getId()).contains(lowerTxt) ||
                                    kh.getTen().toLowerCase().contains(lowerTxt) ||
                                    kh.getSoDienThoai().contains(lowerTxt) ||
                                    kh.getRouteID().toLowerCase().contains(lowerTxt);
                        case "ID":
                            return String.valueOf(kh.getId()).contains(lowerTxt);
                        case "Tên khách hàng":
                            return kh.getTen().toLowerCase().contains(lowerTxt);
                        case "Số điện thoại":
                            return kh.getSoDienThoai().contains(lowerTxt);
                        case "Tuyến":
                            return kh.getRouteID().toLowerCase().contains(lowerTxt);
                        default:
                            return false;
                    }
                })
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
