package org.metro.view.Panel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.metro.DAO.NhanVienDAO;
import org.metro.controller.NhanVienController;
import org.metro.model.NhanVienModel;
import org.metro.service.NhanVienService;
import org.metro.view.Component.IntegratedSearch;
import org.metro.view.Component.MainFunction;
import org.metro.view.Component.ToolBar;
import org.metro.view.Dialog.NhanVienDialog;
import org.metro.view.MainFrame;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import java.util.Timer;
import java.util.TimerTask;

public class NhanVien extends JPanel {
    private static final int machucnang_nhanvien = 8;
    private MainFrame mf;
    private JPanel contentDataPanel,functionBarPanel;
    private JTable nhanVienTabel;
    private JScrollPane nhanVienScroll; 
    private DefaultTableModel dataTabelModel;
    private MainFunction mainfunc;
    private IntegratedSearch searchfunc;
    private List<NhanVienModel> listNhanVien;
    private NhanVienController action = new NhanVienController(this,null);
    private NhanVienService nvs = new NhanVienService(this);
    public NhanVien() {
        initComponent();
        listNhanVien = new ArrayList<>();
        reloadData(); // cap nhap table moi khi run 
    }

    public void initComponent() {
        //layout tong the
        this.setLayout(new BorderLayout(10,10));
        this.setBackground(Color.decode("#dfe6e9"));

        //headerPanel chua search,combo box,btn reset + 4 function them,sua,xoa,chi tiet
        JPanel headerPanel = new JPanel(new BorderLayout());
        
        searchfunc = new IntegratedSearch(new String[]{"----","mã","tên","số điện thoại","giới tính","chức vụ"});
        headerPanel.add(searchfunc,BorderLayout.NORTH);
        
        mainfunc = new MainFunction(8,new String[]{"create","delete","update","detail"});
        functionBarPanel = new JPanel();
        functionBarPanel.setLayout(new BoxLayout(functionBarPanel, BoxLayout.X_AXIS));
        functionBarPanel.setPreferredSize(new Dimension(50,50));
        functionBarPanel.add(mainfunc);
        headerPanel.add(functionBarPanel,BorderLayout.CENTER);

        this.add(headerPanel,BorderLayout.NORTH);

        //panel2 chua table + data
        contentDataPanel = new JPanel();
        contentDataPanel.setBackground(new Color(130,190,223));
        contentDataPanel.setPreferredSize(new Dimension(1100,600));
        contentDataPanel.setLayout(new BorderLayout());
        
        dataTabelModel = new DefaultTableModel(){
            @Override
            // tao model voi so cot va hang co dinh khong cho sua chua
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        //dat ten cho cac row cua table
        dataTabelModel.setColumnIdentifiers(new String[]{"MÃ NV","Tên NV","Số điện thoại","Giới tính","Chức vụ"});
        
        nhanVienTabel = new JTable();
        nhanVienTabel.setRowSelectionAllowed(true); // cho phep chon hang
        nhanVienTabel.setBackground(Color.decode("#ecf0f1"));
        nhanVienTabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        nhanVienTabel.setForeground(Color.decode("#22a6b3"));
        nhanVienTabel.setRowHeight(40);
        nhanVienTabel.setModel(dataTabelModel);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for(int i = 0 ; i < nhanVienTabel.getColumnCount();++i) {
            nhanVienTabel.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        nhanVienScroll = new JScrollPane(nhanVienTabel);
        contentDataPanel.add(nhanVienScroll,BorderLayout.CENTER);


        //them acction
        searchfunc.getCbxChoose().addItemListener(action);
        searchfunc.getTxtSearchForm().addKeyListener(action);
        searchfunc.getBtnReset().addActionListener(action);

        for(String tb : mainfunc.getBtn().keySet()) {
            if(mainfunc.getBtn().get(tb) != null) {
                mainfunc.getBtn().get(tb).addActionListener(action);
            }
        }
        this.add(contentDataPanel, BorderLayout.CENTER);
    }

    public void reloadList(List<NhanVienModel> listNhanVien2) {
        dataTabelModel.setRowCount(0); //xoa het bang de tai lai tu dau
        if(listNhanVien2 != null) {
            for(NhanVienModel nvm : listNhanVien2) {
                dataTabelModel.addRow(new Object[]{
                    nvm.getManv(),
                    nvm.getTennv(),
                    nvm.getSdtnv(),
                    nvm.getGioitinh(),
                    nvm.getChucvu()
                });
            }   
        }
    }
    public void reloadData() {
        List<NhanVienModel> lst = new NhanVienDAO().selectAll();
        reloadList(lst);
    }

    //tim kiem theo field nhap
    public void searchByKeyWord() {
        String key = (String) searchfunc.getCbxChoose().getSelectedItem();
        String word = searchfunc.getTxtSearchForm().getText();
        System.out.println("tim kiem theo tu khoa: " + key + "type: " + word);
        if(word != null || !(word.trim().isEmpty())) {
            List<NhanVienModel> lst = NhanVienService.searchByKeyWord(key,word);
            reloadList(lst);
        } else {
            reloadData();
        }
    }

    public NhanVienModel getSelectedNhanvien() {
        int row = nhanVienTabel.getSelectedRow();
        if(row == - 1) return null;
        int manv = (int) nhanVienTabel.getValueAt(row, 0);
        String tennv = (String) nhanVienTabel.getValueAt(row, 1);
        String sdtnv = (String) nhanVienTabel.getValueAt(row, 2);
        String gioitinh = (String) nhanVienTabel.getValueAt(row, 3);
        String chucvu = (String) nhanVienTabel.getValueAt(row, 4);

        return new NhanVienModel(manv,tennv,sdtnv,gioitinh,chucvu);
    }

    //getter setter
    public JPanel getContentDataPanel() {
        return contentDataPanel;
    }

    public JPanel getFunctionBarPanel() {
        return functionBarPanel;
    }

    public JTable getNhanVienTabel() {
        return nhanVienTabel;
    }

    public JScrollPane getNhanVienScroll() {
        return nhanVienScroll;
    }

    public DefaultTableModel getDataTabelModel() {
        return dataTabelModel;
    }

    public MainFunction getMainfunc() {
        return mainfunc;
    }

    public IntegratedSearch getSearchfunc() {
        return searchfunc;
    }

    public List<NhanVienModel> getListNhanVien() {
        return listNhanVien;
    }

    public MainFrame getMf() {
        return mf;
    }

    public void setContentDataPanel(JPanel contentDataPanel) {
        this.contentDataPanel = contentDataPanel;
    }

    public void setFunctionBarPanel(JPanel functionBarPanel) {
        this.functionBarPanel = functionBarPanel;
    }

    public void setNhanVienTabel(JTable nhanVienTabel) {
        this.nhanVienTabel = nhanVienTabel;
    }

    public void setNhanVienScroll(JScrollPane nhanVienScroll) {
        this.nhanVienScroll = nhanVienScroll;
    }

    public void setDataTabelModel(DefaultTableModel dataTabelModel) {
        this.dataTabelModel = dataTabelModel;
    }

    public void setMainfunc(MainFunction mainfunc) {
        this.mainfunc = mainfunc;
    }

    public void setSearchfunc(IntegratedSearch searchfunc) {
        this.searchfunc = searchfunc;
    }

    public void setListNhanVien(List<NhanVienModel> listNhanVien) {
        this.listNhanVien = listNhanVien;
    }

    public void setMf(MainFrame mf) {
        this.mf = mf;
    }

    public NhanVienService getNvs() {
        return nvs;
    }
}