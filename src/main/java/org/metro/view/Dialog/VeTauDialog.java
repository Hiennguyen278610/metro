package org.metro.view.Dialog;

import org.metro.controller.VeTauController;
import org.metro.model.KhachHangModel;
import org.metro.model.LichTrinhModel;
import org.metro.model.NhanVienModel;
import org.metro.model.VeTauModel;
import org.metro.service.KhachHangService;
import org.metro.service.VeTauService;
import org.metro.util.ComboBoxUtil;
import org.metro.view.Component.handleComponents;
import org.metro.view.Panel.VeTau;

import javax.swing.*;
import java.awt.*;

public class VeTauDialog extends JDialog {
    private JTextField sdtKhachTextField, giaveTextField;
    private JComboBox<LichTrinhModel> machuyenComboBox;
    private DefaultComboBoxModel<LichTrinhModel> machuyenComboBoxModel;
    private JButton ok, cancel;
    private String type;
    private JPanel contentPanel;
    private VeTau veTau;
    private VeTauModel vetauModel;
    private VeTauController action;
    private KhachHangDialog khachHangDialog;

    // Dialog thêm, sửa, chi tiết vé tàu
    public VeTauDialog(Frame parent, String type, VeTau veTau, VeTauModel vetauModel) {
        super(parent, true);
        this.veTau = veTau;
        this.type = type;
        this.vetauModel = vetauModel;
        this.action = new VeTauController(veTau, this);
        this.khachHangDialog = new KhachHangDialog();
        this.setTitle(setTitleType());
        this.setSize(500, 400);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.init();
        checkButtonClicked();
    }

    private void init() {
        contentPanel = new JPanel();
        contentPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 20, 5, 20);

        // Labels
        handleComponents.addLabelGBL(contentPanel, "Mã chuyến:", 0, 0, gbc);
        handleComponents.addLabelGBL(contentPanel, "Số điện thoại khách:", 0, 1, gbc);
        handleComponents.addLabelGBL(contentPanel, "Giá vé:", 0, 2, gbc);

        machuyenComboBoxModel = new DefaultComboBoxModel<>();
        machuyenComboBox = new JComboBox<>(machuyenComboBoxModel);
        ComboBoxUtil.loadComboBoxData(machuyenComboBoxModel, ComboBoxUtil.getDataSupplier(LichTrinhModel.class));
        gbc.gridx = 1;
        gbc.gridy = 0;
        contentPanel.add(machuyenComboBox, gbc);

        sdtKhachTextField = handleComponents.addTextFieldGBL(contentPanel, 20, 1, 1, gbc);
        giaveTextField = handleComponents.addTextFieldGBL(contentPanel, 20, 1, 2, gbc);

        // Buttons - Đặt text chính xác cho mỗi loại dialog
        gbc.gridwidth = 1;
        if ("create".equals(type)) {
            ok = handleComponents.addButtonGBL(contentPanel, "THÊM", 0, 3, gbc);
            cancel = handleComponents.addButtonGBL(contentPanel, "HỦY", 1, 3, gbc);
        } else if ("update".equals(type)) {
            ok = handleComponents.addButtonGBL(contentPanel, "CẬP NHẬT", 0, 3, gbc);
            cancel = handleComponents.addButtonGBL(contentPanel, "HỦY", 1, 3, gbc);
        } else {
            ok = handleComponents.addButtonGBL(contentPanel, "ĐÓNG", 0, 3, gbc);
        }

        // Thêm action listener cho nút OK và Cancel
        if (ok != null) {
            ok.addActionListener(action);
        }
        if (cancel != null) {
            cancel.addActionListener(e -> dispose());
        }

