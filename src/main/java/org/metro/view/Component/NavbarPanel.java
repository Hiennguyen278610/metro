package org.metro.view.Component;

import javax.swing.*;
import java.awt.*;
import org.metro.controller.MainController;

public class NavbarPanel extends JPanel {
    private JPanel exitButton, minimizeButton, dangXuatPanel;
    private JLabel exitIcon, minimizeIcon;

    public NavbarPanel(MainController controller) {
        initComponents(controller);
    }

    private void initComponents(MainController controller) {
        setPreferredSize(new Dimension(1200, 40));
        setBackground(Color.decode("#93BFCF"));
        setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        // Logo và tiêu đề
        JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        logoPanel.setBackground(getBackground());
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/svg/logo.png"));
        Image logoImage = logoIcon.getImage().getScaledInstance(50, 30, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(logoImage));
        logoLabel.setPreferredSize(new Dimension(50, 40));
        logoPanel.add(logoLabel);

        JLabel titleLabel = new JLabel("Quản lý Metro");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(Color.black);
        logoPanel.add(titleLabel);

        logoPanel.setPreferredSize(new Dimension(1110, 40));
        add(logoPanel);

        // Nút minimize
        minimizeButton = new JPanel();
        minimizeButton.setPreferredSize(new Dimension(40, 40));
        minimizeButton.setBackground(getBackground());
        minimizeIcon = new JLabel("-");
        minimizeIcon.setFont(new Font("Segoe UI", Font.BOLD, 20));
        minimizeIcon.setForeground(Color.black);
        minimizeButton.add(minimizeIcon);
        minimizeButton.addMouseListener(controller);
        add(minimizeButton);

        // Nút exit
        exitButton = new JPanel();
        exitButton.setPreferredSize(new Dimension(40, 40));
        exitButton.setBackground(getBackground());
        exitIcon = new JLabel("X");
        exitIcon.setFont(new Font("Segoe UI", Font.BOLD, 20));
        exitIcon.setForeground(Color.black);
        exitButton.add(exitIcon);
        exitButton.addMouseListener(controller);
        add(exitButton);
    }
    public JPanel getExitButton() {
        return exitButton;  // exitButton là thành phần JPanel chứa icon "X"
    }

    public JPanel getMinimizeButton() {
        return minimizeButton;  // minimizeButton là thành phần JPanel chứa icon "-"
    }

    public JPanel getDangXuatPanel() {
        // Nếu bạn có một panel đăng xuất, hãy đảm bảo nó được đặt trong NavbarPanel.
        return dangXuatPanel;  // Nếu có, hoặc bạn có thể truy cập thông qua SidebarPanel nếu đăng xuất nằm ở đó.
    }

}
