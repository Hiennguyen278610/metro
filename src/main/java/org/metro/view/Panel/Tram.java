package org.metro.view.Panel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.metro.model.TramModel;
import org.metro.service.TramService;
import org.metro.view.Component.IntegratedSearch;
import org.metro.view.Component.MainFunction;
import java.awt.*;

public class Tram extends JPanel {
    private JPanel functionPanel, contentPanel, functionBarPanel;
    private IntegratedSearch search;
    private MainFunction mainFunction;
    private DefaultTableModel tableModel;
    private JTable tramTable;
    private TramService tramService = new TramService();

    public void initComponent() {
        this.setLayout(new BorderLayout(0, 10));
        this.setBackground(Color.WHITE);

        functionPanel = new JPanel();
        functionPanel.setBackground(Color.WHITE);
        functionPanel.setLayout(new BorderLayout(5, 10));

        String[] optSearch = { "Tất cả", "Mã trạm", "Tên trạm", "Địa chỉ" };
        search = new IntegratedSearch(optSearch);
        functionPanel.add(search, BorderLayout.WEST);

        // search.getCbxChoose().addItemListener(action);
        // search.getTxtSearchForm().addKeyListener(action);
        // search.getBtnReset().addMouseListener(action);

        String[] optMainFunc = { "create", "delete", "update" };
        mainFunction = new MainFunction(optMainFunc);
        // for (String opt : optMainFunc) {
        // mainFunction.btn.get(opt).addMouseListener(action);
        // }
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
        for (int i = 0; i < tramTable.getColumnCount() - 1; i++) {
            tramTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setPreferredSize(new Dimension(1100, 600));

        JScrollPane tableScrollPane = new JScrollPane(tramTable);
        contentPanel.add(tableScrollPane, BorderLayout.CENTER);

        this.add(contentPanel, BorderLayout.CENTER);

    }

    public Tram() {
        initComponent();
        // loadData();
    }

    // public void loadData() {
    // tableModel.setRowCount(0);
    // for (TramModel tram : dsTram) {
    // tableModel.addRow(new Object[] {
    // tram.getMatram(),
    // tram.getTentram(),
    // tram.getDiachi(),
    // });
    // }
    // }
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
