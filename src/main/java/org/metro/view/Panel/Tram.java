package org.metro.view.Panel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import java.util.List;

import org.metro.controller.TramController;
import org.metro.model.TramModel;
import org.metro.service.TramService;
import org.metro.view.Component.IntegratedSearch;
import org.metro.view.Component.MainFunction;
import java.awt.*;

public class Tram extends JPanel {
    private static final int machucnang_tram = 2;
    private JPanel functionPanel, contentPanel, functionBarPanel;
    private IntegratedSearch search;
    private MainFunction mainFunction;
    private DefaultTableModel tableModel;
    private JTable tramTable;
    private TramService tramService = new TramService();
    private TramController action;

    public void initComponent() {
        this.setLayout(new BorderLayout(0, 10));
        this.setBackground(Color.WHITE);
        action = new TramController(this);
        functionPanel = new JPanel();
        functionPanel.setBackground(Color.WHITE);
        functionPanel.setLayout(new BorderLayout(5, 10));

        String[] optSearch = { "Tất cả", "Mã trạm", "Tên trạm", "Địa chỉ" };
        search = new IntegratedSearch(optSearch);
        functionPanel.add(search, BorderLayout.WEST);

        search.getCbxChoose().addItemListener(action);
        search.getTxtSearchForm().addKeyListener(action);
        search.getBtnReset().addMouseListener(action);

        String[] optMainFunc = { "create", "delete", "update" };
        mainFunction = new MainFunction(machucnang_tram,optMainFunc);
        for (String opt : optMainFunc) {
            if(mainFunction.getBtn().get(opt) != null) {
                mainFunction.getBtn().get(opt).addMouseListener(action);
            }
        }
        functionBarPanel = new JPanel();
        functionBarPanel.setLayout(new BoxLayout(functionBarPanel, BoxLayout.X_AXIS));
        functionBarPanel.setPreferredSize(new Dimension(0, 50));
        functionBarPanel.add(mainFunction);

        functionPanel.add(functionBarPanel, BorderLayout.SOUTH);

        this.add(functionPanel, BorderLayout.NORTH);

        String[] colName = { "Mã trạm", "Tên trạm", "Địa chỉ" };
        tableModel = new DefaultTableModel(colName, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tramTable = new JTable(); // khong the thay doi model sau khi da khoi tao bang, tru khi goi setModel();
        tramTable.setFillsViewportHeight(true);
        tramTable.getTableHeader().setReorderingAllowed(false);
        tramTable.getTableHeader().setResizingAllowed(false);
        tramTable.setModel(tableModel);
        tramTable.setSelectionBackground(new Color(224, 255, 255));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < tramTable.getColumnCount(); i++) {
            tramTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        TableColumnModel columnModel = tramTable.getColumnModel();

        // Đặt độ rộng cho từng cột (theo pixel)
        columnModel.getColumn(0).setPreferredWidth(100); // Cột 1: 100px
        columnModel.getColumn(1).setPreferredWidth(230); // Cột 2: 200px
        columnModel.getColumn(2).setPreferredWidth(770); // Cột 3: 150px

        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setPreferredSize(new Dimension(1100, 600));

        JScrollPane tableScrollPane = new JScrollPane(tramTable);
        contentPanel.add(tableScrollPane, BorderLayout.CENTER);

        this.add(contentPanel, BorderLayout.CENTER);

    }

    public Tram() {
        initComponent();
        loadData(tramService.getDsTram());
    }

    public void loadData(List<TramModel> dsTram) {
        tableModel.setRowCount(0);
        for (TramModel tram : dsTram) {
            tableModel.addRow(new Object[] {
                    tram.getMatram(),
                    tram.getTentram(),
                    tram.getDiachi(),
            });
        }
    }

    public IntegratedSearch getSearch() {
        return search;
    }

    public MainFunction getMainFunction() {
        return mainFunction;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public JTable getTramTable() {
        return tramTable;
    }

    public TramService getTramService() {
        return tramService;
    }
}
