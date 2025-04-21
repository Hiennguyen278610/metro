package org.metro.view.Dialog;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import org.metro.view.Component.InputField;
import org.metro.view.Panel.LichBaoTri;

import com.google.protobuf.Empty;

import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.metro.DAO.TauDAO;
import org.metro.controller.LichBaoTriController;
import org.metro.model.*;

public class LichBaoTriDialog extends JDialog {
    private JButton btnAdd, btnExit, btnUpdate;
    private InputField statusField, matauField, mabaotriField, timeField, createAt, chiphibaotri;

    private JPanel contentPanel, bottomPanel;
    private LichBaoTriModel lbtModel;
    private JTextField trangthai;
    private LichBaoTri lbt;
    private LichBaoTriController action;

    public LichBaoTriDialog(JFrame parent, String title, String type, LichBaoTri lbt) {
        super(parent, title, true);
        this.lbt = lbt;
        action = new LichBaoTriController(lbt, this);
        matauField = new InputField("Mã tàu", 200, 50);
        statusField = new InputField("Trạng thái bảo trì", 200, 50);
        timeField = new InputField("Ngày bảo trì", 200, 50);
        chiphibaotri = new InputField("Chi phí bảo trì", 200, 50);
        init(type);
    }

    public LichBaoTriDialog(JFrame parent, String title, String type, LichBaoTriModel lbtModel, LichBaoTri lbt) {
        super(parent, title, true);
        this.lbtModel = lbtModel;
        this.lbt = lbt;
        action = new LichBaoTriController(lbt, this);
        if (type.equals("detail")) {
            mabaotriField = new InputField("Mã bảo trì:", Integer.toString(lbtModel.getMabaotri()), 250, 10);
            matauField = new InputField("Mã tàu:", Integer.toString(lbtModel.getMatau()), 250, 10);
            timeField = new InputField("Ngày bảo trì:", lbtModel.convertLocalDate(), 250, 10);
            statusField = new InputField("Trạng thái bảo trì:", lbtModel.getTrangthaibaotri(), 250, 10);
            chiphibaotri = new InputField("Chi phí bảo trì: ", String.valueOf(lbtModel.getChiphibaotri()), 250, 10);
            createAt = new InputField("Ngày tạo", lbtModel.convertLocalDateTime(), 200, 10);
        } else if (type.equals("update")) {
            matauField = new InputField("Mã tàu", 200, 50);
            this.setMatauField(Integer.toString(lbtModel.getMatau()));
            statusField = new InputField("Trạng thái bảo trì", 200, 50);
            this.setStatusField(lbtModel.getTrangthaibaotri());
            timeField = new InputField("Ngày bảo trì", 200, 50);
            this.setTimeField(lbtModel.convertLocalDate());
            chiphibaotri = new InputField("Chi phí bảo trì", 200, 50);
            this.setChiphibaotri(String.valueOf(lbtModel.getChiphibaotri()));
        }
        init(type);
    }

    public void init(String type) {
        this.setSize(400, 280);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout(0, 0));
        contentPanel = new JPanel();
        contentPanel.setPreferredSize(new Dimension(400, 240));
        if (type.equals("detail")) {
            contentPanel.setLayout(new GridLayout(6, 1));
            contentPanel.add(mabaotriField);
            contentPanel.add(matauField);
            contentPanel.add(timeField);
            contentPanel.add(statusField);
            contentPanel.add(chiphibaotri);
            contentPanel.add(createAt);
        } else {
            contentPanel.setLayout(new GridLayout(4, 1));
            contentPanel.add(matauField);
            contentPanel.add(timeField);
            contentPanel.add(chiphibaotri);
            contentPanel.add(statusField);
        }

        bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.setBorder(new EmptyBorder(5, 0, 5, 0));
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.setPreferredSize(new Dimension(400, 60));
        bottomPanel.setBorder(new EmptyBorder(10, 0, 0, 0));

        Font font = new Font("Segoe UI", Font.BOLD, 16);

        btnAdd = new JButton("Thêm");
        btnAdd.setFont(font);
        btnAdd.setFocusPainted(false);
        btnAdd.addMouseListener(action);

        btnUpdate = new JButton("Cập nhật");
        btnUpdate.setFont(font);
        btnUpdate.setFocusPainted(false);
        btnUpdate.addMouseListener(action);

        btnExit = new JButton("Thoát");
        btnExit.setBackground(Color.RED);
        btnExit.setFont(font);
        btnExit.setFocusPainted(false);
        btnExit.addMouseListener(action);

        switch (type) {
            case "create":
                bottomPanel.add(btnAdd);
                break;
            case "update":
                bottomPanel.add(btnUpdate);
                break;
            case "view":
                matauField.setDisable();
                statusField.setDisable();
            default:
                break;
        }
        bottomPanel.add(btnExit);

