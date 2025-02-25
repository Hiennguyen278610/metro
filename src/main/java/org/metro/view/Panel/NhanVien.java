package org.metro.view.Panel;

import javax.swing.*;
import java.awt.*;

public class NhanVien extends JPanel {

    Color BackgroundColor = new Color(96, 150, 180);

    public void initComponent(){
        this.setBackground(BackgroundColor);

        JLabel nhanVienLabel = new JLabel("Nhân viên");
        this.add(nhanVienLabel);
    }
}
