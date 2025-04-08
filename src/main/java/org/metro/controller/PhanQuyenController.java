package org.metro.controller;

import org.metro.model.PhanQuyenModel.NhomQuyenModel;
import org.metro.view.Component.ToolBar;
import org.metro.view.Dialog.NhanVienDialog;
import org.metro.view.Dialog.PhanQuyenDialog;
import org.metro.view.Panel.PhanQuyenPackage.PhanQuyen;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PhanQuyenController implements ActionListener {
    private PhanQuyen pq;

    public PhanQuyenController(PhanQuyen pq) {
        this.pq = pq;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton c = (JButton) e.getSource();
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
    }
}

