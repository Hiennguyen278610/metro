package org.metro.view;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import org.metro.controller.LoginController;
import org.metro.service.SetLogoService;

public class LoginFrame extends JFrame {
    private JButton btnDangNhap;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JPanel exitPanel, minimizePanel;
    private JLabel exitIcon, minimizeIcon, showPassIcon, hidePassIcon;

    public LoginFrame() {
        initFrame();
        initComponents();
        SetLogoService.setLogo(this);
        // Gán LoginController làm ActionListener và MouseListener cho các nút cần thiết
        LoginController controller = new LoginController(this);
        btnDangNhap.addActionListener(controller);
        exitPanel.addMouseListener(controller);
        minimizePanel.addMouseListener(controller);
        // Thêm sự kiện hiển thị/ẩn mật khẩ
        showPasswordToggle();
        setVisible(true);
    }

    private void initFrame() {
        setTitle("Quản lý Metro");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);  // Sử dụng absolute layout cho ví dụ; có thể thay bằng layout manager khác
    }

    private void initComponents() {
        Color mainColor = Color.decode("#6096B4");

        // Navbar: Exit và Minimize
        exitPanel = new JPanel();
        exitPanel.setBounds(760, 0, 40, 40);
        exitPanel.setBackground(Color.white);
        exitIcon = new JLabel("X", JLabel.CENTER);
        exitIcon.setForeground(mainColor);
        exitIcon.setFont(new Font("Segoe UI", Font.BOLD, 18));
        exitPanel.add(exitIcon);
        add(exitPanel);

        minimizePanel = new JPanel();
        minimizePanel.setBounds(720, 0, 40, 40);
        minimizePanel.setBackground(Color.white);
        minimizeIcon = new JLabel("-", JLabel.CENTER);
        minimizeIcon.setForeground(mainColor);
        minimizeIcon.setFont(new Font("Segoe UI", Font.BOLD, 20));
        minimizePanel.add(minimizeIcon);
        add(minimizePanel);

        // Left content panel: thiết kế (logo, tiêu đề)
        JPanel leftContent = new JPanel();
        leftContent.setBounds(0, 0, 350, 600);
        leftContent.setBackground(mainColor);
        leftContent.setLayout(null);
        ImageIcon metroIcon = new ImageIcon(getClass().getResource("/svg/metro_logo.svg"));
        Image metroImage = metroIcon.getImage().getScaledInstance(220, 220, Image.SCALE_SMOOTH);
        JLabel lblMetro = new JLabel(new ImageIcon(metroImage), JLabel.CENTER);
        lblMetro.setBounds(70, 120, 220, 220);
        leftContent.add(lblMetro);
        JLabel lblTitle = new JLabel("<html><div style='text-align: center;'>QUẢN LÝ VẬN HÀNH<br>METRO</div></html>");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitle.setForeground(Color.white);
        lblTitle.setBounds(50, 350, 400, 80);
        leftContent.add(lblTitle);
        add(leftContent);

        // Right content panel: form đăng nhập
        JPanel rightContent = new JPanel();
        rightContent.setBounds(350, 0, 450, 600);
        rightContent.setBackground(Color.white);
        rightContent.setLayout(null);
        add(rightContent);

        JLabel lblLogin = new JLabel("ĐĂNG NHẬP");
        lblLogin.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblLogin.setForeground(mainColor);
        lblLogin.setBounds(140, 110, 160, 36);
        rightContent.add(lblLogin);

        // Form đăng nhập: Username
        JLabel lblUsername = new JLabel("Tên đăng nhập");
        lblUsername.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblUsername.setForeground(mainColor);
        lblUsername.setBounds(60, 180, 200, 30);
        rightContent.add(lblUsername);

        txtUsername = new JTextField("1");
        txtUsername.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        txtUsername.setForeground(Color.GRAY);
        txtUsername.setBounds(60, 210, 310, 40);
        txtUsername.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Thêm padding trái
        rightContent.add(txtUsername);

        // Form đăng nhập: Password
        JLabel lblPassword = new JLabel("Mật khẩu");
        lblPassword.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblPassword.setForeground(mainColor);
        lblPassword.setBounds(60, 260, 200, 30);
        rightContent.add(lblPassword);

        // Tạo JLayeredPane để chồng lớp
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(60, 290, 310, 40);
        rightContent.add(layeredPane);

        // Ô nhập mật khẩu
        txtPassword = new JPasswordField("0000");
        txtPassword.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        txtPassword.setForeground(Color.GRAY);
        txtPassword.setBounds(0, 0, 310, 40);
        txtPassword.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 40)); // Tạo khoảng trống bên phải cho icon
        layeredPane.add(txtPassword, Integer.valueOf(1)); // Đặt mật khẩu ở lớp dưới

        //  mắt mở
        showPassIcon = new JLabel(new FlatSVGIcon(getClass().getResource("/svg/eye-open.svg")).derive(25, 25));
        showPassIcon.setBounds(270, 5, 30, 30);
        layeredPane.add(showPassIcon, Integer.valueOf(2)); // Đặt icon lên trên

        //  mắt đóng
        hidePassIcon = new JLabel(new FlatSVGIcon(getClass().getResource("/svg/eye-close.svg")).derive(25, 25));
        hidePassIcon.setBounds(270, 5, 30, 30);
        hidePassIcon.setVisible(false);
        layeredPane.add(hidePassIcon, Integer.valueOf(2)); // Đặt icon lên trên


        // Nút đăng nhập
        btnDangNhap = new JButton("ĐĂNG NHẬP");
        btnDangNhap.setFont(new Font("Segoe UI", Font.BOLD, 20));
        btnDangNhap.setBackground(mainColor);
        btnDangNhap.setForeground(Color.white);
        btnDangNhap.setBounds(60, 360, 310, 50);
        rightContent.add(btnDangNhap);
    }
    // Hàm xử lý ẩn/hiện mật khẩu
    private void showPasswordToggle() {
        showPassIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                txtPassword.setEchoChar((char) 0);  // Hiển thị mật khẩu
                showPassIcon.setVisible(false);
                hidePassIcon.setVisible(true);
            }
        });

        hidePassIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                txtPassword.setEchoChar('*');  // Ẩn mật khẩu
                hidePassIcon.setVisible(false);
                showPassIcon.setVisible(true);
            }
        });
    }

    // Getter cho các trường cần thiết
    public JButton getDangNhapButton() {
        return btnDangNhap;
    }
    public JTextField getUsernameField() {
        return txtUsername;
    }
    public JPasswordField getPasswordField() {
        return txtPassword;
    }
    public JPanel getExitButton() {
        return exitPanel;
    }
    public JLabel getExitIcon() {
        return exitIcon;
    }
    public JPanel getMinimizeButton() {
        return minimizePanel;
    }
    public JLabel getMinimizeIcon() {
        return minimizeIcon;
    }

}
