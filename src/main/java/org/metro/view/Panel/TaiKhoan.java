package org.metro.view.Panel;

import org.metro.model.TaiKhoanModal;
import org.metro.service.TaiKhoanService;
import org.metro.view.Component.IntegratedSearch;
import org.metro.view.Component.MainFunction;
import org.metro.view.Dialog.TaiKhoanDialog;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class TaiKhoan extends JPanel implements ActionListener, ItemListener {
    Color BackgroundColor = new Color(0, 2, 2);
    JPanel contentCenter, functionBar;
    JTable taiKhoanTable;
    JScrollPane taiKhoanScrollTable;
    DefaultTableModel dTable;
    MainFunction mainFunction;
    IntegratedSearch search;
    ArrayList<TaiKhoanModal> listtk;
    Timer searchTimer;

    public TaiKhoan() {
        initComponent();
    }

    private void initComponent() {
        this.setBackground(BackgroundColor);
        this.setLayout(new BorderLayout(0, 0));

        taiKhoanTable = new JTable();
        taiKhoanScrollTable = new JScrollPane();
        dTable = new DefaultTableModel(){
            @Override
             // tao model voi so cot va hang co dinh khong cho sua chua
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        String[] columnNames = {"Mã TK", "Nhóm quyền", "Trạng thái"};
        dTable.setColumnIdentifiers(columnNames);
        taiKhoanTable.setModel(dTable);
        taiKhoanTable.setFocusable(false);
        taiKhoanTable.setRowSelectionAllowed(true); // cho phep chon 1 hang nao do
        taiKhoanScrollTable.setViewportView(taiKhoanTable);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < columnNames.length; i++) {
            taiKhoanTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        taiKhoanTable.setAutoCreateRowSorter(true);

        contentCenter = new JPanel();
        contentCenter.setBackground(new Color(130, 190, 223));
        contentCenter.setPreferredSize(new Dimension(1100, 600));
        contentCenter.setLayout(new BorderLayout(10, 10));
        this.add(contentCenter, BorderLayout.CENTER);

        functionBar = new JPanel();
        functionBar.setLayout(new BoxLayout(functionBar, BoxLayout.X_AXIS));
        functionBar.setPreferredSize(new Dimension(0, 50));

        search = new IntegratedSearch(new String[]{"ID"});
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
                        SwingUtilities.invokeLater(() -> performSearch());
                    }
                }, 300);
            }
        });
        search.btnReset.addActionListener(e -> {
            search.txtSearchForm.setText("");
            loadDataTable();
        });

        String[] actions = {"create", "update", "delete", "detail"};
        mainFunction = new MainFunction(actions);
        for (String action : actions) {
            mainFunction.btn.get(action).addActionListener(this);
        }
        functionBar.add(mainFunction);
        JPanel combinedPanel = new JPanel(new BorderLayout());
        combinedPanel.add(search, BorderLayout.NORTH);
        combinedPanel.add(functionBar, BorderLayout.CENTER);

        contentCenter.add(combinedPanel, BorderLayout.NORTH);
        contentCenter.add(taiKhoanScrollTable, BorderLayout.CENTER);

        loadDataTable();
    }

    private void populateTable(ArrayList<TaiKhoanModal> list) {
        dTable.setRowCount(0);
        for (TaiKhoanModal tk : list) {
            String roleStr;
            switch (tk.getManhomquyen()) {
                case 1:
                    roleStr = "Admin";
                    break;
                case 2:
                    roleStr = "User";
                    break;
                case 3:
                    roleStr = "Nhân viên";
                    break;
                default:
                    roleStr = "Unknown";
            }
            String statusStr = tk.getTrangthai() == 1 ? "Hoạt động" : "Ngừng hoạt động";
            dTable.addRow(new Object[]{
                    tk.getManv(),
                    roleStr,
                    statusStr
            });
        }
    }

    // Hàm tìm kiếm: chỉ tìm theo mã nhân viên ("ID")
    private void performSearch() {
        String txt = search.txtSearchForm.getText().trim();
        // Tìm kiếm theo mã nhân viên (loại "ID")
        ArrayList<TaiKhoanModal> result = TaiKhoanService.search(txt, "ID");
        populateTable(result);
    }

    // Hàm load toàn bộ dữ liệu (reset tìm kiếm)
    public void loadDataTable() {
        ArrayList<TaiKhoanModal> all = TaiKhoanService.search("", "Tất cả");
        populateTable(all);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mainFunction.btn.get("create")) {
            new TaiKhoanDialog().showAddTaiKhoanDialog(this, this::loadDataTable);
        } else if (e.getSource() == mainFunction.btn.get("update")) {
            int selectedRow = taiKhoanTable.getSelectedRow();
            if (selectedRow != -1) {
                int maTk = (int) taiKhoanTable.getValueAt(selectedRow, 0);
                new TaiKhoanDialog().showUpdateTaiKhoanDialog(this, maTk, this::loadDataTable);
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn tài khoản cần cập nhật!");
            }
        } else if (e.getSource() == mainFunction.btn.get("delete")) {
            int selectedRow = taiKhoanTable.getSelectedRow();
            if (selectedRow != -1) {
                int maTk = (int) taiKhoanTable.getValueAt(selectedRow, 0);
                int option = JOptionPane.showConfirmDialog(this,
                        "Bạn có chắc muốn xóa tài khoản này?",
                        "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    if (TaiKhoanService.delete(maTk)) {
                        JOptionPane.showMessageDialog(this, "Xóa tài khoản thành công!");
                        loadDataTable();
                    } else {
                        JOptionPane.showMessageDialog(this, "Xóa tài khoản thất bại!");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn tài khoản cần xóa!");
            }
        } else if (e.getSource() == mainFunction.btn.get("detail")) {
            int selectedRow = taiKhoanTable.getSelectedRow();
            if (selectedRow != -1) {
                int maTk = (int) taiKhoanTable.getValueAt(selectedRow, 0);
                TaiKhoanModal tk = TaiKhoanService.getByID(maTk);
                if (tk != null) {
                    new TaiKhoanDialog().showTaiKhoanDetailDialog(this, tk);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn tài khoản để xem chi tiết!");
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            performSearch();
        }
    }
}
