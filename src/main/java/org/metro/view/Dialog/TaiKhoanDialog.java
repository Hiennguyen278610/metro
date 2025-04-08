package org.metro.view.Dialog;

import org.metro.model.TaiKhoanModel;
import org.metro.service.TaiKhoanService;

import javax.swing.*;
import java.awt.*;

public class TaiKhoanDialog {

    // Dialog thêm tài khoản mới, tích hợp tìm kiếm nhân viên (giả lập qua input
    // dialog)
    public void showAddTaiKhoanDialog(Component parent, Runnable updateCallback) {
        String input = JOptionPane.showInputDialog(parent, "Nhập mã nhân viên cần chọn:");
        int selectedManv;
        try {
            selectedManv = Integer.parseInt(input.trim());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(parent, "Mã nhân viên không hợp lệ!");
            return;
        }
        JDialog dialog = new JDialog(SwingUtilities.getWindowAncestor(parent), "Thêm tài khoản mới",
                Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setSize(400, 350);
        dialog.setLocationRelativeTo(parent);
        dialog.setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblMaNV = new JLabel("Mã nhân viên:");
        JTextField txtMaNV = new JTextField(String.valueOf(selectedManv));
        txtMaNV.setEditable(false);

        JLabel lblMatKhau = new JLabel("Mật khẩu:");
        JPasswordField txtMatKhau = new JPasswordField();

        JLabel lblNhomQuyen = new JLabel("Nhóm quyền:");
        String[] roleOptions = { "Admin", "User", "Nhân viên" };
        JComboBox<String> cbxNhomQuyen = new JComboBox<>(roleOptions);

        JLabel lblTrangThai = new JLabel("Trạng thái:");
        String[] statusOptions = { "Hoạt động", "Ngừng hoạt động" };
        JComboBox<String> cbxTrangThai = new JComboBox<>(statusOptions);
        cbxTrangThai.setSelectedIndex(0);

        formPanel.add(lblMaNV);
        formPanel.add(txtMaNV);
        formPanel.add(lblMatKhau);
        formPanel.add(txtMatKhau);
        formPanel.add(lblNhomQuyen);
        formPanel.add(cbxNhomQuyen);
        formPanel.add(lblTrangThai);
        formPanel.add(cbxTrangThai);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnCancel = new JButton("Hủy");
        btnCancel.addActionListener(_ -> dialog.dispose());
        JButton btnAdd = new JButton("Thêm");
        btnAdd.addActionListener(_ -> {
            String matKhau = new String(txtMatKhau.getPassword()).trim();
            if (matKhau.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Vui lòng nhập mật khẩu!");
                return;
            }
            int nhomQuyen;
            String role = (String) cbxNhomQuyen.getSelectedItem();
            if ("Admin".equals(role)) {
                nhomQuyen = 1;
            } else if ("User".equals(role)) {
                nhomQuyen = 2;
            } else if ("Nhân viên".equals(role)) {
                nhomQuyen = 3;
            } else {
                nhomQuyen = 0;
            }
            int trangThai = "Hoạt động".equals((String) cbxTrangThai.getSelectedItem()) ? 1 : 0;
            TaiKhoanModel tk = new TaiKhoanModel(selectedManv, matKhau, nhomQuyen, trangThai);
            if (TaiKhoanService.insert(tk)) {
                JOptionPane.showMessageDialog(dialog, "Thêm tài khoản thành công!");
                dialog.dispose();
                if (updateCallback != null)
                    updateCallback.run();
            } else {
                JOptionPane.showMessageDialog(dialog, "Thêm tài khoản thất bại!");
            }
        });
        buttonPanel.add(btnCancel);
        buttonPanel.add(btnAdd);

        dialog.add(formPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    // Dialog cập nhật tài khoản (các trường dùng JComboBox để chọn nhóm quyền và
    // trạng thái)
    public void showUpdateTaiKhoanDialog(Component parent, int manv, Runnable updateCallback) {
        TaiKhoanModel tk = TaiKhoanService.getByID(manv);
        if (tk == null) {
            JOptionPane.showMessageDialog(parent, "Không tìm thấy thông tin tài khoản!");
            return;
        }
        JDialog dialog = new JDialog(SwingUtilities.getWindowAncestor(parent), "Cập nhật tài khoản",
                Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setSize(400, 350);
        dialog.setLocationRelativeTo(parent);
        dialog.setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblMaNV = new JLabel("Mã nhân viên:");
        JTextField txtMaNV = new JTextField(String.valueOf(tk.getManv()));
        txtMaNV.setEditable(false);

        JLabel lblMatKhau = new JLabel("Mật khẩu:");
        JPasswordField txtMatKhau = new JPasswordField(tk.getMatkhau());

        JLabel lblNhomQuyen = new JLabel("Nhóm quyền:");
        String[] roleOptions = { "Admin", "User", "Nhân viên" };
        JComboBox<String> cbxNhomQuyen = new JComboBox<>(roleOptions);
        switch (tk.getManhomquyen()) {
            case 1:
                cbxNhomQuyen.setSelectedItem("Admin");
                break;
            case 2:
                cbxNhomQuyen.setSelectedItem("User");
                break;
            case 3:
                cbxNhomQuyen.setSelectedItem("Nhân viên");
                break;
            default:
                cbxNhomQuyen.setSelectedIndex(0);
        }

        JLabel lblTrangThai = new JLabel("Trạng thái:");
        String[] statusOptions = { "Hoạt động", "Ngừng hoạt động" };
        JComboBox<String> cbxTrangThai = new JComboBox<>(statusOptions);
        cbxTrangThai.setSelectedItem(tk.getTrangthai() == 1 ? "Hoạt động" : "Ngừng hoạt động");

        formPanel.add(lblMaNV);
        formPanel.add(txtMaNV);
        formPanel.add(lblMatKhau);
        formPanel.add(txtMatKhau);
        formPanel.add(lblNhomQuyen);
        formPanel.add(cbxNhomQuyen);
        formPanel.add(lblTrangThai);
        formPanel.add(cbxTrangThai);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnCancel = new JButton("Hủy");
        btnCancel.addActionListener(_ -> dialog.dispose());
        JButton btnUpdate = new JButton("Cập nhật");
        btnUpdate.addActionListener(_ -> {
            String matKhau = new String(txtMatKhau.getPassword()).trim();
            if (matKhau.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Vui lòng nhập mật khẩu!");
                return;
            }
            int nhomQuyen;
            String role = (String) cbxNhomQuyen.getSelectedItem();
            if ("Admin".equals(role)) {
                nhomQuyen = 1;
            } else if ("User".equals(role)) {
                nhomQuyen = 2;
            } else if ("Nhân viên".equals(role)) {
                nhomQuyen = 3;
            } else {
                nhomQuyen = 0;
            }
            int trangThai = "Hoạt động".equals((String) cbxTrangThai.getSelectedItem()) ? 1 : 0;
            TaiKhoanModel updatedTk = new TaiKhoanModel(tk.getManv(), matKhau, nhomQuyen, trangThai);
            if (TaiKhoanService.update(updatedTk)) {
                JOptionPane.showMessageDialog(dialog, "Cập nhật tài khoản thành công!");
                dialog.dispose();
                if (updateCallback != null)
                    updateCallback.run();
            } else {
                JOptionPane.showMessageDialog(dialog, "Cập nhật tài khoản thất bại!");
            }
        });
        buttonPanel.add(btnCancel);
        buttonPanel.add(btnUpdate);

        dialog.add(formPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    // Dialog hiển thị chi tiết tài khoản
    public void showTaiKhoanDetailDialog(Component parent, TaiKhoanModel tk) {
        JDialog dialog = new JDialog(SwingUtilities.getWindowAncestor(parent), "Chi tiết tài khoản",
                Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setSize(400, 250);
        dialog.setLocationRelativeTo(parent);
        dialog.setLayout(new BorderLayout());

        JPanel detailPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        detailPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblMaNV = new JLabel("Mã nhân viên: " + tk.getManv());
        String nhomQuyenStr;
        switch (tk.getManhomquyen()) {
            case 1:
                nhomQuyenStr = "Admin";
                break;
            case 2:
                nhomQuyenStr = "User";
                break;
            case 3:
                nhomQuyenStr = "Nhân viên";
                break;
            default:
                nhomQuyenStr = "Unknown";
        }
        JLabel lblNhomQuyen = new JLabel("Nhóm quyền: " + nhomQuyenStr);
        String trangThaiStr = tk.getTrangthai() == 1 ? "Hoạt động" : "Ngừng hoạt động";
        JLabel lblTrangThai = new JLabel("Trạng thái: " + trangThaiStr);

        detailPanel.add(lblMaNV);
        detailPanel.add(lblNhomQuyen);
        detailPanel.add(lblTrangThai);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnClose = new JButton("Đóng");
        btnClose.addActionListener(_ -> dialog.dispose());
        buttonPanel.add(btnClose);

        dialog.add(detailPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }
}
