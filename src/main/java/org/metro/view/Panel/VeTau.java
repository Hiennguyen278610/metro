package org.metro.view.Panel;

import org.metro.model.VeTauModel;
import org.metro.service.VeTauService;
import org.metro.view.Component.IntegratedSearch;
import org.metro.view.Component.MainFunction;
import org.metro.controller.VeTauController;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.Timer;

public class VeTau extends JPanel {
    Color BackgroundColor = new Color(0, 2, 2);
    JPanel contentCenter, functionBar;
    JTable veTauTable;
    JScrollPane veTauScrollTable;
    DefaultTableModel dTable;
    MainFunction mainFunction;
    IntegratedSearch search;
    List<VeTauModel> listVeTau;
    Timer searchTimer;
    private VeTauController controller;

    public VeTau() {
        initComponent();
        controller = new VeTauController(this);
        setupListeners();
    }

    private void initComponent() {
        this.setBackground(BackgroundColor);
        this.setLayout(new BorderLayout(0, 0));

        veTauTable = new JTable();
        veTauScrollTable = new JScrollPane();
        dTable = new DefaultTableModel() {
            @Override
            // tao model voi so cot va hang co dinh khong cho sua chua
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        String[] columnNames = { "Mã vé", "Mã chuyến", "Mã khách hàng", "Giá vé" };
        dTable.setColumnIdentifiers(columnNames);
        veTauTable.setModel(dTable);
        veTauTable.setFocusable(false);
        veTauTable.setRowSelectionAllowed(true); // cho phep chon 1 hang nao do
        veTauScrollTable.setViewportView(veTauTable);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < columnNames.length; i++) {
            veTauTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        veTauTable.setAutoCreateRowSorter(true);

        contentCenter = new JPanel();
        contentCenter.setBackground(new Color(130, 190, 223));
        contentCenter.setPreferredSize(new Dimension(1100, 600));
        contentCenter.setLayout(new BorderLayout(10, 10));
        this.add(contentCenter, BorderLayout.CENTER);

        functionBar = new JPanel();
        functionBar.setLayout(new BoxLayout(functionBar, BoxLayout.X_AXIS));
        functionBar.setPreferredSize(new Dimension(0, 50));

        search = new IntegratedSearch(new String[] { "Tất cả", "Mã vé", "Mã chuyến", "Mã khách hàng", "Giá vé" });

        String[] actions = { "create", "update", "delete", "detail" };
        mainFunction = new MainFunction(actions);

        functionBar.add(mainFunction);
        JPanel combinedPanel = new JPanel(new BorderLayout());
        combinedPanel.add(search, BorderLayout.NORTH);
        combinedPanel.add(functionBar, BorderLayout.CENTER);

        contentCenter.add(combinedPanel, BorderLayout.NORTH);
        contentCenter.add(veTauScrollTable, BorderLayout.CENTER);

        loadDataTable();
    }

    private void populateTable(List<VeTauModel> data) {
        dTable.setRowCount(0);
        for (VeTauModel vt : data) {
            dTable.addRow(new Object[] {
                    vt.getMave(),
                    vt.getMachuyen(),
                    vt.getMakh(),
                    vt.getGiave()
            });
        }
    }

    public void loadDataTable() {
        listVeTau = VeTauService.search("", "Tất cả");
        populateTable(listVeTau);
    }

    public void performSearch() {
        String type = (String) search.getCbxChoose().getSelectedItem();
        String txt = search.getTxtSearchForm().getText().trim();
        List<VeTauModel> result = VeTauService.search(txt, type);
        populateTable(result);
    }

    // Cài đặt tất cả các listeners sau khi controller đã được khởi tạo
    public void setupListeners() {
        // Gắn listener cho combobox tìm kiếm
        search.getCbxChoose().addItemListener(controller);

        // Gắn listener cho text field tìm kiếm
        search.getTxtSearchForm().addKeyListener(controller);

        // Gắn listener cho nút reset tìm kiếm
        search.getBtnReset().addActionListener(controller);

        // Gắn listener cho các nút chức năng (create, update, delete, detail)
        for (String key : mainFunction.getBtn().keySet()) {
            mainFunction.getBtn().get(key).addActionListener(controller);
        }
    }

    public VeTauModel getSelectedVeTau() {
        int selectedRow = veTauTable.getSelectedRow();
        if (selectedRow != -1) {
            int mave = (int) veTauTable.getValueAt(selectedRow, 0);
            return VeTauService.getById(mave);
        }
        return null;
    }

    // Getters for components
    public JTable getVeTauTable() {
        return veTauTable;
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

    public void setSearchTimer(Timer timer) {
        this.searchTimer = timer;
    }
}