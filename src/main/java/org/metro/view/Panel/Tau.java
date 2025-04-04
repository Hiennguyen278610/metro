package org.metro.view.Panel;

import javax.swing.*;
import javax.swing.border.MatteBorder;

import org.metro.DAO.TauDAO;
import org.metro.controller.TauController;
import org.metro.model.TauModel;
import org.metro.view.Component.RoundedPanel;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.formdev.flatlaf.extras.FlatSVGIcon;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.util.List;

public class Tau extends JPanel {
    Color BackgroundColor = new Color(0, 2, 2);
    List<TauModel> listTau;
    JComboBox<String> SortComboBox;
    JPanel DanhSachTauPanel;
    JTextField MaTauTextField, SoGheTextField, NgayNhapTauTextField;
    JComboBox<String> TrangThaiTauCBx;
    JLabel HinhAnhTau;
    private TauController action = new TauController(this);

    public void initComponent() {
        this.setBackground(Color.white);
        this.setLayout(new FlowLayout(1, 0, 0));
        this.setPreferredSize(new Dimension(900, 600));
        List<TauModel> list = new TauDAO().selectAll();

        JPanel ThongTinTauPanel = new JPanel();
        ThongTinTauPanel.setBackground(Color.pink);
        ThongTinTauPanel.setPreferredSize(new Dimension(900, 200));
        ThongTinTauPanel.setLayout(new FlowLayout(1, 0, 0));

        HinhAnhTau = new JLabel();
        HinhAnhTau.setPreferredSize(new Dimension(300, 200));
        HinhAnhTau.setBackground(Color.white);
        HinhAnhTau.setOpaque(true);
        // HinhAnhTau.setIcon(new ImageIcon(getClass().getResource("/svg/logo.png")));
        ThongTinTauPanel.add(HinhAnhTau);

        JPanel ThongTinChiTietTauPanel = new JPanel();
        ThongTinChiTietTauPanel.setPreferredSize(new Dimension(600, 200));
        ThongTinChiTietTauPanel.setLayout(new GridLayout(4, 3, 10, 10));

        JLabel MaTauLabel = new JLabel("Mã tàu: ");
        MaTauTextField = new JTextField();
        MaTauTextField.setPreferredSize(new Dimension(200, 30));

        JLabel SoGheLabel = new JLabel("Số ghế: ");
        SoGheTextField = new JTextField();
        SoGheTextField.setPreferredSize(new Dimension(200, 30));

        JLabel TrangThaiTauLabel = new JLabel("Trạng thái tàu: ");
        TrangThaiTauCBx = new JComboBox<String>(
                new String[] { "Đang hoạt động", "Đang bảo trì", "Ngừng hoạt động" });
        TrangThaiTauCBx.setPreferredSize(new Dimension(200, 30));

        JLabel NgayNhapTauLabel = new JLabel("Ngày nhập tàu: ");
        NgayNhapTauTextField = new JTextField();
        NgayNhapTauTextField.setPreferredSize(new Dimension(200, 30));

        JButton ThemTauButton = new JButton("THÊM");
        JButton SuaTauButton = new JButton("SỬA");
        SuaTauButton.addActionListener(e -> {
            setTextfieldEnable();
            ValidateDuLieu();
        });
        JButton XoaTauButton = new JButton("XÓA");
        JButton LuuTauButton = new JButton("LƯU");
        LuuTauButton.addActionListener(e -> {
            setTextfieldDisable();
            SaveToDB();
        });

        ThongTinChiTietTauPanel.add(MaTauLabel);
        ThongTinChiTietTauPanel.add(MaTauTextField);
        ThongTinChiTietTauPanel.add(ThemTauButton);

        ThongTinChiTietTauPanel.add(SoGheLabel);
        ThongTinChiTietTauPanel.add(SoGheTextField);
        ThongTinChiTietTauPanel.add(SuaTauButton);

        ThongTinChiTietTauPanel.add(TrangThaiTauLabel);
        ThongTinChiTietTauPanel.add(TrangThaiTauCBx);
        ThongTinChiTietTauPanel.add(XoaTauButton);

        ThongTinChiTietTauPanel.add(NgayNhapTauLabel);
        ThongTinChiTietTauPanel.add(NgayNhapTauTextField);
        ThongTinChiTietTauPanel.add(LuuTauButton);

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

        SortComboBox = new JComboBox<>();
        SortComboBox.addItem("Mã tàu");
        SortComboBox.addItem("Sức chứa");
        SortComboBox.addItem("Ngày nhập tàu");
        SortComboBox.addItem("Trạng thái tàu");
        SortComboBox.setBounds(760, 14, 100, 20);
        TongSoTauPanel.add(SortComboBox);

        SortComboBox.addItemListener(action);

        DanhSachTauPanel = new JPanel();
        DanhSachTauPanel.setLayout(new FlowLayout(0, 36, 40));
        DanhSachTauPanel.setPreferredSize(new Dimension(900, ((list.size() + 5) / 3) * 260));
        DanhSachTauPanel.setBackground(Color.white);

        for (int i = 0; i < list.size(); i++) {
            RoundedPanel panel = createTauPanel(list.get(i));
            DanhSachTauPanel.add(panel);
        }

        JScrollPane DanhSachTauScrollPane = new JScrollPane(DanhSachTauPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        DanhSachTauScrollPane.setPreferredSize(new Dimension(900, 600));
        // DanhSachTauScrollPane.setBorder(BorderFactory.createEmptyBorder());
        this.add(DanhSachTauScrollPane);
    }

    public void updateData(List<TauModel> list) {
        this.DanhSachTauPanel.removeAll();
        for (int i = 0; i < list.size(); i++) {
            RoundedPanel panel = createTauPanel(list.get(i));
            DanhSachTauPanel.add(panel);
        }
        this.DanhSachTauPanel.revalidate();
        this.DanhSachTauPanel.repaint();
    }

    private void SaveToDB() {
        // Luoi qa mai lam nha ae

        // String ma = this.MaTauTextField.getText();
        // String soghe = this.SoGheTextField.getText();
        // String ngayNhap = this.NgayNhapTauTextField.getText();
        // String trangThai = (String) this.TrangThaiTauCBx.getSelectedItem();
        // TauModel hihi = new TauModel(ma, ABORT, trangThai);

        // int update = new TauDAO().update(hihi);
        // if (update > 0)
        // JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
        // else
        // JOptionPane.showMessageDialog(this, "Có lỗi xảy ra", "Lỗi",
        // JOptionPane.ERROR_MESSAGE);

        JOptionPane.showMessageDialog(this, "Đã lưu lại thông tin (thật ra là chưa)");
    }

    private void ValidateDuLieu() {

    }

    private RoundedPanel createTauPanel(TauModel tau) {
        RoundedPanel panel = new RoundedPanel(20);
        panel.setPreferredSize(new Dimension(240, 240));
        panel.setBackground(Color.white);
        panel.setLayout(null);
        panel.setBorder(BorderFactory.createLineBorder(Color.black, 2));

        FlatSVGIcon icon = new FlatSVGIcon(getClass().getResource("/svg/train.svg")).derive(100, 100);
        JLabel HinhAnhTau1 = new JLabel(icon);
        HinhAnhTau1.setBounds(75, 10, 100, 100);
        panel.add(HinhAnhTau1);

        JLabel MaTau = new JLabel("Mã tàu: " + tau.getMatau());
        MaTau.setBounds(10, 120, 200, 30);
        MaTau.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel SoGhe = new JLabel("Sức chứa: " + tau.getSoghe());
        SoGhe.setBounds(10, 140, 200, 30);
        SoGhe.setFont(new Font("Arial", Font.BOLD, 14));

        TrangThaiTau trangthai = new TrangThaiTau(tau.getTrangthaitau());
        trangthai.setBounds(10, 190, 200, 30);
        trangthai.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel NgayNhapTau = new JLabel("Ngày nhập tàu: " +
                tau.getNgaynhap());
        NgayNhapTau.setBounds(10, 160, 200, 30);
        NgayNhapTau.setFont(new Font("Arial", Font.BOLD, 14));

        // JButton SuaTau = new JButton("SỬA");
        // SuaTau.setPreferredSize(new Dimension(100, 30));
        // JButton XoaTau = new JButton("XÓA");
        // XoaTau.setPreferredSize(new Dimension(100, 30));

        panel.add(MaTau);
        panel.add(SoGhe);
        panel.add(NgayNhapTau);
        panel.add(trangthai);

        // Bắt sự kiện nhấn chuột
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                updateForm(tau);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                e.getComponent().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                e.getComponent().setCursor(Cursor.getDefaultCursor());
            }
        });
        return panel;
    }

    private void updateForm(TauModel tau) {
        this.MaTauTextField.setText(tau.getMatau());
        this.NgayNhapTauTextField.setText(tau.getNgaynhap());
        this.SoGheTextField.setText(tau.getSoghe() + "");
        this.TrangThaiTauCBx.setSelectedItem((String) tau.getTrangthaitau());
        this.setTextfieldDisable();
        FlatSVGIcon icon = new FlatSVGIcon(getClass().getResource("/svg/train.svg")).derive(200, 200);
        this.HinhAnhTau.setIcon(icon);
    }

    private void setTextfieldDisable() {
        this.MaTauTextField.setEnabled(false);
        this.NgayNhapTauTextField.setEnabled(false);
        this.SoGheTextField.setEnabled(false);
        this.TrangThaiTauCBx.setEnabled(false);
    }

    private void setTextfieldEnable() {
        this.MaTauTextField.setEnabled(true);
        this.NgayNhapTauTextField.setEnabled(true);
        this.SoGheTextField.setEnabled(true);
        this.TrangThaiTauCBx.setEnabled(true);
    }

    public Tau() {
        listTau = new TauDAO().selectAll();
        initComponent();
    }

    public JComboBox<String> getSortComboBox() {
        return SortComboBox;
    }

    public List<TauModel> getListTau() {
        return listTau;
    }

    public JPanel getDanhSachTauPanel() {
        return DanhSachTauPanel;
    }
}