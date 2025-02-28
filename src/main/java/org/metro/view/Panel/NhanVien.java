package org.metro.view.Panel;

import javax.swing.*;
import java.awt.*;

public class NhanVien extends JPanel {

    Color BackgroundColor = new Color(0, 2, 2);

    public void initComponent() {
        this.setBackground(BackgroundColor);
        this.setLayout(new BorderLayout(0, 0));
        JLabel nhanvienLabel = new JLabel("Nhan vien", JLabel.CENTER); // Đảm bảo chữ hiển thị rõ
        nhanvienLabel.setForeground(Color.WHITE); // Đặt màu chữ để nổi trên nền tối
        nhanvienLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        this.add(nhanvienLabel, BorderLayout.CENTER);
    }

    public NhanVien() {
        initComponent();
    }
}