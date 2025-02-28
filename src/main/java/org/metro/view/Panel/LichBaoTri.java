package org.metro.view.Panel;

import javax.swing.*;
import java.awt.*;

public class LichBaoTri extends JPanel {
    Color BackgroundColor = new Color(0, 2, 2);

    public void initComponent() {
        this.setBackground(BackgroundColor);
        this.setLayout(new BorderLayout(0, 0));
        JLabel lichBaoTriLabel = new JLabel("Lich Bao Tri", JLabel.CENTER); // Đảm bảo chữ hiển thị rõ
        lichBaoTriLabel.setForeground(Color.WHITE); // Đặt màu chữ để nổi trên nền tối
        lichBaoTriLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        this.add(lichBaoTriLabel, BorderLayout.CENTER);
    }

    public LichBaoTri() {
        initComponent();
    }
}