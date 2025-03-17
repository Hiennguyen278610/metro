package org.metro.view.Component;

import org.metro.view.Dialog.NhanVienDialog;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;

import javax.swing.*;

public class handleComponents {

    //xu ly label + gridbadLayout
    public static void addLabelGBL(JPanel panel, String nameLabel, int x, int y, GridBagConstraints gbc) {
        JLabel lb = new JLabel(nameLabel);
        lb.setFont(new Font("Segoe UI", Font.PLAIN, 22));
        lb.setForeground(Color.BLACK);
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(lb,gbc);
    }

    //xu ly textnhap + gridbadLayout
    public static JTextField addTextFieldGBL(JPanel panel,int width,int x, int y ,GridBagConstraints gbc) {
        JTextField text = new JTextField(width);
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(text,gbc);
        return text;
    }

    //xuly combobox + gridbadlayout
    public static JComboBox<String> addComboBoxGBL(JPanel panel,String[] combo,int x, int y ,GridBagConstraints gbc) {
        JComboBox<String> cbb = new JComboBox(combo);
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(cbb,gbc);
        return cbb;
    }

    //xu ly button + gridbadLayout
    public static JButton addButtonGBL(JPanel panel,String nameButton,int x,int y,GridBagConstraints gbc) {
        JButton button = new JButton(nameButton);
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(button,gbc);
        return button;
    }
}
