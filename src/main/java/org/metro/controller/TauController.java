package org.metro.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JComboBox;

import org.metro.model.TauModel;
import org.metro.view.Panel.Tau;

public class TauController implements ActionListener, ItemListener, KeyListener {
    private Tau tau;

    public TauController(Tau tau) {
        this.tau = tau;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) { // ktra khi combobox dc tich chon
            JComboBox<String> cbb = (JComboBox<String>) e.getSource();
            String str = (String) cbb.getSelectedItem();
            if (str != null) {
                System.out.println("Bạn đã chọn" + tau.getSortComboBox().getSelectedItem());
                Comparator<TauModel> comparator;
                switch (str) {
                    case "Mã tàu":
                        comparator = Comparator.comparing(TauModel::getMatau);
                        break;
                    case "Ngày nhập tàu":
                        comparator = Comparator.comparing(TauModel::getNgaynhap);
                        break;
                    case "Sức chứa":
                        comparator = Comparator.comparingInt(TauModel::getSoghe);
                        break;
                    case "Trạng thái tàu":
                        comparator = Comparator.comparing(TauModel::getTrangthaitau);
                        break;
                    default:
                        return;
                }
                Collections.sort(tau.getListTau(), comparator);
                tau.updateData(tau.getListTau());
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

}
