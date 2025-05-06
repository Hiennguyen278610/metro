package org.metro.view.Dialog;

import org.metro.controller.NhanVienController;
import org.metro.model.NhanVienModel;
import org.metro.view.Component.ButtonEdit;
import org.metro.view.Component.InputField;
import org.metro.view.Panel.NhanVien;

import java.awt.*;
import java.util.regex.Pattern;

import javax.swing.*;
import javax.swing.border.Border;

public class NhanVienDialog extends JDialog {
    private InputField manvTextfield, tennvTextfield, sodienthoaiTextfield, gioitinhTextfield, chucvuTextfield;
    private JButton ok, cancel;
    private String nhanVienType;
    private JPanel contentPanel;
    private JPanel bottomPanel;
    private NhanVien nv;
    private NhanVienModel nvm;
    private NhanVienController action;

    // dialog them, sua , chi tiet nhanvien
    public NhanVienDialog(Frame parent, String nhanVienType, NhanVien nv, NhanVienModel nvm) {
        super(parent, true);
        this.nv = nv;
        this.nhanVienType = nhanVienType;
        this.nvm = nvm;
        action = new NhanVienController(this.nv, this);
        this.setLayout(new BorderLayout());
        this.setTitle(setTitleType());
        this.setSize(500, 400);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(parent);
        this.init();
        checkButtonClicked();
        this.setResizable(true);
    }

    private void init() {
        contentPanel = new JPanel();
        bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 0, 10));
        contentPanel.setBackground(Color.white);
        contentPanel.setLayout(new GridLayout(4, 1, 2, 2));
        this.add(createDialog(contentPanel), BorderLayout.CENTER);
        ok = setNameBtn(getNhanVienType());
        if(getNhanVienType().equals("detail")) ok.setVisible(false);
        cancel = ButtonEdit.createButton("Thoát", 80, 30);
        bottomPanel.add(ok);
        bottomPanel.add(cancel);
        this.add(bottomPanel, BorderLayout.SOUTH);

        // thêm sự kiện cho nút bấm
       ok.addActionListener(action);
        cancel.addActionListener(action);
    }

    public JButton setNameBtn(String name) {
        String buttonName;
        switch (name) {
            case "create":
                buttonName = "Thêm";
                break;
            case "update":
                buttonName = "Cập nhật";
                break;
            default:
                buttonName = "";
                break;
        }
        return ButtonEdit.createButton(buttonName, 80, 30);
    }

    public JPanel createDialog(JPanel contentPanel) {
        tennvTextfield = new InputField("Tên nhân viên", 300, 50,"");
        sodienthoaiTextfield = new InputField("Số điện thoại ", 300, 50,"");
        gioitinhTextfield = new InputField("Giới tính ",new String[]{"Nam","Nữ"} ,300, 50);
        chucvuTextfield = new InputField("Chức vụ",new String[]{"Quản lý tuyến tàu điện","Nhân viên soát vé","Nhân viên lái tàu","Nhân viên bảo trì","Nhân viên bán vé"} ,300, 50);

        contentPanel.add(tennvTextfield);
        contentPanel.add(sodienthoaiTextfield);
        contentPanel.add(gioitinhTextfield);
        contentPanel.add(chucvuTextfield);
        return contentPanel;
    }

    // ham set title cho tung kieu them,sua,xem chi tiet
    private String setTitleType() {
        if (nhanVienType == null) {
            return null;
        }
        switch (nhanVienType) {
            case "create":
                return "THÊM NHÂN VIÊN";
            case "update":
                return "SỬA THÔNG TIN NHÂN VIÊN";
            case "detail":
                return "THÔNG TIN CHI TIẾT NHÂN VIÊN";
            default:
                return "ERROR";
        }
    }

    // check xem nut them,sua,xoa,delete dc nhan
    public void checkButtonClicked() {
        switch (nhanVienType) {
            case "update":
                editEnabled(true);
                break;
            case "detail":
                editEnabled(false);
                break;
            default:
                System.err.println("button duoc nhan la " + nhanVienType);

        }
    }

    // ham cho phep chinh sua
    public void editEnabled(boolean enabled) {
        NhanVienModel nhanvienduocchon = nv.getSelectedNhanvien();
        if (nhanvienduocchon != null) {
            this.getTennvTextfield().getTxtInput().setText(nhanvienduocchon.getTennv());
            this.getSodienthoaiTextfield().getTxtInput().setText(nhanvienduocchon.getSdtnv());
            this.getGioitinhTextfield().getCombobox().setSelectedItem(nhanvienduocchon.getGioitinh());
            this.getChucvuTextfield().getCombobox().setSelectedItem(nhanvienduocchon.getChucvu());
        }
        if (tennvTextfield != null)
            tennvTextfield.getTxtInput().setEnabled(enabled);
        if (sodienthoaiTextfield != null)
            sodienthoaiTextfield.getTxtInput().setEnabled(enabled);
        if (gioitinhTextfield != null)
            gioitinhTextfield.getCombobox().setEnabled(enabled);
        if (chucvuTextfield != null)
            chucvuTextfield.getCombobox().setEnabled(enabled);
    }

    public InputField getManvTextfield() {
        return manvTextfield;
    }

    public void setManvTextfield(InputField manvTextfield) {
        this.manvTextfield = manvTextfield;
    }

    public InputField getTennvTextfield() {
        return tennvTextfield;
    }

    public void setTennvTextfield(InputField tennvTextfield) {
        this.tennvTextfield = tennvTextfield;
    }

    public InputField getSodienthoaiTextfield() {
        return sodienthoaiTextfield;
    }

    public void setSodienthoaiTextfield(InputField sodienthoaiTextfield) {
        this.sodienthoaiTextfield = sodienthoaiTextfield;
    }

    public InputField getGioitinhTextfield() {
        return gioitinhTextfield;
    }

    public void setGioitinhTextfield(InputField gioitinhTextfield) {
        this.gioitinhTextfield = gioitinhTextfield;
    }

    public InputField getChucvuTextfield() {
        return chucvuTextfield;
    }

    public void setChucvuTextfield(InputField chucvuTextfield) {
        this.chucvuTextfield = chucvuTextfield;
    }

    public JButton getOk() {
        return ok;
    }

    public void setOk(JButton ok) {
        this.ok = ok;
    }

    public JButton getCancel() {
        return cancel;
    }

    public void setCancel(JButton cancel) {
        this.cancel = cancel;
    }

    public String getNhanVienType() {
        return nhanVienType;
    }

    public void setNhanVienType(String nhanVienType) {
        this.nhanVienType = nhanVienType;
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }

    public void setContentPanel(JPanel contentPanel) {
        this.contentPanel = contentPanel;
    }

    public JPanel getBottomPanel() {
        return bottomPanel;
    }

    public void setBottomPanel(JPanel bottomPanel) {
        this.bottomPanel = bottomPanel;
    }

    public NhanVien getNv() {
        return nv;
    }

    public void setNv(NhanVien nv) {
        this.nv = nv;
    }

    public NhanVienModel getNvm() {
        return nvm;
    }

    public void setNvm(NhanVienModel nvm) {
        this.nvm = nvm;
    }

    public NhanVienController getAction() {
        return action;
    }

    public void setAction(NhanVienController action) {
        this.action = action;
    }
}
