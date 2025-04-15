package org.metro.view.Panel;

import org.metro.DAO.TaiKhoanDAO;
import org.metro.controller.NhanVienController;
import org.metro.controller.TaiKhoanController;
import org.metro.model.NhanVienModel;
import org.metro.model.PhanQuyenModel.NhomQuyenModel;
import org.metro.model.TaiKhoanModel;
import org.metro.service.TaiKhoanService;
import org.metro.view.Component.IntegratedSearch;
import org.metro.view.Component.MainFunction;
import org.metro.view.Dialog.TaiKhoanDialog;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class TaiKhoan extends JPanel {
    private static final int machucnang_taikhoan = 9;
    Color BackgroundColor = new Color(0, 2, 2);
    private JPanel contentCenter, functionBar;
    private JTable taiKhoanTable;
    private JScrollPane taiKhoanScrollTable;
    private DefaultTableModel dTable;
    private MainFunction mainFunction;
    private IntegratedSearch search;
    private ArrayList<TaiKhoanModel> listtk;
    private TaiKhoanController action = new TaiKhoanController(this,null);

    public TaiKhoan() {
        listtk = new ArrayList<>();
        init();
        reloadData();
    }
    private void init() {
        this.setLayout(new BorderLayout(10,10));
        this.setBackground(Color.decode("#dfe6e9"));

        JPanel headerPanel = new JPanel(new BorderLayout());
        search = new IntegratedSearch(new String[]{"----","mã"});
        headerPanel.add(search, BorderLayout.NORTH);

        mainFunction = new MainFunction(9,new String[]{"create","delete","update","detail"});
        functionBar= new JPanel();
        functionBar.setLayout(new BoxLayout(functionBar,BoxLayout.Y_AXIS));
        functionBar.setPreferredSize(new Dimension(50,50));
        functionBar.add(mainFunction);

        headerPanel.add(functionBar, BorderLayout.CENTER);
        this.add(headerPanel, BorderLayout.NORTH);

        contentCenter = new JPanel();
        contentCenter.setBackground(Color.decode("#dfe6e9"));
        contentCenter.setLayout(new BorderLayout());
        contentCenter.setPreferredSize(new Dimension(1100,600));

        dTable = new DefaultTableModel(){
            @Override
            // tao model voi so cot va hang co dinh khong cho sua chua
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        dTable.setColumnIdentifiers(new String[]{"MÃ TK","tên nhóm quyền","trạng thái"});
        taiKhoanTable = new JTable();
        taiKhoanTable.setRowSelectionAllowed(true);
        taiKhoanTable.setBackground(Color.decode("#ecf0f1"));
        taiKhoanTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
        taiKhoanTable.setForeground(Color.decode("#22a6b3"));
        taiKhoanTable.setRowHeight(40);
        taiKhoanTable.setModel(dTable);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for(int i = 0 ; i < taiKhoanTable.getColumnCount();++i) {
            taiKhoanTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        taiKhoanScrollTable = new JScrollPane(taiKhoanTable);
        contentCenter.add(taiKhoanScrollTable,BorderLayout.CENTER);

        search.getCbxChoose().addItemListener(action);
        search.getTxtSearchForm().addKeyListener(action);
        search.getBtnReset().addActionListener(action);

        for(String tb : mainFunction.getBtn().keySet()) {
            if(mainFunction.getBtn().get(tb) != null) {
                mainFunction.getBtn().get(tb).addActionListener(action);
            }
        }
        this.add(contentCenter, BorderLayout.CENTER);
    }

    public void reloadlist(List<TaiKhoanModel> lst) {
        dTable.setRowCount(0);
        for(TaiKhoanModel tkm : lst) {
            String tennhomquyen = (tkm.getNqm() == null) ? "":tkm.getNqm().getTennhomquyen();
            dTable.addRow(new Object[]{
                    tkm.getManv(),
                    tennhomquyen,
                    tkm.getTrangthai() == 1 ? "Hoạt Động" : "Ngưng hoạt động"
            });
        }
    }

    public void reloadData() {
        reloadlist(new TaiKhoanDAO().selectAll());
    }

    public TaiKhoanModel getSelectedTaiKhoan() {
        int row = taiKhoanTable.getSelectedRow();

        if (row >= 0) {
            return TaiKhoanService.loadData().get(row);
        }
        return null;
    }

    public Color getBackgroundColor() {
        return BackgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        BackgroundColor = backgroundColor;
    }

    public JPanel getContentCenter() {
        return contentCenter;
    }

    public void setContentCenter(JPanel contentCenter) {
        this.contentCenter = contentCenter;
    }

    public JPanel getFunctionBar() {
        return functionBar;
    }

    public void setFunctionBar(JPanel functionBar) {
        this.functionBar = functionBar;
    }

    public JTable getTaiKhoanTable() {
        return taiKhoanTable;
    }

    public void setTaiKhoanTable(JTable taiKhoanTable) {
        this.taiKhoanTable = taiKhoanTable;
    }

    public JScrollPane getTaiKhoanScrollTable() {
        return taiKhoanScrollTable;
    }

    public void setTaiKhoanScrollTable(JScrollPane taiKhoanScrollTable) {
        this.taiKhoanScrollTable = taiKhoanScrollTable;
    }

    public DefaultTableModel getdTable() {
        return dTable;
    }

    public void setdTable(DefaultTableModel dTable) {
        this.dTable = dTable;
    }

    public MainFunction getMainFunction() {
        return mainFunction;
    }

    public void setMainFunction(MainFunction mainFunction) {
        this.mainFunction = mainFunction;
    }

    public IntegratedSearch getSearch() {
        return search;
    }

    public void setSearch(IntegratedSearch search) {
        this.search = search;
    }

    public ArrayList<TaiKhoanModel> getListtk() {
        return listtk;
    }

    public void setListtk(ArrayList<TaiKhoanModel> listtk) {
        this.listtk = listtk;
    }

    public TaiKhoanController getAction() {
        return action;
    }

    public void setAction(TaiKhoanController action) {
        this.action = action;
    }
}