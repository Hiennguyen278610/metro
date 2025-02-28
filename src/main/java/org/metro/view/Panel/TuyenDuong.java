package org.metro.view.Panel;

import javax.swing.*;
import java.awt.*;

public class TuyenDuong extends JPanel {
    Color BackgroundColor = new Color(0, 2, 2);

    public void initComponent() {
        this.setBackground(BackgroundColor);
        this.setLayout(new BorderLayout(0, 0));
        JLabel tuyenDuongLabel = new JLabel("Tuyen Duong", JLabel.CENTER); // Đảm bảo chữ hiển thị rõ
        tuyenDuongLabel.setForeground(Color.WHITE); // Đặt màu chữ để nổi trên nền tối
        tuyenDuongLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        this.add(tuyenDuongLabel, BorderLayout.CENTER);
    }

    public TuyenDuong() {
        initComponent();
    }
}
