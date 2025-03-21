package org.metro.controller;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.event.MouseInputListener;
import org.metro.model.TaiKhoanModel;
import org.metro.service.TaiKhoanService;
import org.metro.util.SessionManager;
import org.metro.view.LoginFrame;
import org.metro.view.MainFrame;

public class LoginController implements java.awt.event.ActionListener, MouseInputListener {
    private LoginFrame loginFrame;

    public LoginController(LoginFrame loginFrame) {
        this.loginFrame = loginFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Lấy thông tin đăng nhập từ LoginFrame
        String username = loginFrame.getUsernameField().getText().trim();
        String password = new String(loginFrame.getPasswordField().getPassword());
        int manv;
        try {
            manv = Integer.parseInt(username);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(loginFrame, "Tên đăng nhập không hợp lệ");
            return;
        }
        // Sử dụng TaiKhoanService để lấy thông tin tài khoản
        TaiKhoanModel tk = TaiKhoanService.getByID(manv);
        if (tk != null && tk.getMatkhau().equals(password) && tk.getTrangthai() == 1) {
            // Lưu thông tin user vào session
            SessionManager.setCurrentUser(tk);
            loginFrame.dispose();
            new MainFrame();  // Mở MainFrame
        } else {
            JOptionPane.showMessageDialog(loginFrame, "Thông tin đăng nhập không chính xác hoặc tài khoản bị khóa");
        }
    }

    // Các sự kiện chuột (MouseInputListener)
    @Override
    public void mouseClicked(MouseEvent e) {
        Object src = e.getSource();
        // Có thể xử lý sự kiện click nếu cần
        if (src == loginFrame.getExitButton()) {
            loginFrame.dispose();
            return;
        }
        if (src == loginFrame.getMinimizeButton()) {
            loginFrame.setState(Frame.ICONIFIED);
            return;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) { }

    @Override
    public void mouseReleased(MouseEvent e) { }

    @Override
    public void mouseEntered(MouseEvent e) {
        e.getComponent().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        // Ví dụ: thay đổi màu nền cho nút exit và minimize
        if (e.getSource() == loginFrame.getExitButton()) {
            loginFrame.getExitButton().setBackground(Color.decode("#FE2020"));
        } else if (e.getSource() == loginFrame.getMinimizeButton()) {
            loginFrame.getMinimizeButton().setBackground(Color.decode("#6096B4"));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        e.getComponent().setCursor(Cursor.getDefaultCursor());
        if (e.getSource() == loginFrame.getExitButton()) {
            loginFrame.getExitButton().setBackground(Color.white);
        } else if (e.getSource() == loginFrame.getMinimizeButton()) {
            loginFrame.getMinimizeButton().setBackground(Color.white);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) { }

    @Override
    public void mouseMoved(MouseEvent e) { }
}
