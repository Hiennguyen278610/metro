package org.metro.view.Component;

import org.metro.model.PhanQuyenModel.ChiTietPhanQuyenModel;
import org.metro.model.PhanQuyenModel.NhomChucNangModel;
import org.metro.model.TaiKhoanModel;
import org.metro.service.PhanQuyenService;
import org.metro.util.SessionManager;
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
    JPanel pnCenter;

    Color FontColor = new Color(0, 0, 0);
    Color DefaultColor = new Color(148,183,205);
    Color HoverFontColor = new Color(200, 222, 231);
    Color HoverBackgroundColor = new Color(80,138,170);

    public MenuTaskbar(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        list = new ArrayList<>();
        hm = new HashMap<>();

        hm.put("1",new Tau());
        hm.put("2",new Tram());
        hm.put("3",new TuyenDuong());
        hm.put("4",new LichTrinh());
        hm.put("5", new VeTau());
        hm.put("6",new LichBaoTri());
        hm.put("7",new KhachHang());
        hm.put("8",new NhanVien());
        hm.put("9",new TaiKhoan());
        hm.put("10",new PhanQuyen(mainFrame));
        hm.put("11",new ThongKe());

        initComponent();
    }

    public void initComponent() {
        this.setBackground(DefaultColor);
        this.setOpaque(true);
        this.setLayout(new BorderLayout(0, 0));

        pnCenter = new JPanel();
        pnCenter.setBackground(DefaultColor);
        pnCenter.setLayout(new GridLayout(getSt.length, 1, 0, 0)); // Không có khoảng cách giữa các hàng

        List<NhomChucNangModel> listncnm = PhanQuyenService.getNhomChucNang();

        for(NhomChucNangModel ncnm : listncnm) {
            String machucnang = String.valueOf(ncnm.getMachucnang());
            if(SessionManager.checkAnyQuyenCurrentUser(ncnm.getMachucnang())) {
                String icon = getIcon(machucnang);
                itemTaskbar i = new itemTaskbar(icon,ncnm.getTenchucnang(),machucnang);
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
                        if(hm.containsKey(machucnang)) {
                            mainFrame.setPanel(hm.get(machucnang));
                        }
                    }
                });
                list.add(i);
                pnCenter.add(i);
            }
        }
        this.add(pnCenter, BorderLayout.CENTER); // Thêm pnCenter vào MenuTaskbar
    }

    private String getIcon(String machucnang) {
        switch (machucnang) {
            case "1":
                return "train.svg";
            case "2":
                return "station.svg";
            case "3":
                return "route.svg";
            case "4":
                return "schedule.svg";
            case "5":
                return "ticket.svg";
            case "6":
                return "maintenance.svg";
            case "7":
                return "customer.svg";
            case "8":
                return "staff.svg";
            case "9":
                return "account.svg";
            case "10":
                return "permission.svg";
            case "11":
                return "statistics.svg";
            default:
                return "khong tim thay icon";
        }
    }
    public void refresh() {
        pnCenter.removeAll();
        initComponent();
        pnCenter.revalidate();
        pnCenter.repaint();
    }
}