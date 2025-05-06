package org.metro.view.Dialog;

import org.metro.controller.PhanQuyenController;
import org.metro.model.PhanQuyenModel.ChiTietPhanQuyenModel;
import org.metro.model.PhanQuyenModel.NhomChucNangModel;
import org.metro.model.PhanQuyenModel.NhomQuyenModel;
import org.metro.service.PhanQuyenService;
import org.metro.util.SessionManager;
import org.metro.view.MainFrame;
import org.metro.view.Panel.PhanQuyenPackage.PhanQuyen;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;

public class PhanQuyenDialog extends JDialog {
    private MainFrame mf;
    private PhanQuyen pq;
    private PhanQuyenService pqs = new PhanQuyenService();
    private String tennhomquyen; // lay tu viec click vao 1 row
     private String title;
     //header chua tenQuyen + textfieldQuyen
     private JPanel header;
     private JLabel tenQuyen;
     private JTextField textfieldQuyen;
     //center chua table
    private JPanel center;
    private JTable table;
    private DefaultTableModel dtmodel;
    private JScrollPane scrollpane;
    private List<NhomChucNangModel> listncnm;
    //bottom them hoac thoat
    private JPanel bottom;
    private JButton them;
    private JButton thoat;
    private PhanQuyenController pqcontroller;
    public PhanQuyenDialog(String title,PhanQuyen pq,String tennhomquyen,MainFrame mf) {
        this.mf = mf;
        this.title = title;
        this.pq = pq;
        this.tennhomquyen = tennhomquyen;
        pqcontroller = new PhanQuyenController(pq,this,mf);
        this.setTitle(getTitle());
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        header = new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));
        center = new JPanel(new BorderLayout());
        bottom = new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));
        this.init();
    }

    private void init() {
        tenQuyen = new JLabel("Tên nhóm quyền: ");
        tenQuyen.setFont(new Font("Tahoma", Font.PLAIN, 16));
        textfieldQuyen = new JTextField(20);
        textfieldQuyen.setFont(new Font("Tahoma", Font.PLAIN, 13));
        textfieldQuyen.setPreferredSize(new Dimension(150, 40));
        textfieldQuyen.setText(getTennhomquyen());
        textfieldQuyen.setEditable("update".equals(title) || "create".equals(title));
        header.add(tenQuyen);
        header.add(textfieldQuyen);
        this.add(header, BorderLayout.NORTH);
        //tao tieu de cho cot
        String[] colName = {"Tên chức năng","Thêm","Sửa","Chi tiết","Xóa"};
        listncnm = PhanQuyenService.getNhomChucNang();
        dtmodel = new DefaultTableModel(colName,0){
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 0 ? String.class : Boolean.class;
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column > 0 && ("update".equals(title) || "create".equals(title));
            }
        };
        //neu an vao them thi load du lieu cho nut them
        if("create".equals(title)) {
            dataTableForCreate(dtmodel);
        } else {
            //neu la sua,chi tiet,xoa thi chon 1 row
            NhomQuyenModel nqm = pq.getSelectedPhanquyen(); // lay ra cai row dc click
            dataTable(dtmodel, nqm.getManhomquyen()); // load ra du lieu bang
        }
        table = new JTable(dtmodel);
        table.setRowHeight(30);
        table.setRowSelectionAllowed(false);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for(int i = 0 ; i < table.getColumnCount(); i++){
            if(i == 0) {
                table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            } else {
                table.getColumnModel().getColumn(i).setCellRenderer(table.getDefaultRenderer(Boolean.class));
            }
        }
        scrollpane = new JScrollPane(table);
        center.add(scrollpane,BorderLayout.CENTER);
        this.add(center, BorderLayout.CENTER);

        them = changeButton(getTitle());
        them.setPreferredSize(new Dimension(150, 40));
        them.setFont(new Font("Tahoma", Font.PLAIN, 13));
        them.setBackground(Color.CYAN);
        thoat = new JButton("thoat");
        thoat.setPreferredSize(new Dimension(150, 40));
        thoat.setFont(new Font("Tahoma", Font.PLAIN, 13));
        if("create".equals(title) || "update".equals(title)) {
            bottom.add(them);
        }
        bottom.add(thoat);
        this.add(bottom, BorderLayout.SOUTH);

        //them su kien
        them.addActionListener(pqcontroller);
        thoat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    //checkbox cho nút thêm
    public void dataTableForCreate(DefaultTableModel dtmodal) {
        for(NhomChucNangModel ncnm: listncnm) {
            dtmodal.addRow(new Object[]{
                    ncnm.getTenchucnang(),
                    Boolean.FALSE,
                    Boolean.FALSE,
                    Boolean.FALSE,
                    Boolean.FALSE
            });
        }
    }

    public void dataTable(DefaultTableModel dtmodal,int manhomquyen) {
        dtmodal.setRowCount(0);
        List<ChiTietPhanQuyenModel> listctpqm = PhanQuyenService.getChiTietPhanQuyenByManhomQuyen(manhomquyen);
        NhomQuyenModel nqm = pq.getSelectedPhanquyen();
        for(NhomChucNangModel ncnm :listncnm) {
            boolean them = false;
            boolean sua = false;
            boolean chitiet = false;
            boolean xoa = false;
            for(ChiTietPhanQuyenModel ctpqm : listctpqm) {
                if(ctpqm.getMachucnang() == ncnm.getMachucnang() && ctpqm.getManhomquyen() == nqm.getManhomquyen()) {
                    switch (ctpqm.getTenquyen()) {
                        case "create":
                            them = true;
                            break;
                        case "detail":
                            chitiet = true;
                            break;
                        case "update":
                            sua = true;
                            break;
                        case "delete":
                            xoa = true;
                            break;
                        default:
                            break;
                    }
                }
            }
            dtmodal.addRow(new Object[]{
                    ncnm.getTenchucnang(),
                    them,
                    sua,
                    chitiet,
                    xoa
            });
        }
    }

    // thay doi ten theo nut bam
    public String getTitle() {
        switch (title) {
            case "create":
                return "Thêm quyền";
            case "update":
                return "Chỉnh sửa quyền";
            case "detail":
                return "Xem chi tiết quyền";
            default:
                return "";
        }
    }

    //thay doi ten nut them theo nut bam
    public JButton changeButton(String title) {
        String namebtn = "";
        switch (title) {
            case "Thêm quyền":
                namebtn = "Thêm nhóm quyền";
                break;
            case "Chỉnh sửa quyền":
                namebtn = "Cập nhật";
                break;
            case "Xem chi tiết quyền":
                namebtn = "chi tiết quyền";
            default:
                break;
        }
        return new JButton(namebtn);
    }

    public String getTennhomquyen() {return tennhomquyen;}
    public void setTextfieldQuyen(JTextField textfieldQuyen) {
        this.textfieldQuyen = textfieldQuyen;
    }
    public JTextField getTextfieldQuyen() {return textfieldQuyen;}
    public JTable getTable() {return table;}

}
