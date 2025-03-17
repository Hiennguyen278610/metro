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
        gbc.insets = new Insets(5,20,5,20);

        //label
        handleComponents.addLabelGBL(contentPanel,"ten nhan vien: ",0,0,gbc);
        handleComponents.addLabelGBL(contentPanel,"so dien thoai: ",0,1,gbc);
        handleComponents.addLabelGBL(contentPanel,"gioitinh: ",0,2,gbc);
        handleComponents.addLabelGBL(contentPanel,"chucvu: ",0,3,gbc);

        //textfield
        tennvTextfield = handleComponents.addTextFieldGBL(contentPanel,20,1,0,gbc);
        sodienthoaiTextfield = handleComponents.addTextFieldGBL(contentPanel,20,1,1,gbc);

        //combobox
        String[] gioitinh = {"--","Nam", "Nu"};
        gioitinhCombobox = handleComponents.addComboBoxGBL(contentPanel,gioitinh,1,2,gbc);
        String[] chucvu = {"--","Quan li tuyen tau dien","Thu ngan","soat ve","lai tau"};
        chucvuCombobox = handleComponents.addComboBoxGBL(contentPanel,chucvu,1,3,gbc);

        //button
        ok = handleComponents.addButtonGBL(contentPanel,"THEM",0,4,gbc);
        cancel = handleComponents.addButtonGBL(contentPanel,"CANCEl",0,5,gbc);
        
        //them action cho nut ok va cancel
        ok.addActionListener(action);

        this.add(contentPanel);
    }

    //ham set title cho tung kieu them, xoa ,sua
    private String setTitleType() {
        if(type == null) {
            return null;
        }
        switch (type) {
            case "create":
                return "THEM NHAN VIEN";
            case "update":
                return "SUA THONG TIN";
            case "detail":
                return "THONG TIN NHAN VIEN";
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
