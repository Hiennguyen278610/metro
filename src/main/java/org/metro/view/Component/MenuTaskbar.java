package org.metro.view.Component;

import org.metro.view.MainFrame;
import org.metro.view.Panel.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuTaskbar extends JPanel {

    private MainFrame mainFrame;
    String[][] getSt = {
            {"Khách hàng", "customer.svg", "khachhang"},
            {"Nhân viên", "staff.svg", "nhanvien"},
            {"Tài khoản", "account.svg", "taikhoan"},
            {"Phân quyền", "permission.svg", "nhomquyen"},
    };
    public itemTaskbar[] listItem;
    JPanel pnCenter;

    Color FontColor = new Color(0, 0, 0);
    Color DefaultColor = new Color(148,183,205);
    Color HoverFontColor = new Color(200, 222, 231);
    Color HoverBackgroundColor = new Color(80,138,170);

    public MenuTaskbar(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        initComponent();
    }

    public void initComponent() {
        listItem = new itemTaskbar[getSt.length];
        this.setBackground(DefaultColor);
        this.setOpaque(true);
        this.setLayout(new BorderLayout(0, 0));

        pnCenter = new JPanel();
        pnCenter.setPreferredSize(new Dimension(300, 240));
        pnCenter.setBackground(DefaultColor);
        pnCenter.setLayout(new GridLayout(getSt.length, 1, 0, 0)); // Không có khoảng cách giữa các hàng
        this.add(pnCenter, BorderLayout.CENTER); // Thêm pnCenter vào MenuTaskbar

        // Khởi tạo các itemTaskbar
        for (int i = 0; i < getSt.length; i++) {
            listItem[i] = new itemTaskbar(getSt[i][1], getSt[i][0]);
            listItem[i].setVisible(true);
            pnCenter.add(listItem[i]);
        }

        // Thêm MouseListener cho từng mục
        listItem[0].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                pnMenuTaskbarMousePress(e); // Cập nhật giao diện
                KhachHang khachHang = new KhachHang();
                mainFrame.setPanel(khachHang); // Hiển thị panel Khách hàng
            }
        });

        listItem[1].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                pnMenuTaskbarMousePress(e);
                NhanVien nhanVien = new NhanVien();
                mainFrame.setPanel(nhanVien); // Hiển thị panel Nhân viên
            }
        });

        listItem[2].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                pnMenuTaskbarMousePress(e);
                TaiKhoan taiKhoan = new TaiKhoan();
                mainFrame.setPanel(taiKhoan); // Hiển thị panel Tài khoản
            }
        });

        listItem[3].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                pnMenuTaskbarMousePress(e);
                PhanQuyen phanQuyen = new PhanQuyen();
                mainFrame.setPanel(phanQuyen); // Hiển thị panel Phân quyền
            }
        });
    }

    public void pnMenuTaskbarMousePress(MouseEvent e) {
        for (int i = 0; i < getSt.length; i++) {
            if (e.getSource() == listItem[i]) {
                listItem[i].setBackground(HoverBackgroundColor);
                listItem[i].pnlContent.setForeground(HoverFontColor); // Cập nhật màu chữ
            } else {
                listItem[i].setBackground(DefaultColor);
                listItem[i].pnlContent.setForeground(FontColor);
            }
        }
    }
}