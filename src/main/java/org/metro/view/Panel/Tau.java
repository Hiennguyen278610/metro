package org.metro.view.Panel;

import javax.swing.*;
import javax.swing.border.MatteBorder;

import org.metro.DAO.TauDAO;
import org.metro.controller.TauController;
import org.metro.model.TauModel;
import org.metro.util.ExcelExporter;
import org.metro.view.Component.IntegratedSearch;
import org.metro.view.Component.RoundedPanel;
import org.metro.view.Dialog.TauDialog;

import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.formdev.flatlaf.extras.FlatSVGIcon;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.util.List;
import java.util.TimerTask;
import java.util.Timer;

public class Tau extends JPanel {
    Color BackgroundColor = new Color(0, 2, 2);
    List<TauModel> listTau;
    JComboBox<String> SortComboBox;
    JLabel TongSoTauLabel;
    JPanel DanhSachTauPanel;
    JTextField MaTauTextField, SoGheTextField, NgayNhapTauTextField;
    JComboBox<String> TrangThaiTauCBx;
    JLabel HinhAnhTau;
    IntegratedSearch search;
    Timer searchTimer;
    private TauController action = new TauController(this);

    public void initComponent() {
        this.setBackground(Color.white);
        this.setLayout(new FlowLayout(1, 0, 0));
        this.setPreferredSize(new Dimension(900, 600));

        JPanel ThongTinTauPanel = new JPanel();
        ThongTinTauPanel.setBackground(Color.pink);
        ThongTinTauPanel.setPreferredSize(new Dimension(900, 200));
        ThongTinTauPanel.setLayout(new FlowLayout(1, 0, 0));

        HinhAnhTau = new JLabel();
        HinhAnhTau.setPreferredSize(new Dimension(300, 200));
        // HinhAnhTau.setBackground(Color.white);
        HinhAnhTau.setOpaque(true);
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
                new String[] { "", "Đang hoạt động", "Đang bảo trì", "Ngừng hoạt động" });
        TrangThaiTauCBx.setPreferredSize(new Dimension(200, 30));

        JLabel NgayNhapTauLabel = new JLabel("Ngày nhập tàu: ");
        NgayNhapTauTextField = new JTextField();
        NgayNhapTauTextField.setPreferredSize(new Dimension(200, 30));

