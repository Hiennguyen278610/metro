package org.metro.view.Dialog;

import org.metro.model.KhachHangModal;
import org.metro.service.KhachHangService;

import javax.swing.*;
import java.awt.*;

public class KhachHangDialog {

    // Dialog thêm khách hàng mới
    public void showAddKhachHangDialog(Component parent, Runnable updateCallback) {
        JDialog dialog = new JDialog(SwingUtilities.getWindowAncestor(parent), "Thêm khách hàng mới", Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(parent);
        dialog.setLayout(new BorderLayout());

        // Nếu makh là AUTO_INCREMENT, ta không cho người dùng nhập makh
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblTenKh = new JLabel("Tên khách hàng:");
        JTextField txtTenKh = new JTextField();

        JLabel lblSdt = new JLabel("Số điện thoại:");
        JTextField txtSdt = new JTextField();

        JLabel lblSolan = new JLabel("Số lần đi:");
        JTextField txtSolan = new JTextField();

        formPanel.add(lblTenKh); formPanel.add(txtTenKh);
        formPanel.add(lblSdt); formPanel.add(txtSdt);
        formPanel.add(lblSolan); formPanel.add(txtSolan);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnCancel = new JButton("Hủy");
        btnCancel.addActionListener(e -> dialog.dispose());
        JButton btnAdd = new JButton("Thêm");
        btnAdd.addActionListener(e -> {
            String tenKh = txtTenKh.getText().trim();
            String sdt = txtSdt.getText().trim();
            String solanStr = txtSolan.getText().trim();
            if (tenKh.isEmpty() || sdt.isEmpty() || solanStr.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Vui lòng nhập đầy đủ thông tin!");
                return;
            }
            int solan;
            try {
                solan = Integer.parseInt(solanStr);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Số lần đi phải là số!");
                return;
            }
            // makh = 0 để đánh dấu tự động tăng
            KhachHangModal kh = new KhachHangModal(0, tenKh, sdt, solan);
            if (KhachHangService.insert(kh)) {
                JOptionPane.showMessageDialog(dialog, "Thêm khách hàng thành công!");
                dialog.dispose();
                if(updateCallback != null) updateCallback.run();
            } else {
                JOptionPane.showMessageDialog(dialog, "Thêm khách hàng thất bại!");
            }
        });
        buttonPanel.add(btnCancel);
        buttonPanel.add(btnAdd);

        dialog.add(formPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    // Dialog cập nhật khách hàng
    public void showUpdateKhachHangDialog(Component parent, int maKh, Runnable updateCallback) {
        KhachHangModal kh = KhachHangService.getById(maKh);
        if (kh == null) {
            JOptionPane.showMessageDialog(parent, "Không tìm thấy thông tin khách hàng!");
            return;
        }
        JDialog dialog = new JDialog(SwingUtilities.getWindowAncestor(parent), "Cập nhật khách hàng", Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(parent);
        dialog.setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Mã khách hàng không được chỉnh sửa
        JLabel lblMaKh = new JLabel("Mã khách hàng:");
        JTextField txtMaKh = new JTextField(String.valueOf(kh.getMaKh()));
        txtMaKh.setEditable(false);

        JLabel lblTenKh = new JLabel("Tên khách hàng:");
        JTextField txtTenKh = new JTextField(kh.getTenKh());

        JLabel lblSdt = new JLabel("Số điện thoại:");
        JTextField txtSdt = new JTextField(kh.getSdt());

        JLabel lblSolan = new JLabel("Số lần đi:");
        JTextField txtSolan = new JTextField(String.valueOf(kh.getSolan()));

        // Hiển thị mã khách hàng ở dòng đầu
        formPanel.add(lblMaKh); formPanel.add(txtMaKh);
        formPanel.add(lblTenKh); formPanel.add(txtTenKh);
        formPanel.add(lblSdt); formPanel.add(txtSdt);
        formPanel.add(lblSolan); formPanel.add(txtSolan);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnCancel = new JButton("Hủy");
        btnCancel.addActionListener(e -> dialog.dispose());
        JButton btnUpdate = new JButton("Cập nhật");
        btnUpdate.addActionListener(e -> {
            String tenKh = txtTenKh.getText().trim();
            String sdt = txtSdt.getText().trim();
            String solanStr = txtSolan.getText().trim();
            if (tenKh.isEmpty() || sdt.isEmpty() || solanStr.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Vui lòng nhập đầy đủ thông tin!");
                return;
            }
            int solan;
            try {
                solan = Integer.parseInt(solanStr);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Số lần đi phải là số!");
                return;
            }
            KhachHangModal updatedKh = new KhachHangModal(kh.getMaKh(), tenKh, sdt, solan);
            if (KhachHangService.update(updatedKh)) {
                JOptionPane.showMessageDialog(dialog, "Cập nhật khách hàng thành công!");
                dialog.dispose();
                if(updateCallback != null) updateCallback.run();
            } else {
                JOptionPane.showMessageDialog(dialog, "Cập nhật khách hàng thất bại!");
            }
        });
        buttonPanel.add(btnCancel);
        buttonPanel.add(btnUpdate);

        dialog.add(formPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    // Dialog hiển thị chi tiết khách hàng
    public void showKhachHangDetailDialog(Component parent, KhachHangModal kh) {
        JDialog dialog = new JDialog(SwingUtilities.getWindowAncestor(parent), "Chi tiết khách hàng", Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setSize(400, 250);
        dialog.setLocationRelativeTo(parent);
        dialog.setLayout(new BorderLayout());

        JPanel detailPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        detailPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblMaKh = new JLabel("Mã khách hàng: " + kh.getMaKh());
        JLabel lblTenKh = new JLabel("Tên khách hàng: " + kh.getTenKh());
        JLabel lblSdt = new JLabel("Số điện thoại: " + kh.getSdt());
        JLabel lblSolan = new JLabel("Số lần đi: " + kh.getSolan());

        detailPanel.add(lblMaKh);
        detailPanel.add(lblTenKh);
        detailPanel.add(lblSdt);
        detailPanel.add(lblSolan);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnClose = new JButton("Đóng");
        btnClose.addActionListener(e -> dialog.dispose());
        buttonPanel.add(btnClose);

        dialog.add(detailPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }
}
