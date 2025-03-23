package org.metro.view.Dialog;

import org.metro.controller.NhanVienController;
import org.metro.model.NhanVienModel;
import org.metro.view.Component.handleComponents;
import org.metro.view.Panel.NhanVien;

import java.awt.*;

import javax.swing.*;

public class NhanVienDialog extends JDialog{
    private JLabel tennvLabel,sodienthoaiLabel,gioitinhLabel,chucvuLabel;
    private JComboBox<String> gioitinhCombobox,chucvuCombobox;
    private JTextField tennvTextfield,sodienthoaiTextfield;
    private JButton ok,cancel;
    private String type;
    private JPanel contentPanel;
    private NhanVien nv;
    private NhanVienModel nvm;
    private NhanVienController action = new NhanVienController(this);

    // dialog them, sua , chi tiet nhanvien
    public NhanVienDialog(Frame parent, String type,NhanVien nv,NhanVienModel nvm) {
        super(parent,true);
        this.nv = nv;
        this.type = type;
        this.nvm = nvm;
        this.setTitle(setTitleType());
        this.setSize(500,500);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.init();
        checkButtonClicked();
    }

    private void init() {
        contentPanel = new JPanel();
        contentPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10,10,5,5);

        //label
        gbc.anchor = GridBagConstraints.LINE_END;
        handleComponents.addLabelGBL(contentPanel,"Tên nhân viên: ",0,0,gbc);
        handleComponents.addLabelGBL(contentPanel,"Số điện thoại: ",0,1,gbc);
        handleComponents.addLabelGBL(contentPanel,"Giới tính: ",0,2,gbc);
        handleComponents.addLabelGBL(contentPanel,"Chức vụ: ",0,3,gbc);

        //textfield
        gbc.gridwidth = 2;
        tennvTextfield = handleComponents.addTextFieldGBL(contentPanel,25,1,0,gbc);
        sodienthoaiTextfield = handleComponents.addTextFieldGBL(contentPanel,25,1,1,gbc);

        //combobox
        gbc.gridwidth = 2;
        String[] gioitinh = {"--","Nam", "Nữ"};
        gioitinhCombobox = handleComponents.addComboBoxGBL(contentPanel,gioitinh,1,2,gbc);

        String[] chucvu = {"--","Quản lí","Thu ngân","Kiểm vé","Lái tàu"};
        chucvuCombobox = handleComponents.addComboBoxGBL(contentPanel,chucvu,1,3,gbc);

        //button
        gbc.gridy = 4;
        gbc.weightx = 1;
        ok = handleComponents.addButtonGBL(contentPanel,"Thêm",0,4,gbc);
        cancel = handleComponents.addButtonGBL(contentPanel,"Thoát",1,4,gbc);
        
        //them action cho nut ok va cancel
        ok.addActionListener(action);
        cancel.addActionListener(action);

        gbc.gridy = 5;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        contentPanel.add(new JPanel(),gbc);
        this.add(contentPanel);
    }

    //ham set title cho tung kieu them,sua,xem chi tiet
    private String setTitleType() {
        if(type == null) {
            return null;
        }
        switch (type) {
            case "create":
                return "THÊM NHÂN VIÊN";
            case "update":
                return "SỬA THÔNG TIN NHÂN VIÊN";
            case "detail":
                return "THÔNG TIN CHI TIẾT NHÂN VIÊN";
            default: return "ERROR";
        }
    }

    //check xem nut them,sua,xoa,delete dc nhan
    public void checkButtonClicked() {
        switch (type) {
            case "create":
                editEnabled(true);
                break;
            case "update":
                editEnabled(true);
                if(nv != null) {
                    nv.reloadData();
                }
                break;
            case "detail":
                editEnabled(false);
                if(nv != null) {
                    nv.reloadData();
                }
                break;
            case "delete":
//                this.dispose();
                break;
            default:
                System.err.println("button duoc nhan la " + type);

        }
    }

    //ham cho phep chinh sua
    public void editEnabled(boolean enabled) {
        NhanVienModel nhanvienduochon = nv.getSelectedNhanvien();
        if(nhanvienduochon != null) {
            this.getTennvTextfield().setText(nhanvienduochon.getTennv());
            this.getSodienthoaiTextfield().setText(nhanvienduochon.getSdtnv());
            this.getGioitinhCombobox().setSelectedItem(nhanvienduochon.getGioitinh());
            this.getChucvuCombobox().setSelectedItem(nhanvienduochon.getChucvu());
        } else {
            System.out.println("errors");
        }
        tennvTextfield.setEnabled(enabled);
        sodienthoaiTextfield.setEnabled(enabled);
        gioitinhCombobox.setEnabled(enabled);
        chucvuCombobox.setEnabled(enabled);
    }

    //getter setter
    public JLabel getTennvLabel() {
        return tennvLabel;
    }

    public void setTennvLabel(JLabel tennvLabel) {
        this.tennvLabel = tennvLabel;
    }

    public JLabel getSodienthoaiLabel() {
        return sodienthoaiLabel;
    }

    public void setSodienthoaiLabel(JLabel sodienthoaiLabel) {
        this.sodienthoaiLabel = sodienthoaiLabel;
    }

    public JLabel getGioitinhLabel() {
        return gioitinhLabel;
    }

    public void setGioitinhLabel(JLabel gioitinhLabel) {
        this.gioitinhLabel = gioitinhLabel;
    }

    public JLabel getChucvuLabel() {
        return chucvuLabel;
    }

    public void setChucvuLabel(JLabel chucvuLabel) {
        this.chucvuLabel = chucvuLabel;
    }

    public JComboBox<String> getGioitinhCombobox() {
        return gioitinhCombobox;
    }

    public void setGioitinhCombobox(JComboBox<String> gioitinhCombobox) {
        this.gioitinhCombobox = gioitinhCombobox;
    }

    public JComboBox<String> getChucvuCombobox() {
        return chucvuCombobox;
    }

    public void setChucvuCombobox(JComboBox<String> chucvuCombobox) {
        this.chucvuCombobox = chucvuCombobox;
    }

    public JTextField getTennvTextfield() {
        return tennvTextfield;
    }

    public void setTennvTextfield(JTextField tennvTextfield) {
        this.tennvTextfield = tennvTextfield;
    }

    public JTextField getSodienthoaiTextfield() {
        return sodienthoaiTextfield;
    }

    public void setSodienthoaiTextfield(JTextField sodienthoaiTextfield) {
        this.sodienthoaiTextfield = sodienthoaiTextfield;
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

    public JPanel getContentPanel() {
        return contentPanel;
    }

    public void setContentPanel(JPanel contentPanel) {
        this.contentPanel = contentPanel;
    }

}
