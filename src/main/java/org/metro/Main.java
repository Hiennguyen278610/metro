package org.metro;

import javax.swing.SwingUtilities;

import org.metro.view.LoginFrame;

public class Main {
    public static void main(String[] args) {
        // Đặt encoding toàn cục cho ứng dụng
        System.setProperty("file.encoding", "UTF-8");

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginFrame();
            }
        });
    }
}
