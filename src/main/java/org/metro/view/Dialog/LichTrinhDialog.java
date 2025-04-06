package org.metro.view.Dialog;

import org.metro.controller.LichTrinhController;
import org.metro.model.LichTrinhModel;
import org.metro.model.NhanVienModel;
import org.metro.service.NhanVienService;
import org.metro.view.Component.handleComponents;
import org.metro.view.Panel.LichTrinh;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class LichTrinhDialog extends JDialog {
    private JTextField machuyenTextField, matauTextField, matuyenTextField;
    private JComboBox<NhanVienModel> manvComboBox;
    private DefaultComboBoxModel<NhanVienModel> manvComboBoxModel;
    private JComboBox<String> huongdiComboBox;
    private JDateChooser tgkhJDateChooser, tgttJDateChooser;
    private JComboBox<String> tgkhTimeComboBox, tgttTimeComboBox; // Thay thế JSpinner bằng JComboBox
    private JComboBox<String> trangthaiComboBox;
    private JButton ok, cancel;
    private String type;
    private JPanel contentPanel;
    private LichTrinh lichTrinh;
    private LichTrinhModel lichTrinhModel;
    private LichTrinhController controller;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // Option thời gian fix cứng như zalo :))))
    private String[] timeOptions = {
            "05:00", "05:15", "05:30", "05:45",
            "06:00", "06:15", "06:30", "06:45",
            "07:00", "07:15", "07:30", "07:45",
            "08:00", "08:15", "08:30", "08:45",
            "09:00", "09:15", "09:30", "09:45",
            "10:00", "10:15", "10:30", "10:45",
            "11:00", "11:15", "11:30", "11:45",
            "12:00", "12:15", "12:30", "12:45",
            "13:00", "13:15", "13:30", "13:45",
            "14:00", "14:15", "14:30", "14:45",
            "15:00", "15:15", "15:30", "15:45",
            "16:00", "16:15", "16:30", "16:45",
            "17:00", "17:15", "17:30", "17:45",
            "18:00", "18:15", "18:30", "18:45",
            "19:00", "19:15", "19:30", "19:45",
            "20:00", "20:15", "20:30", "20:45",
            "21:00", "21:15", "21:30", "21:45",
            "22:00", "22:15", "22:30", "22:45"
    };

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

    public LichTrinhDialog() {
        super();
        this.type = "default";
    }

    private void init() {
        contentPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 20, 5, 20);

        // Chỉ hiển thị Mã chuyến trong trường hợp update hoặc detail
        if (!"create".equals(type)) {
            handleComponents.addLabelGBL(contentPanel, "Mã chuyến:", 0, 0, gbc);
            machuyenTextField = handleComponents.addTextFieldGBL(contentPanel, 20, 1, 0, gbc);
            machuyenTextField.setEnabled(false);
        }

        int startRow = ("create".equals(type)) ? 0 : 1;
        handleComponents.addLabelGBL(contentPanel, "Nhân viên:", 0, startRow, gbc);
        handleComponents.addLabelGBL(contentPanel, "Mã tàu:", 0, startRow + 1, gbc);
        handleComponents.addLabelGBL(contentPanel, "Mã tuyến:", 0, startRow + 2, gbc);
        handleComponents.addLabelGBL(contentPanel, "Hướng đi:", 0, startRow + 3, gbc);
        handleComponents.addLabelGBL(contentPanel, "Thời gian khởi hành:", 0, startRow + 4, gbc);
        handleComponents.addLabelGBL(contentPanel, "Thời gian đến thực tế:", 0, startRow + 5, gbc);
        handleComponents.addLabelGBL(contentPanel, "Trạng thái lịch trình:", 0, startRow + 6, gbc);
        manvComboBoxModel = new DefaultComboBoxModel<>();
        manvComboBox = new JComboBox<>(manvComboBoxModel);
        loadNhanVienData();
        gbc.gridx = 1;
        gbc.gridy = startRow;
        contentPanel.add(manvComboBox, gbc);

        matauTextField = handleComponents.addTextFieldGBL(contentPanel, 20, 1, startRow + 1, gbc);
        matuyenTextField = handleComponents.addTextFieldGBL(contentPanel, 20, 1, startRow + 2, gbc);

        huongdiComboBox = new JComboBox<>(new String[]{"Đi", "Về"});
        gbc.gridx = 1;
        gbc.gridy = startRow + 3;
        contentPanel.add(huongdiComboBox, gbc);

        // Thời gian khởi hành - DateChooser và ComboBox thời gian
        JPanel tgkhPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        tgkhJDateChooser = new JDateChooser();
        tgkhJDateChooser.setDate(new Date());

        tgkhTimeComboBox = new JComboBox<>(timeOptions);
        tgkhTimeComboBox.setSelectedItem("05:00"); // Mặc định

        tgkhPanel.add(tgkhJDateChooser);
        tgkhPanel.add(tgkhTimeComboBox);

        gbc.gridx = 1;
        gbc.gridy = startRow + 4;
        contentPanel.add(tgkhPanel, gbc);

        // Thời gian đến thực tế - DateChooser và ComboBox thời gian
        JPanel tgttPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        tgttJDateChooser = new JDateChooser();
        tgttJDateChooser.setDate(new Date());

        tgttTimeComboBox = new JComboBox<>(timeOptions);
        tgttTimeComboBox.setSelectedItem("05:15"); // Mặc định

        tgttPanel.add(tgttJDateChooser);
        tgttPanel.add(tgttTimeComboBox);

        gbc.gridx = 1;
        gbc.gridy = startRow + 5;
        contentPanel.add(tgttPanel, gbc);

        trangthaiComboBox = new JComboBox<>(new String[]{"Chưa khởi hành", "sẵn sàng", "Đang chạy", "Đã hoàn thành", "Đã hủy"});
        gbc.gridx = 1;
        gbc.gridy = startRow + 6;
        contentPanel.add(trangthaiComboBox, gbc);

        // Buttons
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

        // Add action listeners
        if (ok != null) {ok.addActionListener(controller);}
        if (cancel != null) {cancel.addActionListener(e -> dispose());}
        this.add(contentPanel);
    }

    // Load danh sách nhân viên từ service
    private void loadNhanVienData() {
        manvComboBoxModel.removeAllElements();
        List<NhanVienModel> nhanVienList = NhanVienService.getListnv();
        for (NhanVienModel nv : nhanVienList) {manvComboBoxModel.addElement(nv);}
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
        if (machuyenTextField != null) machuyenTextField.setEnabled(false); // Luôn disable
        if (manvComboBox != null) manvComboBox.setEnabled(enabled);
        if (matauTextField != null) matauTextField.setEnabled(enabled);
        if (matuyenTextField != null) matuyenTextField.setEnabled(enabled);
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
            selectNhanVienInCombobox(selected.getManv());
            matauTextField.setText(String.valueOf(selected.getMatau()));
            matuyenTextField.setText(String.valueOf(selected.getMatuyen()));
            huongdiComboBox.setSelectedIndex(selected.isHuongdi() ? 0 : 1);

            setDateTime(tgkhJDateChooser, tgkhTimeComboBox, selected.getTgkhoihanh());
            setDateTime(tgttJDateChooser, tgttTimeComboBox, selected.getTgdenthucte());

            trangthaiComboBox.setSelectedItem(selected.getTrangthailichtrinh());
        } else JOptionPane.showMessageDialog(this, "Không thể tải dữ liệu lịch trình!", "Lỗi", JOptionPane.ERROR_MESSAGE);
    }

    // Phương thức hỗ trợ để chọn nhân viên theo mã
    private void selectNhanVienInCombobox(int manv) {
        for (int i = 0; i < manvComboBoxModel.getSize(); i++) {
            NhanVienModel nv = manvComboBoxModel.getElementAt(i);
            if (nv.getManv() == manv) {
                manvComboBox.setSelectedIndex(i);
                break;
            }
        }
    }

    // Phương thức đặt ngày và giờ cho JDateChooser và JComboBox
    private void setDateTime(JDateChooser dateChooser, JComboBox<String> timeComboBox, LocalDateTime dateTime) {
        if (dateTime == null) {
            dateChooser.setDate(new Date());
            timeComboBox.setSelectedItem("08:00");
            return;
        }

        // Đặt ngày cho JDateChooser
        Date date = Date.from(dateTime.toLocalDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
        dateChooser.setDate(date);

        // Định dạng giờ:phút và tìm phần tử phù hợp nhất trong combo box
        String timeStr = String.format("%02d:%02d", dateTime.getHour(), dateTime.getMinute());

        // Tìm thời gian gần nhất trong danh sách
        boolean found = false;
        for (String option : timeOptions) {
            if (option.equals(timeStr)) {
                timeComboBox.setSelectedItem(option);
                found = true;
                break;
            }
        }

        // Nếu dell có giờ chính xác, chọn giá trị đầu tiên
        if (!found) timeComboBox.setSelectedIndex(0);
    }

    public LichTrinhModel getLichTrinhFromForm() {
        try {
            String machuyenStr = (machuyenTextField != null) ? machuyenTextField.getText().trim() : "0";
            int machuyen = "".equals(machuyenStr) ? 0 : Integer.parseInt(machuyenStr);

            NhanVienModel selectedNV = (NhanVienModel) manvComboBox.getSelectedItem();
            int manv = selectedNV.getManv();

            int matau = Integer.parseInt(matauTextField.getText().trim());
            int matuyen = Integer.parseInt(matuyenTextField.getText().trim());
            boolean huongdi = huongdiComboBox.getSelectedIndex() == 0;

            LocalDateTime tgkh = getDateTimeFromComponents(tgkhJDateChooser, tgkhTimeComboBox);
            LocalDateTime tgtt = getDateTimeFromComponents(tgttJDateChooser, tgttTimeComboBox);
            String trangthai = (String) trangthaiComboBox.getSelectedItem();

            return new LichTrinhModel(machuyen, manv, matau, matuyen, huongdi, tgkh, tgtt, trangthai);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi nhập liệu: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    // Phương thức để lấy LocalDateTime từ JDateChooser và JComboBox
    private LocalDateTime getDateTimeFromComponents(JDateChooser dateChooser, JComboBox<String> timeComboBox) {
        try {
            Date selectedDate = dateChooser.getDate();
            if (selectedDate == null) {
                throw new IllegalArgumentException("Vui lòng chọn ngày");
            }

            // Lấy ngày
            LocalDate date = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            // Lấy giờ từ combo box
            String timeString = (String) timeComboBox.getSelectedItem();
            String[] timeParts = timeString.split(":");
            int hour = Integer.parseInt(timeParts[0]);
            int minute = Integer.parseInt(timeParts[1]);

            // Kết hợp ngày và giờ thành LocalDateTime
            return LocalDateTime.of(date, LocalTime.of(hour, minute, 0));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Không thể chuyển đổi thời gian", e);
        }
    }

    // Getters để cho có :)))))
    public JTextField getMachuyenTextField() {return machuyenTextField;}

    public JComboBox<NhanVienModel> getManvComboBox() {return manvComboBox;}

    public JTextField getMatauTextField() {return matauTextField;}

    public JTextField getMatuyenTextField() {return matuyenTextField;}

    public JComboBox<String> getHuongdiComboBox() {return huongdiComboBox;}

    public JDateChooser getTgkhJDateChooser() {return tgkhJDateChooser;}

    public JComboBox<String> getTgkhTimeComboBox() {return tgkhTimeComboBox;}

    public JDateChooser getTgttJDateChooser() {return tgttJDateChooser;}

    public JComboBox<String> getTgttTimeComboBox() {return tgttTimeComboBox;}

    public JComboBox<String> getTrangthaiComboBox() {return trangthaiComboBox;}

    public JButton getOk() {return ok;}

    public LichTrinh getLichTrinh() {return lichTrinh;}

    public LichTrinhModel getLichTrinhModel() {return lichTrinhModel;}
}