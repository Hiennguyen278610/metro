package org.metro.view.Dialog;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import org.metro.DAO.TuyenDAO;
import org.metro.model.TuyenDuongModel;

import java.awt.Frame;

public class TuyenDuongDialog extends JDialog {
    private JTextField txtMaTuyen, txtTramDau, txtTramDich, txtThoiGianDiChuyen;
    private JButton btnLuu, btnDong;
    private boolean saved = false;

    public enum Mode {
        ADD, EDIT, VIEW
    }

    public TuyenDuongDialog(Frame owner, Mode mode, TuyenDuongModel route) {
        super(owner, true);
        setTitle(getTitleByMode(mode));
        setSize(400, 300);
        setLocationRelativeTo(owner);
        setLayout(new BorderLayout());

        JPanel pnlForm = new JPanel(new GridLayout(4, 2, 10, 10));
        pnlForm.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        pnlForm.add(new JLabel("Mã tuyến:"));
        txtMaTuyen = new JTextField();
        pnlForm.add(txtMaTuyen);

        pnlForm.add(new JLabel("Tên tuyến:"));
        txtTramDau = new JTextField();
        pnlForm.add(txtTramDau);

        pnlForm.add(new JLabel("Độ dài (km):"));
        txtTramDich = new JTextField();
        pnlForm.add(txtTramDich);

        pnlForm.add(new JLabel("Số ga:"));
        txtThoiGianDiChuyen = new JTextField();
        pnlForm.add(txtThoiGianDiChuyen);

        add(pnlForm, BorderLayout.CENTER);

        JPanel pnlButtons = new JPanel();
        btnLuu = new JButton("Lưu");
        btnDong = new JButton("Đóng");

        pnlButtons.add(btnLuu);
        pnlButtons.add(btnDong);
        add(pnlButtons, BorderLayout.SOUTH);

        // Nếu là sửa hoặc xem, thì load dữ liệu lên form
        if (route != null) {
            txtMaTuyen.setText(route.getMatuyen() + "");
            txtTramDau.setText(route.getTramdau() + "");
            txtTramDich.setText(String.valueOf(route.getTramdich()));
            txtThoiGianDiChuyen.setText(String.valueOf(route.getThoigiandichuyen()));
        }

        if (mode == Mode.ADD || mode == Mode.EDIT) {
            txtMaTuyen.setEditable(false); // Không cho sửa mã tuyến khi thêm mới
        }

        // Nếu là chế độ xem, disable tất cả input và ẩn nút lưu
        if (mode == Mode.VIEW) {
            txtMaTuyen.setEditable(false);
            txtTramDau.setEditable(false);
            txtTramDich.setEditable(false);
            txtThoiGianDiChuyen.setEditable(false);
            btnLuu.setVisible(false);
        }

        // Action của nút Lưu
        btnLuu.addActionListener(_ -> {
            if (!validateInput())
                return;

            // Nếu hợp lệ thì xử lý lưu
            int ma = Integer.parseInt(txtMaTuyen.getText());
            int tramBD = Integer.parseInt(txtTramDau.getText());
            int tramKT = Integer.parseInt(txtTramDich.getText());
            int tg = Integer.parseInt(txtThoiGianDiChuyen.getText());

            if (mode == Mode.ADD) {
                int success = new TuyenDAO().insert(new TuyenDuongModel(ma, tramBD, tramKT, tg, "Đang hoạt động"));
                if (success > 0) {
                    saved = true;
                    JOptionPane.showMessageDialog(this, "Thêm tuyến metro thành công!");
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm tuyến metro thất bại!", "Lỗi",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            if (mode == Mode.EDIT) {
                int success = new TuyenDAO().update(new TuyenDuongModel(ma, tramBD, tramKT, tg, "Đang hoạt động"));
                if (success > 0) {
                    saved = true;
                    JOptionPane.showMessageDialog(this, "Cập nhật tuyến metro thành công!");
                } else {
                    JOptionPane.showMessageDialog(this, "Cập nhật tuyến metro thất bại!", "Lỗi",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            dispose();
        });

        btnDong.addActionListener(_ -> dispose());
    }

    private String getTitleByMode(Mode mode) {
        switch (mode) {
            case ADD:
                return "Thêm tuyến metro";
            case EDIT:
                return "Sửa tuyến metro";
            case VIEW:
                return "Chi tiết tuyến metro";
            default:
                return "Thông tin tuyến";
        }
    }

    private boolean validateInput() {
        try {
            int tramBD = Integer.parseInt(txtTramDau.getText().trim());
            int tramKT = Integer.parseInt(txtTramDich.getText().trim());
            int thoiGian = Integer.parseInt(txtThoiGianDiChuyen.getText().trim());

            if (tramBD <= 0 || tramKT <= 0 || thoiGian <= 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập các giá trị là số nguyên dương!", "Lỗi dữ liệu",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public boolean isSaved() {
        return saved;
    }
}
