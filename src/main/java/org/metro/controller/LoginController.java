package org.metro.controller;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import org.metro.view.LoginFrame;
import org.metro.view.MainFrame;

public class LoginController implements MouseInputListener {
    private LoginFrame loginFrame;

    public LoginController(LoginFrame loginFrame) {
        this.loginFrame = loginFrame;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == loginFrame.getDangNhapButton()) {
            JOptionPane.showMessageDialog(loginFrame, "Bạn vừa nhấn vào nút đăng nhập",
                    "Thông báo", 1);
            loginFrame.dispose();
            new MainFrame();
        } else if (e.getSource() == loginFrame.getTaoTaiKhoan()) {
            loginFrame.HienTaoTaiKhoan();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        ((JPanel) e.getSource()).setBackground(Color.decode("#80A6C4"));
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        ((JPanel) e.getSource()).setBackground(Color.decode("#6096B4"));
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        e.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
        try {
            ((JPanel) e.getSource()).setBackground(Color.decode("#6F9FBF"));
        } catch (Exception ex) {
            System.err.println("Loi ep kieu: " + ex.getMessage());
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        e.getComponent().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        try {
            ((JPanel) e.getSource()).setBackground(Color.decode("#6096B4"));
        } catch (Exception ex) {
            System.err.println("Loi ep kieu: " + ex.getMessage());
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

}
