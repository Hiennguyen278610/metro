package org.metro.view.Component;

import org.metro.service.PhanQuyenService;
import org.metro.util.SessionManager;

import javax.swing.*;

import java.awt.*;
import java.util.HashMap;

public class  MainFunction extends JToolBar {
    public HashMap<String, ToolBar> btn = new HashMap<>();
//    private PhanQuyenService pqs = new PhanQuyenService();
    private int machucnang;

//    public MainFunction() {
//        initData();
//        initComponent();
//    }

    public MainFunction(int machucnang,String[] actions) {
        this.machucnang = machucnang;
        this.setBackground(Color.WHITE);
        this.setRollover(true);
        this.setFloatable(false);
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        // Thêm đường viền nhỏ
        this.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        for (String action : actions) {
            if(SessionManager.checkQuyenCurrentUser(machucnang,action)) {
                switch (action) {
                    case "create":
                        btn.put("create", new ToolBar("THÊM", "add.svg", "create"));
                        break;
                    case "delete":
                        btn.put("delete", new ToolBar("XÓA", "delete.svg", "delete"));
                        break;
                    case "update":
                        btn.put("update", new ToolBar("SỬA", "update.svg", "update"));
                        break;
                    case "detail":
                        btn.put("detail", new ToolBar("CHI TIẾT", "detail.svg", "view"));
                        break;
                }
            }
        }
        this.add(Box.createHorizontalStrut(10));
        for (ToolBar toolBar : btn.values()) {
            this.add(toolBar);
            this.add(Box.createHorizontalStrut(10));
        }
        if (getComponentCount() > 0) {
            remove(getComponentCount() - 1);
        }
    }

    public void initData() {
        btn.put("create", new ToolBar("THÊM", "add.svg", "create"));
        btn.put("delete", new ToolBar("XÓA", "delete.svg", "delete"));
        btn.put("update", new ToolBar("SỬA", "edit.svg", "update"));
        btn.put("detail", new ToolBar("CHI TIẾT", "detail.svg", "view"));
    }

    public void refreshQuyen() {
        btn.forEach((action, toolBar) -> {
            toolBar.setVisible(SessionManager.checkQuyenCurrentUser(machucnang,action));
        });
    }

    private void initComponent() {
        this.setBackground(Color.WHITE);
        this.setRollover(true);
        this.setFloatable(false);

    }

    // getter setter
    public HashMap<String, ToolBar> getBtn() {
        return btn;
    }

    public void setBtn(HashMap<String, ToolBar> btn) {
        this.btn = btn;
    }
}
