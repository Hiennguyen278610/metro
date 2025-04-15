package org.metro.view.Component;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.metro.model.ThongKeModel;

import java.util.List;
import java.util.function.Supplier;


public class MetroBarChartPanel extends MetroChartPanel {
    private final DefaultCategoryDataset dataset;
    private final Supplier<List<ThongKeModel>> dataSupplier;
    private final String seriesKey;

    /**
     * @param title           Tiêu đề biểu đồ
     * @param categoryAxis    Nhãn trục danh mục (category axis)
     * @param valueAxis       Nhãn trục giá trị (value axis)
     * @param dataSupplier    Nguồn dữ liệu trả về List<ThongKeModel>
     */
    public MetroBarChartPanel(
            String title,
            String categoryAxis,
            String valueAxis,
            Supplier<List<ThongKeModel>> dataSupplier
    ) {
        // Tạo dataset và chart sử dụng cùng 1 dataset
        super(createBarChart(title, categoryAxis, valueAxis, new DefaultCategoryDataset()), title);
        this.dataset = (DefaultCategoryDataset) ((CategoryPlot) chart.getPlot()).getDataset();
        this.dataSupplier = dataSupplier;
        this.seriesKey = valueAxis;
        // Load dữ liệu ban đầu
        refreshData();
    }

    private static JFreeChart createBarChart(
            String title,
            String categoryAxisLabel,
            String valueAxisLabel,
            DefaultCategoryDataset dataset
    ) {
        JFreeChart chart = ChartFactory.createBarChart(
                title,
                categoryAxisLabel,
                valueAxisLabel,
                dataset,
                PlotOrientation.VERTICAL,
                true,  // legend
                false,  // tooltips
                false  // URLs
        );

        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setDataset(dataset);

        // Tinh chỉnh trục
        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryMargin(0.2);
        domainAxis.setLowerMargin(0.05);
        domainAxis.setUpperMargin(0.05);

        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        // Renderer
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);
        renderer.setItemMargin(0.1);

        return chart;
    }

    @Override
    public void refreshData() {
        // Xóa dữ liệu cũ
        dataset.clear();
        List<ThongKeModel> dataList = dataSupplier.get();
        if (dataList != null) {
            for (ThongKeModel item : dataList) {
                dataset.addValue(item.getValue(), seriesKey, item.getLabel());
            }
        }
        // Thông báo chart thay đổi để repaint
        chart.fireChartChanged();
    }
}
