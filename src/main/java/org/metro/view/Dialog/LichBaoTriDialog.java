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
    private InputField statusField, matauField, mabaotriField, timeField, createAt;

    private JPanel contentPanel, bottomPanel;
    private LichBaoTriModel lbtModel;
    private JTextField trangthai;
    private LichBaoTri lbt;
    private LichBaoTriController action;

    public LichBaoTriDialog(JFrame parent, String title, String type, LichBaoTri lbt) {
        super(parent, title, true);
        this.lbt = lbt;
        action = new LichBaoTriController(lbt, this);
        matauField = new InputField("Mã tàu", 200, 70);
        statusField = new InputField("Trạng thái bảo trì", 200, 70);
        timeField = new InputField("Ngày bảo trì", 200, 70);
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
            createAt = new InputField("Ngày tạo", lbtModel.convertLocalDateTime(), 200, 10);
        } else if (type.equals("update")) {
            matauField = new InputField("Mã tàu", 200, 70);
            this.setMatauField(Integer.toString(lbtModel.getMatau()));
            statusField = new InputField("Trạng thái bảo trì", 200, 70);
            this.setStatusField(lbtModel.getTrangthaibaotri());
            timeField = new InputField("Ngày bảo trì", 200, 70);
            this.setTimeField(lbtModel.convertLocalDate());
        }
        init(type);
    }

    public void init(String type) {
        this.setSize(400, 280);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout(0, 0));
        contentPanel = new JPanel();
        contentPanel.setPreferredSize(new Dimension(400, 220));
        if (type.equals("detail")) {
            contentPanel.setLayout(new GridLayout(5, 1));
            contentPanel.add(mabaotriField);
            contentPanel.add(matauField);
            contentPanel.add(timeField);
            contentPanel.add(statusField);
            contentPanel.add(createAt);
        } else {
            contentPanel.setLayout(new GridLayout(3, 1));
            contentPanel.add(matauField);
            contentPanel.add(timeField);
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

    public boolean checkMaTau(String ma) {
        List<TauModel> dsTau = new TauDAO().selectAll();
        for (TauModel tau : dsTau) {
            if (tau.getMatau().equals(ma)) {
                return true;
            }
        }
        return false;
    }

    public boolean validation() {
        if (matauField.getTxtInput().getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã tàu không được rỗng", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return false;
        } else if (!checkMaTau(matauField.getTxtInput().getText())) {
            JOptionPane.showMessageDialog(this, "Mã tàu không hợp lệ!Hãy kiểm tra và nhập lại", "Cảnh báo",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        } else if (statusField.getTxtInput().getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Trạng thái bảo trì không được rỗng", "Cảnh báo",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    public LichBaoTriModel getLichBaoTriModel() {
        if (validation()) {
            int mabaotri = lbtModel.getMabaotri();
            int matau = Integer.parseInt(getMatauField());
            LocalDate ngaybaotri = LocalDate.parse(getTimeField(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            String trangthaibaotri = getStatusField();
            LocalDateTime ngaytao = lbtModel.getNgaytao();
            return new LichBaoTriModel(mabaotri, matau, ngaybaotri, trangthaibaotri, ngaytao);
        }
        return null;
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
}
