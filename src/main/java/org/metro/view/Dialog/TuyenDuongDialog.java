package org.metro.view.Dialog;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;

import org.metro.DAO.TramDAO;
import org.metro.DAO.TuyenDAO;
import org.metro.model.TuyenDuongModel;
import org.metro.model.TramModel; // Ensure TramModel is imported

import java.awt.Frame;

public class TuyenDuongDialog extends JDialog {
    private JTextField txtMaTuyen, txtThoiGianDiChuyen;
    private JComboBox<String> cbxTramDau, cbxTramDich;
    private JButton btnLuu, btnDong;
    private boolean saved = false;

    public enum Mode {
        ADD, EDIT, VIEW
    }

    public TuyenDuongDialog(Frame owner, Mode mode, TuyenDuongModel route) {
        super(owner, true);
        setTitle(getTitleByMode(mode));
        setSize(500, 300);
        setLocationRelativeTo(owner);
        setLayout(new BorderLayout());

        JPanel pnlForm = new JPanel(new GridLayout(4, 2, 10, 10));
        pnlForm.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        pnlForm.add(new JLabel("Mã tuyến:"));
        txtMaTuyen = new JTextField();
        txtMaTuyen.setEditable(false);
        pnlForm.add(txtMaTuyen);

        pnlForm.add(new JLabel("Trạm bắt đầu:"));
        cbxTramDau = new JComboBox<String>();
        List<TramModel> tramList = new TramDAO().selectAll(); // Lay ds tat ca cac tram
        pnlForm.add(cbxTramDau);

        pnlForm.add(new JLabel("Trạm kết thúc:"));
        cbxTramDich = new JComboBox<String>();
        cbxTramDau.addItem("Chọn trạm đầu");
        cbxTramDich.addItem("Chọn trạm đích");
        for (TramModel tram : tramList) {
            cbxTramDau.addItem(tram.toString());
            cbxTramDich.addItem(tram.toString());
        }
        pnlForm.add(cbxTramDich);

        pnlForm.add(new JLabel("Thời gian di chuyển:"));
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

        if (mode == Mode.ADD) {
            int maTuyen = new TuyenDAO().getAutoIncrement();
            System.out.println(maTuyen + "");
            txtMaTuyen.setText(maTuyen + "");
        }
        if (mode == Mode.EDIT || mode == Mode.VIEW) {
            txtMaTuyen.setText(String.valueOf(route.getMatuyen()));
            cbxTramDau.setSelectedIndex(route.getTramdau());
            cbxTramDich.setSelectedIndex(route.getTramdich());
            txtThoiGianDiChuyen.setText(String.valueOf(route.getThoigiandichuyen()));
        }

        // Nếu là chế độ xem, disable tất cả input và ẩn nút lưu
        if (mode == Mode.VIEW) {
            cbxTramDau.setEditable(false);
            cbxTramDich.setEditable(false);
            txtThoiGianDiChuyen.setEditable(false);
            btnLuu.setVisible(false);
        }

        // Action của nút Lưu
        btnLuu.addActionListener(e -> {
            if (!validateInput())
                return;

            // Nếu hợp lệ thì xử lý lưu
            int ma = Integer.parseInt(txtMaTuyen.getText());
            int tramBD = cbxTramDau.getSelectedIndex();
            int tramKT = cbxTramDich.getSelectedIndex();
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

        btnDong.addActionListener(e -> dispose());
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
            int tramBD = cbxTramDau.getSelectedIndex();
            int tramKT = cbxTramDich.getSelectedIndex();
            int thoiGian = Integer.parseInt(txtThoiGianDiChuyen.getText().trim());

            if (tramBD == 0 || tramKT == 0 || thoiGian <= 0 || tramBD == tramKT) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập các giá trị hợp lệ", "Lỗi dữ liệu",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public boolean isSaved() {
        return saved;
    }
}
