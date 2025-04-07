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
    private JFrame parent;

    public NhanVienController(NhanVien nv,NhanVienDialog nvdl) {
        this.nvdl = nvdl;
        this.nv = nv;
    }

    //combobox tim kiem theo id,ten,..
    @Override
    public void itemStateChanged(ItemEvent e) {
        String key = nv.getSearchfunc().getCbxChoose().getSelectedItem().toString();
        String word = nv.getSearchfunc().getTxtSearchForm().getText().trim();
        nv.reloadList(NhanVienService.searchByKeyWord(key,word));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       JButton c = (JButton) e.getSource();
       if(nv != null) {
           for(String namebtn : nv.getMainfunc().getBtn().keySet()) {
               ToolBar tb =nv.getMainfunc().getBtn().get(namebtn);
               if(c.equals(tb)) {
                   if(namebtn == null || namebtn.trim().isEmpty()) return;

                   if("create".equals(namebtn)) {
                       NhanVienDialog nvDialog = new NhanVienDialog(nv.getMf(),namebtn,nv,null);
                       nvDialog.setVisible(true);
                   } else {
                       nvm = nv.getSelectedNhanvien();
                       if (nvm == null) {
                           JOptionPane.showMessageDialog(nvdl,"Vui lòng chọn một nhân viên","thông báo ", JOptionPane.ERROR_MESSAGE);
                           return;
                       }
                       if("update".equals(namebtn) || "detail".equals(namebtn)) {
                          NhanVienDialog nvDialog =  new NhanVienDialog(null,namebtn,nv,nvm);
                          nvDialog.setVisible(true);
                       } else if("delete".equals(namebtn)) {
                           deleteNhanVien();
                       }
                   }
               }
           }
       }
       if(nvdl != null) {
           String namebtn = e.getActionCommand();
           if(c instanceof JButton) {
               if("Thêm".equals(namebtn)) {
                   String ten = String.valueOf(nvdl.getTennvTextfield().getTxtInput().getText().trim());
                   String sdt = String.valueOf(nvdl.getSodienthoaiTextfield().getTxtInput().getText().trim());
                   String gt = String.valueOf(nvdl.getGioitinhTextfield().getTxtInput().getText().trim());
                   String cv = String.valueOf(nvdl.getChucvuTextfield().getTxtInput().getText().trim());

                   if(ten.isEmpty() || sdt.isEmpty() || gt.isEmpty() || cv.isEmpty()) {
                       JOptionPane.showMessageDialog(nvdl,"Vui lòng nhập đầy đủ thông tin","thông báo", JOptionPane.ERROR_MESSAGE);
                       return;
                   }
                   NhanVienModel newnv = new NhanVienModel(ten,sdt,gt,cv);
                   if(NhanVienService.insert(newnv)) {
                       JOptionPane.showMessageDialog(nvdl,"Thêm thông tin thành công","Thông báo", JOptionPane.INFORMATION_MESSAGE);
                       nvdl.dispose();
                   } else {
                       JOptionPane.showMessageDialog(nvdl,"Thêm  thất bại ","Thông báo", JOptionPane.ERROR_MESSAGE);
                       nvdl.requestFocus();
                       return;
                   }
               } else if("Cập nhật".equals(namebtn)) {
                   System.out.println("da nhan nut cap nhat");
                   if(nv == null) {
                       System.out.println("nv = null, đối tượng không tồn tại!");
                       return;
                   }

                   nvm = nv.getSelectedNhanvien();

                   if(nvm == null) {
                       System.out.println("Lỗi: Không có nhân viên nào được chọn!");
                       JOptionPane.showMessageDialog(nvdl, "Vui lòng chọn một nhân viên để cập nhật!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                       return;
                   }
                   if(nv != null) {
                       if(nvm != null) {
                           System.out.println("da chon nhan vien" + nvm.getManv());
                            nvm.setTennv(nvdl.getTennvTextfield().getTxtInput().getText().trim());
                            nvm.setSdtnv(nvdl.getSodienthoaiTextfield().getTxtInput().getText().trim());
                            nvm.setGioitinh(nvdl.getGioitinhTextfield().getTxtInput().getText().trim());
                            nvm.setChucvu(nvdl.getChucvuTextfield().getTxtInput().getText().trim());
                            int ma = nvm.getManv();
                            String ten = nvm.getTennv();
                            String sdt = nvm.getSdtnv();
                            String gt = nvm.getGioitinh();
                            String cv = nvm.getChucvu();
                           if(ten.isEmpty() || sdt.isEmpty() || gt.isEmpty() || cv.isEmpty()) {
                               JOptionPane.showMessageDialog(nvdl,"Vui lòng nhập đầy đủ thông tin","thông báo", JOptionPane.ERROR_MESSAGE);
                               return;
                           }
                           NhanVienModel newnv = new NhanVienModel(ma,ten,sdt,gt,cv);
                           if(NhanVienService.update(newnv)) {
                               JOptionPane.showMessageDialog(nvdl,"Cập nhật thành công","Thông báo", JOptionPane.INFORMATION_MESSAGE);
                               nvdl.dispose();
                           } else {
                               JOptionPane.showMessageDialog(nvdl,"Cập nhật thất bại","Thông báo", JOptionPane.ERROR_MESSAGE);
                               return;
                           }
                       }
                   }
               }  else if("".equals(namebtn)) {
                   c.setEnabled(false);
               } else if("Thoát".equals(namebtn)) nvdl.dispose();
           }
       }
       if(c.equals(nv.getSearchfunc().getBtnReset())) {
           nv.getSearchfunc().getTxtSearchForm().setText("");
           nv.getSearchfunc().getCbxChoose().setSelectedItem("----");
           nv.reloadData();
       }
    }

    // ham xoa nhan vien
    public void deleteNhanVien() {
        if (NhanVienService.delete(nvm.getManv())) {
            JOptionPane.showMessageDialog(null, "xÓA NHÂN VIÊN THÀNH CÔNG", "Thông báo",
                    JOptionPane.INFORMATION_MESSAGE);
        } else
            JOptionPane.showMessageDialog(null, "XÓA NHÂN VIÊN THẤT BẠI", "Thông báo", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        String key = nv.getSearchfunc().getCbxChoose().getSelectedItem().toString();
        String word = nv.getSearchfunc().getTxtSearchForm().getText().trim();
        nv.reloadList(NhanVienService.searchByKeyWord(key,word));
    }

}
