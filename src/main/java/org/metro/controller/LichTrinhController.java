package org.metro.controller;

import org.metro.model.LichTrinhModel;
import org.metro.service.LichTrinhService;
import org.metro.view.Dialog.LichTrinhDialog;
import org.metro.view.Panel.LichTrinh;
import org.metro.view.Component.ToolBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Supplier;

public class LichTrinhController implements ActionListener, ItemListener, KeyListener {
    private LichTrinh lichTrinh;
    private LichTrinhDialog dialog;
    private LichTrinhModel lichTrinhModel;

    public LichTrinhController(LichTrinh lichTrinh) {
        this.lichTrinh = lichTrinh;
        this.dialog = null;
    }

    public LichTrinhController(LichTrinh lichTrinh, LichTrinhDialog dialog) {
        this.lichTrinh = lichTrinh;
        this.dialog = dialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        JComponent c = (JComponent) source;

        if (dialog != null) {
            String buttonText = e.getActionCommand();
            if (c instanceof JButton) {
                if (buttonText.equals("THÊM")) {
                    processLichTrinhAction("insert", "THÊM LỊCH TRÌNH THÀNH CÔNG", "THÊM LỊCH TRÌNH THẤT BẠI");
                } else if (buttonText.equals("CẬP NHẬT")) {
                    processLichTrinhAction("update", "CẬP NHẬT LỊCH TRÌNH THÀNH CÔNG", "CẬP NHẬT LỊCH TRÌNH THẤT BẠI");
                } else if (buttonText.equals("HỦY")) {
                    dialog.dispose();
                }
            }
        }

        if (lichTrinh != null) {
            for (String buttonName : lichTrinh.getMainFunction().getBtn().keySet()) {
                ToolBar tb = lichTrinh.getMainFunction().getBtn().get(buttonName);
                if (c.equals(tb)) {
                    handleToolbarAction(buttonName);
                    return;
                }
            }
        }

        if (source == lichTrinh.getSearch().getBtnReset()) {
            lichTrinh.getSearch().getTxtSearchForm().setText("");
            lichTrinh.getSearch().getCbxChoose().setSelectedIndex(0);
            lichTrinh.loadDataTable();
        }
    }

    private void processLichTrinhAction(String action, String successMessage, String failureMessage) {
        LichTrinhModel lichTrinhModel = dialog.getLichTrinhFromForm();
        if (lichTrinhModel != null) {
            boolean success = false;
            if ("insert".equals(action)) {success = LichTrinhService.insert(lichTrinhModel);}
            else if ("update".equals(action)) {success = LichTrinhService.update(lichTrinhModel);}

            if (success) {
                JOptionPane.showMessageDialog(dialog, successMessage, "THÔNG BÁO", JOptionPane.INFORMATION_MESSAGE);
                dialog.dispose();
                if (lichTrinh != null) lichTrinh.loadDataTable();
            } else {
                JOptionPane.showMessageDialog(dialog, failureMessage, "THÔNG BÁO", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void handleToolbarAction(String actionName) {
        if (actionName == null || actionName.trim().isEmpty()) {
            System.err.println("Error: Invalid action name");
            return;
        }

        if ("create".equals(actionName)) {
            Frame parentFrame = (Frame) SwingUtilities.getWindowAncestor(lichTrinh);
            dialog = new LichTrinhDialog(parentFrame, "create", lichTrinh, null);
            dialog.setVisible(true);
            return;
        }

        int selectedRow = lichTrinh.getLichTrinhTable().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(lichTrinh, "Vui lòng chọn một lịch trình", "Thông báo",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        lichTrinhModel = lichTrinh.getSelectedLichTrinh();
        if (lichTrinhModel == null) {
            JOptionPane.showMessageDialog(lichTrinh, "Không tìm thấy thông tin lịch trình", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Frame parentFrame = (Frame) SwingUtilities.getWindowAncestor(lichTrinh);

        switch (actionName) {
            case "detail":
                dialog = new LichTrinhDialog(parentFrame, "detail", lichTrinh, lichTrinhModel);
                dialog.setVisible(true);
                break;
            case "update":
                dialog = new LichTrinhDialog(parentFrame, "update", lichTrinh, lichTrinhModel);
                dialog.setVisible(true);
                break;
            case "delete":
                if ("Hoàn Thành".equals(lichTrinhModel.getTrangthailichtrinh())) {
                    JOptionPane.showMessageDialog(lichTrinh, 
                        "Không thể xóa lịch trình đã hoàn thành!",
                        "Thông báo", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                int confirm = JOptionPane.showConfirmDialog(lichTrinh,
                        "Bạn có chắc muốn xóa lịch trình này?",
                        "Xác nhận", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    deleteLichTrinh(lichTrinhModel.getMachuyen());
                }
                break;
            default:
                System.err.println("Unknown action: " + actionName);
        }
    }

    private void deleteLichTrinh(int machuyen) {
        if (LichTrinhService.delete(machuyen)) {
            JOptionPane.showMessageDialog(lichTrinh, "Xóa lịch trình thành công", "Thông báo",
                    JOptionPane.INFORMATION_MESSAGE);
            lichTrinh.loadDataTable();
        } else {
            JOptionPane.showMessageDialog(lichTrinh, "Xóa lịch trình thất bại", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            String searchText = lichTrinh.getSearch().getTxtSearchForm().getText().trim();
            if (!searchText.isEmpty()) {
                lichTrinh.performSearch(searchText,
                        (String) lichTrinh.getSearch().getCbxChoose().getSelectedItem());
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == lichTrinh.getSearch().getTxtSearchForm()) {
            Timer searchTimer = lichTrinh.getSearchTimer();
            if (searchTimer != null) {searchTimer.cancel();}
            searchTimer = new Timer();
            lichTrinh.setSearchTimer(searchTimer);

            searchTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    SwingUtilities.invokeLater(() -> {
                        lichTrinh.performSearch(lichTrinh.getSearch().getTxtSearchForm().getText(),
                                (String) lichTrinh.getSearch().getCbxChoose().getSelectedItem());});
                }
            }, 300);
        }
    }
}