package org.metro.view.Panel;

import javax.swing.*;
import java.awt.*;

public class ThongKe extends JPanel{
    Color BackgroundColor = new Color(0, 2, 2);

    public void initComponent() {
        this.setBackground(BackgroundColor);
        this.setLayout(new BorderLayout(0, 0));
        JLabel thongKeLabel = new JLabel("Thong Ke", JLabel.CENTER); // Đảm bảo chữ hiển thị rõ
        thongKeLabel.setForeground(Color.WHITE); // Đặt màu chữ để nổi trên nền tối
        thongKeLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        this.add(thongKeLabel, BorderLayout.CENTER);
    }

    public ThongKe() {
        initComponent();
    }
}
