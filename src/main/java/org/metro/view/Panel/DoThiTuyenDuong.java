package org.metro.view.Panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.metro.DAO.TramDAO;
import org.metro.DAO.TuyenDAO;
import org.metro.model.TramModel;
import org.metro.model.TuyenDuongModel;

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

        List<TramModel> tramList = new TramDAO().selectAll();
        List<TuyenDuongModel> tuyenList = new TuyenDAO().selectAll();
        Map<Integer, Object> luuTram = new HashMap<>();

        try {
            for (TramModel tram : tramList) {
                Object hihi = graph.insertVertex(parent, null, tram.getTentram(), tram.getX(), tram.getY(), 80, 80,
                        "shape=ellipse");
                luuTram.put(tram.getMatram(), hihi);
            }

            for (TuyenDuongModel tuyen : tuyenList) {
                // System.out.println(tuyen.getTramdau() + " " + tuyen.getTramdich());
                Object trambatdau = luuTram.get(tuyen.getTramdau());
                Object tramketthuc = luuTram.get(tuyen.getTramdich());

                if (trambatdau != null && tramketthuc != null) {
                    graph.insertEdge(parent, null, "", trambatdau, tramketthuc,
                            "sourcePerimeterSpacing=0;targetPerimeterSpacing=0;endArrow=none;");
                }
            }

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
