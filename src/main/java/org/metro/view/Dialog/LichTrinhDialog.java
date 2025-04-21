package org.metro.view.Dialog;

import org.metro.model.LichTrinhModel;
import org.metro.model.NhanVienModel;
import org.metro.model.TauModel;
import org.metro.controller.LichTrinhController;
import org.metro.model.TuyenDuongModel;
import org.metro.util.ComboBoxUtil;
import org.metro.view.Component.handleComponents;
import org.metro.view.Panel.LichTrinh;
import org.metro.util.DateTimeUtil;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.Date;
import com.toedter.calendar.JDateChooser;

public class LichTrinhDialog extends JDialog {
    private JTextField machuyenTextField;
    private JComboBox<TuyenDuongModel> matuyenComboBox;
    private DefaultComboBoxModel<TuyenDuongModel> matuyenComboBoxModel;
    private JComboBox<NhanVienModel> manvComboBox;
    private DefaultComboBoxModel<NhanVienModel> manvComboBoxModel;
    private JComboBox<TauModel> matauComboBox;
    private DefaultComboBoxModel<TauModel> matauComboBoxModel;
    private JComboBox<String> huongdiComboBox;
    private JDateChooser tgkhJDateChooser, tgttJDateChooser;
    private JComboBox<String> tgkhTimeComboBox, tgttTimeComboBox;
    private JComboBox<String> trangthaiComboBox;
    private JButton ok, cancel;
    private String type;
    private JPanel contentPanel;
    private LichTrinh lichTrinh;
    private LichTrinhModel lichTrinhModel;
    private LichTrinhController controller;

    public LichTrinhDialog(Frame parent, String type, LichTrinh lichTrinh, LichTrinhModel lichTrinhModel) {
        super(parent, true);
        this.lichTrinh = lichTrinh;
        this.type = type;
        this.lichTrinhModel = lichTrinhModel;
        this.controller = new LichTrinhController(lichTrinh, this);
        this.setTitle(setTitleType());
        this.setSize(650, 500);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.init();
        this.checkButtonClicked();
    }

    private void init() {
        contentPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 20, 5, 20);

        if (!"create".equals(type)) {
            handleComponents.addLabelGBL(contentPanel, "Mã chuyến:", 0, 0, gbc);
            machuyenTextField = handleComponents.addTextFieldGBL(contentPanel, 20, 1, 0, gbc);
            machuyenTextField.setEnabled(false);
        }

        int startRow = ("create".equals(type)) ? 0 : 1;
        handleComponents.addLabelGBL(contentPanel, "Nhân viên:", 0, startRow, gbc);
        handleComponents.addLabelGBL(contentPanel, "Mã tuyến:", 0, startRow + 1, gbc);
        handleComponents.addLabelGBL(contentPanel, "Mã tàu:", 0, startRow + 2, gbc);
        handleComponents.addLabelGBL(contentPanel, "Hướng đi:", 0, startRow + 3, gbc);
        handleComponents.addLabelGBL(contentPanel, "Thời gian khởi hành:", 0, startRow + 4, gbc);
        handleComponents.addLabelGBL(contentPanel, "Thời gian đến thực tế:", 0, startRow + 5, gbc);
        handleComponents.addLabelGBL(contentPanel, "Trạng thái lịch trình:", 0, startRow + 6, gbc);

        manvComboBoxModel = new DefaultComboBoxModel<>();
        manvComboBox = new JComboBox<>(manvComboBoxModel);
        ComboBoxUtil.loadComboBoxData(manvComboBoxModel, ComboBoxUtil.getDataSupplier(NhanVienModel.class));
        gbc.gridx = 1;
        gbc.gridy = startRow;
        contentPanel.add(manvComboBox, gbc);

        matuyenComboBoxModel = new DefaultComboBoxModel<>();
        matuyenComboBox = new JComboBox<>(matuyenComboBoxModel);
        ComboBoxUtil.loadComboBoxData(matuyenComboBoxModel, ComboBoxUtil.getDataSupplier(TuyenDuongModel.class));
        gbc.gridx = 1;
        gbc.gridy = startRow + 1;
        contentPanel.add(matuyenComboBox, gbc);

        matauComboBoxModel = new DefaultComboBoxModel<>();
        matauComboBox = new JComboBox<>(matauComboBoxModel);
        ComboBoxUtil.loadComboBoxData(matauComboBoxModel, ComboBoxUtil.getDataSupplier(TauModel.class));
        gbc.gridx = 1;
        gbc.gridy = startRow + 2;
        contentPanel.add(matauComboBox, gbc);


        huongdiComboBox = new JComboBox<>(new String[]{"Đi", "Về"});
        gbc.gridx = 1;
        gbc.gridy = startRow + 3;
        contentPanel.add(huongdiComboBox, gbc);

        JPanel tgkhPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        tgkhJDateChooser = new JDateChooser();
        tgkhJDateChooser.setDate(new Date());

        tgkhTimeComboBox = new JComboBox<>(DateTimeUtil.timeOptions);
        tgkhTimeComboBox.setSelectedItem("05:00");

        tgkhPanel.add(tgkhJDateChooser);
        tgkhPanel.add(tgkhTimeComboBox);

        gbc.gridx = 1;
        gbc.gridy = startRow + 4;
        contentPanel.add(tgkhPanel, gbc);

        JPanel tgttPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        tgttJDateChooser = new JDateChooser();
        tgttJDateChooser.setDate(new Date());

        tgttTimeComboBox = new JComboBox<>(DateTimeUtil.timeOptions);
        tgttTimeComboBox.setSelectedItem("05:15");

