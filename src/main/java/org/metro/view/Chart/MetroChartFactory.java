package org.metro.view.Chart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;
import org.metro.model.ThongKeBaCotModel;
import org.metro.model.ThongKeModel;
import org.metro.service.ThongKeService;
import org.metro.view.Component.MetroBarChartPanel;
import org.metro.view.Component.MetroLineChartPanel;
import org.metro.view.Component.MetroPieChartPanel;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;
import java.util.function.Function;
import java.util.function.Supplier;

public class MetroChartFactory {
    private final ThongKeService svc;

    public MetroChartFactory(ThongKeService svc) {
        this.svc = svc;
    }

    // Tọa biểu đồ cột cơ bản do dữ liệu nguồn
    public JPanel createBarChart(String title, String timeAxisLabel, String valueAxisLabel, Supplier<List<ThongKeModel>> dataSupplier) {
        return new MetroBarChartPanel(title, timeAxisLabel, valueAxisLabel, dataSupplier);
    }
    // Biểu đồ tròn
    public JPanel createPieChart(String title, Supplier<List<ThongKeModel>> dataSupplier) {
        return new MetroPieChartPanel(title, dataSupplier, false);
    }
    // Biểu đồ đường
    public JPanel createLineChart(String title,
                                  String timeAxisLabel,
                                  String valueAxisLabel,
                                  Supplier<List<ThongKeModel>> dataSupplier,
                                  Function<String, Date> dateParser,
                                  String period) {
        return new MetroLineChartPanel(
                title, timeAxisLabel, valueAxisLabel,
                dataSupplier, dateParser, period
        );
    }

    // Biểu đồ cột
    public JPanel createCustomerByRouteChart(){
        return createBarChart("Số lượng hành khách theo tuyến", "Tuyến", "Số lượng hành khách", svc::getCustomerByRoute);
    }
    // Biểu đồ tròn
    public JPanel createCustomerByPeakOffPeekChart(){
        return createPieChart("Hành khách theo giờ cao điểm / thấp điểm", svc::getCustomerByPeakOffPeak);

    }
    // Biểu đồ đường

    //
    public JPanel createOnTimeVsDelayedChart(){
        return createBarChart("Số chuyến đúng giờ vs trễ giờ", "Trạng thái", "Số chuyến đi", svc::getOnTimeVsDelayed);
    }
    public JPanel createTrainStatusChart(){
        return createPieChart("Tỷ lệ tình trạng hoạt động của tàu", svc::getTrainStatus);
    }
    public JPanel createFinanceByPeriodPanel(String period) {
        ChartPanel cp = new ChartPanel(
                createFinanceByPeriodChart(period, () -> svc.getFinanceByPeriod(period))
        );
        cp.setPreferredSize(new Dimension(600, 400));
        return cp;
    }

    public JFreeChart createFinanceByPeriodChart(String period, Supplier<List<ThongKeBaCotModel>> sup) {
        DefaultCategoryDataset ds = new DefaultCategoryDataset();
        for (ThongKeBaCotModel m : sup.get()) {
            ds.addValue(m.getCost(),    "Chi phí",   m.getLabel());
            ds.addValue(m.getRevenue(), "Doanh thu", m.getLabel());
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Tài chính theo " + period,
                period, "VNĐ",
                ds,
                PlotOrientation.VERTICAL,
                true, true, false
        );

        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setRangeGridlinePaint(new Color(0xDDDDDD));

        CategoryAxis domainAxis = plot.getDomainAxis();

        if (period.equals("DAY")){
            domainAxis.setCategoryLabelPositions(
                    CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 4.0)
            );
        }



        domainAxis.setMaximumCategoryLabelWidthRatio(1.0f);

        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setItemMargin(0.1);
        renderer.setBarPainter(new StandardBarPainter());  // Quan trọng để gradient hoạt động
        renderer.setShadowVisible(false);

        // Gradient màu cho từng series
        GradientPaint costGradient = new GradientPaint(0f, 0f, new Color(255, 102, 102), 0f, 300f, new Color(220, 53, 69)); // đỏ chuyển
        GradientPaint revenueGradient = new GradientPaint(0f, 0f, new Color(102, 255, 178), 0f, 300f, new Color(40, 167, 69)); // xanh chuyển

        renderer.setSeriesPaint(0, costGradient);   // Chi phí
        renderer.setSeriesPaint(1, revenueGradient); // Doanh thu

        return chart;
    }


    // --- Biểu đồ doanh thu & chi phí theo tuyến ---
    public JPanel createRevenueByRoutePanel() {
        return createBarChart("Doanh thu theo tuyến", "Tuyến", "VNĐ", svc::getRevenueByRoute);
    }

    public JPanel createCostByRoutePanel() {
        return createBarChart("Chi phí vận hành theo tuyến", "Tuyến", "VNĐ", svc::getOperatingCostByRoute);
    }
    public JPanel createStationEntriesChart(){
        return createBarChart("Lượt hành khách vào trạm", "Trạm", "Lượng khách vào", svc::getStationEntries);
    }
}