        this.add(contentPanel, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    public boolean checkMaTau(int ma) {
        List<TauModel> dsTau = new TauDAO().selectAll();
        for (TauModel tau : dsTau) {
            if (tau.getMatau() == ma) {
                return true;
            }
        }
        return false;
    }

    public boolean checkEmpty() {
        // Kiểm tra mã tàu
        if (matauField.getTxtInput().getText().trim().isEmpty()) {
            showWarning("Mã tàu không được rỗng");
            return false;
        }

        // Kiểm tra ngày bảo trì
        if (timeField.getTxtInput().getText().trim().isEmpty()) {
            showWarning("Ngày bảo trì không được rỗng");
            return false;
        }

        // Kiểm tra trạng thái
        if (statusField.getTxtInput().getText().trim().isEmpty()) {
            showWarning("Trạng thái bảo trì không được rỗng");
            return false;
        }

        return true;
    }

    public boolean isNumber(String number) {
        try {
            Integer.parseInt(number.trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean validInformation() {
        try {
            String matau = matauField.getTxtInput().getText().trim();
            String trangthai = statusField.getTxtInput().getText().trim();
            String ngayBaoTri = timeField.getTxtInput().getText().trim();

            // Kiểm tra mã tàu
            if (!isNumber(matau)) {
                showWarning("Mã tàu phải là một số!");
                return false;
            }

            // Kiểm tra trạng thái (không được là số)
            if (isNumber(trangthai)) {
                showWarning("Trạng thái không hợp lệ!");
                return false;
            }

            // Kiểm tra định dạng ngày
            if (!isValidDate(ngayBaoTri)) {
                showWarning("Ngày bảo trì không hợp lệ (dd/MM/yyyy)");
                return false;
            }

            return true;
        } catch (Exception e) {
            showWarning("Lỗi khi kiểm tra thông tin: " + e.getMessage());
            return false;
        }
    }

    private boolean isValidDate(String dateStr) {
        try {
            String normalizedDate = chuanHoaNgay(dateStr);
            LocalDate.parse(normalizedDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String chuanHoaNgay(String date) {
        if (date == null || date.trim().isEmpty()) {
            return date;
        }
        date = date.trim();
        String[] parts = date.split("/");
        if (parts.length != 3) {
            return date;
        }

        // Chuẩn hóa ngày và tháng
        if (parts[0].length() == 1) {
            parts[0] = "0" + parts[0];
        }
        if (parts[1].length() == 1) {
            parts[1] = "0" + parts[1];
        }

        return parts[0] + "/" + parts[1] + "/" + parts[2];
    }

    public LichBaoTriModel getLichBaoTriModel() {
        try {
            if (!checkEmpty() || !validInformation()) {
                return null;
            }

            int matau = Integer.parseInt(matauField.getTxtInput().getText().trim());
            String ngayBaoTriStr = timeField.getTxtInput().getText().trim();
            String chuanHoaNagayBaoTri = chuanHoaNgay(ngayBaoTriStr);
            LocalDate ngaybaotri = LocalDate.parse(chuanHoaNagayBaoTri, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            String trangthaibaotri = statusField.getTxtInput().getText().trim();
            double chiphi = Double.parseDouble(chiphibaotri.getTxtInput().getText().trim());
            return new LichBaoTriModel(
                    lbtModel.getMabaotri(),
                    matau,
                    ngaybaotri,
                    trangthaibaotri,
                    lbtModel.getNgaytao(),
                    chiphi);
        } catch (Exception e) {
            showWarning("Lỗi khi tạo đối tượng: " + e.getMessage());
            return null;
        }
    }

    private void showWarning(String message) {
        JOptionPane.showMessageDialog(
                null,
                message,
                "THÔNG BÁO",
                JOptionPane.WARNING_MESSAGE);
    }

    public JButton getBtnAdd() {
        return btnAdd;
    }

    public void setBtnAdd(JButton btnAdd) {
        this.btnAdd = btnAdd;
    }

    public JButton getBtnExit() {
        return btnExit;
    }

    public void setBtnExit(JButton btnExit) {
        this.btnExit = btnExit;
    }

    public JButton getBtnUpdate() {
        return btnUpdate;
    }

    public void setBtnUpdate(JButton btnUpdate) {
        this.btnUpdate = btnUpdate;
    }

    public String getStatusField() {
        return statusField.getTxtInput().getText();
    }

    public void setStatusField(String status) {
        this.statusField.getTxtInput().setText(status);
    }

    public String getMatauField() {
        return matauField.getTxtInput().getText();
    }

    public void setMatauField(String matau) {
        this.matauField.getTxtInput().setText(matau);
    }

    public String getTimeField() {
        return timeField.getTxtInput().getText();
    }

    public void setTimeField(String time) {
        this.timeField.getTxtInput().setText(time);
    }

    public String getChiphibaotri() {
        return chiphibaotri.getTxtInput().getText();
    }

    public void setChiphibaotri(String chiphibaotri) {
        this.chiphibaotri.getTxtInput().setText(chiphibaotri);
        ;
    }
}
