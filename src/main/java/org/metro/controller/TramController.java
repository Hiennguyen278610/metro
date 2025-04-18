package org.metro.controller;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

import org.metro.model.TramModel;
import org.metro.view.Dialog.TramDialog;
import org.metro.view.Panel.Tram;

public class TramController implements MouseListener, ItemListener, KeyListener {
    private Tram tram;
    private TramDialog tramDialog;
    private JFrame parent;

    public TramController(Tram tram, TramDialog tramDialog) {
        this.tram = tram;
        this.tramDialog = tramDialog;
        parent = (JFrame) SwingUtilities.getWindowAncestor(tram);
    }

    public TramController(Tram tram) {
        this.tram = tram;
        this.tramDialog = null;
        parent = (JFrame) SwingUtilities.getWindowAncestor(tram);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        String text = tram.getSearch().getTxtSearchForm().getText();
        String type = (String) tram.getSearch().getCbxChoose().getSelectedItem();

        tram.loadData(tram.getTramService().search(text, type));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == tram.getMainFunction().btn.get("create")) {
            tramDialog = new TramDialog(parent, "Thêm trạm", "create", tram);
        } else if (e.getSource() == tram.getMainFunction().btn.get("update")) {
            int index = tram.getTramTable().getSelectedRow();
            if (index != -1) {
                tramDialog = new TramDialog(parent, "Cập nhật trạm", "update", tram,
                        tram.getTramService().getDsTram().get(index));
            } else {
                JOptionPane.showMessageDialog(parent, "Vui lòng chọn dòng cần cập nhật", "Thông báo",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } else if (e.getSource() == tram.getMainFunction().btn.get("delete")) {
            int index = tram.getTramTable().getSelectedRow();
            if (index != -1) {
                int input = JOptionPane.showConfirmDialog(parent, "Bạn có chắc muốn xóa?", "Xác nhận xóa",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                if (input == JOptionPane.OK_OPTION) {
                    tram.getTramService().delete(tram.getTramService().getDsTram().get(index).getMatram());
                    tram.getTramService().getDsTram().remove(index);
                    tram.loadData(tram.getTramService().getDsTram());
                    JOptionPane.showMessageDialog(parent, "Xóa thành công!", "Thông báo",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(parent, "Vui lòng chọn dòng muốn xóa", "Thông báo",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } else if (tramDialog != null && e.getSource() == tramDialog.getBtnExit()) {
            tramDialog.dispose();
        } else if (tramDialog != null && e.getSource() == tramDialog.getBtnAdd() && tramDialog.checkEmpty()
                && tramDialog.validInformation()) {
            int matram = tram.getTramService().getNextID();
            String tentram = tramDialog.getTenField();
            String diachi = tramDialog.getAddressField();
            int x = Integer.parseInt(tramDialog.getxField());
            int y = Integer.parseInt(tramDialog.getyField());
            double chiphi = Double.parseDouble(tramDialog.getChiphi());
            TramModel tramModel = new TramModel(matram, tentram, diachi, x, y, chiphi);
            if (tram.getTramService().insert(tramModel)) {
                JOptionPane.showMessageDialog(tram, "Thêm thành công", "THÔNG BÁO",
                        JOptionPane.INFORMATION_MESSAGE);
                tramDialog.dispose();
                tram.loadData(tram.getTramService().getDsTram());
            }
        } else if (tramDialog != null && e.getSource() == tramDialog.getBtnUpdate()) {
            TramModel updateTram = tramDialog.getTramModel();
            if (updateTram != null) {
                if (tram.getTramService().update(updateTram)) {
                    JOptionPane.showMessageDialog(tram, "Cập nhật thành công", "THÔNG BÁO",
                            JOptionPane.INFORMATION_MESSAGE);
                    tramDialog.dispose();
                    tram.loadData(tram.getTramService().getDsTram());
                } else {
                    JOptionPane.showMessageDialog(tram, "Cập nhật không thành công", "THÔNG BÁO",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } else if (e.getSource() == tram.getSearch().getBtnReset()) {
            tram.getSearch().getCbxChoose().setSelectedIndex(0);
            tram.getSearch().getTxtSearchForm().setText("");
            tram.loadData(tram.getTramService().getDsTram());

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
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        String text = tram.getSearch().getTxtSearchForm().getText();
        String type = (String) tram.getSearch().getCbxChoose().getSelectedItem();

        tram.loadData(tram.getTramService().search(text, type));
    }

}
