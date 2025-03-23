package org.metro.view.Panel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import org.metro.DAO.NhanVienDAO;
import org.metro.controller.NhanVienController;
import org.metro.model.NhanVienModel;
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
    Color BackgroundColor = new Color(0, 2, 2);
    private MainFrame mf;
    private JPanel contentDataPanel,functionBarPanel;
    private JTable nhanVienTabel;
    private JScrollPane nhanVienScroll; 
    private DefaultTableModel dataTabelModel;
    private MainFunction mainfunc;
    private IntegratedSearch searchfunc;
    private List<NhanVienModel> listNhanVien;
    private Timer timeSearch;
    private NhanVienDialog nvdl;
    private NhanVienController action = new NhanVienController(this);
    public NhanVien() {
        initComponent();
        listNhanVien = new ArrayList<>();
        timeSearch = new Timer();
        this.nvdl = nvdl;
        reloadData(); // cap nhap table moi khi run 
    }

    public void initComponent() {
        //layout tong the
        this.setLayout(new BorderLayout(0,0));
        this.setBackground(BackgroundColor);

        //headerPanel chua search,combo box,btn reset + 4 function them,sua,xoa,chi tiet
        JPanel headerPanel = new JPanel(new BorderLayout(5,5));
        
        searchfunc = new IntegratedSearch(new String[]{"----","mã","tên","số điện thoại","giới tính","chức vụ"});
        headerPanel.add(searchfunc,BorderLayout.WEST);
        
        mainfunc = new MainFunction(new String[]{"create","delete","update","detail"});
        functionBarPanel = new JPanel();
        functionBarPanel.setLayout(new BoxLayout(functionBarPanel, BoxLayout.X_AXIS));
        functionBarPanel.setPreferredSize(new Dimension(50,50));
        functionBarPanel.add(mainfunc);
        headerPanel.add(functionBarPanel,BorderLayout.SOUTH);

        this.add(headerPanel,BorderLayout.NORTH);

        //panel2 chua table + data
        contentDataPanel = new JPanel();
        contentDataPanel.setBackground(new Color(130,190,223));
        contentDataPanel.setPreferredSize(new Dimension(1100,600));
        contentDataPanel.setLayout(new BorderLayout(10,10));
        
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
        nhanVienTabel.setFillsViewportHeight(true); // lap day JScrollPane 
        nhanVienTabel.setRowSelectionAllowed(true); // cho phep chon hang
        nhanVienTabel.setModel(dataTabelModel);
        nhanVienScroll = new JScrollPane(nhanVienTabel);
        contentDataPanel.add(nhanVienScroll,BorderLayout.CENTER);


        //them acction
        searchfunc.getCbxChoose().addItemListener(action);

        //action check xem nhan nut nao
        for(ToolBar tb : mainfunc.getBtn().values()) {
            tb.addActionListener(action);
        }

        this.add(contentDataPanel, BorderLayout.CENTER);
    }

    public void reloadList(List<NhanVienModel> listNhanVien2) {
        dataTabelModel.setRowCount(0); //xoa het bang de tai lai tu dau
        if(listNhanVien2 != null) {
            for(NhanVienModel nvd : listNhanVien2) {
                dataTabelModel.addRow(new Object[]{
                    nvd.getManv(),
                    nvd.getTennv(),
                    nvd.getSdtnv(),
                    nvd.getGioitinh(),
                    nvd.getChucvu()
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
        String word = (String) searchfunc.getTxtSearchForm().getText(); 
        
    }
    
    //load thoi gian tim kiem moi 0.2s
    public void loadTimeSearch() {
        timeSearch.cancel();
        timeSearch = new Timer();
        timeSearch.schedule(new TimerTask() {

            @Override
            public void run() {
                
            }
            
        }, 200);
    }

    public NhanVienModel getSelectedNhanvien() {
        int row = nhanVienTabel.getSelectedRow();
        System.out.println(row);
        if(row == - 1) return null;
        int manv = (int) nhanVienTabel.getValueAt(row, 0);
        String tennv = (String) nhanVienTabel.getValueAt(row, 1);
        String sdtnv = (String) nhanVienTabel.getValueAt(row, 2);
        String gioitinh = (String) nhanVienTabel.getValueAt(row, 3);
        String chucvu = (String) nhanVienTabel.getValueAt(row, 4);

        return new NhanVienModel(manv,tennv,sdtnv,gioitinh,chucvu);
    }

    //getter setter
    public Color getBackgroundColor() {
        return BackgroundColor;
    }

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

    public void setBackgroundColor(Color backgroundColor) {
        BackgroundColor = backgroundColor;
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

    public Timer getTimeSearch() {
        return timeSearch;
    }

    public void setTimeSearch(Timer timeSearch) {
        this.timeSearch = timeSearch;
    }
    public void setMf(MainFrame mf) {
        this.mf = mf;
    }


}