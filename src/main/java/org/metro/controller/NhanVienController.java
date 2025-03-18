package org.metro.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;
import org.metro.view.Component.ToolBar;
import org.metro.view.Dialog.NhanVienDialog;
import org.metro.model.NhanVienModel;
import org.metro.service.NhanVienService;
import org.metro.view.Panel.NhanVien;

public class NhanVienController implements ActionListener, ItemListener, KeyListener {
    private NhanVien nv;
    private NhanVienDialog nvdl;
    private NhanVienModel nvm;

    public NhanVienController(NhanVienDialog nvdl) {
        this.nvdl = nvdl;
    }

    public NhanVienController(NhanVien nv) {
        this.nv = nv;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) { // ktra khi combo box dc tich chon
            JComboBox<String> cbb = (JComboBox<String>) e.getSource();
            String str = (String) cbb.getSelectedItem();
            String selectedCheckbox = (String) nv.getSearchfunc().getCbxChoose().getSelectedItem();
            if (str.equals(selectedCheckbox)) {
                System.out.println("ban da chon " + nv.getSearchfunc().getCbxChoose().getSelectedItem());
            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JComponent c = (JComponent) e.getSource(); // dung combonent de su dung cho nhieu kieu nhu button,label,..
        if (nv != null) {
            for (String namebtn : nv.getMainfunc().getBtn().keySet()) {
                ToolBar tb = nv.getMainfunc().getBtn().get(namebtn);
                if (c.equals(tb)) {
                    if (namebtn == null || namebtn.trim().isEmpty()) {
                        System.err.println("errors");
                        return;
                    }

                    if ("create".equals(namebtn)) {
                        new NhanVienDialog(nv.getMf(), namebtn, nv, null).setVisible(true);
                        ;
                    } else {
                        nvm = nv.getSelectedNhanvien();
                        if (nvm == null) {
                            JOptionPane.showMessageDialog(nv, "Hay chon 1 nhan vien", "Thong bao",
                                    JOptionPane.WARNING_MESSAGE);
                            return;
                        }
                        if ("detail".equals(namebtn) || "update".equals(namebtn)) {
                            new NhanVienDialog(nv.getMf(), namebtn, nv, nvm).setVisible(true);
                        } else if ("delete".equals(namebtn)) {
                            int confirm = JOptionPane.showConfirmDialog(nv, "Ban co chac muon xoa ? ", "Xac nhan",
                                    JOptionPane.YES_NO_OPTION);
                            if (confirm == JOptionPane.YES_OPTION) {
                                deleteNhanvien();
                                if (nv.getNhanVienTabel() != null)
                                    nv.getNhanVienTabel().updateUI();
                            }
                        } else {
                            System.out.println("khong co nut nao duoc click");
                            return;
                        }
                    }
                }
            }
        }

        // xu li khi an nut them de xac nhan them 1 nhan vien
        if (nvdl != null) {
            String namebtn = e.getActionCommand();
            if (c instanceof JButton) {
                if (namebtn.equals("THEM")) {
                    String ten = String.valueOf(nvdl.getTennvTextfield().getText().trim());
                    String sdt = String.valueOf(nvdl.getSodienthoaiTextfield().getText().trim());
                    String gt = String.valueOf(nvdl.getGioitinhCombobox().getSelectedItem());
                    String cv = String.valueOf(nvdl.getChucvuCombobox().getSelectedItem());

                    if (ten.isEmpty()) {
                        JOptionPane.showMessageDialog(nvdl, "ten khong duoc de trong", "thong bao",
                                JOptionPane.INFORMATION_MESSAGE);
                        nvdl.getTennvTextfield().requestFocus();
                        return;
                    }
                    if (sdt.isEmpty()) {
                        JOptionPane.showMessageDialog(nvdl, "sdt khong duoc de trong", "thong bao",
                                JOptionPane.INFORMATION_MESSAGE);
                        nvdl.getTennvTextfield().requestFocus();
                        return;
                    }

                    if (nvdl.getGioitinhCombobox().getSelectedItem() == "--"
                            || nvdl.getChucvuCombobox().getSelectedItem() == "--") {
                        JOptionPane.showMessageDialog(nvdl, "vui long chon 1 chi muc!", "thong bao",
                                JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    NhanVienModel nvm = new NhanVienModel(ten, sdt, gt, cv); // ma nv la auto increment nen dung
                                                                             // constructor kh co manv
                    if (NhanVienService.insert(nvm)) {
                        JOptionPane.showMessageDialog(nvdl, "THEM NHAN VIEN THANH CONG", "THONG BAO",
                                JOptionPane.INFORMATION_MESSAGE);
                        nvdl.dispose();
                    } else {
                        JOptionPane.showMessageDialog(nvdl, "THEM NHAN VIEN THAT BAI", "THONG BAO",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } else if (namebtn.equals("CANCEl"))
                    nvdl.dispose();
            }
        }
    }

    // ham xoa nhan vien
    public void deleteNhanvien() {
        nvm = nv.getSelectedNhanvien();
        System.out.println("phuong thuc delete");
        if (nvm == null) {
            System.out.println("khong tim thay nhan vien duoc chon");
            return;
        }
        System.out.println("nhan vien duoc chon de xoa la : " + nvm.getManv());
        if (NhanVienService.delete(nvm.getManv())) {
            JOptionPane.showMessageDialog(null, "Xoa nhan vien thanh cong", "Thong bao",
                    JOptionPane.INFORMATION_MESSAGE);
        } else
            JOptionPane.showMessageDialog(null, "Xoa nhan vien that bai", "Thong bao", JOptionPane.ERROR_MESSAGE);
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
