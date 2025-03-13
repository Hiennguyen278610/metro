package org.metro.view.Panel;

import javax.swing.*;

import org.metro.DAO.TauDAO;
import org.metro.model.TauModel;
import org.metro.view.Component.RoundedPanel;

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
        TongSoTauPanel.setBackground(Color.yellow);
        TongSoTauPanel.setLayout(null);
        JLabel TongSoTauLabel = new JLabel("Tổng số tàu (" + list.size() + ")");
        TongSoTauLabel.setFont(new Font("Arial", Font.BOLD, 22));
        TongSoTauLabel.setBounds(10, 10, 500, 40);
        TongSoTauPanel.add(TongSoTauLabel);
        this.add(TongSoTauPanel);

        JPanel DanhSachTauPanel = new JPanel();
        DanhSachTauPanel.setLayout(new FlowLayout(1, 0, 0));
        DanhSachTauPanel.setPreferredSize(new Dimension(900, 360));
        DanhSachTauPanel.setBackground(Color.blue);

        JScrollBar scrollBar = new JScrollBar();
        scrollBar.setPreferredSize(new Dimension(900, 360));
        DanhSachTauPanel.add(scrollBar);
        this.add(DanhSachTauPanel);

    }

    public Tau() {
        listTau = new ArrayList<>();
        initComponent();
    }
}