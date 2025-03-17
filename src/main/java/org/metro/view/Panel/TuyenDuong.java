package org.metro.view.Panel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import org.metro.controller.TuyenDuongController;
import org.metro.view.Component.IntegratedSearch;
import org.metro.view.Component.MainFunction;
import org.metro.view.Component.ToolBar;

import com.formdev.flatlaf.ui.FlatListCellBorder.Default;

import java.awt.*;

public class TuyenDuong extends JPanel {
    Color BackgroundColor = new Color(0, 2, 2);
    private IntegratedSearch searchfunc;
    private MainFunction mainfunc;
    private JPanel contentDataPanel, functionBarPanel;
    private DefaultTableModel dataTabelModel;
    private JTable nhanVienTabel;
    private JScrollPane nhanVienScroll;
    private TuyenDuongController action = new TuyenDuongController(this);

    public void initComponent() {
        this.setBackground(Color.white);
        this.setLayout(new BorderLayout(0, 0));
        // headerPanel chua search,combo box,btn reset + 4 function them,sua,xoa,chi
        // tiet
        JPanel headerPanel = new JPanel(new BorderLayout(5, 5));

        searchfunc = new IntegratedSearch(new String[] { "----", "id", "tên", "sdt", "gioi tinh", "chuc vu" });
        headerPanel.add(searchfunc, BorderLayout.WEST);

        mainfunc = new MainFunction(new String[] { "create", "delete", "update", "detail" });
        functionBarPanel = new JPanel();
        functionBarPanel.setLayout(new BoxLayout(functionBarPanel, BoxLayout.X_AXIS));
        functionBarPanel.setPreferredSize(new Dimension(50, 50));
        functionBarPanel.add(mainfunc);
        headerPanel.add(functionBarPanel, BorderLayout.SOUTH);

        this.add(headerPanel, BorderLayout.NORTH);

        // panel2 chua table + data
        contentDataPanel = new JPanel();
        contentDataPanel.setBackground(new Color(130, 190, 223));
        contentDataPanel.setPreferredSize(new Dimension(1100, 600));
        contentDataPanel.setLayout(new BorderLayout(10, 10));

        dataTabelModel = new DefaultTableModel() {
            @Override
            // tao model voi so cot va hang co dinh khong cho sua chua
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        // dat ten cho cac row cua table
        dataTabelModel
                .setColumnIdentifiers(new String[] { "ma NV", "Ten NV", "so dien thoai", "gioi tinh", "chuc vu" });

        nhanVienTabel = new JTable();
        nhanVienTabel.setFillsViewportHeight(true); // lap day JScrollPane
        nhanVienTabel.setRowSelectionAllowed(true); // cho phep chon hang
        nhanVienTabel.setModel(dataTabelModel);
        nhanVienScroll = new JScrollPane(nhanVienTabel);
        contentDataPanel.add(nhanVienScroll, BorderLayout.CENTER);

        // them acction
        searchfunc.getCbxChoose().addItemListener(action);

        // action check xem nhan nut nao
        for (ToolBar tb : mainfunc.getBtn().values()) {
            tb.addActionListener(action);
        }

        this.add(contentDataPanel, BorderLayout.CENTER);
    }

    public TuyenDuong() {
        initComponent();
    }
}
