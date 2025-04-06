package org.metro.view.Panel;

import org.metro.model.LichTrinhModel;
import org.metro.service.LichTrinhService;
import org.metro.controller.LichTrinhController;
import org.metro.view.Component.IntegratedSearch;
import org.metro.view.Component.MainFunction;

import java.awt.*;
import javax.swing.*;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;

public class LichTrinh extends JPanel {
    Color BackgroundColor = new Color(0, 2, 2);
    JPanel contentCenter, functionBar;
    JTable lichTrinhTable;
    JScrollPane lichTrinhScrollTable;
    DefaultTableModel dTable;
    MainFunction mainFunction;
    IntegratedSearch search;
    List<LichTrinhModel> listLichTrinh;
    Timer searchTimer;
    private LichTrinhController controller;
    private DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("HH:mm - dd/MM/yyyy");

    public LichTrinh() {
        initComponent();
        controller = new LichTrinhController(this);
        setupListeners();
    }

    public void initComponent() {
        this.setBackground(BackgroundColor);
        this.setLayout(new BorderLayout(0, 0));

        lichTrinhTable = new JTable();
        lichTrinhScrollTable = new JScrollPane();
        dTable = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        // Loại bỏ cột thời gian đến thực tế
        String[] columnNames = { "Mã chuyến", "Mã nhân viên", "Mã tàu", "Mã tuyến", "Hướng đi", "Thời gian khởi hành", "Trạng thái" };
        dTable.setColumnIdentifiers(columnNames);
        lichTrinhTable.setModel(dTable);
        lichTrinhTable.setFocusable(false);
        lichTrinhTable.setRowSelectionAllowed(true);
        lichTrinhScrollTable.setViewportView(lichTrinhTable);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < columnNames.length; i++) {
            lichTrinhTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        lichTrinhTable.setAutoCreateRowSorter(true);

        contentCenter = new JPanel();
        contentCenter.setBackground(new Color(130, 190, 223));
        contentCenter.setPreferredSize(new Dimension(1100, 600));
        contentCenter.setLayout(new BorderLayout(10, 10));
        this.add(contentCenter, BorderLayout.CENTER);

        functionBar = new JPanel();
        functionBar.setLayout(new BoxLayout(functionBar, BoxLayout.X_AXIS));
        functionBar.setPreferredSize(new Dimension(0, 50));

        search = new IntegratedSearch(new String[] { "Tất cả", "Mã chuyến", "Mã nhân viên", "Mã tàu", "Mã tuyến", "Hướng đi", "Thời gian khởi hành", "Trạng thái lịch trình" });

        String[] actions = { "create", "update", "delete", "detail" };
        mainFunction = new MainFunction(actions);

        functionBar.add(mainFunction);
        JPanel combinedPanel = new JPanel(new BorderLayout());
        combinedPanel.add(search, BorderLayout.NORTH);
        combinedPanel.add(functionBar, BorderLayout.CENTER);

        contentCenter.add(combinedPanel, BorderLayout.NORTH);
        contentCenter.add(lichTrinhScrollTable, BorderLayout.CENTER);

        loadDataTable();
    }

    public void populateTable(List<LichTrinhModel> listLichTrinh) {
        dTable.setRowCount(0);
        for (LichTrinhModel lichTrinh : listLichTrinh) {
            // Định dạng thời gian theo yêu cầu
            String formattedTime = lichTrinh.getThoigiankhoihanh().format(displayFormatter);

            Object[] rowData = {
                    lichTrinh.getMachuyen(),
                    lichTrinh.getManv(),
                    lichTrinh.getMatau(),
                    lichTrinh.getMatuyen(),
                    lichTrinh.getHuongdi() ? "Đi" : "Về", // Hiển thị hướng đi dễ đọc hơn
                    formattedTime, // Thời gian khởi hành đã định dạng
                    lichTrinh.getTrangthai()
            };
            dTable.addRow(rowData);
        }
    }

    public void loadDataTable() {
        listLichTrinh = LichTrinhService.search("", "Tất cả");
        populateTable(listLichTrinh);
    }

    public void performSearch(String searchText, String searchType) {
        String type = (String) search.getCbxChoose().getSelectedItem();
        String txt = search.getTxtSearchForm().getText().trim();
        List<LichTrinhModel> result = LichTrinhService.search(txt, type);
        populateTable(result);
    }

    public void setupListeners() {
        search.getCbxChoose().addItemListener(controller);
        search.getTxtSearchForm().addKeyListener(controller);
        search.getBtnReset().addActionListener(controller);

        for (String key : mainFunction.getBtn().keySet()) {
            mainFunction.getBtn().get(key).addActionListener(controller);
        }
    }

    public LichTrinhModel getSelectedLichTrinh() {
        int selectedRow = lichTrinhTable.getSelectedRow();
        if (selectedRow != -1) {
            int modelRow = lichTrinhTable.convertRowIndexToModel(selectedRow);
            return listLichTrinh.get(modelRow);
        }
        return null;
    }

    public JTable getLichTrinhTable() {
        return lichTrinhTable;
    }

    public MainFunction getMainFunction() {
        return mainFunction;
    }

    public IntegratedSearch getSearch() {
        return search;
    }

    public Timer getSearchTimer() {
        return searchTimer;
    }

    public void setSearchTimer(Timer searchTimer) {
        this.searchTimer = searchTimer;
    }
}