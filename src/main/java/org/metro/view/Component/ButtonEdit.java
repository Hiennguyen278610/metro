package org.metro.view.Component;

import javax.swing.*;
import java.awt.*;

public class ButtonEdit {
    public static JButton createButton(String nameButton, int width, int height) {
        JButton button = new JButton(nameButton);
        button.setPreferredSize(new Dimension(width, height));
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setBackground(null);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }
}
