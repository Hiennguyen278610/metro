package org.metro.view.Panel;

import javax.swing.*;
import java.awt.*;

public class LichTrinh extends JPanel {
    Color BackgroundColor = new Color(0, 2, 2);

    public void initComponent() {
        this.setBackground(BackgroundColor);
        this.setLayout(new BorderLayout(0, 0));
        JLabel lichTrinhLabel = new JLabel("Lich Trinh", JLabel.CENTER); // Đảm bảo chữ hiển thị rõ
        lichTrinhLabel.setForeground(Color.WHITE); // Đặt màu chữ để nổi trên nền tối
        lichTrinhLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        this.add(lichTrinhLabel, BorderLayout.CENTER);
    }

    public LichTrinh() {
        initComponent();
    }
}