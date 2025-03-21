package org.metro.controller;

import org.metro.model.VeTauModel;
import org.metro.service.VeTauService;
import org.metro.view.Panel.VeTau;
import org.metro.view.Component.ToolBar;
import org.metro.view.Dialog.VeTauDialog;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;


public class VeTauController implements ActionListener, ItemListener, KeyListener {
    private VeTau vetau;
    private VeTauDialog dialog;
    private VeTauModel vetauModel;

    public VeTauController(VeTau vetau) {
        this.vetau = vetau;
        this.dialog = null;
    }

    public VeTauController(VeTau vetau, VeTauDialog dialog) {
        this.vetau = vetau;
        this.dialog = dialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JComponent c = (JComponent) e.getSource();

        // Xử lý click từ nút trong dialog
        if (dialog != null) {
            String namebtn = e.getActionCommand();
            if (c instanceof JButton) {
                if (namebtn.equals("THÊM")) {
                    // Lấy dữ liệu từ form
                    VeTauModel newVeTau = dialog.getVeTauFromForm();
                    if (newVeTau != null) {
                        if (VeTauService.insert(newVeTau)) {
                            JOptionPane.showMessageDialog(dialog, "THÊM VÉ TÀU THÀNH CÔNG", "THÔNG BÁO",
                                    JOptionPane.INFORMATION_MESSAGE);
                            dialog.dispose();
                            if (vetau != null) vetau.loadDataTable();
                        } else {
                            JOptionPane.showMessageDialog(dialog, "THÊM VÉ TÀU THẤT BẠI", "THÔNG BÁO",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else if (namebtn.equals("CẬP NHẬT")) {
                    // Xử lý cập nhật
                    VeTauModel updatedVeTau = dialog.getVeTauFromForm();
                    if (updatedVeTau != null) {
                        if (VeTauService.update(updatedVeTau)) {
                            JOptionPane.showMessageDialog(dialog, "CẬP NHẬT VÉ TÀU THÀNH CÔNG", "THÔNG BÁO",
                                    JOptionPane.INFORMATION_MESSAGE);
                            dialog.dispose();
                            if (vetau != null) vetau.loadDataTable();
                        } else {
                            JOptionPane.showMessageDialog(dialog, "CẬP NHẬT VÉ TÀU THẤT BẠI", "THÔNG BÁO",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
                else if (namebtn.equals("HỦY")) {
                    dialog.dispose();
                }
            }
        }

        // Xử lý các nút trong panel VeTau
        if (vetau != null) {
            for (String namebtn : vetau.getMainFunction().getBtn().keySet()) {
                ToolBar tb = vetau.getMainFunction().getBtn().get(namebtn);
                if (c.equals(tb)) {
                    if (namebtn == null || namebtn.trim().isEmpty()) {
                        System.err.println("errors");
                        return;
                    }

                    // Xử lý nút tạo mới
                    if ("create".equals(namebtn)) {
                        // Sửa: Lưu dialog vào biến instance của controller
                        Frame parentFrame = (Frame) SwingUtilities.getWindowAncestor(vetau);
                        dialog = new VeTauDialog(parentFrame, "create", vetau, null);
                        dialog.setVisible(true);
                    } else {
                        // Lấy vé tàu được chọn
                        int selectedRow = vetau.getVeTauTable().getSelectedRow();
                        if (selectedRow == -1) {
                            JOptionPane.showMessageDialog(vetau, "Vui lòng chọn một vé tàu", "Thông báo",
                                    JOptionPane.WARNING_MESSAGE);
                            return;
                        }

                        int maVe = (int) vetau.getVeTauTable().getValueAt(selectedRow, 0);
                        vetauModel = VeTauService.getById(maVe);

                        if (vetauModel == null) {
                            JOptionPane.showMessageDialog(vetau, "Không tìm thấy thông tin vé tàu", "Lỗi",
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        // Lấy parent frame
                        Frame parentFrame = (Frame) SwingUtilities.getWindowAncestor(vetau);

                        // Xử lý nút chi tiết
                        if ("detail".equals(namebtn)) {
                            dialog = new VeTauDialog(parentFrame, "detail", vetau, vetauModel);
                            dialog.setVisible(true);
                        }
                        // Xử lý nút cập nhật
                        else if ("update".equals(namebtn)) {
                            dialog = new VeTauDialog(parentFrame, "update", vetau, vetauModel);
                            dialog.setVisible(true);
                        }
                        // Xử lý nút xóa
                        else if ("delete".equals(namebtn)) {
                            int confirm = JOptionPane.showConfirmDialog(vetau, "Bạn có chắc muốn xóa vé tàu này?", "Xác nhận",
                                    JOptionPane.YES_NO_OPTION);
                            if (confirm == JOptionPane.YES_OPTION) {
                                deleteVeTau(maVe);
                            }
                        }
                    }
                    return;
                }
            }
        }

        // Xử lý nút reset trong thanh tìm kiếm
        if (c == vetau.getSearch().getBtnReset()) {
            vetau.getSearch().getTxtSearchForm().setText("");
            vetau.getSearch().getCbxChoose().setSelectedIndex(0);
            vetau.loadDataTable();
        }
    }

    // Hàm xóa vé tàu
    private void deleteVeTau(int maVe) {
        System.out.println("Thực hiện xóa vé tàu: " + maVe);
        if (VeTauService.delete(maVe)) {
            JOptionPane.showMessageDialog(vetau, "Xóa vé tàu thành công", "Thông báo",
                    JOptionPane.INFORMATION_MESSAGE);
            vetau.loadDataTable();
        } else {
            JOptionPane.showMessageDialog(vetau, "Xóa vé tàu thất bại", "Thông báo",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            // Khi thay đổi loại tìm kiếm, thực hiện tìm kiếm lại nếu có từ khóa
            String searchText = vetau.getSearch().getTxtSearchForm().getText().trim();
            if (!searchText.isEmpty()) {
                vetau.performSearch();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Không xử lý
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Không xử lý
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Xử lý tìm kiếm khi gõ
        if (e.getSource() == vetau.getSearch().getTxtSearchForm()) {
            Timer searchTimer = vetau.getSearchTimer();
            if (searchTimer != null) {
                searchTimer.cancel();
            }

            searchTimer = new Timer();
            vetau.setSearchTimer(searchTimer);

            searchTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    SwingUtilities.invokeLater(() -> vetau.performSearch());
                }
            }, 300);
        }
    }
}