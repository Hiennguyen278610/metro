package org.metro.view.Component;

import org.metro.util.SessionManager;
import org.metro.view.MainFrame;

import javax.swing.*;
import java.awt.*;

public class SidebarPanel extends JPanel {
    private JPanel userInfoPanel;
    private MenuTaskbar menuTaskbar;

    public SidebarPanel() {
        initComponents();
    }

    private void initComponents() {
        setPreferredSize(new Dimension(300, 760));
        setBackground(Color.white);
        setLayout(null);

        // User Info Panel: chứa avatar, tên và vai trò (lấy từ SessionManager trong triển khai thực tế)
        userInfoPanel = new JPanel();
        userInfoPanel.setLayout(null);
        userInfoPanel.setBackground(Color.decode("#BDCDD6"));
        userInfoPanel.setBounds(0, 0, 300, 100);
        JLabel avatar = new JLabel(new ImageIcon(getClass().getResource("/svg/avatar.svg")));
        avatar.setBounds(0, 0, 100, 100);
        userInfoPanel.add(avatar);
        JLabel userName = new JLabel("Tên người dùng");
        userName.setFont(new Font("Segoe UI", Font.BOLD, 22));
        userName.setBounds(100, 0, 200, 80);
        userInfoPanel.add(userName);
        JLabel roleLabel = new JLabel("Vai trò");
        roleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        roleLabel.setBounds(100, 20, 200, 90);
        userInfoPanel.add(roleLabel);
        add(userInfoPanel);

        // MenuTaskbar Panel: hiển thị các mục điều hướng
        JPanel menuPanel = new JPanel(new BorderLayout());
        menuPanel.setBounds(0, 100, 300, 660);
        menuPanel.setBackground(Color.decode("#BDCDD6"));
        menuTaskbar = new MenuTaskbar(new MainFrame());
        menuTaskbar.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        menuPanel.add(menuTaskbar, BorderLayout.CENTER);
        add(menuPanel);
    }
}
