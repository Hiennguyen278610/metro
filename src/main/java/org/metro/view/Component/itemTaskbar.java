package org.metro.view.Component;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

public class itemTaskbar extends JPanel implements MouseListener {
    Color FontColor = new Color(0, 0, 0);
    Color DefaultColor = new Color(148, 183, 205);
    Color HoverFontColor = new Color(255, 255, 255);
    Color HoverBackgroundColor = new Color(80, 138, 170);

    JLabel lblIcon, lblContent;

    public itemTaskbar(String linkIcon, String content) {
        this.setOpaque(true);
        this.setBackground(DefaultColor);
        this.setLayout(new BorderLayout()); // Sử dụng BorderLayout
        this.addMouseListener(this);

        // Icon
        lblIcon = new JLabel();
        lblIcon.setPreferredSize(new Dimension(50, 30));
        lblIcon.setBorder(new EmptyBorder(0, 20, 0, 0));
        URL iconUrl = getClass().getResource("/svg/" + linkIcon);
        if (iconUrl != null) {
            lblIcon.setIcon(new FlatSVGIcon(iconUrl).derive(30, 30));
        } else {
            System.err.println("Không tìm thấy icon: /svg/" + linkIcon);
            lblIcon.setText("X");
        }
        lblIcon.setHorizontalAlignment(JLabel.CENTER);
        this.add(lblIcon, BorderLayout.WEST); // Thêm icon vào WEST

        // Content
        lblContent = new JLabel(content);
        lblContent.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblContent.setForeground(FontColor);
        lblContent.setBorder(new EmptyBorder(0, 40, 0, 0));
        this.add(lblContent, BorderLayout.CENTER); // Thêm nội dung vào CENTER
    }

    // Các phương thức MouseListener giữ nguyên
    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {
        this.setBackground(HoverBackgroundColor);
        lblContent.setForeground(HoverFontColor);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.setBackground(DefaultColor);
        lblContent.setForeground(FontColor);
    }
}