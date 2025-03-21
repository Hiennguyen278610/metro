package org.metro.view.Dialog;

import org.metro.controller.VeTauController;
import org.metro.model.VeTauModel;
import org.metro.service.VeTauService;
import org.metro.view.Component.handleComponents;
import org.metro.view.Panel.VeTau;

import javax.swing.*;
import java.awt.*;

public class VeTauDialog extends JDialog {
    private JTextField maveTextField, machuyenTextField, makhTextField, giaveTextField;
    private JButton ok, cancel;
    private String type;
    private JPanel contentPanel;
    private VeTau veTau;
    private VeTauModel vetauModel;
    private VeTauController action;

    // Dialog thêm, sửa, chi tiết vé tàu
    public VeTauDialog(Frame parent, String type, VeTau veTau, VeTauModel vetauModel) {
        super(parent, true);
        this.veTau = veTau;
        this.type = type;
        this.vetauModel = vetauModel;
        this.action = new VeTauController(veTau, this);
        this.setTitle(setTitleType());
        this.setSize(500, 400);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.init();
        checkButtonClicked();
    }

    public VeTauDialog() {
        super();
        this.type = "default";
        this.contentPanel = new JPanel(new GridBagLayout());
        this.maveTextField = new JTextField(20);
        this.machuyenTextField = new JTextField(20);
        this.makhTextField = new JTextField(20);
        this.giaveTextField = new JTextField(20);
        this.ok = new JButton("OK");
    }

    private void init() {
        contentPanel = new JPanel();
        contentPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 20, 5, 20);

        // Labels
        handleComponents.addLabelGBL(contentPanel, "Mã vé:", 0, 0, gbc);
        handleComponents.addLabelGBL(contentPanel, "Mã chuyến:", 0, 1, gbc);
        handleComponents.addLabelGBL(contentPanel, "Mã khách hàng:", 0, 2, gbc);
        handleComponents.addLabelGBL(contentPanel, "Giá vé:", 0, 3, gbc);

        // TextFields
        maveTextField = handleComponents.addTextFieldGBL(contentPanel, 20, 1, 0, gbc);
        machuyenTextField = handleComponents.addTextFieldGBL(contentPanel, 20, 1, 1, gbc);
        makhTextField = handleComponents.addTextFieldGBL(contentPanel, 20, 1, 2, gbc);
        giaveTextField = handleComponents.addTextFieldGBL(contentPanel, 20, 1, 3, gbc);

