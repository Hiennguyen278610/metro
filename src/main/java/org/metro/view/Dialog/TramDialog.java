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
    private InputField maField, tenField, addressField, xField, yField, chiphi;

    private JPanel contentPanel, bottomPanel;
    private TramModel tramModel;
    private Tram tram;
    private TramController action;

    public TramDialog(JFrame parent, String title, String type, Tram tram) {
        super(parent, title, true);
        this.tram = tram;
        action = new TramController(tram, this);
        tenField = new InputField("Tên trạm", 300, 60);
        addressField = new InputField("Địa chỉ", 300, 60);
        xField = new InputField("Nhập tọa độ x", 300, 60);
        yField = new InputField("Nhập tọa độ y", 300, 60);
        chiphi = new InputField("Chi phí", 300, 60);
        init(type);
    }

    public TramDialog(JFrame parent, String title, String type, Tram tram, TramModel tramModel) {
        super(parent, title, true);
        this.tram = tram;
        action = new TramController(tram, this);
        this.tramModel = tramModel;
        if(type.equals("detail")){
            maField = new InputField("Mã trạm:", String.valueOf(tramModel.getMatram()), 590, 10,"tram");
            tenField = new InputField("Tên trạm:", tramModel.getTentram(), 590, 10,"tram");
            addressField = new InputField("Địa chỉ:",tramModel.getDiachi(), 590, 10,"tram");
            xField = new InputField("Nhập tọa độ x:", String.valueOf(tramModel.getX()),590, 10,"tram");
            yField = new InputField("Nhập tọa độ y:", String.valueOf(tramModel.getY()),590, 10,"tram");
            chiphi = new InputField("Chi phí:", String.valueOf(tramModel.getChiphitram()),590, 10,"tram");
        }else{
            tenField = new InputField("Tên trạm", 300, 60);
            addressField = new InputField("Địa chỉ", 300, 60);
            xField = new InputField("Nhập tọa độ x", 300, 60);
            yField = new InputField("Nhập tọa độ y", 300, 60);
            chiphi = new InputField("Chi phí", 300, 60);
        }
        init(type);
    }

    public void init(String type) {
        if(type.equals("detail")){
            this.setSize(600, 390);
        }
        else{
            this.setSize(450,370);
        }
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout(0, 0));

        contentPanel = new JPanel();
        contentPanel.setPreferredSize(new Dimension(400, 330));
        contentPanel.setBorder(BorderFactory.createLineBorder(Color.white));
        if(type.equals("detail")){
            contentPanel.setLayout(new GridLayout(6, 1));
            contentPanel.add(maField);
        }else{
            contentPanel.setLayout(new GridLayout(5, 1));
        }    

        contentPanel.add(tenField);
        contentPanel.add(addressField);
        contentPanel.add(xField);
        contentPanel.add(yField);
        contentPanel.add(chiphi);

        bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.setBorder(new EmptyBorder(5, 0, 5, 0));
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.setPreferredSize(new Dimension(400, 70));
        // bottomPanel.setBorder(new EmptyBorder(10, 0, 0, 0));

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
        btnExit.setFont(font);
        btnExit.setFocusPainted(false);
        btnExit.addMouseListener(action);

        switch (type) {
            case "create":
                bottomPanel.add(btnAdd);
                break;
            case "update":
                bottomPanel.add(btnUpdate);
                // setMaField(tramModel.getMatram());
                setTenField(tramModel.getTentram().trim());
                setAddressField(tramModel.getDiachi().trim());
                setxField(tramModel.getX());
                setyField(tramModel.getY());
                setChiphi(tramModel.getChiphitram());
                break;
            default:
                break;
        }
        bottomPanel.add(btnExit);

        this.add(contentPanel, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    public boolean checkEmpty() {
        if (getTenField().isEmpty()) {
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
        } else if (getChiphi().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chi phí không được rỗng", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    public boolean isValidCost(String input) {
        try {
            if (input.matches("^0\\d+")) {
                // dau + lap lai nhieu lan
                return false;
            }
            double cost = Double.parseDouble(input);
            return cost > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isNumber(String number) {
        try {
            Integer.parseInt(number);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean validInformation() {
        String x = getxField().trim();
        String y = getyField().trim();
        String chiPhi = getChiphi().trim();

        if (!isNumber(x)) {
            showWarning("Tọa độ x phải là một số!");
            return false;
        }

        if (!isNumber(y)) {
            showWarning("Tọa độ y phải là một số!");
            return false;
        }

        if (!isValidCost(chiPhi)) {
            showWarning("Chi phí phải là số thực dương hợp lệ!");
            return false;
        }

        return true;
    }

    private void showWarning(String message) {
        JOptionPane.showMessageDialog(null, message, "THÔNG BÁO", JOptionPane.WARNING_MESSAGE);
    }

    public TramModel getTramModel() {
        if (checkEmpty() && validInformation()) {
            int matram = tramModel.getMatram();
            String tentram = getTenField();
            String diachi = getAddressField();
            int x = Integer.parseInt(getxField());
            int y = Integer.parseInt(getyField());
            double chiphi = Double.parseDouble(getChiphi());
            return new TramModel(matram, tentram, diachi, x, y, chiphi);
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

    public String getChiphi() {
        return this.chiphi.getTxtInput().getText();
    }

    public void setChiphi(double chiphi) {
        this.chiphi.getTxtInput().setText(String.valueOf(chiphi));
    }

}
