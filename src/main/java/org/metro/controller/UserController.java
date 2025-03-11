package org.metro.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.metro.DAO.DangkyDao;

public class UserController implements ActionListener {
    private final DangkyDao userService = new DangkyDao();
    @Override
    public void actionPerformed(ActionEvent e) {

    }

//    public boolean handleDangKy(String TenDangNhap,String matKhau,String sodienthoai) {
//        return userService.DangKy(TenDangNhap,matKhau,sodienthoai);
//    }
}
