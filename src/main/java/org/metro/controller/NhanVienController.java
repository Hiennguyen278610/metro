package org.metro.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComboBox;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.metro.view.Panel.NhanVien;

public class NhanVienController implements ActionListener,ItemListener,KeyListener{
    private NhanVien nv;
    public NhanVienController(NhanVien nv) {
        this.nv = nv;
    }
    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getStateChange() == ItemEvent.SELECTED) { // ktra khi combo box dc tich chon
            JComboBox<String> cbb = (JComboBox<String>) e.getSource();
            String str = (String) cbb.getSelectedItem();
            String selectedCheckbox = (String) nv.getSearchfunc().getCbxChoose().getSelectedItem();
            if(str.equals(selectedCheckbox)) {
                    System.out.println("ban da chon " + nv.getSearchfunc().getCbxChoose().getSelectedItem());            
            }   
        }
       
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       
    }
    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
    }
    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyPressed'");
    }
    @Override
    public void keyReleased(KeyEvent e) {
        nv.loadTimeSearch();
    }


}
