package org.metro.view.Panel.PhanQuyenPackage;

import org.metro.view.Component.IntegratedSearch;
import org.metro.view.Component.MainFunction;
import org.metro.view.MainFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PhanQuyen extends JPanel {
    private MainFrame mf;
    private JTable phanquyenTable;
    private DefaultTableModel phanquyentabelModel;
    private JScrollPane phanquyentabelScrolLPane;
    private MainFunction mainfunc;
    private IntegratedSearch searchfunc;
    private JPanel headerPanel;
    private JPanel centerPanel;
    private JPanel headerMainFuncPanel;

    public PhanQuyen() {
        this.setLayout(new BorderLayout());
        mainfunc = new MainFunction(new String[]{"create","delete","update","detail"});
        searchfunc = new IntegratedSearch(new String[]{"--","mã nhóm quyền","tên nhnhóm quyền"});
        headerPanel = new JPanel(new BorderLayout());
        centerPanel = new JPanel(new BorderLayout());
        init();
    }
    private void init() {
        headerMainFuncPanel = new JPanel();
        headerMainFuncPanel.setLayout(new BoxLayout(headerMainFuncPanel, BoxLayout.X_AXIS));
        headerMainFuncPanel.setPreferredSize(new Dimension(50,50));
        headerMainFuncPanel.add(mainfunc);

        headerPanel.add(searchfunc,BorderLayout.NORTH);
        headerPanel.add(headerMainFuncPanel,BorderLayout.CENTER);

        phanquyentabelModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        phanquyentabelModel.setColumnIdentifiers(new String[]{"mã nhóm quyền","tên nhóm quyền"});
        phanquyenTable = new JTable();
        phanquyenTable.setRowSelectionAllowed(true);
        phanquyenTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
        phanquyenTable.setRowHeight(30);
        phanquyenTable.setModel(phanquyentabelModel);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for(int i = 0 ; i < phanquyentabelModel.getRowCount();++i) {
            phanquyenTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        phanquyentabelScrolLPane = new JScrollPane(phanquyenTable);
        centerPanel.add(phanquyentabelScrolLPane,BorderLayout.CENTER);
        this.add(headerPanel,BorderLayout.NORTH);
        this.add(centerPanel,BorderLayout.CENTER);

    }
}