package org.metro.view.Panel;

import javax.swing.*;
import java.awt.*;

public class Tram extends JPanel {
    Color BackgroundColor = new Color(0, 2, 2);

    public void initComponent() {
        this.setBackground(BackgroundColor);
        this.setLayout(new BorderLayout(0, 0));
        JLabel tramLabel = new JLabel("Tram", JLabel.CENTER); // Đảm bảo chữ hiển thị rõ
        tramLabel.setForeground(Color.WHITE); // Đặt màu chữ để nổi trên nền tối
        tramLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        this.add(tramLabel, BorderLayout.CENTER);
    }

    public Tram() {
        initComponent();
    }
}
