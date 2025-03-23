package org.metro.controller;

import org.metro.model.KhachHangModel;
import org.metro.service.KhachHangService;
import java.util.List;

public class KhachHangController {

    // Lấy danh sách tất cả khách hàng
    public List<KhachHangModel> getAllCustomers() {
        // Gọi đến Service, Service đảm bảo load dữ liệu mới nhất
        return KhachHangService.search("", "Tất cả");
    }

    // Thực hiện xóa khách hàng theo mã
    public boolean deleteCustomer(int maKh) {
        return KhachHangService.delete(maKh);
    }

    // Thêm khách hàng mới
    public boolean addCustomer(KhachHangModel kh) {
        return KhachHangService.insert(kh);
    }

    // Cập nhật thông tin khách hàng
    public boolean updateCustomer(KhachHangModel kh) {
        return KhachHangService.update(kh);
    }

    // Lấy thông tin khách hàng theo mã
    public KhachHangModel getCustomerById(int maKh) {
        return KhachHangService.getById(maKh);
    }
}