        tgttPanel.add(tgttJDateChooser);
        tgttPanel.add(tgttTimeComboBox);

        gbc.gridx = 1;
        gbc.gridy = startRow + 5;
        contentPanel.add(tgttPanel, gbc);

        trangthaiComboBox = new JComboBox<>(new String[]{"Chưa khởi hành", "Đang khởi hành", "Hoàn Thành", "Đã hủy"});
        gbc.gridx = 1;
        gbc.gridy = startRow + 6;
        contentPanel.add(trangthaiComboBox, gbc);

        gbc.gridwidth = 1;
        if ("create".equals(type)) {
            ok = handleComponents.addButtonGBL(contentPanel, "THÊM", 0, startRow + 7, gbc);
            cancel = handleComponents.addButtonGBL(contentPanel, "HỦY", 1, startRow + 7, gbc);
        } else if ("update".equals(type)) {
            ok = handleComponents.addButtonGBL(contentPanel, "CẬP NHẬT", 0, startRow + 7, gbc);
            cancel = handleComponents.addButtonGBL(contentPanel, "HỦY", 1, startRow + 7, gbc);
        } else {
            cancel = handleComponents.addButtonGBL(contentPanel, "ĐÓNG", 0, startRow + 7, gbc);
        }

        if (ok != null) {ok.addActionListener(controller);}
        if (cancel != null) {cancel.addActionListener(e -> dispose());}
        this.add(contentPanel);
    }


    private String setTitleType() {
        if (type == null) return "Lịch Trình";
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
                break;
            case "update":
                editEnabled(true);
                if (machuyenTextField != null)
                    loadLichTrinhData();
                break;
            case "detail":
                editEnabled(false);
                loadLichTrinhData();
                break;
            default:
                System.err.println("Lỗi ròi, kiểm tra checkButtonClicked() đi: " + type);
        }
    }

    public void editEnabled(boolean enabled) {
        if (machuyenTextField != null) machuyenTextField.setEnabled(false); // Luôn disable mã chuyến
        if (manvComboBox != null) manvComboBox.setEnabled(enabled);
        if (matauComboBox != null) matauComboBox.setEnabled(enabled);
        if (matuyenComboBox != null) matuyenComboBox.setEnabled(enabled);
        if (huongdiComboBox != null) huongdiComboBox.setEnabled(enabled);
        if (tgkhJDateChooser != null) tgkhJDateChooser.setEnabled(enabled);
        if (tgkhTimeComboBox != null) tgkhTimeComboBox.setEnabled(enabled);
        if (tgttJDateChooser != null) tgttJDateChooser.setEnabled(enabled);
        if (tgttTimeComboBox != null) tgttTimeComboBox.setEnabled(enabled);
        if (trangthaiComboBox != null) trangthaiComboBox.setEnabled(enabled);
    }

    private void loadLichTrinhData() {
        LichTrinhModel selected = (lichTrinhModel != null) ? lichTrinhModel : lichTrinh.getSelectedLichTrinh();
        if (selected != null) {
            if (machuyenTextField != null) machuyenTextField.setText(String.valueOf(selected.getMachuyen()));
            ComboBoxUtil.selectItemInComboBox(manvComboBox, manvComboBoxModel,
                    nv -> ((NhanVienModel)nv).getManv() == selected.getManv());
            ComboBoxUtil.selectItemInComboBox(matauComboBox, matauComboBoxModel,
                    tau -> tau.getMatau() == selected.getMatau());
            ComboBoxUtil.selectItemInComboBox(matuyenComboBox, matuyenComboBoxModel,
                    tuyen -> ((TuyenDuongModel)tuyen).getMatuyen() == selected.getMatuyen());
            huongdiComboBox.setSelectedIndex(selected.isHuongdi() ? 0 : 1);

            DateTimeUtil.setDateTime(tgkhJDateChooser, tgkhTimeComboBox, selected.getTgkhoihanh());
            DateTimeUtil.setDateTime(tgttJDateChooser, tgttTimeComboBox, selected.getTgdenthucte());

            trangthaiComboBox.setSelectedItem(selected.getTrangthailichtrinh());
        } else JOptionPane.showMessageDialog(this, "Không thể tải dữ liệu lịch trình!", "Lỗi", JOptionPane.ERROR_MESSAGE);
    }

    // Phương thức lấy dữ liệu từ form
    public LichTrinhModel getLichTrinhFromForm() {
        try {
            String machuyenStr = (machuyenTextField != null) ? machuyenTextField.getText().trim() : "0";
            int machuyen = "".equals(machuyenStr) ? 0 : Integer.parseInt(machuyenStr);

            int manv = ((NhanVienModel) manvComboBox.getSelectedItem()).getManv();
            int matuyen = ((TuyenDuongModel) matuyenComboBox.getSelectedItem()).getMatuyen();
            int matau = ((TauModel) matauComboBox.getSelectedItem()).getMatau();
            boolean huongdi = huongdiComboBox.getSelectedIndex() == 0;

            LocalDateTime tgkh = DateTimeUtil.getTimeComponents(tgkhJDateChooser, tgkhTimeComboBox);
            LocalDateTime tgtt = DateTimeUtil.getTimeComponents(tgttJDateChooser, tgttTimeComboBox);
            String trangthai = (String) trangthaiComboBox.getSelectedItem();

            return new LichTrinhModel(machuyen, manv, matau, matuyen, huongdi, tgkh, tgtt, trangthai);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi nhập liệu: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}