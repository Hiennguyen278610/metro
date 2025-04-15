package org.metro.controller;

import org.metro.model.PhanQuyenModel.NhomQuyenModel;
import org.metro.model.TaiKhoanModel;
import org.metro.service.NhanVienService;
import org.metro.service.TaiKhoanService;
import org.metro.view.Component.ToolBar;
import org.metro.view.Dialog.NhanVienDialog;
import org.metro.view.Dialog.TaiKhoanDialog;
import org.metro.view.Panel.TaiKhoan;

import javax.swing.*;
import java.awt.event.*;

public class TaiKhoanController implements ActionListener, ItemListener, KeyListener {
    private TaiKhoan tk;
    private TaiKhoanDialog tkdialog;
    public  TaiKhoanController(TaiKhoan tk,TaiKhoanDialog tkdialog) {
        this.tk = tk;
        this.tkdialog = tkdialog;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton c = (JButton) e.getSource();
        if(tk != null) {
            for(String namebtn : tk.getMainFunction().getBtn().keySet()) {
                ToolBar tb =tk.getMainFunction().getBtn().get(namebtn);
                if(c.equals(tb)) {
                    if(namebtn == null || namebtn.trim().isEmpty()) return;
                    if("create".equals(namebtn)) {
                        TaiKhoanDialog tkdl = new TaiKhoanDialog("create",tk);
                        tkdl.setVisible(true);
                    } else {
                        TaiKhoanModel tkm = tk.getSelectedTaiKhoan();
                        if (tkm == null) {
                            JOptionPane.showMessageDialog(null,"Vui lòng chọn một nhân viên","thông báo ", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        if("update".equals(namebtn)) {
                            TaiKhoanDialog tkdl =  new TaiKhoanDialog("update",tk);
                            tkdl.setVisible(true);
                        } else if("detail".equals(namebtn)) {
                            TaiKhoanDialog tkdl = new TaiKhoanDialog("detail",tk);
                            tkdl.setVisible(true);
                        }else if("delete".equals(namebtn)) {
                            if(TaiKhoanService.delete(tkm.getManv())) {
                                JOptionPane.showMessageDialog(null, "XÓA TÀI KHOẢN THÀNH CÔNG", "Thông báo",
                                        JOptionPane.INFORMATION_MESSAGE);
                                tk.reloadData();
                            } else {
                                JOptionPane.showMessageDialog(null, "XÓA TÀI KHOẢN THẤT BẠI", "Thông báo", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                }
            }
        }

            //nut them
            if(tkdialog != null) {
                String btn = e.getActionCommand();
                if(c instanceof JButton) {
                    if("Thêm".equals(btn)) {
                        String manv = tkdialog.getManvTextfield().getTxtInput().getText().trim();
                        String mk = tkdialog.getMatkhauTextfield().getTxtInput().getText().trim();
                        NhomQuyenModel nqm = (NhomQuyenModel) tkdialog.getNhomquyenTextfield().getComboboxnhomquyen().getSelectedItem();
                        int  tt = tkdialog.getTrangThaiTextfield().getComboboxtrangthai().getSelectedItem().toString().equalsIgnoreCase("Hoạt động") ? 1 : 0;
                        if(manv.isEmpty() || mk.isEmpty() || nqm == null) {
                            JOptionPane.showMessageDialog(null,"Vui lòng nhập đủ thông tin","thông báo",JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        TaiKhoanModel tkm = new TaiKhoanModel(Integer.parseInt(manv),mk,tt,nqm);
                        if(TaiKhoanService.insert(tkm)) {
                            JOptionPane.showMessageDialog(null,"thêm tài khoản thành công","thông báo",JOptionPane.INFORMATION_MESSAGE);
                            tk.reloadData();
                            tkdialog.dispose();
                        } else {
                            JOptionPane.showMessageDialog(null,"Thêm tài khoản thất bại","thông báo",JOptionPane.ERROR_MESSAGE);
                        }
                    } else if("Cập nhật".equals(btn)) {
                        TaiKhoanModel tkm = tk.getSelectedTaiKhoan();
                        if(tkm != null) {
                            tkm.setManv(Integer.parseInt(tkdialog.getManvTextfield().getTxtInput().getText().trim()));
                            tkm.setMatkhau(tkdialog.getMatkhauTextfield().getTxtInput().getText().trim());
                            tkm.setNqm((NhomQuyenModel) tkdialog.getNhomquyenTextfield().getComboboxnhomquyen().getSelectedItem());
                            tkm.setTrangthai((tkdialog.getTrangThaiTextfield().getComboboxtrangthai().getSelectedItem().toString().equalsIgnoreCase("Hoạt Động") ? 1 : 0));
                            if(TaiKhoanService.update(tkm)) {
                                JOptionPane.showMessageDialog(null,"Cập nhật tài khoản thành công","thông báo",JOptionPane.INFORMATION_MESSAGE);
                                tk.reloadData();
                                tkdialog.dispose();
                            } else JOptionPane.showMessageDialog(null,"Cập nhật tài khoản thất bại","thông báo",JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }

    }
    @Override
    public void itemStateChanged(ItemEvent e) {

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
}
