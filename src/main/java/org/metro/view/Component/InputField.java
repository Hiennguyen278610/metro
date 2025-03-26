package org.metro.view.Component;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

public class InputField extends JPanel {
    private JLabel lbContent, lbData;
    private JTextField txtInput;

    public InputField(String label, int width, int height) {
        this.setLayout(new GridLayout(2, 1));
        this.init(width, height);
        lbContent = new JLabel(label);
        lbContent.setFont(new Font("Segoe UI", Font.BOLD, 15));
        txtInput = new JTextField();
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

    public void setDisable() {
        txtInput.setEnabled(false);// khong the thao tac va khong nhan su kien chuot
        // setEditable(false)//khong the chinh sua, nhan su kien chuot
    }
}
