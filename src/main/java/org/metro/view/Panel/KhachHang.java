package org.metro.view.Panel;

import javax.swing.*;
import java.awt.*;

public class KhachHang extends JPanel {

    Color BackgroundColor = new Color(96, 150, 180);

    public void initComponent(){
        this.setBackground(BackgroundColor);

        // Thêm các thành phần khác vào panel Khách hàng nếu cần
        JLabel khachHangLabel = new JLabel("Khách hàng");
        this.add(khachHangLabel);
    }
}
