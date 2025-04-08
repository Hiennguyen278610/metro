package org.metro.view.Component;

import org.metro.service.PhanQuyenService;

import javax.swing.*;

import java.awt.*;
import java.util.HashMap;

public class  MainFunction extends JToolBar {
    public HashMap<String, ToolBar> btn = new HashMap<>();
    private PhanQuyenService pqs = new PhanQuyenService();

    public MainFunction() {
        initData();
        initComponent();
    }

    public MainFunction(String[] actions) {
        pqs = new PhanQuyenService();
        this.setBackground(Color.WHITE);
        this.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));
        for (String action : actions) {
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
        this.add(Box.createHorizontalStrut(10));
        for (ToolBar toolBar : btn.values()) {
            this.add(toolBar);
            this.add(Box.createHorizontalStrut(10));
        }
    }

    public void initData() {
        btn.put("create", new ToolBar("THÊM", "add.svg", "create"));
        btn.put("delete", new ToolBar("XÓA", "delete.svg", "delete"));
        btn.put("update", new ToolBar("SỬA", "edit.svg", "update"));
        btn.put("detail", new ToolBar("CHI TIẾT", "detail.svg", "view"));
    }

    private void initComponent() {
        this.setBackground(Color.WHITE);
        this.setRollover(true);
        this.setFloatable(false);
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        for (ToolBar button : btn.values()) {
            button.setAlignmentY(CENTER_ALIGNMENT);
            this.add(button);
            this.add(Box.createHorizontalStrut(10));
        }
        if (getComponentCount() > 0) {
            remove(getComponentCount() - 1);
        }
        // Thêm đường viền nhỏ
        this.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
    }

    // getter setter
    public HashMap<String, ToolBar> getBtn() {
        return btn;
    }

    public void setBtn(HashMap<String, ToolBar> btn) {
        this.btn = btn;
    }
}
