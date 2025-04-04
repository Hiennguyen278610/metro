package org.metro.view.Dialog;

import org.metro.controller.LichTrinhController;
import org.metro.model.LichTrinhModel;
import org.metro.view.Component.handleComponents;
import org.metro.view.Panel.LichTrinh;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class LichTrinhDialog extends JDialog {
    private JTextField machuyenTextField, manvTextField, matauTextField, matuyenTextField;
    private JComboBox<String> huongdiComboBox;
    private JTextField tgkhoihanhTextField, tgdenthucteTextField;
    private JComboBox<String> trangthaiComboBox;
    private JButton ok, cancel;
    private String type;
    private JPanel contentPanel;
    private LichTrinh lichTrinh;
    private LichTrinhModel lichTrinhModel;
    private LichTrinhController controller;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public LichTrinhDialog(Frame parent, String type, LichTrinh lichTrinh, LichTrinhModel lichTrinhModel) {
        super(parent, true);
        this.lichTrinh = lichTrinh;
        this.type = type;
        this.lichTrinhModel = lichTrinhModel;
        this.controller = new LichTrinhController(lichTrinh, this);
        this.setTitle(setTitleType());
        this.setSize(600, 500);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.init();
        this.checkButtonClicked();
    }

    public LichTrinhDialog() {
        super();
        this.type = "default";
    }

    private void init() {
        contentPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 20, 5, 20);

        // Labels
        handleComponents.addLabelGBL(contentPanel, "Mã chuyến:", 0, 0, gbc);
        handleComponents.addLabelGBL(contentPanel, "Mã nhân viên:", 0, 1, gbc);
        handleComponents.addLabelGBL(contentPanel, "Mã tàu:", 0, 2, gbc);
        handleComponents.addLabelGBL(contentPanel, "Mã tuyến:", 0, 3, gbc);
        handleComponents.addLabelGBL(contentPanel, "Hướng đi:", 0, 4, gbc);
        handleComponents.addLabelGBL(contentPanel, "Thời gian khởi hành (yyyy-MM-dd HH:mm:ss):", 0, 5, gbc);
        handleComponents.addLabelGBL(contentPanel, "Thời gian đến thực tế (yyyy-MM-dd HH:mm:ss):", 0, 6, gbc);
        handleComponents.addLabelGBL(contentPanel, "Trạng thái lịch trình:", 0, 7, gbc);

        // Input fields
        machuyenTextField = handleComponents.addTextFieldGBL(contentPanel, 20, 1, 0, gbc);
        manvTextField = handleComponents.addTextFieldGBL(contentPanel, 20, 1, 1, gbc);
        matauTextField = handleComponents.addTextFieldGBL(contentPanel, 20, 1, 2, gbc);
        matuyenTextField = handleComponents.addTextFieldGBL(contentPanel, 20, 1, 3, gbc);

        huongdiComboBox = new JComboBox<>(new String[]{"Đi", "Về"});
        gbc.gridx = 1;
        gbc.gridy = 4;
        contentPanel.add(huongdiComboBox, gbc);

        tgkhoihanhTextField = handleComponents.addTextFieldGBL(contentPanel, 20, 1, 5, gbc);
        tgdenthucteTextField = handleComponents.addTextFieldGBL(contentPanel, 20, 1, 6, gbc);

        trangthaiComboBox = new JComboBox<>(new String[]{"Chưa khởi hành", "Đang chạy", "Đã hoàn thành", "Đã hủy"});
        gbc.gridx = 1;
        gbc.gridy = 7;
        contentPanel.add(trangthaiComboBox, gbc);

        // Buttons
        gbc.gridwidth = 1;
        if ("create".equals(type)) {
            ok = handleComponents.addButtonGBL(contentPanel, "THÊM", 0, 8, gbc);
            cancel = handleComponents.addButtonGBL(contentPanel, "HỦY", 1, 8, gbc);
        } else if ("update".equals(type)) {
            ok = handleComponents.addButtonGBL(contentPanel, "CẬP NHẬT", 0, 8, gbc);
            cancel = handleComponents.addButtonGBL(contentPanel, "HỦY", 1, 8, gbc);
        } else {
            ok = handleComponents.addButtonGBL(contentPanel, "ĐÓNG", 0, 8, gbc);
            cancel = null;
        }

        // Add action listeners
        if (ok != null) {
            ok.addActionListener(controller);
        }
        if (cancel != null) {
            cancel.addActionListener(e -> dispose());
        }

        this.add(contentPanel);
    }

    private String setTitleType() {
        if (type == null) {
            return "Lịch Trình";
        }
        switch (type) {
            case "create":
                return "THÊM LỊCH TRÌNH";
            case "update":
                return "SỬA THÔNG TIN LỊCH TRÌNH";
            case "detail":
                return "THÔNG TIN LỊCH TRÌNH";
            default:
                return "LỊCH TRÌNH";
        }
    }

    public void checkButtonClicked() {
        if (type == null) return;

        switch (type) {
            case "create":
                editEnabled(true);
                machuyenTextField.setEnabled(false); // Auto-generated ID
                break;
            case "update":
                editEnabled(true);
                machuyenTextField.setEnabled(false); // Can't change ID
                loadLichTrinhData();
                break;
            case "detail":
                editEnabled(false);
                loadLichTrinhData();
                break;
            default:
                System.err.println("Button clicked: " + type);
        }
    }

    public void editEnabled(boolean enabled) {
        if (machuyenTextField != null) machuyenTextField.setEnabled(enabled);
        if (manvTextField != null) manvTextField.setEnabled(enabled);
        if (matauTextField != null) matauTextField.setEnabled(enabled);
        if (matuyenTextField != null) matuyenTextField.setEnabled(enabled);
        if (huongdiComboBox != null) huongdiComboBox.setEnabled(enabled);
        if (tgkhoihanhTextField != null) tgkhoihanhTextField.setEnabled(enabled);
        if (tgdenthucteTextField != null) tgdenthucteTextField.setEnabled(enabled);
        if (trangthaiComboBox != null) trangthaiComboBox.setEnabled(enabled);
    }

    private void loadLichTrinhData() {
        LichTrinhModel selected = (lichTrinhModel != null) ? lichTrinhModel : lichTrinh.getSelectedLichTrinh();
        if (selected != null) {
            machuyenTextField.setText(String.valueOf(selected.getMachuyen()));
            manvTextField.setText(String.valueOf(selected.getManv()));
            matauTextField.setText(String.valueOf(selected.getMatau()));
            matuyenTextField.setText(String.valueOf(selected.getMatuyen()));

            huongdiComboBox.setSelectedIndex(selected.getHuongdi() ? 0 : 1);

            if (selected.getThoigiankhoihanh() != null) {
                tgkhoihanhTextField.setText(selected.getThoigiankhoihanh().format(formatter));
            }

            if (selected.getThoigianthucte() != null) {
                tgdenthucteTextField.setText(selected.getThoigianthucte().format(formatter));
            }

            String trangthai = selected.getTrangthai();
            for (int i = 0; i < trangthaiComboBox.getItemCount(); i++) {
                if (trangthaiComboBox.getItemAt(i).equals(trangthai)) {
                    trangthaiComboBox.setSelectedIndex(i);
                    break;
                }
            }
        } else {
            System.err.println("Không có dữ liệu lịch trình để hiển thị");
        }
    }

    public LichTrinhModel getLichTrinhFromForm() {
        try {
            // Validate required fields
            if (manvTextField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Mã nhân viên không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            if (matauTextField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Mã tàu không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            if (matuyenTextField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Mã tuyến không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            if (tgkhoihanhTextField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Thời gian khởi hành không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return null;
            }

            // Parse numeric fields
            int machuyen = 0;
            if (!"create".equals(type)) {
                try {
                    machuyen = Integer.parseInt(machuyenTextField.getText().trim());
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Mã chuyến phải là số nguyên!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return null;
                }
            }

            int manv, matau, matuyen;
            try {
                manv = Integer.parseInt(manvTextField.getText().trim());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Mã nhân viên phải là số nguyên!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return null;
            }

            try {
                matau = Integer.parseInt(matauTextField.getText().trim());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Mã tàu phải là số nguyên!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return null;
            }

            try {
                matuyen = Integer.parseInt(matuyenTextField.getText().trim());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Mã tuyến phải là số nguyên!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return null;
            }

            // Parse boolean value
            boolean huongdi = huongdiComboBox.getSelectedIndex() == 0;

            // Parse date-time fields
            LocalDateTime tgkhoihanh = null;
            try {
                tgkhoihanh = LocalDateTime.parse(tgkhoihanhTextField.getText().trim(), formatter);
            } catch (DateTimeParseException e) {
                JOptionPane.showMessageDialog(this,
                        "Định dạng thời gian khởi hành không hợp lệ! Yêu cầu: yyyy-MM-dd HH:mm:ss",
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
                return null;
            }

            LocalDateTime tgdenthucte = null;
            if (!tgdenthucteTextField.getText().trim().isEmpty()) {
                try {
                    tgdenthucte = LocalDateTime.parse(tgdenthucteTextField.getText().trim(), formatter);
                } catch (DateTimeParseException e) {
                    JOptionPane.showMessageDialog(this,
                            "Định dạng thời gian đến thực tế không hợp lệ! Yêu cầu: yyyy-MM-dd HH:mm:ss",
                            "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return null;
                }
            }

            // Get selected status
            String trangthailichtrinh = (String) trangthaiComboBox.getSelectedItem();

            // Create and return the model object
            return new LichTrinhModel(machuyen, manv, matau, matuyen, huongdi, tgkhoihanh, tgdenthucte, trangthailichtrinh);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    // Getters
    public JTextField getMachuyenTextField() {
        return machuyenTextField;
    }

    public JTextField getManvTextField() {
        return manvTextField;
    }

    public JTextField getMatauTextField() {
        return matauTextField;
    }

    public JTextField getMatuyenTextField() {
        return matuyenTextField;
    }

    public JComboBox<String> getHuongdiComboBox() {
        return huongdiComboBox;
    }

    public JTextField getTgkhoihanhTextField() {
        return tgkhoihanhTextField;
    }

    public JTextField getTgdenthucteTextField() {
        return tgdenthucteTextField;
    }

    public JComboBox<String> getTrangthaiComboBox() {
        return trangthaiComboBox;
    }

    public JButton getOk() {
        return ok;
    }

    public LichTrinh getLichTrinh() {
        return lichTrinh;
    }

    public LichTrinhModel getLichTrinhModel() {
        return lichTrinhModel;
    }
}