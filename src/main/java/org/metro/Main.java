package org.metro;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import mdlaf.MaterialLookAndFeel;

import org.metro.view.LoginFrame;

public class Main {
    public static void main(String[] args) {
        // Đặt encoding toàn cục cho ứng dụng
        System.setProperty("file.encoding", "UTF-8");

        try {
            // Thiết lập Material Look and Feel
            UIManager.setLookAndFeel(new MaterialLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginFrame();
            }
        });
    }
}
