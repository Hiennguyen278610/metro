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
        String username = loginFrame.getUsernameField().getText().trim();
        String password = new String(loginFrame.getPasswordField().getPassword());
        int manv;
        try {
            manv = Integer.parseInt(username);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(loginFrame, "Tên đăng nhập không hợp lệ");
            return;
        }
        TaiKhoanModel tk = TaiKhoanService.getByID(manv);
        if (tk != null && tk.getMatkhau().equals(password) && tk.getTrangthai() == 1) {
            SessionManager.setCurrentUser(tk);
            loginFrame.dispose();
            new MainFrame();
        } else {
            JOptionPane.showMessageDialog(loginFrame, "Thông tin đăng nhập không chính xác hoặc tài khoản bị khóa");
        }
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        Object src = e.getSource();
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
