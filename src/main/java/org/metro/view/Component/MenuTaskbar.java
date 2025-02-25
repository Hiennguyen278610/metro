package org.metro.view.Component;

import org.metro.view.MainFrame;
import org.metro.view.Panel.KhachHang;
import org.metro.view.Panel.User;
import org.metro.view.Panel.NhanVien;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuTaskbar extends JPanel {

    User user;
    KhachHang khachHang;
    NhanVien nhanVien;

    public MenuTaskbar() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Sắp xếp theo chiều dọc

        user = new User();
        user.initComponent();

        khachHang = new KhachHang();
        khachHang.initComponent();

        nhanVien = new NhanVien();
        nhanVien.initComponent();

        // Tạo nút hoặc label cho User
        JLabel userLabel = new JLabel("User");
        userLabel.setIcon(new ImageIcon("path/to/user-icon.svg")); // Thêm icon nếu cần
        userLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Thêm sự kiện click
        userLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MainFrame mainFrame = (MainFrame) SwingUtilities.getWindowAncestor(MenuTaskbar.this);
                mainFrame.showUserContent();
            }
        });

        // Thêm label vào giao diện
        this.add(userLabel);

        // Thêm các panel khác vào menu nếu cần
        JLabel khachHangLabel = new JLabel("Khách hàng");
        khachHangLabel.setIcon(new ImageIcon("path/to/customer-icon.svg")); // Thêm icon nếu cần
        khachHangLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        khachHangLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showKhachHangPanel();
            }
        });
        this.add(khachHangLabel);

        JLabel nhanVienLabel = new JLabel("Nhân viên");
        nhanVienLabel.setIcon(new ImageIcon("path/to/staff-icon.svg")); // Thêm icon nếu cần
        nhanVienLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        nhanVienLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showNhanVienPanel();
            }
        });
        this.add(nhanVienLabel);
    }

    private void showCustomerPanel() {
        // Hiển thị panel Khách hàng
        JFrame mainFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        mainFrame.getContentPane().removeAll();
        mainFrame.add(khachHang);
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    private void showKhachHangPanel() {
        // Hiển thị panel Khách hàng (có thể trùng với showCustomerPanel)
        JFrame mainFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        mainFrame.getContentPane().removeAll();
        mainFrame.add(khachHang);
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    private void showNhanVienPanel() {
        // Hiển thị panel Nhân viên
        JFrame mainFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        mainFrame.getContentPane().removeAll();
        mainFrame.add(nhanVien);
        mainFrame.revalidate();
        mainFrame.repaint();
    }
}