        this.add(contentPanel);
    }

    // Hàm check SDT khách hàng tồn tại và trả về mã khách
    public KhachHangModel checkSdtKhachHang(String sdt) {
        return KhachHangService.getBySdt(sdt);
    }

    // Hàm set title cho dialog
    private String setTitleType() {
        if (type == null) {
            return "Vé Tàu";
        }
        switch (type) {
            case "create":
                return "THÊM VÉ TÀU";
            case "update":
                return "SỬA THÔNG TIN VÉ TÀU";
            case "detail":
                return "THÔNG TIN VÉ TÀU";
            default:
                return "ERROR";
        }
    }

    // Check loại dialog và thiết lập trạng thái tương ứng
    public void checkButtonClicked() {
        if (type == null) return;

        switch (type) {
            case "create":
                // Trường hợp thêm mới
                editEnabled(true);
                break;
            case "update":
                // Trường hợp cập nhật
                editEnabled(true);
                loadVeTauData();
                break;
            case "detail":
                // Trường hợp xem chi tiết, không cho phép nhập
                editEnabled(false);
                loadVeTauData();
                break;
            case "delete":
                // Trường hợp xóa, thường không cần dialog
                break;
            default:
                System.err.println("Button được nhấn là " + type);
        }
    }

    // Hàm thiết lập khả năng chỉnh sửa của các trường
    public void editEnabled(boolean enabled) {
        if (machuyenComboBox != null) machuyenComboBox.setEnabled(enabled);
        if (sdtKhachTextField != null) sdtKhachTextField.setEnabled(enabled);
        if (giaveTextField != null) giaveTextField.setEnabled(enabled);
    }

    // Hàm load dữ liệu vé tàu vào form
    private void loadVeTauData() {
        VeTauModel veTauSelected = (vetauModel != null) ? vetauModel : veTau.getSelectedVeTau();
        if (veTauSelected != null) {
            // Lấy thông tin khách hàng theo mã khách hàng để hiển thị số điện thoại
            KhachHangModel kh = KhachHangService.getById(veTauSelected.getMakh());

            ComboBoxUtil.selectItemInComboBox(machuyenComboBox, machuyenComboBoxModel,
                    nv -> ((LichTrinhModel)nv).getManv() == veTauSelected.getMachuyen());
            sdtKhachTextField.setText(kh != null ? kh.getSdt() : "Không tìm thấy");
            giaveTextField.setText(String.valueOf(veTauSelected.getGiave()));
        } else {
            System.err.println("Không có dữ liệu vé tàu để hiển thị");
        }
    }

    // Phương thức lấy dữ liệu từ form và tạo đối tượng VeTauModel
    public VeTauModel getVeTauFromForm() {
        try {
            // Kiểm tra null trước khi truy cập các thành phần
            if (machuyenComboBox == null || sdtKhachTextField == null || giaveTextField == null) {
                JOptionPane.showMessageDialog(this,
                        "Lỗi: Các trường dữ liệu chưa được khởi tạo!",
                        "Lỗi hệ thống", JOptionPane.ERROR_MESSAGE);
                return null;
            }

            if (sdtKhachTextField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập số điện thoại khách hàng!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                sdtKhachTextField.requestFocus();
                return null;
            }
            if (giaveTextField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập giá vé!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                giaveTextField.requestFocus();
                return null;
            }

            // Lấy mã chuyến từ ComboBox
            int machuyen = ((LichTrinhModel) machuyenComboBox.getSelectedItem()).getMachuyen();

            // Kiểm tra SĐT khách hàng có tồn tại trong DB không
            String sdtKhach = sdtKhachTextField.getText().trim();
            KhachHangModel khachHang = checkSdtKhachHang(sdtKhach);

            // Nếu không tìm thấy khách hàng, hiển thị dialog thêm khách hàng mới
            if (khachHang == null) {
                int choice = JOptionPane.showConfirmDialog(this,
                        "Số điện thoại này chưa có trong hệ thống. Bạn có muốn thêm khách hàng mới không?",
                        "Thông báo", JOptionPane.YES_NO_OPTION);

                if (choice == JOptionPane.YES_OPTION) {
                    // Hiển thị dialog thêm khách hàng mới với callback để tạo vé sau khi thêm thành công
                    khachHangDialog.showAddKhachHangDialog(this, () -> {
                        // Kiểm tra lại sau khi thêm khách hàng
                        KhachHangModel newCustomer = checkSdtKhachHang(sdtKhach);
                        if (newCustomer != null) {
                            // Tạo vé tàu với khách hàng mới
                            createVeTauWithCustomer(machuyen, newCustomer, giaveTextField.getText().trim());
                        }
                    });
                }
                return null;
            }

            double giave;
            try {
                giave = Double.parseDouble(giaveTextField.getText().trim().replace(",", "."));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Giá vé phải là số thực!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                giaveTextField.requestFocus();
                return null;
            }

            // mave = 0 để đánh dấu tự động tăng
            return new VeTauModel(0, machuyen, khachHang.getMaKh(), giave);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    // Tạo vé tàu với thông tin khách hàng đã có
    private void createVeTauWithCustomer(int machuyen, KhachHangModel khachHang, String giaveText) {
        try {
            double giave = Double.parseDouble(giaveText.replace(",", "."));
            VeTauModel veTau = new VeTauModel(0, machuyen, khachHang.getMaKh(), giave);

            if (VeTauService.insert(veTau)) {
                JOptionPane.showMessageDialog(this, "THÊM VÉ TÀU THÀNH CÔNG", "THÔNG BÁO",
                        JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
                if (this.veTau != null) this.veTau.loadDataTable();
            } else {
                JOptionPane.showMessageDialog(this, "THÊM VÉ TÀU THẤT BẠI", "THÔNG BÁO",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Giá vé phải là số thực!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
}