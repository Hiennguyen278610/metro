package org.metro.view.Panel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import org.metro.controller.LichBaoTriController;
import org.metro.model.LichBaoTriModel;
import org.metro.service.LichBaoTriService;
import org.metro.view.Component.IntegratedSearch;
import org.metro.view.Component.MainFunction;
import org.metro.view.Dialog.LichBaoTriDialog;
import java.util.List;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LichBaoTri extends JPanel {
    private JPanel functionPanel, contentPanel, functionBarPanel;
    private IntegratedSearch search;

    private MainFunction mainFunction;
    private DefaultTableModel tableModel;
    private JTable maintenanceTable;
    private LichBaoTriService lbtService = new LichBaoTriService();
    // private List<LichBaoTriModel> dsBaoTri = lbtService.getAll();
    private LichBaoTriController action = new LichBaoTriController(this);
    // private LichBaoTriModel lbtModel;

    public LichBaoTri() {
        initComponent();
        loadData(lbtService.getDsBaoTri());
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

        search.getCbxChoose().addItemListener(action);
        search.getTxtSearchForm().addKeyListener(action);
        search.getBtnReset().addMouseListener(action);

        String[] optMainFunc = { "create", "delete", "update", "detail" };
        mainFunction = new MainFunction(optMainFunc);
        for (String opt : optMainFunc) {
            mainFunction.btn.get(opt).addMouseListener(action);
        }
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
        maintenanceTable = new JTable(); // khong the thay doi model sau khi da khoi tao bang, tru khi goi setModel();
        maintenanceTable.setFillsViewportHeight(true);
        maintenanceTable.getTableHeader().setReorderingAllowed(false);
        maintenanceTable.getTableHeader().setResizingAllowed(false);
        maintenanceTable.setModel(tableModel);
        maintenanceTable.setSelectionBackground(new Color(224, 255, 255));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < maintenanceTable.getColumnCount(); i++) {
            maintenanceTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setPreferredSize(new Dimension(1100, 600));
        // contentPanel.setBackground(Color.RED);

        JScrollPane tableScrollPane = new JScrollPane(maintenanceTable);
        contentPanel.add(tableScrollPane, BorderLayout.CENTER);

        this.add(contentPanel, BorderLayout.CENTER);

    }

    public void loadData(List<LichBaoTriModel> dsBaoTri) {
        tableModel.setRowCount(0);
        for (LichBaoTriModel lbt : dsBaoTri) {

            tableModel.addRow(new Object[] {
                    lbt.getMabaotri(),
                    lbt.getMatau(),
                    lbt.convertLocalDate(),
                    lbt.getTrangthaibaotri()
            });
        }
    }

    public IntegratedSearch getSearch() {
        return search;
    }

    public MainFunction getMainFunction() {
        return mainFunction;
    }

    public void setMainFunction(MainFunction mainFunction) {
        this.mainFunction = mainFunction;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public void setTableModel(DefaultTableModel tableModel) {
        this.tableModel = tableModel;
    }

    public JTable getMaintenanceTable() {
        return maintenanceTable;
    }

    public void setMaintenanceTable(JTable maintenanceTable) {
        this.maintenanceTable = maintenanceTable;
    }

    public LichBaoTriService getLbtService() {
        return lbtService;
    }

    public void setLbtService(LichBaoTriService lbtService) {
        this.lbtService = lbtService;
    }

}