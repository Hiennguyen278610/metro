package org.metro.view.Component;

import javax.swing.*;
import java.awt.*;

public class ContentPanel extends JPanel {
    public ContentPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.white);
        // Hiển thị trang chủ mặc định
        JLabel welcomeLabel = new JLabel(
                "<html><div style='text-align: center;'><h1>Hệ thống quản lý Metro</h1><p>Chào mừng bạn đến với hệ thống</p></div></html>",
                SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        add(welcomeLabel, BorderLayout.CENTER);
    }

    public void setContent(JPanel content) {
        removeAll();
        add(content, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
}