        JButton ThemTauButton = new JButton("THÊM");
        ThemTauButton.addActionListener(e -> {
            Window window = SwingUtilities.getWindowAncestor(this);
            TauDialog dialog = new TauDialog(window);
            dialog.setVisible(true);

            if (dialog.isSaved())
                updateData();

        });
        JButton SuaTauButton = new JButton("SỬA");
        SuaTauButton.addActionListener(e -> {
            setTextfieldEnable();
        });
        JButton XoaTauButton = new JButton("XÓA");
        XoaTauButton.addActionListener(e -> {
            XoaTau();
        });
        JButton LuuTauButton = new JButton("LƯU");
        LuuTauButton.addActionListener(e -> {
            if (ValidateDuLieu() == true)
                setTextfieldDisable();
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
        TongSoTauPanel.setPreferredSize(new Dimension(900, 100));
        TongSoTauPanel.setBorder(new MatteBorder(0, 0, 2, 0, Color.black));
        TongSoTauPanel.setBackground(Color.white);
        TongSoTauPanel.setLayout(null);
        TongSoTauLabel = new JLabel("Tổng số tàu (" + listTau.size() + ")");
        TongSoTauLabel.setFont(new Font("Arial", Font.BOLD, 22));
        TongSoTauLabel.setBounds(30, 55, 200, 40);
        TongSoTauPanel.add(TongSoTauLabel);
        JLabel SortLabel = new JLabel("Sắp xếp theo: ");
        SortLabel.setFont(new Font("Arial", Font.BOLD, 16));
        SortLabel.setBounds(650, 5, 200, 40);
        TongSoTauPanel.add(SortLabel);
        search = new IntegratedSearch(new String[] { "Tất cả", "Mã tàu", "Sức chứa", "Ngày nhập tàu" });
        TongSoTauPanel.add(search);
        search.setBounds(30, 5, 400, 36);
        search.getCbxChoose().addItemListener(action);
        search.txtSearchForm.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (searchTimer != null) {
                    searchTimer.cancel();
                }
                searchTimer = new Timer();
                searchTimer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        SwingUtilities.invokeLater(() -> performSearch());
                    }
                }, 300);
            }
        });
        search.btnReset.addActionListener(e -> {
            search.txtSearchForm.setText("");
            updateData();
        });

        JButton XuatExcelButton = new JButton("Xuất Excel");
        XuatExcelButton.setBounds(760, 55, 100, 30);
        XuatExcelButton.setFont(new Font("Arial", Font.BOLD, 16));
        XuatExcelButton.setBackground(Color.white);
        XuatExcelButton.setBorder(new MatteBorder(0, 0, 2, 0, Color.black));
        XuatExcelButton.setFocusable(false);
        XuatExcelButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showSaveDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                String path = fileChooser.getSelectedFile().getAbsolutePath();
                if (!path.endsWith(".xlsx")) {
                    path += ".xlsx";
                }
                ExcelExporter.exportToExcel(listTau, path); // thay myJTable bằng JTable của bạn
            }
        });
        TongSoTauPanel.add(XuatExcelButton);

        this.add(TongSoTauPanel);

        SortComboBox = new JComboBox<>();
        SortComboBox.addItem("Mã tàu");
        SortComboBox.addItem("Sức chứa");
        SortComboBox.addItem("Ngày nhập tàu");
        SortComboBox.addItem("Trạng thái tàu");
        SortComboBox.setBounds(760, 14, 100, 22);
        TongSoTauPanel.add(SortComboBox);

        SortComboBox.addItemListener(action);

        DanhSachTauPanel = new JPanel();
        DanhSachTauPanel.setLayout(new FlowLayout(0, 36, 40));
        DanhSachTauPanel.setPreferredSize(new Dimension(900, ((listTau.size() + 5) / 3) * 260));
        DanhSachTauPanel.setBackground(Color.white);

        for (int i = 0; i < listTau.size(); i++) {
            RoundedPanel panel = createTauPanel(listTau.get(i));
            if (panel != null) DanhSachTauPanel.add(panel);
        }

        JScrollPane DanhSachTauScrollPane = new JScrollPane(DanhSachTauPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        DanhSachTauScrollPane.setPreferredSize(new Dimension(900, 500));
        // DanhSachTauScrollPane.setBorder(BorderFactory.createEmptyBorder());
        this.add(DanhSachTauScrollPane);
    }

    public void updateData(List<TauModel> list) {
        this.TongSoTauLabel.setText("Tổng số tàu (" + list.size() + ")");
        this.DanhSachTauPanel.removeAll();
        for (int i = 0; i < list.size(); i++) {
            RoundedPanel panel = createTauPanel(list.get(i));
            DanhSachTauPanel.add(panel);
        }
        this.DanhSachTauPanel.revalidate();
        this.DanhSachTauPanel.repaint();
    }

    public void XoaTau() {
        String matau = this.MaTauTextField.getText();
        int delete = new TauDAO().delete(Integer.parseInt(matau));
        if (delete > 0) {
            JOptionPane.showMessageDialog(this, "Xóa thành công!");
            updateData();
        } else
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
    }

    public void updateData() {
        System.err.println("hihihihi");
        this.DanhSachTauPanel.removeAll();
        listTau = new TauDAO().selectAll();
        this.TongSoTauLabel.setText("Tổng số tàu (" + listTau.size() + ")");
        for (int i = 0; i < listTau.size(); i++) {
            RoundedPanel panel = createTauPanel(listTau.get(i));
            if (panel != null) DanhSachTauPanel.add(panel);
        }
        this.DanhSachTauPanel.revalidate();
        this.DanhSachTauPanel.repaint();
    }

    private void performSearch() {
        String type = (String) search.cbxChoose.getSelectedItem();
        String txt = search.txtSearchForm.getText().trim();
        System.out.println("Đang tìm kiếm: " + txt + " - Loại: " + type);
        List<TauModel> result = new TauDAO().search(txt, type);
        updateData(result);
    }

    private boolean SaveToDB() {
        System.out.println("hihihi");
        // Luoi qa mai lam nha ae
        String ma = this.MaTauTextField.getText();
        int soghe = Integer.parseInt(this.SoGheTextField.getText());
        String ngayNhap = this.NgayNhapTauTextField.getText().trim(); // Ví dụ: "07-04-2025"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(ngayNhap, formatter);

        String trangThai = (String) this.TrangThaiTauCBx.getSelectedItem();
        TauModel hihi = new TauModel(Integer.parseInt(ma), soghe, trangThai, localDate);

        int update = new TauDAO().update(hihi);
        if (update > 0) {
            JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
            updateData();
        } else
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
        return update > 0;
    }

    private boolean ValidateDuLieu() {
        int sucChua;
        try {
            sucChua = Integer.parseInt(this.SoGheTextField.getText());
            if (sucChua <= 0) {
                JOptionPane.showMessageDialog(this, "Sức chứa phải lớn hơn 0", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Sức chứa phải là số nguyên", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        String ngayNhapStr = this.NgayNhapTauTextField.getText().trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        try {
            LocalDate.parse(ngayNhapStr, formatter);
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(null, "Ngày nhập không đúng định dạng dd-MM-yyyy", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return SaveToDB();
    }

    private RoundedPanel createTauPanel(TauModel tau) {
        if (tau.isVisible() == false) {
            return null;
        }
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
        this.MaTauTextField.setText(String.valueOf(tau.getMatau()));
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
        // this.MaTauTextField.setEnabled(true);
        this.NgayNhapTauTextField.setEnabled(true);
        this.SoGheTextField.setEnabled(true);
        this.TrangThaiTauCBx.setEnabled(true);
    }

    public Tau() {
        listTau = new TauDAO().selectAll();
        initComponent();
        setTextfieldDisable();
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