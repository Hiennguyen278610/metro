package org.metro.view.Dialog;

import org.metro.controller.TaiKhoanController;
import org.metro.model.PhanQuyenModel.NhomQuyenModel;
import org.metro.model.TaiKhoanModel;
import org.metro.service.PhanQuyenService;
import org.metro.service.TaiKhoanService;
import org.metro.view.Component.ButtonEdit;
import org.metro.view.Component.InputField;
import org.metro.view.Panel.TaiKhoan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TaiKhoanDialog extends  JDialog{
    private InputField manvTextfield,matkhauTextfield,nhomquyenTextfield,trangThaiTextfield;
    private JButton okButton,cancelButton;
    private String typeDialog;
    private JPanel centerPanel;
    private JPanel bottomPanel;
    private TaiKhoan tk;
    private TaiKhoanModel tkm;
    private TaiKhoanService tks;
    private PhanQuyenService pqs;
    private TaiKhoanController tkController;

    public TaiKhoanDialog(Frame parent, String typeDialog, TaiKhoan tk, TaiKhoanModel tkm) {
        super(parent,true);
        this.tk = tk;
        this.typeDialog = typeDialog;
        this.tkm = tkm;
        tks = new TaiKhoanService();
        pqs = new PhanQuyenService();
        tkController = new TaiKhoanController(tk,this);
        this.setTitle(titleDialog());
        this.setSize(500,400);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        centerPanel = new JPanel(new GridLayout(4,1,10,5));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        centerPanel.setBackground(Color.WHITE);
        bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,5,5));
        this.init();
        setEdit();
    }

    public String titleDialog() {
        switch (typeDialog) {
            case "create":
                return "THEM TAI KHOAN";
            case "update":
                return "CAP NHAT TAI KHOAN";
            case "detail":
                return "CHI TIET TAI KHOAN";
            default:
                return "";
        }
    }
    public String setNameButton(String title) {
        String namebtn = "";
        switch (typeDialog) {
            case "create":
                namebtn = "Thêm";
                break;
            case "update":
                namebtn = "Cập nhật";
                break;
            case "detail":
                namebtn = "xem";
                break;
            default:
                break;
        }
        return namebtn;
    }


    private void setEdit() {
        if("create".equals(typeDialog)) return;
        TaiKhoanModel tkm = tk.getSelectedTaiKhoan();
        if(tkm != null) {
            this.manvTextfield.getTxtInput().setText(String.valueOf(tkm.getManv()));
            this.matkhauTextfield.getTxtInput().setText(String.valueOf(tkm.getMatkhau()));
            NhomQuyenModel nqm = tkm.getNqm();
            this.nhomquyenTextfield.getComboboxnhomquyen().setSelectedItem(nqm);
            this.trangThaiTextfield.getCombobox().setSelectedItem(tkm.getTrangthai());
        }
        if ("detail".equals(typeDialog)) {
            this.manvTextfield.getTxtInput().setEditable(false);
            this.matkhauTextfield.getTxtInput().setEditable(false);
            this.nhomquyenTextfield.getComboboxnhomquyen().setEnabled(false);
            this.trangThaiTextfield.getCombobox().setEnabled(false);
            okButton.setVisible(false);
        } else if ("update".equals(typeDialog)) {
            this.manvTextfield.getTxtInput().setEditable(false);
        }
    }
    private void init() {
        manvTextfield = new InputField("Mã nhân viên ",300,10,"");
        centerPanel.add(manvTextfield);
        matkhauTextfield = new InputField("Mật khẩu ",300,10,"");
        centerPanel.add(matkhauTextfield);

        ArrayList<NhomQuyenModel> listnq = new ArrayList<>();
        listnq.addAll(pqs.getAllNhomquyen());
        nhomquyenTextfield = new InputField("Tên nhóm quyền: ", listnq,300,20);
        centerPanel.add(nhomquyenTextfield);

        String[] trangthai = new String[]{"Hoạt động","Ngừng hoạt động"};
        trangThaiTextfield = new InputField("Trạng thái: ", trangthai,300,20);
        centerPanel.add(trangThaiTextfield);

        this.add(centerPanel, BorderLayout.CENTER);

        okButton = ButtonEdit.createButton(setNameButton(getTitle()),80,30);
        cancelButton = ButtonEdit.createButton("Thoát",80,30);
        bottomPanel.add(okButton);
        bottomPanel.add(cancelButton);

        this.add(bottomPanel, BorderLayout.SOUTH);

        //action
        okButton.addActionListener(tkController);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public InputField getManvTextfield() {
        return manvTextfield;
    }

    public void setManvTextfield(InputField manvTextfield) {
        this.manvTextfield = manvTextfield;
    }

    public InputField getMatkhauTextfield() {
        return matkhauTextfield;
    }

    public void setMatkhauTextfield(InputField matkhauTextfield) {
        this.matkhauTextfield = matkhauTextfield;
    }

    public InputField getNhomquyenTextfield() {
        return nhomquyenTextfield;
    }

    public void setNhomquyenTextfield(InputField nhomquyenTextfield) {
        this.nhomquyenTextfield = nhomquyenTextfield;
    }

    public InputField getTrangThaiTextfield() {
        return trangThaiTextfield;
    }

    public void setTrangThaiTextfield(InputField trangThaiTextfield) {
        this.trangThaiTextfield = trangThaiTextfield;
    }

    public JButton getOkButton() {
        return okButton;
    }

    public void setOkButton(JButton okButton) {
        this.okButton = okButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public void setCancelButton(JButton cancelButton) {
        this.cancelButton = cancelButton;
    }

    public String getTypeDialog() {
        return typeDialog;
    }

    public void setTypeDialog(String typeDialog) {
        this.typeDialog = typeDialog;
    }

    public JPanel getCenterPanel() {
        return centerPanel;
    }

    public void setCenterPanel(JPanel centerPanel) {
        this.centerPanel = centerPanel;
    }

    public JPanel getBottomPanel() {
        return bottomPanel;
    }

    public void setBottomPanel(JPanel bottomPanel) {
        this.bottomPanel = bottomPanel;
    }

    public TaiKhoan getTk() {
        return tk;
    }

    public void setTk(TaiKhoan tk) {
        this.tk = tk;
    }

    public TaiKhoanService getTks() {
        return tks;
    }

    public void setTks(TaiKhoanService tks) {
        this.tks = tks;
    }

    public PhanQuyenService getPqs() {
        return pqs;
    }

    public void setPqs(PhanQuyenService pqs) {
        this.pqs = pqs;
    }

    public TaiKhoanController getTkController() {
        return tkController;
    }

    public void setTkController(TaiKhoanController tkController) {
        this.tkController = tkController;
    }

    public TaiKhoanModel getTkm() {
        return tkm;
    }

    public void setTkm(TaiKhoanModel tkm) {
        this.tkm = tkm;
    }
}
