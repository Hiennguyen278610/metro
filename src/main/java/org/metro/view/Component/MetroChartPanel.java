package org.metro.view.Component;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;


public class MetroChartPanel extends JPanel {
    protected final JFreeChart chart;
    private final ChartPanel chartPanel;
    private final String title;

    public MetroChartPanel(JFreeChart chart, String title) {
        this.chart = chart;
        this.title = title;
        setLayout(new BorderLayout());
        setBackground(new Color(248, 249, 250));

        // Card container
        JPanel card = new JPanel(new BorderLayout(0, 5));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(12, 12, 12, 12),
                BorderFactory.createLineBorder(new Color(248, 249, 250), 1, true)
        ));

        // Title label
        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(4, 0, 8, 0));
        card.add(titleLabel, BorderLayout.NORTH);

        applyModernStyling(chart);

        // Chart panel
        chartPanel = new ChartPanel(chart);
        chartPanel.setMouseWheelEnabled(true);
        chartPanel.setDomainZoomable(true);
        chartPanel.setRangeZoomable(true);
        chartPanel.setOpaque(false);
        chartPanel.setPreferredSize(new Dimension(600, 400));
        card.add(chartPanel, BorderLayout.CENTER);

        // Toolbar
        JPanel toolBar = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        toolBar.setOpaque(false);
        JButton exportBtn = new JButton("Export PNG");
        exportBtn.setToolTipText("Export chart as PNG");
        exportBtn.addActionListener(this::onExport);
        toolBar.add(exportBtn);
        card.add(toolBar, BorderLayout.SOUTH);

        add(card, BorderLayout.CENTER);
    }

    private void applyModernStyling(JFreeChart chart) {
        chart.setBackgroundPaint(Color.WHITE);
        if (chart.getLegend() != null) {
            chart.getLegend().setBackgroundPaint(Color.WHITE);
            chart.getLegend().setFrame(BlockBorder.NONE);
        }
        Plot plot = chart.getPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setOutlinePaint(null);

        GradientPaint[] colors = {
                new GradientPaint(0f, 0f, new Color(52, 152, 219), 0f, 300f, new Color(185, 41, 144)),
                new GradientPaint(0f, 0f, new Color(46, 204, 113), 0f, 300f, new Color(39, 174, 96)),
                new GradientPaint(0f, 0f, new Color(231, 76, 60),  0f, 300f, new Color(192, 57, 43)),
                new GradientPaint(0f, 0f, new Color(155, 89, 182), 0f, 300f, new Color(142, 68, 173)),
                new GradientPaint(0f, 0f, new Color(241, 196, 15), 0f, 300f, new Color(243, 156, 18)),
                new GradientPaint(0f, 0f, new Color(52, 73, 94),   0f, 300f, new Color(44, 62, 80)),
                new GradientPaint(0f, 0f, new Color(26, 188, 156), 0f, 300f, new Color(22, 160, 133)),
                new GradientPaint(0f, 0f, new Color(230, 126, 34), 0f, 300f, new Color(211, 84, 0))
        };

        if (plot instanceof CategoryPlot) {
            CategoryPlot cp = (CategoryPlot) plot;
            CategoryAxis domainAxis = cp.getDomainAxis();
            domainAxis.setMaximumCategoryLabelLines(2);
            cp.setRangeGridlinePaint(new Color(0xF0F0F0));
            cp.setDomainGridlinesVisible(false);
            if (cp.getRenderer() instanceof BarRenderer) {
                BarRenderer br = (BarRenderer) cp.getRenderer();
                for (int i = 0; i < colors.length; i++) br.setSeriesPaint(i, colors[i]);
                br.setBarPainter(new org.jfree.chart.renderer.category.StandardBarPainter());
                br.setShadowVisible(false);
            }
        } else if (plot instanceof XYPlot) {
            XYPlot xy = (XYPlot) plot;
            xy.setDomainGridlinePaint(new Color(0xF0F0F0));
            xy.setRangeGridlinePaint(new Color(0xF0F0F0));
            if (xy.getRenderer() instanceof XYLineAndShapeRenderer) {
                XYLineAndShapeRenderer r = (XYLineAndShapeRenderer) xy.getRenderer();
                for (int i = 0; i < colors.length; i++) {
                    r.setSeriesPaint(i, colors[i]);
                    r.setSeriesStroke(i, new BasicStroke(2.0f));
                }
            }
        } else if (plot instanceof PiePlot) {
            PiePlot p = (PiePlot) plot;
            p.setShadowPaint(null);
            p.setSimpleLabels(true);
            p.setLabelBackgroundPaint(new Color(255, 255, 255, 200));
            for (int i = 0; i < colors.length; i++) {
                p.setSectionPaint(i, colors[i]);
            }
        }
    }

    private void onExport(ActionEvent e) {
        JFileChooser fc = new JFileChooser();
        fc.setDialogTitle("Lưu biểu đồ dưới dạng hình ảnh PNG");
        fc.setSelectedFile(new File(title.replaceAll("\\s+", "_") + ".png"));
        if (fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            try {
                ChartUtils.saveChartAsPNG(file, chart, chartPanel.getWidth(), chartPanel.getHeight());
                JOptionPane.showMessageDialog(this,
                        "Biểu đồ đã được lưu tới " + file.getAbsolutePath(),
                        "Export Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this,
                        "Lỗi : " + ex.getMessage(),
                        "Export Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void refreshData() {
        chart.fireChartChanged();
    }
}