package org.metro.view.Panel;

import javax.swing.*;
import java.awt.*;

public class PhanQuyen extends JPanel {

    Color BackgroundColor = new Color(0, 2, 2);

    public void initComponent() {
        this.setBackground(BackgroundColor);
        this.setLayout(new BorderLayout(0, 0));
        JLabel phanQuyenLabel = new JLabel("Phan Quyen", JLabel.CENTER); // Đảm bảo chữ hiển thị rõ
        phanQuyenLabel.setForeground(Color.WHITE); // Đặt màu chữ để nổi trên nền tối
        phanQuyenLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        this.add(phanQuyenLabel, BorderLayout.CENTER);
    }

    public PhanQuyen() {
        initComponent();
    }
}