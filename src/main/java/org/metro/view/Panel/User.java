package org.metro.view.Panel;

import javax.swing.*;
import java.awt.*;

public class User extends JPanel {

    Color BackgroundColor = new Color(96, 150, 180);
    public void initComponent(){
        this.setBackground(BackgroundColor);

        JLabel userLabel = new JLabel("User");
        this.add(userLabel);
    }

}
