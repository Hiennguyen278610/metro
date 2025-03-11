package org.metro.view.Component;

import java.awt.GridBagConstraints;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class handleComponents {
    
    //xu ly label + gridbadLayout
    public static void addLayout(JPanel panel,String nameLabel, int x, int y, GridBagConstraints gbc) {
        JLabel lb = new JLabel(nameLabel);
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(lb,gbc);
    }

    //xu ly textnhap + gridbadLayout
    public static JTextField addTextField(JPanel panel,int x, int y ,GridBagConstraints gbc) {
        JTextField text = new JTextField(10);
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(text,gbc);
        return text;
    }
}
