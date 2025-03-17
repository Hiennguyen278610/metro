package org.metro.view.Panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JPanel;

import org.metro.DAO.TauDAO;
import org.metro.model.TauModel;

public class DanhSachTau extends JPanel {
    List<TauModel> list = new TauDAO().selectAll();

    public DanhSachTau() {
        init();
    }

    private void init() {
        this.setLayout(new FlowLayout(0, 32, 40));
        this.setPreferredSize(new Dimension(900, ((list.size() + 5) / 3) * 300));
        this.setBackground(Color.white);

        ThongTinTau[] thongTinTau = new ThongTinTau[list.size()];

        for (int i = 0; i < list.size(); i++) {
            thongTinTau[i] = new ThongTinTau(list.get(i));
            this.add(thongTinTau[i]);
        }
    }

}
