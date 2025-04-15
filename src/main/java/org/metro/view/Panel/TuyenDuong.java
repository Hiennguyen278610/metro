package org.metro.view.Panel;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import org.metro.DAO.TuyenDAO;
import org.metro.controller.TuyenDuongController;
import org.metro.model.TuyenDuongModel;
import org.metro.view.Component.IntegratedSearch;
import org.metro.view.Component.MainFunction;
import org.metro.view.Component.ToolBar;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class TuyenDuong extends JPanel {
    private static final int machucnang_tuyenduong = 3;
    Color BackgroundColor = new Color(0, 2, 2);
    private IntegratedSearch search;
    private MainFunction mainfunc;
    private JPanel contentDataPanel, functionBarPanel;
    private DefaultTableModel dataTabelModel;
    private JTable nhanVienTabel;
    private JScrollPane nhanVienScroll;
    private JPanel XemDoThi;
    private CardLayout cardLayout;
    private TuyenDuongController action = new TuyenDuongController(this);
    private JPanel MainPanel;
    private DoThiTuyenDuong DoThiPanel;
    private Timer searchTimer;

    public void initComponent() {
        this.setBackground(Color.white);
        cardLayout = new CardLayout();
        this.setLayout(cardLayout);
        // headerPanel chua search,combo box,btn reset + 4 function them,sua,xoa,chi

        MainPanel = new JPanel();
        MainPanel.setLayout(new BorderLayout());
        MainPanel.setBackground(Color.white);

        DoThiPanel = new DoThiTuyenDuong();

        this.add(MainPanel, "MainPanel");
        this.add(DoThiPanel, "DoThiPanel");

        JPanel headerPanel = new JPanel(new BorderLayout(5, 5));
        headerPanel.setBackground(Color.white);

        search = new IntegratedSearch(
                new String[] { "Tất cả", "Mã tuyến", "Trạm bắt đầu", "Trạm đích", "Trạng thái" });
        search.cbxChoose.addItemListener(action);
        search.txtSearchForm.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (searchTimer != null) {
                    searchTimer.cancel();
                }
                searchTimer = new Timer();
                searchTimer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        SwingUtilities.invokeLater(() -> performSearch());
                    }
                }, 300);
            }
        });
        search.btnReset.addActionListener(e -> {
            search.txtSearchForm.setText("");
            loadData();
        });
        headerPanel.add(search, BorderLayout.WEST);

        mainfunc = new MainFunction(machucnang_tuyenduong,new String[] { "create", "delete", "update", "detail" });
        functionBarPanel = new JPanel();
        functionBarPanel.setLayout(new BoxLayout(functionBarPanel, BoxLayout.X_AXIS));
        functionBarPanel.setPreferredSize(new Dimension(50, 50));
        functionBarPanel.add(mainfunc);
        headerPanel.add(functionBarPanel, BorderLayout.SOUTH);

        JPanel XemDoThiPanel = new JPanel();
        XemDoThiPanel.setBackground(Color.white);
        XemDoThiPanel.setPreferredSize(new Dimension(200, 52));
        XemDoThiPanel.setLayout(null);

        XemDoThi = new JPanel();
        XemDoThi.setBounds(5, 10, 140, 40);
        XemDoThi.setBackground(Color.white);
        XemDoThi.setLayout(null);
        XemDoThi.setBorder(new LineBorder(Color.black, 1));
        XemDoThiPanel.add(XemDoThi);

        XemDoThi.addMouseListener(action);
        DoThiPanel.getBack().addMouseListener(action);

        JLabel XemDoThiLabel = new JLabel("XEM TỔNG QUÁT");
        XemDoThiLabel.setBounds(10, 0, 140, 40);
        XemDoThiLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        XemDoThi.add(XemDoThiLabel);

        headerPanel.add(XemDoThiPanel, BorderLayout.EAST);

        MainPanel.add(headerPanel, BorderLayout.NORTH);

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
                .setColumnIdentifiers(
                        new String[] { "Mã tàu", "Trạm bắt đầu", "Trạm đích", "Thời gian di chuyển", "Trạng thái" });

        nhanVienTabel = new JTable();
        nhanVienTabel.setFillsViewportHeight(true); // lap day JScrollPane
        nhanVienTabel.setRowSelectionAllowed(true); // cho phep chon hang
        nhanVienTabel.setModel(dataTabelModel);
        nhanVienScroll = new JScrollPane(nhanVienTabel);
        contentDataPanel.add(nhanVienScroll, BorderLayout.CENTER);

        // action check xem nhan nut nao
        for (ToolBar tb : mainfunc.getBtn().values()) {
            if(tb != null) {
                tb.addMouseListener(action);
            }
        }

        MainPanel.add(contentDataPanel, BorderLayout.CENTER);
    }

    public TuyenDuong() {
        initComponent();
        loadData();
    }

    private void performSearch() {
        String searchText = search.txtSearchForm.getText().trim();
        String selectedCheckbox = (String) search.cbxChoose.getSelectedItem();
        List<TuyenDuongModel> listTuyenDuong = new TuyenDAO().search(searchText, selectedCheckbox);
        reloadList(listTuyenDuong);
    }

    public void loadData() {
        List<TuyenDuongModel> lst = new TuyenDAO().selectAll();
        reloadList(lst);
    }

    private void reloadList(List<TuyenDuongModel> listTuyenDuong) {
        dataTabelModel.setRowCount(0); // xoa het bang de tai lai tu dau
        if (listTuyenDuong != null) {
            for (TuyenDuongModel tdm : listTuyenDuong) {
                dataTabelModel.addRow(new Object[] { tdm.getMatuyen(), tdm.getTramdau(), tdm.getTramdich(),
                        tdm.getThoigiandichuyen(), tdm.getTrangthaituyen() });
            }
        }

    }

    public JPanel getXemDoThi() {
        return XemDoThi;
    }

    public void showPanel(String name) {
        cardLayout.show(this, name);
    }

    public JLabel getBack() {
        return DoThiPanel.getBack();
    }

    public IntegratedSearch getSearchfunc() {
        return search;
    }

    public MainFunction getMainfunc() {
        return mainfunc;
    }

}
