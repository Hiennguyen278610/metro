package org.metro.view.Component;

import org.metro.model.PhanQuyenModel.ChiTietPhanQuyenModel;
import org.metro.model.TaiKhoanModel;
import org.metro.service.PhanQuyenService;
import org.metro.view.MainFrame;
import org.metro.view.Panel.*;
import org.metro.view.Panel.PhanQuyenPackage.PhanQuyen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MenuTaskbar extends JPanel {
    private List<itemTaskbar> list;
    private HashMap<String,JPanel> hm;
    private MainFrame mainFrame;
    private PhanQuyenService pqs = new PhanQuyenService();
    String[][] getSt = {
            {"Tàu", "train.svg", "1"},
            {"Trạm", "station.svg", "2"},
            {"Tuyến đường", "route.svg", "3"},
            {"Lịch trình", "schedule.svg", "4"},
            {"Vé tàu", "ticket.svg", "5"},
            {"Bảo trì", "maintenance.svg", "6"},
            {"Khách hàng", "customer.svg", "7"},
            {"Nhân viên", "staff.svg", "8"},
            {"Tài khoản", "account.svg", "9"},
            {"Phân quyền", "permission.svg", "10"},
            {"Thống kê", "statistics.svg", "11"},
    };
//    public itemTaskbar[] listItem;
    JPanel pnCenter;

    Color FontColor = new Color(0, 0, 0);
    Color DefaultColor = new Color(148,183,205);
    Color HoverFontColor = new Color(200, 222, 231);
    Color HoverBackgroundColor = new Color(80,138,170);

    public MenuTaskbar(MainFrame mainFrame,int manv) {
        list = new ArrayList<itemTaskbar>();
        hm = new HashMap<String,JPanel>();
        this.mainFrame = mainFrame;
        hm.put("1",new Tau());
        hm.put("2",new Tram());
        hm.put("3",new TuyenDuong());
        hm.put("4",new LichTrinh());
        hm.put("5", new VeTau());
        hm.put("6",new LichBaoTri());
        hm.put("7",new KhachHang());
        hm.put("8",new NhanVien());
        hm.put("9",new TaiKhoan());
        hm.put("10",new PhanQuyen());
        hm.put("11",new ThongKe());
        List<ChiTietPhanQuyenModel> listctpqm = PhanQuyenService.getChiTietPhanQuyen(manv);
        initComponent(listctpqm);
    }

    public void initComponent(List<ChiTietPhanQuyenModel> listctpqm) {
        this.setBackground(DefaultColor);
        this.setOpaque(true);
        this.setLayout(new BorderLayout(0, 0));

        pnCenter = new JPanel();
        pnCenter.setBackground(DefaultColor);
        pnCenter.setLayout(new GridLayout(getSt.length, 1, 0, 0)); // Không có khoảng cách giữa các hàng

        for(String[] it : getSt) {
            String machucnang = it[2];
            for(ChiTietPhanQuyenModel ctpqm : listctpqm) {
                if(String.valueOf(ctpqm.getMachucnang()).equals(machucnang)) {
                    itemTaskbar i = new itemTaskbar(it[1],it[0],it[2]);
                    i.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            if(e.getSource() == i) {
                                i.setBackground(HoverBackgroundColor);
                                i.getLblContent().setForeground(HoverFontColor);
                            } else {
                                i.setBackground(DefaultColor);
                                i.getLblContent().setForeground(FontColor);
                            }
                            if(hm.containsKey(it[2])) {
                                mainFrame.setPanel(hm.get(it[2]));
                            }
                        }
                    });
                    list.add(i);
                    pnCenter.add(i);
                    break;
                }
            }
        }

        this.add(pnCenter, BorderLayout.CENTER); // Thêm pnCenter vào MenuTaskbar
    }
}