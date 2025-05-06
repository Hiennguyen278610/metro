package org.metro.view.Component;

import org.metro.model.PhanQuyenModel.NhomQuyenModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class InputField extends JPanel {
    private JLabel lbContent, lbData;
    private JTextField txtInput;
    private JComboBox<NhomQuyenModel> comboboxnhomquyen;
    private JComboBox<String> comboboxtrangthai;

    public InputField(String label, int width, int height) {
        this.setLayout(new FlowLayout(2, 7, 5));
        this.init(width, height);
        lbContent = new JLabel(label);
        lbContent.setFont(new Font("Segoe UI", Font.BOLD, 15));
        txtInput = new JTextField();
        txtInput.setPreferredSize(new Dimension(180, 40));
        // this.setBorder(BorderFactory.createLineBorder(Color.RED));
        this.add(lbContent);
        this.add(txtInput);
    }

    public InputField(String label, String data, int w, int h) {
        this.setLayout(new FlowLayout(0, 7, 0));
        this.init(w, h);
        // this.setBorder(BorderFactory.createLineBorder(Color.RED));
        lbContent = new JLabel(label);
        lbContent.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        lbData = new JLabel(data);
        lbData.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        lbData.setPreferredSize(new Dimension(150, 30));
        this.add(lbContent);
        this.add(lbData);
    }

    public InputField(String label, ArrayList<NhomQuyenModel> itemCbx, int w, int h) {
        this.setLayout(new GridLayout(2, 1));
        this.init(w, h);
        lbContent = new JLabel(label);
        lbContent.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        comboboxnhomquyen = new JComboBox<>();
        for (NhomQuyenModel NQM : itemCbx) {
            comboboxnhomquyen.addItem(NQM);
        }
        comboboxnhomquyen.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        comboboxnhomquyen.setPreferredSize(new Dimension(150, 30));
        this.add(lbContent);
        this.add(comboboxnhomquyen);
    }

    public InputField(String label, String[] item, int w, int h) {
        this.setLayout(new GridLayout(2, 1));
        this.init(w, h);
        lbContent = new JLabel(label);
        lbContent.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        comboboxtrangthai = new JComboBox<>(item);
        comboboxtrangthai.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        comboboxtrangthai.setPreferredSize(new Dimension(150, 30));
        this.add(lbContent);
        this.add(comboboxtrangthai);
    }

    public void init(int width, int height) {
        this.setBackground(Color.WHITE);
        this.setBorder(new EmptyBorder(0, 10, 5, 10));
        this.setPreferredSize(new Dimension(width, height));
    }

    public JTextField getTxtInput() {
        return txtInput;
    }

    public void setTxtInput(JTextField txtInput) {
        this.txtInput = txtInput;
    }

    public JLabel getLbContent() {
        return lbContent;
    }

    public void setLbContent(JLabel lbContent) {
        this.lbContent = lbContent;
    }

    public JLabel getLbData() {
        return lbData;
    }

    public void setLbData(JLabel lbData) {
        this.lbData = lbData;
    }

    public JComboBox<NhomQuyenModel> getComboboxnhomquyen() {
        return comboboxnhomquyen;
    }

    public void setComboboxnhomquyen(JComboBox<NhomQuyenModel> comboboxnhomquyen) {
        this.comboboxnhomquyen = comboboxnhomquyen;
    }

    public JComboBox<String> getComboboxtrangthai() {
        return comboboxtrangthai;
    }

    public void setComboboxtrangthai(JComboBox<String> comboboxtrangthai) {
        this.comboboxtrangthai = comboboxtrangthai;
    }

    public void setDisable() {
        txtInput.setEnabled(false);// khong the thao tac va khong nhan su kien chuot
        // setEditable(false)//khong the chinh sua, nhan su kien chuot
    }
}
