package org.metro.controller;

import org.metro.view.Component.MenuTaskbar;
import org.metro.view.Component.itemTaskbar;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuTaskbarController {
    private MenuTaskbar menuTaskbar;
    private itemTaskbar selectedItem; // Lưu trạng thái item được chọn
    private final Color DefaultColor = new Color(148, 183, 205);
    private final Color HoverBackgroundColor = new Color(80, 138, 170);
    private final Color FontColor = new Color(0, 0, 0);
    private final Color HoverFontColor = new Color(200, 222, 231);

//    public MenuTaskbarController(MenuTaskbar menuTaskbar) {
//        this.menuTaskbar = menuTaskbar;
//        addListeners();
//    }

//    private void addListeners() {
//        for (itemTaskbar item : menuTaskbar.getListItems()) {
//            item.addMouseListener(new MouseAdapter() {
//                @Override
//                public void mousePressed(MouseEvent e) {
//                    setSelectedItem(item);
//                }
//            });
//        }
//    }

//    private void setSelectedItem(itemTaskbar item) {
//        if (selectedItem != null) {
//            selectedItem.setBackground(DefaultColor);
//            selectedItem.lblContent.setForeground(FontColor);
//        }
//
//        selectedItem = item;
//        selectedItem.setBackground(HoverBackgroundColor);
//        selectedItem.lblContent.setForeground(HoverFontColor);
//    }
}
