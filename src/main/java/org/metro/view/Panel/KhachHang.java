package org.metro.view.Panel;

import org.metro.controller.MainFunction;
import org.metro.model.KhachHangDTO;
import org.metro.service.IntegratedSearch;
import org.metro.service.KhachHangBUS;
import org.metro.view.Dialog.KhachHangDialog;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class KhachHang extends JPanel implements ActionListener, ItemListener {
    Color BackgroundColor = new Color(0, 2, 2);
    JPanel contentCenter, functionBar;
    JTable khachHangTable;
    JScrollPane khachHangScrollTable;
    DefaultTableModel dTable;
    MainFunction mainFunction;
    IntegratedSearch search;
    ArrayList<KhachHangDTO> listkh;
    Timer searchTimer;

    public KhachHang() {
        initComponent();
    }

    private void initComponent() {
        this.setBackground(BackgroundColor);
        this.setLayout(new BorderLayout(0,0));

        khachHangTable = new JTable();
        khachHangScrollTable = new JScrollPane();
        dTable = new DefaultTableModel();
        String[] columnNames = {"Mã KH", "Tên khách hàng", "Số điện thoại", "Số lần đi"};
        dTable.setColumnIdentifiers(columnNames);
        khachHangTable.setModel(dTable);
        khachHangTable.setFocusable(false);
        khachHangScrollTable.setViewportView(khachHangTable);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for(int i = 0; i < columnNames.length; i++){
            khachHangTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        khachHangTable.setAutoCreateRowSorter(true);

        contentCenter = new JPanel();
        contentCenter.setBackground(new Color(130,190,223));
        contentCenter.setPreferredSize(new Dimension(1100,600));
        contentCenter.setLayout(new BorderLayout(10,10));
        this.add(contentCenter, BorderLayout.CENTER);

        functionBar = new JPanel();
        functionBar.setLayout(new BoxLayout(functionBar, BoxLayout.X_AXIS));
        functionBar.setPreferredSize(new Dimension(0,50));

        search = new IntegratedSearch(new String[]{"Tất cả", "ID", "Tên khách hàng", "Số lần đi"});
        search.cbxChoose.addItemListener(this);
        search.txtSearchForm.addKeyListener(new KeyAdapter(){
            @Override
            public void keyReleased(KeyEvent e){
                if(searchTimer != null){
                    searchTimer.cancel();
                }
                searchTimer = new Timer();
                searchTimer.schedule(new TimerTask(){
                    @Override
                    public void run(){
                        SwingUtilities.invokeLater(() -> performSearch());
                    }
                },300);
            }
        });
        search.btnReset.addActionListener(e -> {
            search.txtSearchForm.setText("");
            loadDataTable();
        });

        String[] actions = {"create", "update", "delete", "detail"};
        mainFunction = new MainFunction(actions);
        for(String action : actions){
            mainFunction.btn.get(action).addActionListener(this);
        }
        functionBar.add(mainFunction);
        JPanel combinedPanel = new JPanel(new BorderLayout());
        combinedPanel.add(search, BorderLayout.NORTH);
        combinedPanel.add(functionBar, BorderLayout.CENTER);

        contentCenter.add(combinedPanel, BorderLayout.NORTH);
        contentCenter.add(khachHangScrollTable, BorderLayout.CENTER);

        loadDataTable();
    }

    private void populateTable(ArrayList<KhachHangDTO> data) {
        dTable.setRowCount(0);
        for (KhachHangDTO kh : data) {
            dTable.addRow(new Object[]{
                    kh.getMaKh(),
                    kh.getTenKh(),
                    kh.getSdt(),
                    kh.getSolan()
            });
        }
    }

    public void loadDataTable() {
        listkh = KhachHangBUS.search("", "Tất cả");
        populateTable(listkh);
    }

    private void performSearch() {
        String type = (String) search.cbxChoose.getSelectedItem();
        String txt = search.txtSearchForm.getText().trim();
        System.out.println("Đang tìm kiếm: " + txt + " - Loại: " + type);
        ArrayList<KhachHangDTO> result = KhachHangBUS.search(txt, type);
        populateTable(result);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == mainFunction.btn.get("create")){
            new KhachHangDialog().showAddKhachHangDialog(this, this::loadDataTable);
        } else if(e.getSource() == mainFunction.btn.get("update")){
            int selectedRow = khachHangTable.getSelectedRow();
            if(selectedRow != -1){
                int maKh = (int) khachHangTable.getValueAt(selectedRow, 0);
                new KhachHangDialog().showUpdateKhachHangDialog(this, maKh, this::loadDataTable);
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn khách hàng cần cập nhật!");
            }
        } else if(e.getSource() == mainFunction.btn.get("delete")){
            int selectedRow = khachHangTable.getSelectedRow();
            if(selectedRow != -1){
                int maKh = (int) khachHangTable.getValueAt(selectedRow, 0);
                int option = JOptionPane.showConfirmDialog(this,
                        "Bạn có chắc muốn xóa khách hàng này?",
                        "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
                if(option == JOptionPane.YES_OPTION){
                    if(KhachHangBUS.delete(maKh)){
                        JOptionPane.showMessageDialog(this, "Xóa khách hàng thành công!");
                        loadDataTable();
                    } else {
                        JOptionPane.showMessageDialog(this, "Xóa khách hàng thất bại!");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn khách hàng cần xóa!");
            }
        } else if(e.getSource() == mainFunction.btn.get("detail")){
            int selectedRow = khachHangTable.getSelectedRow();
            if(selectedRow != -1){
                int maKh = (int) khachHangTable.getValueAt(selectedRow, 0);
                KhachHangDTO kh = KhachHangBUS.getById(maKh);
                if(kh != null){
                    new KhachHangDialog().showKhachHangDetailDialog(this, kh);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn khách hàng để xem chi tiết!");
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getStateChange() == ItemEvent.SELECTED){
            performSearch();
        }
    }
}
