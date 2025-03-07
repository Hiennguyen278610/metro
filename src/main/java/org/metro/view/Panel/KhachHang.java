package org.metro.view.Panel;

import org.metro.controller.MainFunction;
import org.metro.model.KhachHangDTO;
import org.metro.service.IntegratedSearch;
import org.metro.service.KhachHangBUS;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class KhachHang extends JPanel implements ActionListener, ItemListener {
    Color BackgroundColor = new Color(0, 2, 2);
    JPanel contentCenter, functionBar;
    JTable khachHangTable;
    JScrollPane khachHangScrollTable;
    DefaultTableModel dTable;
    MainFunction mainFunction;
    IntegratedSearch search;
    ArrayList<KhachHangDTO> listkh;
    Timer searchTimer;

    private void initComponent() {
        this.setBackground(BackgroundColor);
        this.setLayout(new BorderLayout(0, 0));

        khachHangTable = new JTable();
        khachHangScrollTable = new JScrollPane();
        dTable = new DefaultTableModel();
        String[] columnNames = { "Mã KH", "Tên khách hàng", "Số điện thoại", "Mã tuyến", "Ngày tham gia" };
        dTable.setColumnIdentifiers(columnNames);
        khachHangTable.setModel(dTable);
        khachHangTable.setFocusable(false);
        khachHangScrollTable.setViewportView(khachHangTable);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        for (int i = 0; i < columnNames.length; i++) {
            khachHangTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        khachHangTable.setAutoCreateRowSorter(true);

        contentCenter = new JPanel();
        contentCenter.setBackground(new Color(130, 190, 223));
        contentCenter.setPreferredSize(new Dimension(1100, 600));
        contentCenter.setLayout(new BorderLayout(10, 10));
        this.add(contentCenter, BorderLayout.CENTER);

        functionBar = new JPanel();
        functionBar.setLayout(new BoxLayout(functionBar, BoxLayout.X_AXIS));
        functionBar.setPreferredSize(new Dimension(0, 50));

        search = new IntegratedSearch(new String[] { "Tất cả", "ID", "Tên khách hàng", "Số điện thoại", "Tuyến" });
        search.cbxChoose.addItemListener(this);
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
                        performSearch();
                    }
                }, 300);
            }
        });

        search.btnReset.addActionListener(e -> {
            search.txtSearchForm.setText("");
            loadDataTable();
        });

        String[] actions = { "create", "update", "delete", "detail" };
        mainFunction = new MainFunction(actions);
        // mainFunction.setPreferredSize(new Dimension(50, 20));
        for (String action : actions) {
            mainFunction.btn.get(action).addActionListener(this);
        }

        functionBar.add(mainFunction);
        // functionBar.add(Box.createHorizontalStrut(1));
        JPanel combinedPanel = new JPanel();
        combinedPanel.setLayout(new BorderLayout());
        combinedPanel.add(search, BorderLayout.NORTH);
        combinedPanel.add(functionBar, BorderLayout.CENTER);

        contentCenter.add(combinedPanel, BorderLayout.NORTH);

        contentCenter.add(khachHangScrollTable, BorderLayout.CENTER);
    }

    public KhachHang() {
        // Đảm bảo dữ liệu được tải trước khi tìm kiếm
        KhachHangBUS.loadData();
        listkh = KhachHangBUS.search("", "Tất cả");
        initComponent();
        khachHangTable.setDefaultEditor(Object.class, null);
        loadDataTable();
    }

    public void loadDataTable() {
        listkh = KhachHangBUS.search("", "Tất cả"); // Lấy dữ liệu mới nhất
        dTable.setRowCount(0);
        for (KhachHangDTO khachHang : listkh) {
            dTable.addRow(new Object[] {
                    khachHang.getMaKh(),
                    khachHang.getTenKh(),
                    khachHang.getSdt(),
                    khachHang.getMaTuyen(),
                    khachHang.getNgayThamGia().toString()
            });
        }
    }

    private void performSearch() {
        String type = (String) search.cbxChoose.getSelectedItem();
        String txt = search.txtSearchForm.getText();
        listkh = KhachHangBUS.search(txt, type);
        loadDataTable();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mainFunction.btn.get("create")) {
            // Gọi dialog để nhập thông tin khách hàng mới
            showAddKhachHangDialog();
        } else if (e.getSource() == mainFunction.btn.get("update")) {
            // Lấy khách hàng được chọn từ bảng và cập nhật
            int selectedRow = khachHangTable.getSelectedRow();
            if (selectedRow != -1) {
                int maKh = (int) khachHangTable.getValueAt(selectedRow, 0);
                showUpdateKhachHangDialog(maKh);
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn khách hàng cần cập nhật!");
            }
        } else if (e.getSource() == mainFunction.btn.get("delete")) {
            // Xóa khách hàng được chọn
            int selectedRow = khachHangTable.getSelectedRow();
            if (selectedRow != -1) {
                int maKh = (int) khachHangTable.getValueAt(selectedRow, 0);
                int option = JOptionPane.showConfirmDialog(this,
                        "Bạn có chắc muốn xóa khách hàng này?",
                        "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    if (KhachHangBUS.delete(maKh)) {
                        JOptionPane.showMessageDialog(this, "Xóa khách hàng thành công!");
                        // Cập nhật lại danh sách
                        listkh = KhachHangBUS.search("", "Tất cả");
                        loadDataTable();
                    } else {
                        JOptionPane.showMessageDialog(this, "Xóa khách hàng thất bại!");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn khách hàng cần xóa!");
            }
        } else if (e.getSource() == mainFunction.btn.get("detail")) {
            // Hiển thị chi tiết khách hàng
            int selectedRow = khachHangTable.getSelectedRow();
            if (selectedRow != -1) {
                int maKh = (int) khachHangTable.getValueAt(selectedRow, 0);
                KhachHangDTO kh = KhachHangBUS.getById(maKh);
                if (kh != null) {
                    showKhachHangDetail(kh);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn khách hàng để xem chi tiết!");
            }
        }
    }

    // Thêm các phương thức hỗ trợ
    private void showAddKhachHangDialog() {
        // Code để hiển thị dialog thêm khách hàng mới
        // Ví dụ, tạo JDialog với các field nhập liệu
        // và xử lý khi người dùng nhấn nút Thêm
    }

    private void showUpdateKhachHangDialog(int maKh) {
        // Code để hiển thị dialog cập nhật thông tin khách hàng
        // Tương tự như showAddKhachHangDialog nhưng với dữ liệu ban đầu
    }

    private void showKhachHangDetail(KhachHangDTO kh) {
        // Code để hiển thị chi tiết khách hàng
        // Ví dụ, tạo JDialog hiển thị thông tin chi tiết
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            performSearch();
        }
    }

    public static boolean isPhoneNumber(String str) {
        str = str.trim().replaceAll("[^0-9]", "");
        return str.length() == 10;
    }

}
