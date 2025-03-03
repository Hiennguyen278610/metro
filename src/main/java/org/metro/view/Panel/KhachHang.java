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
        String[] columnNames = {"ID", "Tên khách hàng", "Số điện thoại", "Tuyến"};
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


        search = new IntegratedSearch(new String[]{"Tất cả", "ID", "Tên khách hàng", "Số điện thoại", "Tuyến"});
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
            loadDataTable(listkh);
        });

        String[] actions = {"create", "update", "delete", "detail"};
        mainFunction = new MainFunction(actions);
//        mainFunction.setPreferredSize(new Dimension(50, 20));
        for (String action : actions) {
            mainFunction.btn.get(action).addActionListener(this);
        }

        functionBar.add(mainFunction);
//        functionBar.add(Box.createHorizontalStrut(1));
        JPanel combinedPanel = new JPanel();
        combinedPanel.setLayout(new BorderLayout());
        combinedPanel.add(search, BorderLayout.NORTH);
        combinedPanel.add(functionBar, BorderLayout.CENTER);

        contentCenter.add(combinedPanel, BorderLayout.NORTH);

        contentCenter.add(khachHangScrollTable, BorderLayout.CENTER);
    }

    public KhachHang() {
        listkh = KhachHangBUS.search("", "Tất cả");
        initComponent();
        khachHangTable.setDefaultEditor(Object.class, null);
        loadDataTable(listkh);
    }

    public void loadDataTable(ArrayList<KhachHangDTO> result) {
        dTable.setRowCount(0);
        for (KhachHangDTO khachHang : result) {
            dTable.addRow(new Object[]{
                    khachHang.getId(),
                    khachHang.getTen(),
                    khachHang.getSoDienThoai(),
                    khachHang.getRouteID()
            });
        }
    }

    private void performSearch() {
        String type = (String) search.cbxChoose.getSelectedItem();
        String txt = search.txtSearchForm.getText();
        listkh = KhachHangBUS.search(txt, type);
        loadDataTable(listkh);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mainFunction.btn.get("create")) {
            JOptionPane.showMessageDialog(this, "Thêm khách hàng mới");
        } else if (e.getSource() == mainFunction.btn.get("update")) {
            JOptionPane.showMessageDialog(this, "Cập nhật thông tin khách hàng");
        } else if (e.getSource() == mainFunction.btn.get("delete")) {
            JOptionPane.showMessageDialog(this, "Xóa khách hàng");
        } else if (e.getSource() == mainFunction.btn.get("detail")) {
            JOptionPane.showMessageDialog(this, "Xem chi tiết khách hàng");
        }
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
