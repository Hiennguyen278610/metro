package org.metro.view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.metro.controller.MainController;

import com.formdev.flatlaf.extras.FlatSVGIcon;

public class MainFrame extends JFrame {
    private JPanel TuyenDuongPanel;
    private JPanel ThongKePanel;
    private JPanel DangXuatPanel;
    private JPanel LichTrinhPanel;
    private JPanel MuaVePanel;
    private JPanel TuyenDuongContent;
    private JPanel ThongKeContent;
    private JPanel LichTrinhContent;
    private JPanel MuaVeContent;
    private CardLayout cardLayout;
    private JPanel rightPanel;
    private JPanel navbarPanel;
    private JPanel ExitButton;
    private JPanel MinimizeButton;
    private JLabel ExitIcon;
    private JLabel MinimizeIcon;

    public MainFrame() {
        setSize(1200, 800);
        setTitle("Quan ly Metro");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        this.init();
        setVisible(true);
    }

    private void init() {
        JPanel mainPanel = new JPanel();
        this.setContentPane(mainPanel);
        Color MainColor = Color.decode("#6096B4");
        MainController controller = new MainController(this);
        mainPanel.setLayout(null);

        // Navbar
        navbarPanel = new JPanel();
        navbarPanel.setBounds(0, 0, 1200, 40);
        navbarPanel.setBackground(Color.pink);
        navbarPanel.setLayout(null);
        mainPanel.add(navbarPanel);

        ExitButton = new JPanel();
        ExitButton.setBounds(1160, 0, 40, 40);
        ExitButton.setBackground(Color.pink);
        ExitButton.addMouseListener(controller);
        navbarPanel.add(ExitButton);

        ExitIcon = new JLabel("X");
        ExitIcon.setFont(new Font("Segoe UI", Font.BOLD, 20));
        ExitIcon.setForeground(Color.white);
        ExitIcon.setBounds(0, 0, 40, 40);
        ExitButton.add(ExitIcon);

        MinimizeButton = new JPanel();
        MinimizeButton.setBounds(1120, 0, 40, 40);
        MinimizeButton.setBackground(Color.pink);
        MinimizeButton.addMouseListener(controller);
        navbarPanel.add(MinimizeButton);

        MinimizeIcon = new JLabel("-");
        MinimizeIcon.setFont(new Font("Segoe UI", Font.BOLD, 20));
        MinimizeIcon.setForeground(Color.white);
        MinimizeIcon.setBounds(0, 0, 40, 40);
        MinimizeButton.add(MinimizeIcon);

        // Left Panel
        JPanel leftPanel = new JPanel();
        leftPanel.setBounds(0, 40, 300, 760);
        leftPanel.setBackground(Color.white);
        leftPanel.setLayout(null);
        mainPanel.add(leftPanel);

        // Right Panel
        rightPanel = new JPanel();
        rightPanel.setBounds(300, 40, 900, 760);
        rightPanel.setLayout(null);
        mainPanel.add(rightPanel);

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(null);
        titlePanel.setBackground(MainColor);
        titlePanel.setBounds(0, 0, 300, 100);

        JLabel metroLabel = new JLabel("<html><div style='text-align: center;'>QUẢN LÝ VẬN HÀNH<br>METRO</div></html>");
        metroLabel.setForeground(Color.white);
        metroLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        metroLabel.setBounds(30, 14, 270, 80);

        titlePanel.add(metroLabel);
        leftPanel.add(titlePanel);

        JPanel ChucNangPanel = new JPanel();
        ChucNangPanel.setBackground(Color.decode("#BDCDD6"));
        ChucNangPanel.setBounds(0, 100, 300, 700);
        ChucNangPanel.setLayout(null);
        leftPanel.add(ChucNangPanel);

        TuyenDuongPanel = new JPanel();
        TuyenDuongPanel.setBounds(10, 10, 280, 80);
        TuyenDuongPanel.setLayout(null);
        TuyenDuongPanel.setBackground(Color.decode("#6096B4"));
        ChucNangPanel.add(TuyenDuongPanel);

        TuyenDuongPanel.addMouseListener(controller);

        JLabel TuyenDuongLabel = new JLabel("TUYẾN ĐƯỜNG");
        TuyenDuongLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        TuyenDuongLabel.setBounds(80, 22, 160, 30);
        TuyenDuongPanel.add(TuyenDuongLabel);

        ImageIcon iconTuyenDuong = new ImageIcon(
                new ImageIcon("assets/icons/route.png").getImage().getScaledInstance(40, 40,
                        Image.SCALE_SMOOTH));
        JLabel TuyenDuongIcon = new JLabel(iconTuyenDuong, JLabel.CENTER);
        TuyenDuongIcon.setForeground(Color.white);
        TuyenDuongIcon.setBounds(20, 20, 40, 40);
        TuyenDuongPanel.add(TuyenDuongIcon);

        ThongKePanel = new JPanel();
        ThongKePanel.setBounds(10, 100, 280, 80);
        ThongKePanel.setLayout(null);
        ThongKePanel.setBackground(Color.decode("#93BFCF"));
        ChucNangPanel.add(ThongKePanel);

        ThongKePanel.addMouseListener(controller);

        JLabel ThongKeLabel = new JLabel("THỐNG KÊ");
        ThongKeLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        ThongKeLabel.setBounds(80, 22, 160, 30);
        ThongKePanel.add(ThongKeLabel);

        ImageIcon iconThongKe = new ImageIcon(
                new ImageIcon("assets/icons/diagram.png").getImage().getScaledInstance(40, 40,
                        Image.SCALE_SMOOTH));
        JLabel ThongKeIcon = new JLabel(iconThongKe, JLabel.CENTER);
        ThongKeIcon.setForeground(Color.white);
        ThongKeIcon.setBounds(20, 20, 40, 40);
        ThongKePanel.add(ThongKeIcon);

        LichTrinhPanel = new JPanel();
        LichTrinhPanel.setBounds(10, 190, 280, 80);
        LichTrinhPanel.setLayout(null);
        LichTrinhPanel.setBackground(Color.decode("#93BFCF"));
        LichTrinhPanel.addMouseListener(controller);
        ChucNangPanel.add(LichTrinhPanel);

        JLabel LichTrinhLabel = new JLabel("LỊCH TRÌNH");
        LichTrinhLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        LichTrinhLabel.setBounds(80, 22, 160, 30);
        LichTrinhPanel.add(LichTrinhLabel);

        ImageIcon iconLichTrinh = new ImageIcon(
                new ImageIcon("assets/icons/train.png").getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        JLabel LichTrinhIcon = new JLabel(iconLichTrinh, JLabel.CENTER);
        LichTrinhIcon.setForeground(Color.white);
        LichTrinhIcon.setBounds(20, 20, 40, 40);
        LichTrinhPanel.add(LichTrinhIcon);

        MuaVePanel = new JPanel();
        MuaVePanel.setBounds(10, 280, 280, 80);
        MuaVePanel.setLayout(null);
        MuaVePanel.setBackground(Color.decode("#93BFCF"));
        MuaVePanel.addMouseListener(controller);
        ChucNangPanel.add(MuaVePanel);

        JLabel GiaVeLabel = new JLabel("MUA VÉ");
        GiaVeLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        GiaVeLabel.setBounds(80, 22, 160, 30);
        MuaVePanel.add(GiaVeLabel);

        FlatSVGIcon iconGiaVe = new FlatSVGIcon(getClass().getResource("/svg/ticket.svg")).derive(40, 40);
        JLabel GiaVeIcon = new JLabel(iconGiaVe, JLabel.CENTER);
        GiaVeIcon.setForeground(Color.BLACK);
        GiaVeIcon.setBounds(0, 0, 80, 80);
        MuaVePanel.add(GiaVeIcon);

        DangXuatPanel = new JPanel();
        DangXuatPanel.setBounds(10, 550, 280, 80);
        DangXuatPanel.setLayout(null);
        DangXuatPanel.setBackground(Color.decode("#93BFCF"));
        DangXuatPanel.addMouseListener(controller);
        ChucNangPanel.add(DangXuatPanel);

        JLabel DangXuatLabel = new JLabel("ĐĂNG XUẤT");
        DangXuatLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        DangXuatLabel.setBounds(80, 22, 160, 30);
        DangXuatPanel.add(DangXuatLabel);

        FlatSVGIcon iconDangXuat = new FlatSVGIcon(getClass().getResource("/svg/arrow-back.svg")).derive(30, 30);

        JLabel DangXuatIcon = new JLabel(iconDangXuat, JLabel.CENTER);
        DangXuatIcon.setForeground(Color.BLACK);
        DangXuatIcon.setBounds(0, 0, 80, 80);
        DangXuatPanel.add(DangXuatIcon);

        // Phần nội dung bên phải
        cardLayout = new CardLayout();
        rightPanel.setLayout(cardLayout);

        TuyenDuongContent = new JPanel();
        TuyenDuongContent.add(new JLabel("Tuyenduong"));
        TuyenDuongContent.setBackground(Color.white);
        rightPanel.add(TuyenDuongContent, "Tuyenduong");

        ThongKeContent = new JPanel();
        ThongKeContent.add(new JLabel("Thongke"));
        ThongKeContent.setBackground(Color.white);
        rightPanel.add(ThongKeContent, "Thongke");

        LichTrinhContent = new JPanel();
        LichTrinhContent.add(new JLabel("Lichtrinh"));
        LichTrinhContent.setBackground(Color.white);
        rightPanel.add(LichTrinhContent, "Lichtrinh");

        MuaVeContent = new JPanel();
        MuaVeContent.add(new JLabel("Muave"));
        MuaVeContent.setBackground(Color.white);
        rightPanel.add(MuaVeContent, "Muave");
    }

    public void resetPanel() {
        TuyenDuongPanel.setBackground(Color.decode("#93BFCF"));
        ThongKePanel.setBackground(Color.decode("#93BFCF"));
        DangXuatPanel.setBackground(Color.decode("#93BFCF"));
        LichTrinhPanel.setBackground(Color.decode("#93BFCF"));
        MuaVePanel.setBackground(Color.decode("#93BFCF"));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame();
            }
        });
    }

    public JPanel getTuyenDuongPanel() {
        return TuyenDuongPanel;
    }

    public JPanel getThongKePanel() {
        return ThongKePanel;
    }

    public JPanel getDangXuatPanel() {
        return DangXuatPanel;
    }

    public void showTuyenDuongContent() {
        // CardLayout cardLayout = (CardLayout) this.getContentPane().getLayout();
        cardLayout.show(this.rightPanel, "Tuyenduong");
    }

    public void showThongKeContent() {
        // CardLayout cardLayout = (CardLayout) this.getContentPane().getLayout();
        cardLayout.show(this.rightPanel, "Thongke");
    }

    public void showLichTrinhContent() {
        cardLayout.show(this.rightPanel, "Lichtrinh");
    }

    public void showMuaVeContent() {
        cardLayout.show(this.rightPanel, "Muave");
    }

    public JPanel getLichTrinhPanel() {
        return LichTrinhPanel;
    }

    public JPanel getMuaVePanel() {
        return MuaVePanel;
    }

    public JPanel getExitButton() {
        return ExitButton;
    }

    public JPanel getMinimizeButton() {
        return MinimizeButton;
    }

    public JLabel getExitIcon() {
        return ExitIcon;
    }

    public JLabel getMinimizeIcon() {
        return MinimizeIcon;
    }

}
