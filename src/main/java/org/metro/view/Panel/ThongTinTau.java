package org.metro.view.Panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import org.metro.model.TauModel;
import org.metro.view.Component.RoundedPanel;

import com.formdev.flatlaf.extras.FlatSVGIcon;

public class ThongTinTau extends RoundedPanel {
    public ThongTinTau(TauModel tau) {
        super(20);
        initComponent(tau);
    }

    private void initComponent(TauModel tau) {
        this.setPreferredSize(new Dimension(240, 240));
        this.setBackground(Color.white);
        this.setLayout(null);
        this.setBorder(BorderFactory.createLineBorder(Color.black, 2));

        FlatSVGIcon icon = new FlatSVGIcon(getClass().getResource("/svg/train.svg")).derive(100, 100);
        JLabel HinhAnhTau1 = new JLabel(icon);
        HinhAnhTau1.setBounds(75, 10, 100, 100);
        this.add(HinhAnhTau1);

        JLabel MaTau = new JLabel("Mã tàu: " + tau.getMatau());
        MaTau.setBounds(10, 120, 200, 30);
        MaTau.setFont(new Font("Arial", Font.BOLD, 16));

        // JLabel SoGhe = new JLabel("Số ghế: " + list.get(i).getSoghe());
        // SoGhe.setPreferredSize(new Dimension(200, 30));
        // SoGhe.setFont(new Font("Arial", Font.BOLD, 16));

        TrangThaiTau trangthai = new TrangThaiTau(tau.getTrangthaitau());
        trangthai.setBounds(10, 190, 200, 30);
        trangthai.setFont(new Font("Arial", Font.BOLD, 16));

        // JLabel NgayNhapTau = new JLabel("Ngày nhập tàu: " +
        // list.get(i).getNgaynhap());
        // NgayNhapTau.setPreferredSize(new Dimension(200, 30));
        // NgayNhapTau.setFont(new Font("Arial", Font.BOLD, 16));

        // JButton SuaTau = new JButton("SỬA");
        // SuaTau.setPreferredSize(new Dimension(100, 30));
        // JButton XoaTau = new JButton("XÓA");
        // XoaTau.setPreferredSize(new Dimension(100, 30));

        this.add(MaTau);
        // this.add(SoGhe);
        this.add(trangthai);
        // this.add(NgayNhapTau);
        // this.add(SuaTau);
        // this.add(XoaTau);

    }
}