        // Buttons - Đặt text chính xác cho mỗi loại dialog
        gbc.gridwidth = 1;
        if ("create".equals(type)) {
            ok = handleComponents.addButtonGBL(contentPanel, "THÊM", 0, 4, gbc);
            cancel = handleComponents.addButtonGBL(contentPanel, "HỦY", 1, 4, gbc);
        } else if ("update".equals(type)) {
            ok = handleComponents.addButtonGBL(contentPanel, "CẬP NHẬT", 0, 4, gbc);
            cancel = handleComponents.addButtonGBL(contentPanel, "HỦY", 1, 4, gbc);
        } else {
            ok = handleComponents.addButtonGBL(contentPanel, "HỦY", 0, 4, gbc);
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

    // Hàm lấy text cho nút OK
    private String getButtonText() {
        if (type == null) {
            return "OK";
        }
        switch (type) {
            case "create":
                return "THÊM";
            case "update":
                return "CẬP NHẬT";
            case "detail":
                return "ĐÓNG";
            default:
                return "OK";
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
        if (maveTextField != null) maveTextField.setEnabled(enabled);
        if (machuyenTextField != null) machuyenTextField.setEnabled(enabled);
        if (makhTextField != null) makhTextField.setEnabled(enabled);
        if (giaveTextField != null) giaveTextField.setEnabled(enabled);
    }

    // Hàm load dữ liệu vé tàu vào form
    private void loadVeTauData() {
        VeTauModel veTauSelected = (vetauModel != null) ? vetauModel : veTau.getSelectedVeTau();
        if (veTauSelected != null) {
            maveTextField.setText(String.valueOf(veTauSelected.getMave()));
            machuyenTextField.setText(String.valueOf(veTauSelected.getMachuyen()));
            makhTextField.setText(String.valueOf(veTauSelected.getMakh()));
            giaveTextField.setText(String.valueOf(veTauSelected.getGiave()));
        } else {
            System.err.println("Không có dữ liệu vé tàu để hiển thị");
        }
    }

    // Các phương thức cũ giữ lại để tương thích ngược
    public void showAddVeTauDialog(Component parent, Runnable updateCallback) {
        Frame frame = JOptionPane.getFrameForComponent(parent);
        VeTauDialog dialog = new VeTauDialog(frame, "create", (VeTau) parent, null);
        dialog.setVisible(true);
    }

    public void showUpdateVeTauDialog(Component parent, int mave, Runnable updateCallback) {
        VeTauModel vetau = VeTauService.getById(mave);
        if (vetau == null) {
            JOptionPane.showMessageDialog(parent, "Không tìm thấy thông tin vé tàu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Frame frame = JOptionPane.getFrameForComponent(parent);
        VeTauDialog dialog = new VeTauDialog(frame, "update", (VeTau) parent, vetau);
        dialog.setVisible(true);
    }

    public void showVeTauDetailDialog(Component parent, VeTauModel vetau) {
        if (vetau == null) {
            JOptionPane.showMessageDialog(parent, "Không có thông tin vé tàu để hiển thị!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Frame frame = JOptionPane.getFrameForComponent(parent);
        VeTauDialog dialog = new VeTauDialog(frame, "detail", (VeTau) parent, vetau);
        dialog.setVisible(true);
    }

    // Phương thức lấy dữ liệu từ form
    public VeTauModel getVeTauFromForm() {
        try {
            // Kiểm tra null trước khi truy cập các thành phần
            if (maveTextField == null || machuyenTextField == null ||
                    makhTextField == null || giaveTextField == null) {
                JOptionPane.showMessageDialog(this,
                        "Lỗi: Các trường dữ liệu chưa được khởi tạo!",
                        "Lỗi hệ thống", JOptionPane.ERROR_MESSAGE);
                return null;
            }

            // Kiểm tra các trường bắt buộc không được để trống
            if (machuyenTextField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập mã chuyến!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                machuyenTextField.requestFocus();
                return null;
            }
            if (makhTextField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập mã khách hàng!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                makhTextField.requestFocus();
                return null;
            }
            if (giaveTextField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập giá vé!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                giaveTextField.requestFocus();
                return null;
            }

            // Xử lý trường mã vé
            int mave;
            try {
                mave = Integer.parseInt(maveTextField.getText().trim());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Mã vé phải là số nguyên!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                maveTextField.requestFocus();
                return null;
            }

            int machuyen;
            try {
                machuyen = Integer.parseInt(machuyenTextField.getText().trim());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Mã chuyến phải là số nguyên!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                machuyenTextField.requestFocus();
                return null;
            }

            int makh;
            try {
                makh = Integer.parseInt(makhTextField.getText().trim());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Mã khách hàng phải là số nguyên!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                makhTextField.requestFocus();
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

            return new VeTauModel(mave, machuyen, makh, giave);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    // Getters và Setters
    public JTextField getMaveTextField() {
        return maveTextField;
    }

    public JTextField getMachuyenTextField() {
        return machuyenTextField;
    }

    public JTextField getMakhTextField() {
        return makhTextField;
    }

    public JTextField getGiaveTextField() {
        return giaveTextField;
    }

    public JButton getOk() {
        return ok;
    }


    public VeTau getVeTau() {
        return veTau;
    }

    public VeTauModel getVetauModel() {
        return vetauModel;
    }
}