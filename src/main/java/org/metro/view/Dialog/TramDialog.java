package org.metro.view.Dialog;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import org.metro.controller.TramController;
import org.metro.model.TramModel;
import org.metro.view.Component.InputField;
import org.metro.view.Panel.Tram;

import java.awt.*;

public class TramDialog extends JDialog {
    private JButton btnAdd, btnExit, btnUpdate;
    private InputField maField, tenField, addressField, xField, yField;
    private JPanel contentPanel, bottomPanel;
    private TramModel tramModel;
    private Tram tram;
    private TramController action;

    public TramDialog(JFrame parent, String title, String type, Tram tram) {
        super(parent, title, true);
        this.tram = tram;
        action = new TramController(tram, this);
        tenField = new InputField("Tên trạm", 300, 50);
        addressField = new InputField("Địa chỉ", 300, 50);
        xField = new InputField("Nhập tọa độ x", 300, 50);
        yField = new InputField("Nhập tọa độ y", 300, 50);
        init(type);
    }

    public TramDialog(JFrame parent, String title, String type, Tram tram, TramModel tramModel) {
        super(parent, title, true);
        this.tram = tram;
        action = new TramController(tram, this);
        this.tramModel = tramModel;
        maField = new InputField("Mã trạm", String.valueOf(tramModel.getMatram()), 300, 50);
        tenField = new InputField("Tên trạm", tramModel.getTentram(), 300, 50);
        addressField = new InputField("Địa chỉ", tramModel.getDiachi(), 300, 50);
        xField = new InputField("Nhập tọa độ x", String.valueOf(tramModel.getX()), 300, 50);
        yField = new InputField("Nhập tọa độ y", String.valueOf(tramModel.getY()), 300, 50);
        init(type);
    }

    public void init(String type) {
        this.setSize(400, 300);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout(0, 0));

        contentPanel = new JPanel();
        contentPanel.setPreferredSize(new Dimension(400, 250));
        contentPanel.setLayout(new GridLayout(5, 1));

        if (maField != null) {
            contentPanel.add(maField);
        }
        contentPanel.add(tenField);
        contentPanel.add(addressField);
        contentPanel.add(xField);
        contentPanel.add(yField);

        bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.setBorder(new EmptyBorder(5, 0, 5, 0));
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.setPreferredSize(new Dimension(400, 50));
        bottomPanel.setBorder(new EmptyBorder(10, 0, 0, 0));

        Font font = new Font("Segoe UI", Font.BOLD, 16);

        btnAdd = new JButton("Thêm");
        btnAdd.setFont(font);
        btnAdd.setFocusPainted(false);
        // btnAdd.addMouseListener(action);

        btnUpdate = new JButton("Cập nhật");
        btnUpdate.setFont(font);
        btnUpdate.setFocusPainted(false);
        // btnUpdate.addMouseListener(action);

        btnExit = new JButton("Thoát");
        btnExit.setFont(font);
        btnExit.setFocusPainted(false);
        // btnExit.addMouseListener(action);

        switch (type) {
            case "create":
                bottomPanel.add(btnAdd);
                break;
            case "update":
                bottomPanel.add(btnUpdate);
            default:
                break;
        }
        bottomPanel.add(btnExit);

        this.add(contentPanel, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    public boolean validation() {
        if (getMaField().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã trạm không được rỗng", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return false;
        } else if (getTenField().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên trạm không được rỗng", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return false;
        } else if (getAddressField().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Địa chỉ không được rỗng", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return false;
        } else if (getxField().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tọa độ x không được rỗng", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return false;
        } else if (getyField().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tọa độ y không được rỗng", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
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

    public String getMaField() {
        return maField.getTxtInput().getText();
    }

    public void setMaField(int maField) {
        this.maField.getTxtInput().setText(String.valueOf(maField));
    }

    public String getTenField() {
        return tenField.getTxtInput().getText();
    }

    public void setTenField(String tenField) {
        this.tenField.getTxtInput().setText(tenField);
    }

    public String getAddressField() {
        return addressField.getTxtInput().getText();
    }

    public void setAddressField(String addressField) {
        this.addressField.getTxtInput().setText(addressField);
    }

    public String getxField() {
        return xField.getTxtInput().getText();
    }

    public void setxField(int xField) {
        this.xField.getTxtInput().setText(String.valueOf(xField));
    }

    public String getyField() {
        return yField.getTxtInput().getText();
    }

    public void setyField(int yField) {
        this.yField.getTxtInput().setText(String.valueOf(yField));
    }
}
