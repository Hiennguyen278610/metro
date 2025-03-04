package org.metro.view.Dialog;

import org.metro.model.KhachHangDTO;
import org.metro.service.KhachHangBUS;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.List;

public class KhachHangDialog extends JDialog {

    private JTextField makhField, tenkhField, sdtField, matuyenField;
    private List<KhachHangDTO> listkh;

    public KhachHangDialog(Frame parent) {
        super(parent, "Thêm Khách Hàng", true);
        setSize(400, 300);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        add(createInputPanel(), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);

        setVisible(true);
    }

    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        makhField = new JTextField();
        tenkhField = new JTextField();
        sdtField = new JTextField();
        matuyenField = new JTextField();

        panel.add(new JLabel("Mã Khách Hàng:"));
        panel.add(makhField);
        panel.add(new JLabel("Tên Khách Hàng:"));
        panel.add(tenkhField);
        panel.add(new JLabel("Số Điện Thoại:"));
        panel.add(sdtField);
        panel.add(new JLabel("Mã Tuyến:"));
        panel.add(matuyenField);

        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton addButton = new JButton("Thêm");
        JButton cancelButton = new JButton("Hủy");

        addButton.addActionListener(e -> addKhachHang());
        cancelButton.addActionListener(e -> dispose());

        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);
        return buttonPanel;
    }

    private void addKhachHang() {
        try {
            int makh = Integer.parseInt(makhField.getText().trim());
            String tenkh = tenkhField.getText().trim();
            String sdt = sdtField.getText().trim();
            String matuyen = matuyenField.getText().trim();

            if (tenkh.isEmpty() || sdt.isEmpty() || matuyen.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!isPhoneNumber(sdt)) {
                JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            KhachHangDTO kh = new KhachHangDTO(makh, tenkh, sdt, matuyen, LocalDateTime.now());

            if (KhachHangBUS.insert(kh)) {
                JOptionPane.showMessageDialog(this, "Thêm khách hàng thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                dispose();

                // Cập nhật lại danh sách khách hàng
                listkh = KhachHangBUS.search("", "Tất cả");
                loadDataTable(listkh);
            } else {
                JOptionPane.showMessageDialog(this, "Thêm khách hàng thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Mã khách hàng phải là số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean isPhoneNumber(String sdt) {
        return sdt.matches("\\d{10,11}");
    }

    private void loadDataTable(List<KhachHangDTO> listkh) {
        // TODO: Cập nhật JTable hoặc danh sách hiển thị khách hàng sau khi thêm mới
    }
}
