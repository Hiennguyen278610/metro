package org.metro.service;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class SetLogoService {

    public static void setLogo(JFrame frame) {
        ImageIcon icon = new ImageIcon(SetLogoService.class.getResource("/svg/logo.png"));
        frame.setIconImage(icon.getImage());
    }
}
