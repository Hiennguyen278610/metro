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
    Color DefaultColor = new Color(148,183,205);
    Color HoverFontColor = new Color(255, 255, 255);
    Color HoverBackgroundColor = new Color(80,138,170);

    JLabel lblIcon, pnlContent;

    public itemTaskbar(String linkIcon, String content){
        this.setOpaque(true);
        this.setBackground(DefaultColor);

        this.addMouseListener(this);
        lblIcon = new JLabel();
        lblIcon.setPreferredSize(new Dimension(30, 30));
        lblIcon.setBorder(new EmptyBorder(0, 0, 0, 0));

        // Tải icon từ resources trong classpath
        URL iconUrl = getClass().getResource("/svg/" + linkIcon);
        if (iconUrl != null) {
            lblIcon.setIcon(new FlatSVGIcon(iconUrl).derive(30, 30));
        } else {
            System.err.println("Không tìm thấy icon: /svg/" + linkIcon);
            lblIcon.setText("X"); // Hiển thị placeholder nếu icon không tìm thấy
        }
        this.add(lblIcon);

        pnlContent = new JLabel(content);
        pnlContent.setFont(new Font("Segoe UI", Font.BOLD, 20));
        pnlContent.setBorder(new EmptyBorder(0, 30, 0, 0)); // Tạo khoảng cách 10px bên trái
        pnlContent.setPreferredSize(new Dimension(160, 60));
        pnlContent.setForeground(FontColor);
        this.add(pnlContent);

    }
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        this.setBackground(HoverBackgroundColor);
        pnlContent.setForeground(HoverFontColor);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.setBackground(DefaultColor);
        pnlContent.setForeground(FontColor);
    }

}
