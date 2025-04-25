package org.metro.view.Component;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.*;

public class SelectInput extends JPanel {
    private JLabel lbContent;
    private JComboBox<String> cboChoose;
    private Font font = new Font("Segoe UI", Font.BOLD, 15);

    public SelectInput(String content, String[] arr, int w, int h) {
        this.setLayout(new FlowLayout(2, 10, 5));
        this.setPreferredSize(new Dimension(w, h));
        this.setBackground(Color.white);
        this.lbContent = new JLabel(content);
        this.lbContent.setBackground(Color.white);
        this.lbContent.setFont(font);
        this.add(lbContent);
        // this.setBorder(BorderFactory.createLineBorder(Color.RED));
        cboChoose = new JComboBox<>();
        cboChoose.setModel(new DefaultComboBoxModel<>(arr));
        cboChoose.setPreferredSize(new Dimension(180, 40));
        cboChoose.setBackground(Color.WHITE);
        cboChoose.setCursor(new Cursor(Cursor.HAND_CURSOR));
        // cboChoose.setBorder(null);
        this.add(cboChoose);
    }

    public JLabel getLbContent() {
        return lbContent;
    }

    public void setLbContent(JLabel lbContent) {
        this.lbContent = lbContent;
    }

    public JComboBox<String> getCboChoose() {
        return cboChoose;
    }

    public void setCboChoose(JComboBox<String> cboChoose) {
        this.cboChoose = cboChoose;
    }

}
