package org.metro.controller;

import org.metro.DAO.PhanQuyenDAO.NhomQuyenDAO;
import org.metro.model.PhanQuyenModel.ChiTietPhanQuyenModel;
import org.metro.model.PhanQuyenModel.NhomChucNangModel;
import org.metro.model.PhanQuyenModel.NhomQuyenModel;
import org.metro.service.PhanQuyenService;
import org.metro.util.Helper;
import org.metro.util.SessionManager;
import org.metro.view.Component.ToolBar;
import org.metro.view.Dialog.NhanVienDialog;
import org.metro.view.Dialog.PhanQuyenDialog;
import org.metro.view.MainFrame;
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
    private MainFrame mf;

    public PhanQuyenController(PhanQuyen pq, PhanQuyenDialog pqDialog,MainFrame mf) {
        this.pq = pq;
        this.pqDialog = pqDialog;
        this.mf = mf;
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
                        PhanQuyenDialog pqDialog = new PhanQuyenDialog(namebtn,pq,"Nhập tên nhóm quyền....",mf);
                        pqDialog.setVisible(true);
                    } else {
                        NhomQuyenModel nqm = pq.getSelectedPhanquyen();
                        if(nqm == null) {
                            JOptionPane.showMessageDialog(null,"vui lòng chọn một dòng","thông báo",JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        if("update".equals(namebtn)) {
                            PhanQuyenDialog  phanQuyenDialog = new PhanQuyenDialog(namebtn,pq,nqm.getTennhomquyen(),mf);
                            phanQuyenDialog.setVisible(true);
                        } else if("detail".equals(namebtn)) {
                            PhanQuyenDialog phanQuyenDialog = new PhanQuyenDialog(namebtn,pq,nqm.getTennhomquyen(),mf);
                            phanQuyenDialog.setVisible(true);
                        } else if("delete".equals(namebtn)) {
                            delelenhomquyen();
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
                } else if("chi tiết quyền".equals(namebutton)) {
                    chitietnhomquyen();
                }
            }
        }
    }

    //ham xu ly them nhom quyen
    private void themnhomquyen() {
        NhomQuyenModel nqm = new NhomQuyenModel();
        String tnq = pqDialog.getTextfieldQuyen().getText();
        nqm.setTennhomquyen(tnq);
        if(!Helper.isValidName(tnq)) {
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
                listctpqm.add(new ChiTietPhanQuyenModel(mnq,ncnm.getMachucnang(),"delete"   ));
            }
        }
        if(PhanQuyenService.insertChiTietPhanQuyen(listctpqm)) {
            JOptionPane.showMessageDialog(null,"Thêm quyền thành công","thông báo",JOptionPane.INFORMATION_MESSAGE);
            pq.reloadData();
            SessionManager.reloadQuyen();
            pqDialog.dispose();
        } else {
            JOptionPane.showMessageDialog(null,"Thêm quyền thất bại","thông báo",JOptionPane.INFORMATION_MESSAGE);
        }
    }
    //ham xu ly cap nhat nhom quyen
    private void capnhatnhomquyen() {
        //lay ra cai row duoc click
        NhomQuyenModel nqm = pq.getSelectedPhanquyen();
        int mnq= nqm.getManhomquyen();
        String tennhomquyen = pqDialog.getTextfieldQuyen().getText().toString();
        if(!Helper.isValidName(tennhomquyen) && tennhomquyen.equals("Nhập tên nhóm quyền....")) {
            JOptionPane.showMessageDialog(null,"vui lòng cập nhật tên nhóm quyền","thông báo",JOptionPane.ERROR_MESSAGE);
            return;
        }
        nqm.setTennhomquyen(tennhomquyen);
        if(!PhanQuyenService.updateNhomQuyen(nqm)) {
            JOptionPane.showMessageDialog(null, "cập nhật tên nhóm quyền thất bại", "Thông báo", JOptionPane.ERROR_MESSAGE);
            return;
        }
        List<NhomChucNangModel> listncnm = PhanQuyenService.getNhomChucNang();
        List<ChiTietPhanQuyenModel> listctpqm = new ArrayList<>();
        DefaultTableModel dtm = (DefaultTableModel) pqDialog.getTable().getModel();
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
                listctpqm.add(new ChiTietPhanQuyenModel(mnq,ncnm.getMachucnang(),"delete"));
            }
        }
        if(PhanQuyenService.deleteChiTietPhanQuyen(mnq)) {
            for(ChiTietPhanQuyenModel ctpqm : listctpqm) {
                if(!PhanQuyenService.updateChiTietNhomQuyen(ctpqm)) {
                    JOptionPane.showMessageDialog(null,"cập nhật nhóm quyền thất bại","thông báo",JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            JOptionPane.showMessageDialog(null,"cập nhật nhóm quyền thành công","thông báo",JOptionPane.INFORMATION_MESSAGE);
            pq.reloadData();
            SessionManager.reloadQuyen();
            if (mf != null) {
                mf.getMenuTaskbar().refresh();
            } else {
                System.out.println("loi r");
            }
            pqDialog.dispose();
        }
    }

    //ham xu li xem chi tiet nhom quyen
    private void chitietnhomquyen() {
        NhomQuyenModel nqm = pq.getSelectedPhanquyen();
        if (nqm == null) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một nhóm quyền", "Thông báo", JOptionPane.ERROR_MESSAGE);
            return;
        }
        pqDialog.getTextfieldQuyen().setText(nqm.getTennhomquyen());
        pqDialog.getTextfieldQuyen().setEditable(false);
        List<NhomChucNangModel> listncnm = PhanQuyenService.getNhomChucNang();
        List<ChiTietPhanQuyenModel> listctpqm = PhanQuyenService.getChiTietPhanQuyen(nqm.getManhomquyen());
        DefaultTableModel dtm = (DefaultTableModel) pqDialog.getTable().getModel();
        dtm.setRowCount(0);
        for (NhomChucNangModel ncnm : listncnm) {
            String tenchucnang = ncnm.getTenchucnang();
            boolean them = false, sua = false, chitiet = false, xoa = false;

            // Kiểm tra quyền tương ứng với chức năng này
            for (ChiTietPhanQuyenModel ctpqm : listctpqm) {
                if (ctpqm.getMachucnang() == ncnm.getMachucnang()) {
                    switch (ctpqm.getTenquyen().toLowerCase()) {
                        case "create":
                            them = true;
                            break;
                        case "update":
                            sua = true;
                            break;
                        case "detail":
                            chitiet = true;
                            break;
                        case "xoa":
                            xoa = true;
                            break;
                    }
                }
            }
            dtm.addRow(new Object[]{tenchucnang, them, sua, chitiet, xoa});
        }
        pqDialog.getTable().setEnabled(false);
    }
    public void delelenhomquyen() {
        NhomQuyenModel nqm = pq.getSelectedPhanquyen();
        if (nqm == null) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một nhóm quyền", "Thông báo", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa nhóm quyền này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;

        if (PhanQuyenService.deleteNhomquyen(nqm.getManhomquyen())) {
            JOptionPane.showMessageDialog(null, "Xóa nhóm quyền thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            pq.reloadData();
        } else {
            JOptionPane.showMessageDialog(null, "Xóa nhóm quyền thất bại", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
    }

}

