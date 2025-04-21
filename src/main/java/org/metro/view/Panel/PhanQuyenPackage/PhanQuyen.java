package org.metro.view.Panel.PhanQuyenPackage;

import org.metro.DAO.PhanQuyenDAO.NhomQuyenDAO;
import org.metro.controller.PhanQuyenController;
import org.metro.model.NhanVienModel;
import org.metro.model.PhanQuyenModel.NhomQuyenModel;
import org.metro.view.Component.IntegratedSearch;
import org.metro.view.Component.MainFunction;
import org.metro.view.MainFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PhanQuyen extends JPanel {
    private static final int machucnang_phanquyen = 10;
    private MainFrame mf;
    private JTable phanquyenTable;
    private DefaultTableModel phanquyentabelModel;
    private JScrollPane phanquyentabelScrolLPane;
    private MainFunction mainfunc;
    private IntegratedSearch searchfunc;
    private JPanel headerPanel;
    private JPanel centerPanel;
    private JPanel headerMainFuncPanel;
    private PhanQuyenController pqAction = new PhanQuyenController(this,null,mf);
    public PhanQuyen(MainFrame mf) {
        this.setLayout(new BorderLayout());
        mainfunc = new MainFunction(machucnang_phanquyen,new String[]{"create","delete","update","detail"});
        searchfunc = new IntegratedSearch(new String[]{"--","mã nhóm quyền","tên nhóm quyền"});
        headerPanel = new JPanel(new BorderLayout());
        centerPanel = new JPanel(new BorderLayout());
        init();
        reloadData();
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

        //add su kien click
        for(String tb : mainfunc.getBtn().keySet()) {
            if(mainfunc.getBtn().get(tb) != null) {
                mainfunc.getBtn().get(tb).addActionListener(pqAction);
            }
        }
    }

    public void reloadList(List<NhomQuyenModel> lst) {
        phanquyentabelModel.setRowCount(0);
        if(lst != null) {
            for(NhomQuyenModel nqm: lst) {
                phanquyentabelModel.addRow(new Object[]{
                        nqm.getManhomquyen(),
                        nqm.getTennhomquyen()
                });
            }
        }
    }
    public void reloadData() {
        List<NhomQuyenModel> lst = new NhomQuyenDAO().selectAll();
        reloadList(lst);
    }

    public NhomQuyenModel getSelectedPhanquyen() {
        int row = phanquyenTable.getSelectedRow();
        if(row == -1) return null;
        int manhomquyen = (int) phanquyenTable.getValueAt(row, 0);
        String tennhomquyen = (String) phanquyenTable.getValueAt(row, 1);
        return new NhomQuyenModel(manhomquyen,tennhomquyen);
    }


    public MainFrame getMf() {
        return mf;
    }

    public void setMf(MainFrame mf) {
        this.mf = mf;
    }

    public JTable getPhanquyenTable() {
        return phanquyenTable;
    }

    public void setPhanquyenTable(JTable phanquyenTable) {
        this.phanquyenTable = phanquyenTable;
    }

    public DefaultTableModel getPhanquyentabelModel() {
        return phanquyentabelModel;
    }

    public void setPhanquyentabelModel(DefaultTableModel phanquyentabelModel) {
        this.phanquyentabelModel = phanquyentabelModel;
    }

    public JScrollPane getPhanquyentabelScrolLPane() {
        return phanquyentabelScrolLPane;
    }

    public void setPhanquyentabelScrolLPane(JScrollPane phanquyentabelScrolLPane) {
        this.phanquyentabelScrolLPane = phanquyentabelScrolLPane;
    }

    public MainFunction getMainfunc() {
        return mainfunc;
    }

    public void setMainfunc(MainFunction mainfunc) {
        this.mainfunc = mainfunc;
    }

    public IntegratedSearch getSearchfunc() {
        return searchfunc;
    }

    public void setSearchfunc(IntegratedSearch searchfunc) {
        this.searchfunc = searchfunc;
    }

    public JPanel getHeaderPanel() {
        return headerPanel;
    }

    public void setHeaderPanel(JPanel headerPanel) {
        this.headerPanel = headerPanel;
    }

    public JPanel getCenterPanel() {
        return centerPanel;
    }

    public void setCenterPanel(JPanel centerPanel) {
        this.centerPanel = centerPanel;
    }

    public JPanel getHeaderMainFuncPanel() {
        return headerMainFuncPanel;
    }

    public void setHeaderMainFuncPanel(JPanel headerMainFuncPanel) {
        this.headerMainFuncPanel = headerMainFuncPanel;
    }
}