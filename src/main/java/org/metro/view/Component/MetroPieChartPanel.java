package org.metro.view.Component;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.RingPlot;
import org.jfree.chart.ui.RectangleEdge;
import org.jfree.chart.ui.RectangleInsets;
import org.jfree.data.general.DefaultPieDataset;
import org.metro.model.ThongKeModel;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PieLabelLinkStyle;

import java.awt.*;
import java.text.DecimalFormat;
import java.util.List;
import java.util.function.Supplier;

/**
 * Panel hiển thị biểu đồ tròn hoặc vòng (ring) với dữ liệu động và giao diện hiện đại.
 */
public class MetroPieChartPanel extends MetroChartPanel {
    private final DefaultPieDataset<String> dataset;
    private final Supplier<List<ThongKeModel>> dataSupplier;

    // Bảng màu hiện đại
    private static final Color[] MODERN_COLORS = {
            new Color(85, 180, 244),  // Bright Blue
            new Color(60, 244, 136),  // Emerald Green
            new Color(248, 102, 89),   // Coral Red
            new Color(213, 113, 255),  // Purple
            new Color(241, 196, 15),  // Yellow
            new Color(52, 73, 94),    // Dark Blue-Gray
            new Color(26, 188, 156),  // Turquoise
            new Color(230, 126, 34)   // Orange
    };


    public MetroPieChartPanel(String title, Supplier<List<ThongKeModel>> dataSupplier, boolean isRing) {
        super(createChart(title, isRing), title);
        PiePlot plot = (PiePlot) chart.getPlot();
        this.dataset = (DefaultPieDataset<String>) plot.getDataset();
        this.dataSupplier = dataSupplier;

        setBackground(new Color(248, 249, 250));
        chart.setPadding(new RectangleInsets(10, 10, 10, 10));

        // Tăng size và tự lệch trái do khoảng cách trong nhỏ
        plot.setInteriorGap(0.02);
        refreshData();
    }

    private static JFreeChart createChart(String title, boolean isRing) {
        DefaultPieDataset<String> ds = new DefaultPieDataset<>();
        JFreeChart chart;
        if (isRing) {
            chart = ChartFactory.createRingChart(title, ds, true, false, false);
        } else {
            chart = ChartFactory.createPieChart(title, ds, true, false, false);
        }

        PiePlot plot = (PiePlot) chart.getPlot();
        customizePlotStyle(plot);

        // Giao diện chung
        chart.setBackgroundPaint(new Color(248, 249, 250));
        chart.getTitle().setFont(new Font("SansSerif", Font.BOLD, 16));
        chart.getTitle().setPaint(new Color(44, 62, 80));

        // Chú thích bên phải
        chart.getLegend().setPosition(RectangleEdge.RIGHT);
        chart.getLegend().setBackgroundPaint(new Color(255, 255, 255, 200));
        chart.getLegend().setItemFont(new Font("SansSerif", Font.PLAIN, 12));

        return chart;
    }

    private static void customizePlotStyle(PiePlot plot) {
        // Màu sắc
        for (int i = 0; i < MODERN_COLORS.length; i++) {
            plot.setSectionPaint(i, MODERN_COLORS[i]);
        }

        // Nhãn
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{2}", new DecimalFormat("0"), new DecimalFormat("0.0%")));
        plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
        plot.setLabelBackgroundPaint(new Color(0, 0, 0, 0));
        plot.setLabelOutlinePaint(null);
        plot.setLabelShadowPaint(null);
        plot.setLabelLinkStyle(PieLabelLinkStyle.QUAD_CURVE);
        plot.setLabelLinkPaint(new Color(100, 100, 100));

        // Giao diện
        plot.setShadowPaint(null);
        plot.setBackgroundPaint(Color.WHITE);
        plot.setOutlineVisible(false);
        plot.setLabelGap(0.02);
    }


    @Override
    public void refreshData() {
        dataset.clear();
        List<ThongKeModel> dataList = dataSupplier.get();
        if (dataList != null) {
            for (ThongKeModel model : dataList) {
                dataset.setValue(model.getLabel(), model.getValue());
            }
        }
        if (dataset.getKeys().isEmpty()) {
            PiePlot plot = (PiePlot) chart.getPlot();
            plot.setNoDataMessage("Không có dữ liệu");
            plot.setNoDataMessageFont(new Font("SansSerif", Font.ITALIC, 14));
            plot.setNoDataMessagePaint(new Color(150, 150, 150));
        }
        chart.fireChartChanged();
    }

    public void toggleLabels(boolean show) {
        PiePlot plot = (PiePlot) chart.getPlot();
        if (!show) plot.setLabelGenerator(null);
        else customizePlotStyle(plot);
        chart.fireChartChanged();
    }


    public void updateColors(Color[] colors) {
        if (colors == null || colors.length == 0) return;
        PiePlot plot = (PiePlot) chart.getPlot();
        int i = 0;
        for (Comparable<?> key : dataset.getKeys()) {
            plot.setSectionPaint(key, colors[i % colors.length]);
            i++;
        }
        chart.fireChartChanged();
    }
}
