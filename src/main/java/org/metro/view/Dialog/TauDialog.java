package org.metro.view.Dialog;

import javax.swing.*;

import org.metro.DAO.TauDAO;
import org.metro.model.TauModel;

import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TauDialog extends JDialog {
    private JTextField txtSoGhe;
    private JComboBox<String> cbxTrangThai;
    private JTextField txtNgayNhap;
    private boolean saved = false;

    public TauDialog(Window frame) {
        super(frame, "Thêm tàu", ModalityType.APPLICATION_MODAL);
        initUI();
        setLocationRelativeTo(frame);
    }

    private void initUI() {
        setLayout(new BorderLayout(10, 10));

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        txtSoGhe = new JTextField();
        cbxTrangThai = new JComboBox<>(new String[] {
                "-- Chọn trạng thái --", "Đang hoạt động", "Đang bảo trì", "Ngừng hoạt động"
        });

        txtNgayNhap = new JTextField(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        txtNgayNhap.setEditable(false);

        panel.add(new JLabel("Số ghế:"));
        panel.add(txtSoGhe);

        panel.add(new JLabel("Trạng thái:"));
        panel.add(cbxTrangThai);

        panel.add(new JLabel("Ngày nhập:"));
        panel.add(txtNgayNhap);

        add(panel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton btnLuu = new JButton("Lưu");
        JButton btnHuy = new JButton("Hủy");

        buttonPanel.add(btnLuu);
        buttonPanel.add(btnHuy);
        add(buttonPanel, BorderLayout.SOUTH);

        btnLuu.addActionListener(e -> {
            if (!validateData())
                return;

            int success = new TauDAO()
                    .insert(new TauModel(
                            Integer.parseInt(this.txtSoGhe.getText().trim()),
                            this.cbxTrangThai.getSelectedItem().toString(),
                            LocalDate.parse(txtNgayNhap.getText().trim())));
            if (success > 0) {
                saved = true;
                JOptionPane.showMessageDialog(this, "Đã lưu thành công!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Lưu thất bại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnHuy.addActionListener(e -> dispose());

        pack();
    }

    private boolean validateData() {
        String soGheStr = txtSoGhe.getText().trim();

        if (soGheStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số ghế.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        try {
            int soGhe = Integer.parseInt(soGheStr);
            if (soGhe <= 0) {
                JOptionPane.showMessageDialog(this, "Số ghế phải lớn hơn 0.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Số ghế phải là số nguyên.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (cbxTrangThai.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn trạng thái.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    public boolean isSaved() {
        return saved;
    }
}
