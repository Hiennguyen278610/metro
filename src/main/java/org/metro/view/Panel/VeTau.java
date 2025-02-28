package org.metro.view.Panel;

import javax.swing.*;
import java.awt.*;

public class VeTau extends JPanel {
    Color BackgroundColor = new Color(0, 2, 2);

    public void initComponent() {
        this.setBackground(BackgroundColor);
        this.setLayout(new BorderLayout(0, 0));
        JLabel veTauLabel = new JLabel("Ve tau", JLabel.CENTER); // Đảm bảo chữ hiển thị rõ
        veTauLabel.setForeground(Color.WHITE); // Đặt màu chữ để nổi trên nền tối
        veTauLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        this.add(veTauLabel, BorderLayout.CENTER);
    }

    public VeTau() {
        initComponent();
    }
}