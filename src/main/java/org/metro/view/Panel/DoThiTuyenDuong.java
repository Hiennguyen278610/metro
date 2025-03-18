package org.metro.view.Panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

public class DoThiTuyenDuong extends JPanel {
    JLabel back;

    public DoThiTuyenDuong() {
        super();
        setLayout(new BorderLayout());
        initComponent();
    }

    public void initComponent() {
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(null);
        headerPanel.setBackground(Color.white);
        headerPanel.setPreferredSize(new Dimension(900, 50));
        this.add(headerPanel, BorderLayout.NORTH);

        JLabel title = new JLabel("ĐỒ THỊ TUYẾN ĐƯỜNG");
        title.setFont(new Font("Tahoma", Font.BOLD, 20));
        title.setBounds(320, 10, 300, 30);
        headerPanel.add(title, JLabel.CENTER);

        FlatSVGIcon backIcon = new FlatSVGIcon(getClass().getResource("/svg/arrow-back.svg")).derive(30, 30);
        back = new JLabel(backIcon);
        back.setBounds(10, 10, 30, 30);
        headerPanel.add(back);

        JLabel backText = new JLabel("Quay lại");
        backText.setFont(new Font("Tahoma", Font.PLAIN, 16));
        backText.setBounds(50, 10, 100, 30);
        headerPanel.add(backText);

        mxGraph graph = new mxGraph();
        Object parent = graph.getDefaultParent();
        graph.getModel().beginUpdate();

        // Vô hiệu hóa chỉnh sửa
        graph.setCellsEditable(false);
        graph.setCellsMovable(false);
        graph.setCellsResizable(false);
        graph.setCellsDisconnectable(false);
        graph.setCellsSelectable(false);

        try {
            Object v1 = graph.insertVertex(parent, null, "Bến Thành", 100, 100, 80, 80, "shape=ellipse");
            Object v2 = graph.insertVertex(parent, null, "World!", 240, 150, 80, 30);
            graph.insertEdge(parent, null, "Edge", v1, v2, "endArrow=none");
        } finally {
            graph.getModel().endUpdate();
        }

        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        graphComponent.setConnectable(false);
        graphComponent.getGraphHandler().setCloneEnabled(false);
        graphComponent.getGraphHandler().setImagePreview(false);

        add(graphComponent, BorderLayout.CENTER);
    }

    public JLabel getBack() {
        return back;
    }
}
