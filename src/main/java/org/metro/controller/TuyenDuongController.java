package org.metro.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import org.metro.view.Panel.TuyenDuong;

public class TuyenDuongController implements ItemListener, ActionListener {
    private TuyenDuong tuyenDuong;

    public TuyenDuongController(TuyenDuong tuyenDuong) {
        this.tuyenDuong = tuyenDuong;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'itemStateChanged'");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

}
