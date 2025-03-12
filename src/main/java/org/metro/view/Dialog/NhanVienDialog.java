package org.metro.view.Dialog;

import java.awt.*;

import javax.swing.*;

public class NhanVienDialog extends JDialog{
    private JLabel tennvLabel,sodienthoaiLabel,gioitinhLabel,chucvuLabel;
    private JComboBox<String> gioitinhCombobox,chucvuCombobox;
    private JTextField tennvTextfield,sodienthoaiTextfield;
    private JButton ok,cancel;
    private String type;

    // dialog them, sua , chi tiet nhanvien
    public NhanVienDialog(Frame parent, String type) {
        super(parent,true);
        this.type = type;
        this.setTitle(setTileType());
        this.setSize(500,500);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
       this.setLocationRelativeTo(null);
        this.init();
        this.setDetail();
        this.setVisible(true);
    }

    private void init() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5,5,5,5);

        //label
        tennvLabel = new JLabel("ten nhan vien: ");
        sodienthoaiLabel = new JLabel("so dien thoai: ");
        gioitinhLabel = new JLabel("gioitinh: ");
        chucvuLabel = new JLabel("chucvu: ");

        //textfield
        tennvTextfield = new JTextField(10);
        sodienthoaiTextfield = new JTextField(10);

        //combobox
        gioitinhCombobox = new JComboBox<>(new String[]{"Nam", "Nu"});
        chucvuCombobox = new JComboBox<>(new String[]{"Quan li tuyen tau dien","Thu ngan","soat ve","lai tau"});

        //button
        ok = new JButton("OK");
        cancel = new JButton("Cancel");

        //set vi tri cho tung cap label,textfield label,combobox
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(tennvLabel,gbc);
        gbc.gridx = 1;
        this.add(tennvTextfield,gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(sodienthoaiLabel,gbc);
        gbc.gridx = 1;
        this.add(sodienthoaiTextfield,gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        this.add(gioitinhLabel,gbc);
        gbc.gridx = 1;
        this.add(gioitinhCombobox,gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        this.add(chucvuLabel,gbc);
        gbc.gridx = 1;
        this.add(chucvuCombobox,gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        this.add(ok,gbc);
        gbc.gridy = 5;
        this.add(cancel,gbc);

    }

    //ham set title cho tung kieu them, xoa ,sua
    private String setTileType() {
        switch (type) {
            case "create": type = "THEM NHAN VIEN"; break;
            case "update": type = "SUA THONG TIN"; break;
            case "detail": type = "THONG TIN NHAN VIEN"; break;
        }
        return type;
    }

    //ham set cho nut chi tiet thi khong dc thay doi text field
    private void setDetail() {
        boolean check = type.equals("detail");
        tennvTextfield.setEditable(false);
        sodienthoaiTextfield.setEditable(false);
        gioitinhCombobox.setEnabled(!check);
        chucvuCombobox.setEnabled(!check);
    }
}
