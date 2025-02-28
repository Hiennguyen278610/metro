package org.metro.view.Panel;

import javax.swing.*;
import java.awt.*;

public class KhachHang extends JPanel {

    Color BackgroundColor = new Color(0, 2, 2);

    public void initComponent() {
        this.setBackground(BackgroundColor);
        this.setLayout(new BorderLayout(0, 0));
        JLabel khachHangLabel = new JLabel("Khách hàng", JLabel.CENTER); // Đảm bảo chữ hiển thị rõ
        khachHangLabel.setForeground(Color.WHITE); // Đặt màu chữ để nổi trên nền tối
        khachHangLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        this.add(khachHangLabel, BorderLayout.CENTER);
    }
    public KhachHang(){
        initComponent();
    }
}
