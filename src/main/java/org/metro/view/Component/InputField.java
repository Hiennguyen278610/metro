package org.metro.view.Component;

import org.metro.model.PhanQuyenModel.NhomQuyenModel;

import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.util.*;
import java.util.List;

public class InputField extends JPanel {
    private JLabel lbContent, lbData;
    private JTextField txtInput;
    private JComboBox<NhomQuyenModel> comboboxnhomquyen;
    private JComboBox<String> combobox;
    private JDateChooser dateChooser;
    private boolean isDateField;
    private Font font = new Font("Segoe UI", Font.BOLD, 15);
    private Font txtfont = new Font("Segoe UI", Font.PLAIN, 15);

    public InputField(String label, int width, int height) {
        this.setLayout(new FlowLayout(2, 7, 5));
        // this.setLayout(new GridLayout(5, 5));
        this.init(width, height);
        lbContent = new JLabel(label, JLabel.LEFT);
        lbContent.setFont(new Font("Segoe UI", Font.BOLD, 15));
        txtInput = new JTextField();
        txtInput.setPreferredSize(new Dimension(250, 40));
        // this.setBorder(BorderFactory.createLineBorder(Color.RED));
        this.add(lbContent);
        this.add(txtInput);
    }

    public InputField(String label, int width, int height, String txt) {
        this.setLayout(new GridLayout(2, 1));
        this.init(width, height);
        lbContent = new JLabel(label, JLabel.LEFT);
        lbContent.setFont(new Font("Segoe UI", Font.BOLD, 15));
        txtInput = new JTextField();
        txtInput.setPreferredSize(new Dimension(250, 40));
        this.add(lbContent);
        this.add(txtInput);
    }

    public InputField(String label, String data, int w, int h, String type) {
        this.setLayout(new FlowLayout(0, 7, 0));
        this.init(w, h);
        // this.setBorder(BorderFactory.createLineBorder(Color.RED));
        lbContent = new JLabel(label);
        lbContent.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lbData = new JLabel(data);
        lbData.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        if (!type.equals("tram")) {
            lbData.setPreferredSize(new Dimension(250, 30));
        }
        this.add(lbContent);
        this.add(lbData);
    }

    public InputField(String label, ArrayList<NhomQuyenModel> itemCbx, int w, int h) {
        this.setLayout(new GridLayout(2, 1));
        this.init(w, h);
        lbContent = new JLabel(label);
        lbContent.setFont(new Font("Segoe UI", Font.BOLD, 15));
        comboboxnhomquyen = new JComboBox<>();
        for (NhomQuyenModel NQM : itemCbx) {
            comboboxnhomquyen.addItem(NQM);
        }
        comboboxnhomquyen.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        comboboxnhomquyen.setPreferredSize(new Dimension(250, 30));
        this.add(lbContent);
        this.add(comboboxnhomquyen);
        // this.setBorder(BorderFactory.createLineBorder(Color.RED));
    }

    public InputField(String label, String[] cbx, int w, int h) {
        this.setLayout(new GridLayout(2, 1));
        this.init(w, h);
        lbContent = new JLabel(label);
        lbContent.setFont(new Font("Segoe UI", Font.BOLD, 15));
        combobox = new JComboBox<>(cbx);
        combobox.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        combobox.setPreferredSize(new Dimension(250, 30));
        this.add(lbContent);
        this.add(combobox);
    }

    public InputField(String content, int w, int h, boolean isDateField) {
        this.isDateField = isDateField;
        this.setLayout(new FlowLayout(2, 7, 5));
        this.setPreferredSize(new Dimension(w, h));
        this.setBackground(Color.white);
        lbContent = new JLabel(content);
        lbContent.setFont(font);
        this.add(lbContent);
        // JPanel inputPanel = new JPanel(new BorderLayout());
        if (isDateField) {
            dateChooser = new JDateChooser();
            dateChooser.setDateFormatString("dd/MM/yyyy");
            dateChooser.setPreferredSize(new Dimension(w - 120, h - 10));
            dateChooser.setFont(txtfont);
            this.add(dateChooser, BorderLayout.CENTER);
        } else {
            txtInput = new JTextField();
            txtInput.setFont(txtfont);
            txtInput.setPreferredSize(new Dimension(w, h));
            this.add(txtInput, BorderLayout.CENTER);
        }
    }

    public void init(int width, int height) {
        this.setBackground(Color.WHITE);
        this.setBorder(new EmptyBorder(0, 10, 5, 10));
        this.setPreferredSize(new Dimension(width, height));
    }

    public Date getDate() {
        return dateChooser != null ? dateChooser.getDate() : null;
    }

    public void setDate(Date date) {
        if (isDateField && dateChooser != null) {
            dateChooser.setDate(date); // Thiết lập ngày cho JDateChooser
        }
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

    public JComboBox<String> getCombobox() {
        return combobox;
    }

    public void setCombobox(JComboBox<String> combobox) {
        this.combobox = combobox;
    }

    public void setDisable() {
        txtInput.setEnabled(false);// khong the thao tac va khong nhan su kien chuot
        // setEditable(false)//khong the chinh sua, nhan su kien chuot
    }
}
