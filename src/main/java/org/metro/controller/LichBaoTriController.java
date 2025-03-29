package org.metro.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDateTime;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import org.metro.model.LichBaoTriModel;
import org.metro.view.Component.MainFunction;
import org.metro.view.Dialog.LichBaoTriDialog;
import org.metro.view.Panel.LichBaoTri;

public class LichBaoTriController implements MouseListener, ItemListener, KeyListener {
    private LichBaoTri lbt;
    private LichBaoTriDialog lbtDialog;
    private JFrame parent;

    public LichBaoTriController(LichBaoTri lbt, LichBaoTriDialog lbtDialog) {
        this.lbt = lbt;
        this.lbtDialog = lbtDialog;
        parent = (JFrame) SwingUtilities.getWindowAncestor(lbt);
    }

    public LichBaoTriController(LichBaoTri lbt) {
        this.lbt = lbt;
        this.lbtDialog = null;
        parent = (JFrame) SwingUtilities.getWindowAncestor(lbt);
    }

    public LichBaoTriController(LichBaoTriDialog lbtDialog) {
        this.lbtDialog = lbtDialog;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == lbt.getMainFunction().btn.get("create")) {
            lbtDialog = new LichBaoTriDialog(parent, "Thêm lịch bảo trì", "create", lbt);
        } else if (e.getSource() == lbt.getMainFunction().btn.get("update")) {
            int index = lbt.getMaintenanceTable().getSelectedRow();
            if (index != -1) {
                lbtDialog = new LichBaoTriDialog(parent, "Cập nhật lịch bảo trì", "update",
                        lbt.getLbtService().getDsBaoTri().get(index), lbt);
            } else {
                JOptionPane.showMessageDialog(parent, "Vui lòng chọn dòng cần cập nhật", "Thông báo",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } else if (e.getSource() == lbt.getMainFunction().btn.get("detail")) {
            int index = lbt.getMaintenanceTable().getSelectedRow();
            if (index != -1) {
                lbtDialog = new LichBaoTriDialog(parent, "Chi tiết lịch bảo trì", "detail",
                        lbt.getLbtService().getDsBaoTri().get(index), lbt);
            } else {
                JOptionPane.showMessageDialog(parent, "Vui lòng chọn dòng xem chi tiết", "Thông báo",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } else if (e.getSource() == lbt.getMainFunction().btn.get("delete")) {
            int index = lbt.getMaintenanceTable().getSelectedRow();
            if (index != -1) {
                int input = JOptionPane.showConfirmDialog(parent, "Bạn có chắc muốn xóa?", "Xác nhận xóa",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                if (input == JOptionPane.OK_OPTION) {
                    lbt.getLbtService().delete(lbt.getLbtService().getDsBaoTri().get(index).getMabaotri());
                    lbt.getLbtService().getDsBaoTri().remove(index);
                    lbt.loadData(lbt.getLbtService().getDsBaoTri());
                    JOptionPane.showMessageDialog(parent, "Xóa thành công!", "Thông báo",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(parent, "Vui lòng chọn dòng muốn xóa", "Thông báo",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } else if (lbtDialog != null && e.getSource() == lbtDialog.getBtnExit()) {
            System.out.println("hihiiih");
            lbtDialog.dispose();
        } else if (lbtDialog != null && e.getSource() == lbtDialog.getBtnUpdate()) {
            LichBaoTriModel updatelbt = lbtDialog.getLichBaoTriModel();
            if (updatelbt != null) {
                if (lbt.getLbtService().update(lbtDialog.getLichBaoTriModel())) {
                    JOptionPane.showMessageDialog(lbt, "Cập nhật thành công", "THÔNG BÁO",
                            JOptionPane.INFORMATION_MESSAGE);
                    lbtDialog.dispose();
                    lbt.loadData(lbt.getLbtService().getDsBaoTri());
                }
            }
        } else if (lbtDialog != null && e.getSource() == lbtDialog.getBtnAdd() && lbtDialog.validation()) {
            int mabaotri = lbt.getLbtService().getNextID();
            int matau = Integer.parseInt(lbtDialog.getMatauField());
            LocalDateTime now = LocalDateTime.now();
            String trangthaibaotri = lbtDialog.getStatusField();
            LichBaoTriModel newlbt = new LichBaoTriModel(mabaotri, matau, now, trangthaibaotri);
            if (lbt.getLbtService().insert(newlbt)) {
                JOptionPane.showMessageDialog(lbt, "Thêm thành công", "THÔNG BÁO",
                        JOptionPane.INFORMATION_MESSAGE);
                lbtDialog.dispose();
                lbt.loadData(lbt.getLbtService().getDsBaoTri());
            }

        } else if (e.getSource() == lbt.getSearch().getBtnReset()) {
            lbt.getSearch().getCbxChoose().setSelectedIndex(0);
            lbt.getSearch().getTxtSearchForm().setText("");
            lbt.loadData(lbt.getLbtService().getDsBaoTri());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        String text = lbt.getSearch().getTxtSearchForm().getText();
        String type = (String) lbt.getSearch().getCbxChoose().getSelectedItem();

        lbt.loadData(lbt.getLbtService().search(text, type));
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        String type = (String) lbt.getSearch().getCbxChoose().getSelectedItem();
        String text = lbt.getSearch().getTxtSearchForm().getText();

        lbt.loadData(lbt.getLbtService().search(text, type));
    }

}
