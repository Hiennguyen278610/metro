package org.metro.view.Dialog;

import org.metro.controller.NhanVienController;
import org.metro.view.Component.handleComponents;

import java.awt.*;

import javax.swing.*;

public class NhanVienDialog extends JDialog{
    private JLabel tennvLabel,sodienthoaiLabel,gioitinhLabel,chucvuLabel;
    private JComboBox<String> gioitinhCombobox,chucvuCombobox;
    private JTextField tennvTextfield,sodienthoaiTextfield;
    private JButton ok,cancel;
    private String type;
    private JPanel contentPanel;
    private NhanVienController action = new NhanVienController(this);

    // dialog them, sua , chi tiet nhanvien
    public NhanVienDialog(Frame parent, String type) {
        super(parent,true);
        this.type = type;
        this.setTitle(setTileType());
        this.setSize(500,500);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
       this.setLocationRelativeTo(null);
        this.init();
        this.setVisible(true);
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
    private String setTileType() {
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
