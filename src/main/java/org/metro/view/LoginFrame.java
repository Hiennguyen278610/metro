package org.metro.view;

import java.awt.*;
import java.awt.event.MouseEvent;
import javax.swing.*;
import org.metro.controller.LoginController;
import org.metro.service.SetLogoService;

public class LoginFrame extends JFrame {
    private JButton btnDangNhap;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JPanel exitPanel, minimizePanel;
    private JLabel exitIcon, minimizeIcon;
    private JLabel lblCreateAccount;  // Cho mục "Chưa có tài khoản?"

    public LoginFrame() {
        initFrame();
        initComponents();
        SetLogoService.setLogo(this);
        // Gán LoginController làm ActionListener và MouseListener cho các nút cần thiết
        LoginController controller = new LoginController(this);
        btnDangNhap.addActionListener(controller);
        exitPanel.addMouseListener(controller);
        minimizePanel.addMouseListener(controller);
        lblCreateAccount.addMouseListener(controller);
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

        txtUsername = new JTextField("1001");
        txtUsername.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtUsername.setForeground(Color.GRAY);
        txtUsername.setBounds(60, 210, 310, 40);
        rightContent.add(txtUsername);

        // Form đăng nhập: Password
        JLabel lblPassword = new JLabel("Mật khẩu");
        lblPassword.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblPassword.setForeground(mainColor);
        lblPassword.setBounds(60, 260, 200, 30);
        rightContent.add(lblPassword);

        txtPassword = new JPasswordField("0000");
        txtPassword.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        txtPassword.setForeground(Color.GRAY);
        txtPassword.setEchoChar((char)0); // hiển thị placeholder
        txtPassword.setBounds(60, 290, 310, 40);
        rightContent.add(txtPassword);

        // Placeholder handling for username
        txtUsername.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (txtUsername.getText().equals("Nhập tên đăng nhập...")) {
                    txtUsername.setText("1001");
                    txtUsername.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (txtUsername.getText().trim().isEmpty()) {
                    txtUsername.setText("0000");
                    txtUsername.setForeground(Color.GRAY);
                }
            }
        });

        // Placeholder handling for password
        txtPassword.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (new String(txtPassword.getPassword()).equals("Nhập mật khẩu...")) {
                    txtPassword.setText("0000");
                    txtPassword.setForeground(Color.BLACK);
                    txtPassword.setEchoChar('*');
                }
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (new String(txtPassword.getPassword()).trim().isEmpty()) {
                    txtPassword.setText("0000");
                    txtPassword.setForeground(Color.GRAY);
                    txtPassword.setEchoChar((char)0);
                }
            }
        });

        // Nút đăng nhập
        btnDangNhap = new JButton("ĐĂNG NHẬP");
        btnDangNhap.setFont(new Font("Segoe UI", Font.BOLD, 20));
        btnDangNhap.setBackground(mainColor);
        btnDangNhap.setForeground(Color.white);
        btnDangNhap.setBounds(60, 360, 310, 50);
        rightContent.add(btnDangNhap);

        // Label "Chưa có tài khoản?"
        lblCreateAccount = new JLabel("<html><u><i>Chưa có tài khoản ?</i></u></html>");
        lblCreateAccount.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblCreateAccount.setForeground(mainColor);
        lblCreateAccount.setBounds(265, 415, 120, 20);
        rightContent.add(lblCreateAccount);
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
    public JLabel getTaoTaiKhoan() {
        return lblCreateAccount;
    }

    // Hàm hiển thị/ẩn mật khẩu (đã được triển khai)
    public static void showPassword(JPasswordField pf, JLabel show, JLabel hide) {
        hide.setVisible(false);
        show.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                pf.setEchoChar((char)0);
                show.setVisible(false);
                hide.setVisible(true);
                pf.requestFocusInWindow();
            }
        });
        hide.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                pf.setEchoChar('*');
                hide.setVisible(false);
                show.setVisible(true);
                pf.requestFocusInWindow();
            }
        });
    }
}
