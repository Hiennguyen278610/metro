package org.metro.view.Panel;

import javax.swing.*;
import java.awt.*;

public class NhanVien extends JPanel {

    Color BackgroundColor = new Color(96, 150, 180);

    public void initComponent(){
        this.setBackground(BackgroundColor);

        // Thêm các thành phần khác vào panel Nhân viên nếu cần
        JLabel nhanVienLabel = new JLabel("Nhân viên");
        this.add(nhanVienLabel);
    }
}
