package org.metro.controller;

import org.metro.service.UserService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserController implements ActionListener {
    private final UserService userService = new UserService();
    @Override
    public void actionPerformed(ActionEvent e) {

    }

//    public boolean handleDangKy(String TenDangNhap,String matKhau,String sodienthoai) {
//        return userService.DangKy(TenDangNhap,matKhau,sodienthoai);
//    }
}
