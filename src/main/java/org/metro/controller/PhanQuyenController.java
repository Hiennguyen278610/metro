package org.metro.controller;

import org.metro.model.PhanQuyenModel.ChiTietPhanQuyenModel;
import org.metro.model.PhanQuyenModel.NhomChucNangModel;
import org.metro.model.PhanQuyenModel.NhomQuyenModel;
import org.metro.service.PhanQuyenService;
import org.metro.view.Component.ToolBar;
import org.metro.view.Dialog.NhanVienDialog;
import org.metro.view.Dialog.PhanQuyenDialog;
import org.metro.view.Panel.PhanQuyenPackage.PhanQuyen;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PhanQuyenController implements ActionListener {
    private PhanQuyen pq;
    private PhanQuyenDialog pqDialog;

    public PhanQuyenController(PhanQuyen pq, PhanQuyenDialog pqDialog) {
        this.pq = pq;
        this.pqDialog = pqDialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton c = (JButton) e.getSource();
        //4 nut them xoa sua chi tiet
        if (pq != null) {
            for (String namebtn : pq.getMainfunc().getBtn().keySet()) {
                ToolBar tb = pq.getMainfunc().getBtn().get(namebtn);
                if (c.equals(tb)) {
                    if (namebtn == null || namebtn.trim().isEmpty()) return;

                    if ("create".equals(namebtn)) {
                        PhanQuyenDialog pqDialog = new PhanQuyenDialog(namebtn,pq,"Nhập tên nhóm quyền....");
                        pqDialog.setVisible(true);
                    } else {
                        NhomQuyenModel nqm = pq.getSelectedPhanquyen();
                        if(nqm == null) {
                            JOptionPane.showMessageDialog(null,"vui lòng chọn một dòng","thông báo",JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        if("update".equals(namebtn)) {
                            PhanQuyenDialog  phanQuyenDialog = new PhanQuyenDialog(namebtn,pq,nqm.getTennhomquyen());
                            phanQuyenDialog.setVisible(true);
                        } else if("detail".equals(namebtn)) {
                            PhanQuyenDialog phanQuyenDialog = new PhanQuyenDialog(namebtn,pq,nqm.getTennhomquyen());
                            phanQuyenDialog.setVisible(true);
                        }
                    }
                }
            }
        }

        //sau khi vao dialog -> check cac nut them,cap nhat
        if(pqDialog != null) {
            String namebutton = e.getActionCommand();
            if(c instanceof JButton) {
                if("Thêm nhóm quyền".equals(namebutton)) {
                    System.out.println("nut duoc nhan la " + namebutton);
                    themnhomquyen();
                } else if("Cập nhật".equals(namebutton)) {
                    capnhatnhomquyen();
                }
            }
        }
    }

    //ham xu ly them nhom quyen
    private void themnhomquyen() {
        NhomQuyenModel nqm = new NhomQuyenModel();
        String tnq = pqDialog.getTextfieldQuyen().getText();
        System.out.println("quan li duong ray " + tnq);
        nqm.setTennhomquyen(tnq);
        if(tnq == null || tnq.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null,"Vui lòng nhập tên nhóm quyền","thông báo",JOptionPane.ERROR_MESSAGE);
            return;
        }
        int mnq = PhanQuyenService.insertNhomQuyen(nqm);
        System.out.println("mnq: " + mnq);
        if(mnq == 0) {
            JOptionPane.showMessageDialog(null,"thêm nhóm quyền thất bại","thông báo",JOptionPane.ERROR_MESSAGE);
            return;
        }
        nqm.setManhomquyen(mnq);

        //lay du lieu trong table
        DefaultTableModel dtm = (DefaultTableModel) pqDialog.getTable().getModel();
        List<NhomChucNangModel> listncnm = PhanQuyenService.getNhomChucNang();
        List<ChiTietPhanQuyenModel> listctpqm = new ArrayList<>();
        for(int i = 0 ; i < dtm.getRowCount(); i++) {
            String tenchunang = (String)dtm.getValueAt(i, 0);
            System.out.println("ten chuc nang" + tenchunang);
            boolean them = (boolean) dtm.getValueAt(i, 1);
            boolean sua = (boolean) dtm.getValueAt(i, 2);
            boolean chitiet = (boolean) dtm.getValueAt(i, 3);
            boolean xoa = (boolean) dtm.getValueAt(i,4);

            NhomChucNangModel ncnm = listncnm.get(i);
            if(them) {
                System.out.println("co them");
                listctpqm.add(new ChiTietPhanQuyenModel(mnq,ncnm.getMachucnang(),"create"));
            }
            if(sua) {
                System.out.println("co sua");
                listctpqm.add(new ChiTietPhanQuyenModel(mnq,ncnm.getMachucnang(),"update"));
            }
            if(chitiet) {
                System.out.println("co chitiet");
                listctpqm.add(new ChiTietPhanQuyenModel(mnq,ncnm.getMachucnang(),"detail"));
            }
            if(xoa) {
                System.out.println("co xoa");
                listctpqm.add(new ChiTietPhanQuyenModel(mnq,ncnm.getMachucnang(),"xoa"));
            }


        }
        if(PhanQuyenService.insertChiTietPhanQuyen(listctpqm)) {
            JOptionPane.showMessageDialog(null,"Thêm quyền thành công","thông báo",JOptionPane.INFORMATION_MESSAGE);
            pq.reloadData();
            pqDialog.dispose();
        } else {
            JOptionPane.showMessageDialog(null,"Thêm quyền thất bại","thông báo",JOptionPane.INFORMATION_MESSAGE);
        }
    }
    //ham xu ly cap nhat nhom quyen
    private void capnhatnhomquyen() {}
}

