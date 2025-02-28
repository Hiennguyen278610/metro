package org.metro.view.Panel;

import javax.swing.*;
import java.awt.*;

public class TaiKhoan extends JPanel {

    Color BackgroundColor = new Color(0, 2, 2);

    public void initComponent() {
        this.setBackground(BackgroundColor);
        this.setLayout(new BorderLayout(0, 0));
        JLabel taiKhoanLabel = new JLabel("Tai Khoan", JLabel.CENTER); // Đảm bảo chữ hiển thị rõ
        taiKhoanLabel.setForeground(Color.WHITE); // Đặt màu chữ để nổi trên nền tối
        taiKhoanLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        this.add(taiKhoanLabel, BorderLayout.CENTER);
    }

    public TaiKhoan() {
        initComponent();
    }
}