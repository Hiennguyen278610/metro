package org.metro.controller;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Frame;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import org.metro.view.LoginFrame;
import org.metro.view.MainFrame;

public class MainController implements MouseInputListener {
    private MainFrame frame;
    private JPanel PanelDangChon;

    public MainController(MainFrame frame) {
        this.frame = frame;
        PanelDangChon = frame.getTuyenDuongPanel();
    }

    public MainController(MainFrame frame, JPanel PanelDangChon) {
        this.frame = frame;
        this.PanelDangChon = frame.getTuyenDuongPanel();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == frame.getDangXuatPanel()) {
            frame.dispose();
            new LoginFrame();
            return;
        }
        if (e.getSource() == frame.getTuyenDuongPanel()) {
            frame.showTuyenDuongContent();
        } else if (e.getSource() == frame.getThongKePanel()) {
            frame.showThongKeContent();
        } else if (e.getSource() == frame.getLichTrinhPanel()) {
            frame.showLichTrinhContent();
        } else if (e.getSource() == frame.getMuaVePanel()) {
            frame.showMuaVeContent();
        } else if (e.getSource() == frame.getMuaVePanel()) {
            frame.showMuaVeContent();
        } else if (e.getSource() == frame.getExitButton()) {
            frame.dispose();
        } else if (e.getSource() == frame.getMinimizeButton()) {
            frame.setState(Frame.ICONIFIED);
        }
        PanelDangChon = ((JPanel) e.getSource());
        frame.resetPanel();
        ((JPanel) e.getSource()).setBackground(Color.decode("#6096B4"));

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // ((JPanel) e.getSource()).setBackground(Color.decode("#80A6C4"));
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // ((JPanel) e.getSource()).setBackground(Color.decode("#6096B4"));
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        e.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
        if (e.getSource() != PanelDangChon) {
            ((JPanel) e.getSource()).setBackground(Color.decode("#6096B4"));
            ((JLabel) ((JPanel) e.getSource()).getComponent(0)).setForeground(Color.decode("#ffffff"));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        e.getComponent().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        if (e.getSource() != PanelDangChon) {
            ((JPanel) e.getSource()).setBackground(Color.decode("#93BFCF"));
            ((JLabel) ((JPanel) e.getSource()).getComponent(0)).setForeground(Color.decode("#000000"));
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

}
