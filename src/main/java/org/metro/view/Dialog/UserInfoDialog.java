package org.metro.view.Dialog;

import org.metro.model.TaiKhoanModel;
import org.metro.util.SessionManager;

import javax.swing.*;
import java.awt.*;

public class UserInfoDialog extends JDialog {

    public UserInfoDialog(JFrame owner) {
        super(owner, "Thông tin tài khoản", true);
        initComponents();
    }

    private void initComponents() {
        // Lấy thông tin user từ SessionManager
        TaiKhoanModel user = SessionManager.getCurrentUser();

        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        mainPanel.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("Thông tin tài khoản", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel infoPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        infoPanel.setBackground(Color.WHITE);

        // mã nhân viên
        JLabel lblManv = new JLabel("Mã NV: " + user.getManv());
        lblManv.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        infoPanel.add(lblManv);

        // vai trò
        JLabel lblRole = new JLabel("Vai trò: " + getRoleName(user.getManhomquyen()));
        lblRole.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        infoPanel.add(lblRole);

        // trạng thái
        JLabel lblStatus = new JLabel("Trạng thái: " + (user.getTrangthai() == 1 ? "Hoạt động" : "Ngừng hoạt động"));
        lblStatus.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        infoPanel.add(lblStatus);

        mainPanel.add(infoPanel, BorderLayout.CENTER);

        JButton btnClose = new JButton("Đóng");
        btnClose.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnClose.addActionListener(e -> dispose());
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnPanel.setBackground(Color.WHITE);
        btnPanel.add(btnClose);
        mainPanel.add(btnPanel, BorderLayout.SOUTH);

        setPreferredSize(new Dimension(500, 400));
        getContentPane().add(mainPanel);
        pack();
        setLocationRelativeTo(getOwner());
    }

    private String getRoleName(int manhomquyen) {
        switch(manhomquyen) {
            case 1: return "Admin";
            case 2: return "Nhân viên";
            case 3: return "Quản lý";
            default: return "Unknown";
        }
    }
}
