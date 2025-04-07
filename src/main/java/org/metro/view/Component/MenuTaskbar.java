package org.metro.view.Component;

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
    String[][] getSt = {
            {"Tàu", "train.svg", "tau"},
            {"Trạm", "station.svg", "tram"},
            {"Tuyến đường", "route.svg", "tuyenduong"},
            {"Lịch trình", "schedule.svg", "lichtrinh"},
            {"Vé tàu", "ticket.svg", "vetau"},
            {"Bảo trì", "maintenance.svg", "baotri"},
            {"Khách hàng", "customer.svg", "khachhang"},
            {"Nhân viên", "staff.svg", "nhanvien"},
            {"Tài khoản", "account.svg", "taikhoan"},
            {"Phân quyền", "permission.svg", "nhomquyen"},
            {"Thống kê", "statistics.svg", "thongke"},
    };
//    public itemTaskbar[] listItem;
    JPanel pnCenter;

    Color FontColor = new Color(0, 0, 0);
    Color DefaultColor = new Color(148,183,205);
    Color HoverFontColor = new Color(200, 222, 231);
    Color HoverBackgroundColor = new Color(80,138,170);

    public MenuTaskbar(MainFrame mainFrame) {
        list = new ArrayList<itemTaskbar>();
        hm = new HashMap<String,JPanel>();
        this.mainFrame = mainFrame;
        hm.put("tau",new Tau());
        hm.put("tram",new Tram());
        hm.put("tuyenduong",new TuyenDuong());
        hm.put("lichtrinh",new LichTrinh());
        hm.put("vetau", new VeTau());
        hm.put("baotri",new LichBaoTri());
        hm.put("khachhang",new KhachHang());
        hm.put("nhanvien",new NhanVien());
        hm.put("taikhoan",new TaiKhoan());
        hm.put("nhomquyen",new PhanQuyen());
        hm.put("thongke",new ThongKe());
        initComponent();
    }

    public void initComponent() {
//        listItem = new itemTaskbar[getSt.length];
        this.setBackground(DefaultColor);
        this.setOpaque(true);
        this.setLayout(new BorderLayout(0, 0));

        pnCenter = new JPanel();
        pnCenter.setBackground(DefaultColor);
        pnCenter.setLayout(new GridLayout(getSt.length, 1, 0, 0)); // Không có khoảng cách giữa các hàng
        this.add(pnCenter, BorderLayout.CENTER); // Thêm pnCenter vào MenuTaskbar

        for(String[] it : getSt) {
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
        }
        for(itemTaskbar i : list) pnCenter.add(i);

//        for (int i = 0; i < getSt.length; i++) {
//            listItem[i] = new itemTaskbar(getSt[i][1], getSt[i][0]);
//            listItem[i].setVisible(true);
//            pnCenter.add(listItem[i]);
//        }

        // Thêm MouseListener cho từng mục
//        listItem[0].addMouseListener(new MouseAdapter() {
//            @Override
//            public void mousePressed(MouseEvent e) {
//                pnMenuTaskbarMousePress(e);
//                Tau tau = new Tau();
//                mainFrame.setPanel(tau);
//            }
//        });
//        listItem[1].addMouseListener(new MouseAdapter() {
//            @Override
//            public void mousePressed(MouseEvent e) {
//                pnMenuTaskbarMousePress(e);
//                Tram tram = new Tram();
//                mainFrame.setPanel(tram);
//            }
//        });
//        listItem[2].addMouseListener(new MouseAdapter() {
//            @Override
//            public void mousePressed(MouseEvent e) {
//                pnMenuTaskbarMousePress(e);
//                TuyenDuong tuyenDuong = new TuyenDuong();
//                mainFrame.setPanel(tuyenDuong);
//            }
//        });
//        listItem[3].addMouseListener(new MouseAdapter() {
//            @Override
//            public void mousePressed(MouseEvent e) {
//                pnMenuTaskbarMousePress(e);
//                LichTrinh lichTrinh = new LichTrinh();
//                mainFrame.setPanel(lichTrinh);
//            }
//        });
//        listItem[4].addMouseListener(new MouseAdapter() {
//            @Override
//            public void mousePressed(MouseEvent e) {
//                pnMenuTaskbarMousePress(e);
//                VeTau veTau = new VeTau();
//                mainFrame.setPanel(veTau);
//            }
//        });
//        listItem[5].addMouseListener(new MouseAdapter() {
//            @Override
//            public void mousePressed(MouseEvent e) {
//                pnMenuTaskbarMousePress(e);
//                LichBaoTri lichBaoTri = new LichBaoTri();
//                mainFrame.setPanel(lichBaoTri);
//            }
//        });
//        listItem[6].addMouseListener(new MouseAdapter() {
//            @Override
//            public void mousePressed(MouseEvent e) {
//                pnMenuTaskbarMousePress(e);
//                KhachHang khachHang = new KhachHang();
//                mainFrame.setPanel(khachHang);
//            }
//        });
//
//        listItem[7].addMouseListener(new MouseAdapter() {
//            @Override
//            public void mousePressed(MouseEvent e) {
//                pnMenuTaskbarMousePress(e);
//                NhanVien nhanVien = new NhanVien();
//                mainFrame.setPanel(nhanVien); // Hiển thị panel Nhân viên
//            }
//        });
//
//        listItem[8].addMouseListener(new MouseAdapter() {
//            @Override
//            public void mousePressed(MouseEvent e) {
//                pnMenuTaskbarMousePress(e);
//                TaiKhoan taiKhoan = new TaiKhoan();
//                mainFrame.setPanel(taiKhoan); // Hiển thị panel Tài khoản
//            }
//        });
//
//        listItem[9].addMouseListener(new MouseAdapter() {
//            @Override
//            public void mousePressed(MouseEvent e) {
//                pnMenuTaskbarMousePress(e);
//                PhanQuyen phanQuyen = new PhanQuyen();
//                mainFrame.setPanel(phanQuyen); // Hiển thị panel Phân quyền
//            }
//        });
//        listItem[10].addMouseListener(new MouseAdapter() {
//            @Override
//            public void mousePressed(MouseEvent e) {
//                pnMenuTaskbarMousePress(e);
//                ThongKe thongKe = new ThongKe();
//                mainFrame.setPanel(thongKe); // Hiển thị panel Phân quyền
//            }
//        });
    }

//    public void pnMenuTaskbarMousePress(MouseEvent e) {
//        for (int i = 0; i < getSt.length; i++) {
//            if (e.getSource() == listItem[i]) {
//                listItem[i].setBackground(HoverBackgroundColor);
//                listItem[i].lblContent.setForeground(HoverFontColor); // Cập nhật màu chữ
//            } else {
//                listItem[i].setBackground(DefaultColor);//
//                listItem[i].lblContent.setForeground(FontColor);
//            }
//        }
//    }
}