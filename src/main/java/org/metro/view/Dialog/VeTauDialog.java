package org.metro.view.Dialog;

import org.metro.controller.VeTauController;
import org.metro.model.KhachHangModel;
import org.metro.model.LichTrinhModel;
import org.metro.model.NhanVienModel;
import org.metro.model.VeTauModel;
import org.metro.service.KhachHangService;
import org.metro.service.TuyenDuongService;
import org.metro.service.VeTauService;
import org.metro.util.ComboBoxUtil;
import org.metro.view.Component.handleComponents;
import org.metro.view.Panel.VeTau;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class VeTauDialog extends JDialog {
    private JTextField sdtKhachTextField, giaveTextField;
    private JComboBox<LichTrinhModel> machuyenComboBox;
    private DefaultComboBoxModel<LichTrinhModel> machuyenComboBoxModel;
    private JButton ok, cancel;
    private String type;
    private JPanel contentPanel;
    private VeTau veTau;
    private VeTauModel vetauModel;
    private VeTauController action;
    private KhachHangDialog khachHangDialog;
    
    // Đơn giá vé: 500 VNĐ/phút di chuyển
    private static final double DONGIAVE = 500;

    // Dialog thêm, sửa, chi tiết vé tàu
    public VeTauDialog(Frame parent, String type, VeTau veTau, VeTauModel vetauModel) {
        super(parent, true);
        this.veTau = veTau;
        this.type = type;
        this.vetauModel = vetauModel;
        this.action = new VeTauController(veTau, this);
        this.khachHangDialog = new KhachHangDialog();
        this.setTitle(setTitleType());
        this.setSize(500, 400);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.init();
        checkButtonClicked();
    }

    private void init() {
        contentPanel = new JPanel();
        contentPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 20, 5, 20);

        // Labels
        handleComponents.addLabelGBL(contentPanel, "Mã chuyến:", 0, 0, gbc);
        handleComponents.addLabelGBL(contentPanel, "Số điện thoại khách:", 0, 1, gbc);
        handleComponents.addLabelGBL(contentPanel, "Giá vé:", 0, 2, gbc);

        // Khởi tạo tất cả các trường nhập liệu trước
        sdtKhachTextField = handleComponents.addTextFieldGBL(contentPanel, 20, 1, 1, gbc);
        giaveTextField = handleComponents.addTextFieldGBL(contentPanel, 20, 1, 2, gbc);
        
        // Vô hiệu hóa việc chỉnh sửa giá vé
        giaveTextField.setEditable(false);

        // Khởi tạo combobox
        machuyenComboBoxModel = new DefaultComboBoxModel<>();
        machuyenComboBox = new JComboBox<>(machuyenComboBoxModel);
        
        // Thêm ItemListener để cập nhật giá vé khi chọn chuyến
        machuyenComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    updateGiaVe();
                }
            }
        });

        gbc.gridx = 1;
        gbc.gridy = 0;
        contentPanel.add(machuyenComboBox, gbc);
        
        // Sau khi đã khởi tạo các trường giao diện, tiến hành nạp dữ liệu
        // Lấy danh sách lịch trình và lọc theo điều kiện
        Supplier<List<LichTrinhModel>> lichTrinhSupplier = ComboBoxUtil.getDataSupplier(LichTrinhModel.class);
        if (lichTrinhSupplier != null) {
            List<LichTrinhModel> allLichTrinh = lichTrinhSupplier.get();
            LocalDate today = LocalDate.now();

            List<LichTrinhModel> filteredLichTrinh = allLichTrinh.stream()
                .filter(lt -> "Chờ khởi hành".equals(lt.getTrangthailichtrinh()))
                .filter(lt -> lt.getTgkhoihanh() != null && lt.getTgkhoihanh().toLocalDate().isEqual(today))
                .collect(Collectors.toList());

            machuyenComboBoxModel.removeAllElements(); // Xóa các item cũ (nếu có)
            
            // Chỉ thêm các phần tử nếu danh sách không rỗng
            if (!filteredLichTrinh.isEmpty()) {
                for (LichTrinhModel lt : filteredLichTrinh) {
                    machuyenComboBoxModel.addElement(lt);
                }
                // Gọi updateGiaVe sau khi đã nạp dữ liệu vào combobox
                updateGiaVe();
            } else {
                // Đặt giá trị mặc định cho giá vé nếu không có lịch trình nào
                giaveTextField.setText("0");
            }
        } else {
            System.err.println("Không thể lấy danh sách lịch trình.");
            // Đặt giá trị mặc định cho giá vé
            giaveTextField.setText("0");
        }

        // Buttons - Đặt text chính xác cho mỗi loại dialog
        gbc.gridwidth = 1;
        if ("create".equals(type)) {
            ok = handleComponents.addButtonGBL(contentPanel, "THÊM", 0, 3, gbc);
            cancel = handleComponents.addButtonGBL(contentPanel, "HỦY", 1, 3, gbc);
        } else if ("update".equals(type)) {
            ok = handleComponents.addButtonGBL(contentPanel, "CẬP NHẬT", 0, 3, gbc);
            cancel = handleComponents.addButtonGBL(contentPanel, "HỦY", 1, 3, gbc);
        } else {
            ok = handleComponents.addButtonGBL(contentPanel, "ĐÓNG", 0, 3, gbc);
        }

        if (ok != null) {ok.addActionListener(action);}
        if (cancel != null) {cancel.addActionListener(e -> dispose());}

        this.add(contentPanel);
    }
    
    // Phương thức để cập nhật giá vé dựa trên chuyến đã chọn
    private void updateGiaVe() {
        if (giaveTextField == null) {
            System.err.println("Lỗi: giaveTextField chưa được khởi tạo!");
            return;
        }
        
        if (machuyenComboBox == null || machuyenComboBox.getSelectedItem() == null) {
            giaveTextField.setText("0");
            return;
        }
        
        LichTrinhModel selectedLichTrinh = (LichTrinhModel) machuyenComboBox.getSelectedItem();
        int matuyen = selectedLichTrinh.getMatuyen();
        
        // Lấy thông tin tuyến đường từ mã tuyến
        org.metro.model.TuyenDuongModel tuyenDuong = TuyenDuongService.getById(matuyen);
        
        if (tuyenDuong != null) {
            // Tính giá vé dựa trên thời gian di chuyển và đơn giá
            int thoiGianDiChuyen = tuyenDuong.getThoigiandichuyen();
            double giaVe = thoiGianDiChuyen * DONGIAVE;
            
            // Hiển thị giá vé đã tính
            giaveTextField.setText(String.valueOf(giaVe));
        } else {
            giaveTextField.setText("0");
            System.err.println("Không tìm thấy thông tin tuyến đường với mã: " + matuyen);
        }
    }

    // Hàm check SDT khách hàng tồn tại và trả về mã khách
    public KhachHangModel checkSdtKhachHang(String sdt) {
        return KhachHangService.getBySdt(sdt);
    }

    // Hàm set title cho dialog
    private String setTitleType() {
        if (type == null) {
            return "Vé Tàu";
        }
        switch (type) {
            case "create":
                return "THÊM VÉ TÀU";
            case "update":
                return "SỬA THÔNG TIN VÉ TÀU";
            case "detail":
                return "THÔNG TIN VÉ TÀU";
            default:
                return "ERROR";
        }
    }

    // Check loại dialog và thiết lập trạng thái tương ứng
    public void checkButtonClicked() {
        if (type == null) return;

        switch (type) {
            case "create":
                editEnabled(true);
                break;
            case "update":
                editEnabled(true);
                loadVeTauData();
                break;
            case "detail":
                editEnabled(false);
                loadVeTauData();
                break;
            case "delete":
                break;
            default:
                System.err.println("Button được nhấn là " + type);
        }
    }

    // Hàm thiết lập khả năng chỉnh sửa của các trường
    public void editEnabled(boolean enabled) {
        if (machuyenComboBox != null) machuyenComboBox.setEnabled(enabled);
        if (sdtKhachTextField != null) sdtKhachTextField.setEnabled(enabled);
        // Ô giá vé luôn không được chỉnh sửa
        if (giaveTextField != null) giaveTextField.setEnabled(enabled);
    }

    // Hàm load dữ liệu vé tàu vào form
    private void loadVeTauData() {
        VeTauModel veTauSelected = (vetauModel != null) ? vetauModel : veTau.getSelectedVeTau();
        if (veTauSelected != null) {
            KhachHangModel kh = KhachHangService.getById(veTauSelected.getMakh());

            ComboBoxUtil.selectItemInComboBox(machuyenComboBox, machuyenComboBoxModel,
                    nv -> ((LichTrinhModel)nv).getMachuyen() == veTauSelected.getMachuyen());
            sdtKhachTextField.setText(kh != null ? kh.getSdt() : "Không tìm thấy");
            giaveTextField.setText(String.valueOf(veTauSelected.getGiave()));
        } else {
            System.err.println("Không có dữ liệu vé tàu để hiển thị");
        }
    }

    // Phương thức lấy dữ liệu từ form và tạo đối tượng VeTauModel
    public VeTauModel getVeTauFromForm() {
        try {
            if (machuyenComboBox == null || sdtKhachTextField == null || giaveTextField == null) {
                JOptionPane.showMessageDialog(this,
                        "Lỗi: Các trường dữ liệu chưa được khởi tạo!",
                        "Lỗi hệ thống", JOptionPane.ERROR_MESSAGE);
                return null;
            }

            if (machuyenComboBox.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(this, "Hôm nay không có chuyến hợp lệ!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                machuyenComboBox.requestFocus();
                return null;
            }

            if (sdtKhachTextField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập số điện thoại khách hàng!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                sdtKhachTextField.requestFocus();
                return null;
            }
            
            int machuyen = ((LichTrinhModel) machuyenComboBox.getSelectedItem()).getMachuyen();

            // Kiểm tra SĐT khách hàng có tồn tại trong DB không
            String sdtKhach = sdtKhachTextField.getText().trim();
            KhachHangModel khachHang = checkSdtKhachHang(sdtKhach);

            // Nếu không tìm thấy khách hàng, hiển thị dialog thêm khách hàng mới
            if (khachHang == null) {
                int choice = JOptionPane.showConfirmDialog(this,
                        "Số điện thoại này chưa có trong hệ thống. Hãy thêm khách hàng mới",
                        "Thông báo", JOptionPane.YES_NO_OPTION);

                if (choice == JOptionPane.YES_OPTION) {
                    khachHangDialog.showAddKhachHangDialog(this, sdtKhach, () -> {
                        KhachHangModel newCustomer = checkSdtKhachHang(sdtKhach);
                        if (newCustomer != null) {
                            createVeTauWithCustomer(machuyen, newCustomer, giaveTextField.getText().trim());
                        }
                    });
                }
                return null;
            }

            double giave;
            try {
                giave = Double.parseDouble(giaveTextField.getText().trim().replace(",", "."));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Giá vé phải là số thực!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                giaveTextField.requestFocus();
                return null;
            }
            return new VeTauModel(0, machuyen, khachHang.getMaKh(), giave);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    private void createVeTauWithCustomer(int machuyen, KhachHangModel khachHang, String giaveText) {
        try {
            double giave = Double.parseDouble(giaveText.replace(",", "."));
            VeTauModel veTau = new VeTauModel(0, machuyen, khachHang.getMaKh(), giave);

            if (VeTauService.insert(veTau)) {
                JOptionPane.showMessageDialog(this, "THÊM VÉ TÀU THÀNH CÔNG", "THÔNG BÁO",
                        JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
                if (this.veTau != null) this.veTau.loadDataTable();
            } else {
                JOptionPane.showMessageDialog(this, "THÊM VÉ TÀU THẤT BẠI", "THÔNG BÁO",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Giá vé phải là số thực!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
}