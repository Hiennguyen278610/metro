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
    private JPanel selectedPanel;

    public MainController(MainFrame frame) {
        this.frame = frame;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object src = e.getSource();

        // Xử lý các nút đặc biệt từ Navbar
        if (src == frame.getDangXuatPanel()) {
            frame.dispose();
            new LoginFrame();
            return;
        }
        if (src == frame.getExitButton()) {
            // System.exit(0);
            frame.dispose();
            return;
        }
        if (src == frame.getMinimizeButton()) {
            frame.setState(Frame.ICONIFIED);
            return;
        }

        // Nếu sự kiện đến từ một JPanel (các mục taskbar)
        if (src instanceof JPanel) {
            JPanel panel = (JPanel) src;
            // Reset màu của panel đang được chọn nếu khác với panel hiện tại
            if (selectedPanel != null && selectedPanel != panel) {
                resetPanelColor(selectedPanel);
            }
            setSelectedColor(panel);
            selectedPanel = panel;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        e.getComponent().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Object src = e.getSource();
        if (src instanceof JPanel && src != selectedPanel) {
            setHoverColor((JPanel) src);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        e.getComponent().setCursor(Cursor.getDefaultCursor());
        Object src = e.getSource();
        if (src instanceof JPanel && src != selectedPanel) {
            resetPanelColor((JPanel) src);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    // Hàm đặt màu cho panel được chọn
    private void setSelectedColor(JPanel panel) {
        panel.setBackground(Color.decode("#6096B4"));
        if (panel.getComponentCount() > 0 && panel.getComponent(0) instanceof JLabel) {
            ((JLabel) panel.getComponent(0)).setForeground(Color.WHITE);
        }
    }

    // Hàm reset màu cho panel không được chọn
    private void resetPanelColor(JPanel panel) {
        panel.setBackground(Color.decode("#93BFCF"));
        if (panel.getComponentCount() > 0 && panel.getComponent(0) instanceof JLabel) {
            ((JLabel) panel.getComponent(0)).setForeground(Color.BLACK);
        }
    }

    // Hàm đặt màu hover cho panel
    private void setHoverColor(JPanel panel) {
        panel.setBackground(Color.decode("#6096B4"));
        if (panel.getComponentCount() > 0 && panel.getComponent(0) instanceof JLabel) {
            ((JLabel) panel.getComponent(0)).setForeground(Color.WHITE);
        }
    }

}
