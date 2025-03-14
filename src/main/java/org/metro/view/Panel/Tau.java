package org.metro.view.Panel;

import javax.swing.*;
import javax.swing.border.MatteBorder;

import org.metro.DAO.TauDAO;
import org.metro.model.TauModel;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Tau extends JPanel {
    Color BackgroundColor = new Color(0, 2, 2);
    List<TauModel> listTau;

    public void initComponent() {
        this.setBackground(Color.white);
        this.setLayout(new FlowLayout(1, 0, 0));
        this.setPreferredSize(new Dimension(900, 600));
        List<TauModel> list = new TauDAO().selectAll();

        JPanel ThongTinTauPanel = new JPanel();
        ThongTinTauPanel.setBackground(Color.pink);
        ThongTinTauPanel.setPreferredSize(new Dimension(900, 200));
        ThongTinTauPanel.setLayout(new FlowLayout(1, 0, 0));

        JLabel HinhAnhTau = new JLabel();
        HinhAnhTau.setPreferredSize(new Dimension(300, 200));
        HinhAnhTau.setBackground(Color.green);
        HinhAnhTau.setOpaque(true);
        // HinhAnhTau.setIcon(new ImageIcon(getClass().getResource("/svg/tau.png")));
        ThongTinTauPanel.add(HinhAnhTau);

        JPanel ThongTinChiTietTauPanel = new JPanel();
        ThongTinChiTietTauPanel.setPreferredSize(new Dimension(600, 200));
        ThongTinChiTietTauPanel.setLayout(new GridLayout(4, 3, 10, 10));

        JLabel MaTauLabel = new JLabel("Mã tàu: ");
        JTextField MaTauTextField = new JTextField();
        MaTauTextField.setPreferredSize(new Dimension(200, 30));

        JLabel SoGheLabel = new JLabel("Số ghế: ");
        JTextField SoGheTextField = new JTextField();
        SoGheTextField.setPreferredSize(new Dimension(200, 30));

        JLabel TrangThaiTauLabel = new JLabel("Trạng thái tàu: ");
        JTextField TrangThaiTauTextField = new JTextField();
        TrangThaiTauTextField.setPreferredSize(new Dimension(200, 30));

        JLabel NgayNhapTauLabel = new JLabel("Ngày nhập tàu: ");
        JTextField NgayNhapTauTextField = new JTextField();
        NgayNhapTauTextField.setPreferredSize(new Dimension(200, 30));

        JButton ThemTauButton = new JButton("THÊM");
        JButton SuaTauButton = new JButton("SỬA");
        JButton XoaTauButton = new JButton("XÓA");

        ThongTinChiTietTauPanel.add(MaTauLabel);
        ThongTinChiTietTauPanel.add(MaTauTextField);
        ThongTinChiTietTauPanel.add(ThemTauButton);

        ThongTinChiTietTauPanel.add(SoGheLabel);
        ThongTinChiTietTauPanel.add(SoGheTextField);
        ThongTinChiTietTauPanel.add(SuaTauButton);

        ThongTinChiTietTauPanel.add(TrangThaiTauLabel);
        ThongTinChiTietTauPanel.add(TrangThaiTauTextField);
        ThongTinChiTietTauPanel.add(XoaTauButton);

        ThongTinChiTietTauPanel.add(NgayNhapTauLabel);
        ThongTinChiTietTauPanel.add(NgayNhapTauTextField);

        ThongTinTauPanel.add(ThongTinChiTietTauPanel);
        this.add(ThongTinTauPanel);

        JPanel TongSoTauPanel = new JPanel();
        TongSoTauPanel.setPreferredSize(new Dimension(900, 40));
        TongSoTauPanel.setBorder(new MatteBorder(0, 0, 2, 0, Color.black));
        TongSoTauPanel.setBackground(Color.white);
        TongSoTauPanel.setLayout(null);
        JLabel TongSoTauLabel = new JLabel("Tổng số tàu (" + list.size() + ")");
        TongSoTauLabel.setFont(new Font("Arial", Font.BOLD, 22));
        TongSoTauLabel.setBounds(30, 5, 200, 40);
        TongSoTauPanel.add(TongSoTauLabel);
        this.add(TongSoTauPanel);
        JLabel SortLabel = new JLabel("Sắp xếp theo: ");
        SortLabel.setFont(new Font("Arial", Font.BOLD, 16));
        SortLabel.setBounds(650, 5, 200, 40);
        TongSoTauPanel.add(SortLabel);
        JComboBox<String> SortComboBox = new JComboBox<>();
        SortComboBox.addItem("Mã tàu");
        SortComboBox.addItem("Trạng thái tàu");
        SortComboBox.addItem("Ngày nhập tàu");
        SortComboBox.setBounds(760, 14, 100, 20);
        TongSoTauPanel.add(SortComboBox);

        JPanel DanhSachTauPanel = new DanhSachTau();

        JScrollPane DanhSachTauScrollPane = new JScrollPane(DanhSachTauPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        DanhSachTauScrollPane.setPreferredSize(new Dimension(900, 600));
        // DanhSachTauScrollPane.setBorder(BorderFactory.createEmptyBorder());
        this.add(DanhSachTauScrollPane);

    }

    public Tau() {
        listTau = new ArrayList<>();
        initComponent();
    }
}