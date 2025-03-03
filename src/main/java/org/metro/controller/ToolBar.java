package org.metro.controller;

import com.formdev.flatlaf.extras.FlatSVGIcon;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

public class ToolBar extends JButton {
    String permisson;
    JLabel lblIcon, lblContent;

    public ToolBar(String text, String linkIcon, String permisson) {
        this.permisson = permisson;
        this.setPreferredSize(new Dimension(25, 25));
        this.setBackground(new Color(240, 248, 255));
        this.setOpaque(true);

        this.setLayout(new BorderLayout());

        lblIcon = new JLabel();
        URL iconUrl = getClass().getResource("/svg/" + linkIcon);
        if (iconUrl != null) {
            lblIcon.setIcon(new FlatSVGIcon(iconUrl).derive(20, 20));
        } else {
            System.err.println("Không tìm thấy icon: /svg/" + linkIcon);
            lblIcon.setText("X");
        }
        lblIcon.setHorizontalAlignment(JLabel.LEFT);
        lblIcon.setBorder(new EmptyBorder(0, 0, 0, 10));
        this.add(lblIcon, BorderLayout.WEST);

        lblContent = new JLabel(text);
        lblContent.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblContent.setForeground(new Color(0, 0, 0));
        this.add(lblContent, BorderLayout.CENTER);


        this.setFocusable(false);
        this.setHorizontalTextPosition(SwingConstants.LEFT);
        this.setVerticalTextPosition(SwingConstants.CENTER);
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                setBackground(new Color(230, 242, 255));
            }
            @Override
            public void mouseExited(MouseEvent evt) {
                setBackground(new Color(240, 248, 255));
            }
        });
    }


    public String getPermisson() {
        return this.permisson;
    }
}