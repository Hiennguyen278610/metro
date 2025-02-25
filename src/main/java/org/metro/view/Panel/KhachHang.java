package org.metro.view.Panel;

import javax.swing.*;
import java.awt.*;

public class KhachHang extends JPanel {

    Color BackgroundColor = new Color(96, 150, 180);

    public void initComponent(){
        this.setBackground(BackgroundColor);

        JLabel khachHangLabel = new JLabel("Khách hàng");
        this.add(khachHangLabel);
    }
}
