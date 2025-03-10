package org.metro.view.Panel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import org.metro.controller.MainFunction;
import org.metro.dao.NhanVienDAO;
import org.metro.model.NhanVienDTO;
import org.metro.service.IntegratedSearch;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class NhanVien extends JPanel {
    Color BackgroundColor = new Color(0, 2, 2);
    private JPanel contentDataPanel,functionBarPanel;
    private JTable nhanVienTabel;
    private JScrollPane nhanVienScroll; 
    private DefaultTableModel dataTabelModel;
    private MainFunction mainfunc;
    private IntegratedSearch searchfunc;
    private List<NhanVienDTO> listNhanVien;
    public NhanVien() {
        initComponent();
        listNhanVien = new ArrayList<>();
        reloadData(); // cap nhap table moi khi run 
    }

    public void initComponent() {
        //layout tong the
        this.setLayout(new BorderLayout(0,0));
        this.setBackground(BackgroundColor);

        //headerPanel chua search,combo box,btn reset + 4 function them,sua,xoa,chi tiet
        JPanel headerPanel = new JPanel(new BorderLayout(5,5));
        
        searchfunc = new IntegratedSearch(new String[]{"----","id","tên","sdt","gioi tinh","chuc vu"});
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
        dataTabelModel.setColumnIdentifiers(new String[]{"ma NV","Ten NV","so dien thoai","gioi tinh","chuc vu"});
        
        nhanVienTabel = new JTable();
        nhanVienTabel.setFillsViewportHeight(true); // lap day JScrollPane 
        nhanVienTabel.setModel(dataTabelModel);
        nhanVienScroll = new JScrollPane(nhanVienTabel);
        contentDataPanel.add(nhanVienScroll,BorderLayout.CENTER);

        this.add(contentDataPanel, BorderLayout.CENTER);
    }

    public void reloadList(List<NhanVienDTO> listNhanVien2) {
            dataTabelModel.setRowCount(0); //xoa het bang de tai lai tu dau
            for(NhanVienDTO nvd : listNhanVien2) {
            dataTabelModel.addRow(new Object[]{
                nvd.getManv(),
                nvd.getTennv(),
                nvd.getSdtnv(),
                nvd.getGioitinh(),
                nvd.getChucvu()
            });
        }
    }
    public void reloadData() {
        listNhanVien = new NhanVienDAO().selectAll();
        reloadList(listNhanVien);
    }
    
}