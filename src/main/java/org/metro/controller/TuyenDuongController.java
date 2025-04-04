package org.metro.controller;

import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComboBox;

import org.metro.view.Panel.TuyenDuong;

public class TuyenDuongController implements ItemListener, MouseListener {
    private TuyenDuong tuyenDuong;

    public TuyenDuongController(TuyenDuong tuyenDuong) {
        this.tuyenDuong = tuyenDuong;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) { // ktra khi combobox dc tich chon
            JComboBox<String> cbb = (JComboBox<String>) e.getSource();
            String str = (String) cbb.getSelectedItem();
            String selectedCheckbox = (String) tuyenDuong.getSearchfunc().getCbxChoose().getSelectedItem();
            if (str.equals(selectedCheckbox)) {
                System.out.println("Bạn đã chọn" + tuyenDuong.getSearchfunc().getCbxChoose().getSelectedItem());
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == tuyenDuong.getXemDoThi()) {
            tuyenDuong.showPanel("DoThiPanel");
        } else if (e.getSource() == tuyenDuong.getBack()) {
            tuyenDuong.showPanel("MainPanel");
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == tuyenDuong.getXemDoThi()) {
            tuyenDuong.getXemDoThi().setBackground(Color.decode("#f0f0f0"));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == tuyenDuong.getXemDoThi()) {
            tuyenDuong.getXemDoThi().setBackground(Color.white);
        }
    }

}
