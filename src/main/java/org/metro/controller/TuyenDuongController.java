package org.metro.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import org.metro.model.TuyenDuongModel;
import org.metro.view.Component.ToolBar;
import org.metro.view.Dialog.TuyenDuongDialog;
import org.metro.view.Panel.TuyenDuong;

public class TuyenDuongController implements ItemListener, MouseListener, ActionListener {
    private TuyenDuong tuyenDuong;

    public TuyenDuongController(TuyenDuong tuyenDuong) {
        this.tuyenDuong = tuyenDuong;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) { // ktra khi combobox dc tich chon
            @SuppressWarnings("unchecked")
            JComboBox<String> cbb = (JComboBox<String>) e.getSource();
            String str = (String) cbb.getSelectedItem();
            String selectedCheckbox = (String) tuyenDuong.getSearchfunc().getCbxChoose().getSelectedItem();
            if (str.equals(selectedCheckbox)) {
                System.out.println("Bạn đã chọn" + tuyenDuong.getSearchfunc().getCbxChoose().getSelectedItem());
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == tuyenDuong.getXemDoThi()) {
            tuyenDuong.showPanel("DoThiPanel");
        } else if (e.getSource() == tuyenDuong.getBack()) {
            tuyenDuong.showPanel("MainPanel");
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == tuyenDuong.getXemDoThi()) {
            tuyenDuong.getXemDoThi().setBackground(Color.decode("#f0f0f0"));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == tuyenDuong.getXemDoThi()) {
            tuyenDuong.getXemDoThi().setBackground(Color.white);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton c = (JButton) e.getSource();
        if (tuyenDuong != null) {
            for (String namebtn : tuyenDuong.getMainfunc().getBtn().keySet()) {
                ToolBar tb = tuyenDuong.getMainfunc().getBtn().get(namebtn);
                if (c.equals(tb)) {
                    System.out.println("Bạn đã chọn " + namebtn);
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(tuyenDuong);
                    if (namebtn == "create") {
                        TuyenDuongDialog hihi = new TuyenDuongDialog(frame, TuyenDuongDialog.Mode.ADD, null);
                        hihi.setVisible(true);
                        if (hihi.isSaved()) {
                            // Cập nhật lại dữ liệu trong bảng
                            tuyenDuong.loadData();
                        }
                    } else if (namebtn == "update") {
                        TuyenDuongModel route = tuyenDuong.getSelectedTuyenDuong();
                        if (route == null) {
                            JOptionPane.showMessageDialog(tuyenDuong, "Vui lòng chọn một tuyến đường", "Thông báo",
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        TuyenDuongDialog hihi = new TuyenDuongDialog(frame, TuyenDuongDialog.Mode.EDIT, route);
                        hihi.setVisible(true);
                        if (hihi.isSaved()) {
                            // Cập nhật lại dữ liệu trong bảng
                            tuyenDuong.loadData();
                        }
                    } else if (namebtn == "detail") {
                        TuyenDuongModel route = tuyenDuong.getSelectedTuyenDuong();
                        if (route == null) {
                            JOptionPane.showMessageDialog(tuyenDuong, "Vui lòng chọn một tuyến đường", "Thông báo",
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        new TuyenDuongDialog(frame, TuyenDuongDialog.Mode.VIEW, route).setVisible(true);
                    } else if (namebtn == "delete") {
                        TuyenDuongModel route = tuyenDuong.getSelectedTuyenDuong();
                        if (route == null) {
                            JOptionPane.showMessageDialog(tuyenDuong, "Vui lòng chọn một tuyến đường", "Thông báo",
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        int confirm = JOptionPane.showConfirmDialog(tuyenDuong,
                                "Bạn có chắc chắn muốn xóa tuyến đường này không?", "Xác nhận",
                                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                        if (confirm == JOptionPane.YES_OPTION) {
                            // Xóa tuyến đường
                            tuyenDuong.deleteTuyenDuong(route.getMatuyen());
                            // Cập nhật lại dữ liệu trong bảng
                            tuyenDuong.loadData();
                        }

                    }
                }
            }
        }
    }

}
