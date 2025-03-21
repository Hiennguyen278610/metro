package org.metro.view.Panel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import org.metro.view.Component.IntegratedSearch;
import org.metro.view.Component.MainFunction;

import java.awt.*;
import java.time.LocalDateTime;

public class LichBaoTri extends JPanel {
    private JPanel functionPanel, contentPanel, functionBarPanel;
    private IntegratedSearch search;
    private MainFunction mainFunction;
    private DefaultTableModel tableModel;
    private JTable maintenanceTable;

    public LichBaoTri() {
        initComponent();
    }

    public void initComponent() {
        this.setLayout(new BorderLayout(0, 10));
        this.setBackground(Color.WHITE);

        functionPanel = new JPanel();
        functionPanel.setBackground(Color.WHITE);
        functionPanel.setLayout(new BorderLayout(5, 10));
        String[] optSearch = { "Tất cả", "Mã bảo trì", "Mã tàu", "Ngày bảo trì", "Trạng thái" };
        search = new IntegratedSearch(optSearch);
        functionPanel.add(search, BorderLayout.WEST);

        String[] optMainFunc = { "create", "delete", "update", "detail" };
        mainFunction = new MainFunction(optMainFunc);
        functionBarPanel = new JPanel();
        functionBarPanel.setLayout(new BoxLayout(functionBarPanel, BoxLayout.X_AXIS));
        functionBarPanel.setPreferredSize(new Dimension(0, 50));
        functionBarPanel.add(mainFunction);

        functionPanel.add(functionBarPanel, BorderLayout.SOUTH);

        this.add(functionPanel, BorderLayout.NORTH);

        String[] colName = { "Mã bảo trì", "Mã tàu", "Ngày bảo trì", "Trạng thái" };
        tableModel = new DefaultTableModel(colName, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        // Object[] row1 = { 1001, 5001, LocalDateTime.of(2025, 3, 16, 10, 30), "Đã hoàn
        // thành" };
        // Object[] row2 = { 1002, 5002, LocalDateTime.of(2025, 3, 17, 14, 0), "Đang bảo
        // trì" };
        // tableModel.addRow(row1);
        // tableModel.addRow(row2);

        maintenanceTable = new JTable(); // khong the thay doi model sau khi da khoi tao bang, tru khi goi setModel();
        maintenanceTable.setFillsViewportHeight(true);
        maintenanceTable.getTableHeader().setReorderingAllowed(false);
        maintenanceTable.getTableHeader().setResizingAllowed(false);
        maintenanceTable.setModel(tableModel);
        maintenanceTable.setSelectionBackground(new Color(224, 255, 255));

        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setPreferredSize(new Dimension(1100, 600));
        // contentPanel.setBackground(Color.RED);

        JScrollPane tableScrollPane = new JScrollPane(maintenanceTable);
        contentPanel.add(tableScrollPane, BorderLayout.CENTER);

        this.add(contentPanel, BorderLayout.CENTER);

    }

}