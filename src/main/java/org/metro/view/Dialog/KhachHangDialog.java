package org.metro.view.Dialog;

import org.metro.model.KhachHangModel;
import org.metro.service.KhachHangService;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Pattern;

public class KhachHangDialog {
    private static final Pattern PHONE_PATTERN = Pattern.compile("^0\\d{9}$");
    private static final Pattern NAME_PATTERN = Pattern.compile("^[\\p{L} ]+$");
    private boolean isValidPhoneNumber(String phone) {
        if (phone == null || phone.isEmpty()) return false;
        return PHONE_PATTERN.matcher(phone).matches();
    }
    private boolean isValidName(String name) {
        if(name == null || name.trim().isEmpty()) return false;
        return NAME_PATTERN.matcher(name).matches();
    }
    // Dialog thêm khách hàng mới
    public void showAddKhachHangDialog(Component parent, Runnable updateCallback) {
        showAddKhachHangDialog(parent, "", updateCallback);
    }

    // Dialog thêm khách hàng mới với số điện thoại được điền sẵn
    public void showAddKhachHangDialog(Component parent, String phoneNumber, Runnable updateCallback) {
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
        JTextField txtSdt = new JTextField(phoneNumber); // Điền sẵn số điện thoại

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
            
            // Kiểm tra các trường dữ liệu nhập vào
            if (!isValidName(tenKh)) {
                JOptionPane.showMessageDialog(dialog, "Tên khách hàng không hợp lệ! Tên chỉ chứa chữ cái và dấu cách",
                        "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
                txtTenKh.requestFocus();
                return;
            }
            
            if (!isValidPhoneNumber(sdt)) {
                JOptionPane.showMessageDialog(dialog, 
                    "Số điện thoại không hợp lệ! Số điện thoại phải có đúng 10 chữ số và bắt đầu bằng số 0.",
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
                txtSdt.requestFocus();
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
            KhachHangModel kh = new KhachHangModel(0, tenKh, sdt, solan);
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
        KhachHangModel kh = KhachHangService.getById(maKh);
        if (kh == null) {
            JOptionPane.showMessageDialog(parent, "Không tìm thấy thông tin khách hàng!");
            return;
        }
        JDialog dialog = new JDialog(SwingUtilities.getWindowAncestor(parent), "Cập nhật khách hàng", Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(parent);
        dialog.setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
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

            if (!isValidName(tenKh)) {
                JOptionPane.showMessageDialog(dialog, "Tên chỉ chứa chữ cái và dấu cách",
                        "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
                txtTenKh.requestFocus();
                return;
            }
            
            if (!isValidPhoneNumber(sdt)) {
                JOptionPane.showMessageDialog(dialog, 
                    "Số điện thoại không hợp lệ! Số điện thoại phải có đúng 10 chữ số và bắt đầu bằng số 0.",
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
                txtSdt.requestFocus();
                return;
            }
            
            int solan;
            try {
                solan = Integer.parseInt(solanStr);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Số lần đi phải là số!");
                return;
            }
            KhachHangModel updatedKh = new KhachHangModel(kh.getMaKh(), tenKh, sdt, solan);
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
    public void showKhachHangDetailDialog(Component parent, KhachHangModel kh) {
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
