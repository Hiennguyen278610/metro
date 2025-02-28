package org.metro.view.Panel;

import javax.swing.*;
import java.awt.*;

public class Tau extends JPanel {
    Color BackgroundColor = new Color(0, 2, 2);

    public void initComponent() {
        this.setBackground(BackgroundColor);
        this.setLayout(new BorderLayout(0, 0));
        JLabel tauLabel = new JLabel("Tau", JLabel.CENTER); // Đảm bảo chữ hiển thị rõ
        tauLabel.setForeground(Color.WHITE); // Đặt màu chữ để nổi trên nền tối
        tauLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        this.add(tauLabel, BorderLayout.CENTER);
    }

    public Tau() {
        initComponent();
    }
}