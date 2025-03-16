package org.metro.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.metro.view.Component.ToolBar;
import org.metro.view.Dialog.NhanVienDialog;
import org.metro.DAO.NhanVienDAO;
import org.metro.model.NhanVienModel;
import org.metro.service.NhanVienService;
import org.metro.view.MainFrame;
import org.metro.view.Panel.NhanVien;

public class NhanVienController implements ActionListener,ItemListener,KeyListener{
    private NhanVien nv;
    private NhanVienDialog nvdl;
    public NhanVienController(NhanVienDialog nvdl) {
        this.nvdl = nvdl;
    }
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
        JComponent c = (JComponent) e.getSource(); // dung combonent de su dung cho nhieu kieu nhu button,label,..
       if(nv != null) {
        for(String namebtn : nv.getMainfunc().getBtn().keySet()) {
            ToolBar tb = nv.getMainfunc().getBtn().get(namebtn);
            if(c.equals(tb)) {
                if(namebtn == null || namebtn.isEmpty()) {
                    System.err.println("errors");
                    return;
                }
                new NhanVienDialog(nv.getMf(),namebtn); // MainFrame la cha
                break;
            }
        }
       }

        //xu li khi an nut them
       if(nvdl != null) {
            String namebtn = e.getActionCommand();
            if(c instanceof JButton) {
                if(namebtn.equals("THEM")) {
                    String ten = String.valueOf(nvdl.getTennvTextfield().getText().trim());
                    String sdt = String.valueOf(nvdl.getSodienthoaiTextfield().getText().trim());
                    String gt = String.valueOf(nvdl.getGioitinhCombobox().getSelectedItem());
                    String cv = String.valueOf(nvdl.getChucvuCombobox().getSelectedItem());

                    if(ten.isEmpty()) {
                        JOptionPane.showMessageDialog(null,"ten khong duoc de trong","thong bao",JOptionPane.INFORMATION_MESSAGE);
                        nvdl.getTennvTextfield().requestFocus();
                        return;
                    }
                    if(sdt.isEmpty()) {
                        JOptionPane.showMessageDialog(null,"sdt khong duoc de trong","thong bao",JOptionPane.INFORMATION_MESSAGE);
                        nvdl.getTennvTextfield().requestFocus();
                        return;
                    }

                    if(nvdl.getGioitinhCombobox().getSelectedItem() == "--" || nvdl.getChucvuCombobox().getSelectedItem() == "--") {
                        JOptionPane.showMessageDialog(null, "vui long chon 1 chi muc!","thong bao",JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    NhanVienModel nvm = new NhanVienModel(ten, sdt, gt, cv); // ma nv la auto increment nen dung constructor kh co manv
                    if(NhanVienService.insert(nvm)) {
                        JOptionPane.showMessageDialog(null, "THEM NHAN VIEN THANH CONG","THONG BAO",JOptionPane.INFORMATION_MESSAGE);
                        nvdl.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "THEM NHAN VIEN THAT BAI","THONG BAO",JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } else if(namebtn.equals("CANCEl")) nvdl.dispose();
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
        nv.loadTimeSearch();
    }

}
