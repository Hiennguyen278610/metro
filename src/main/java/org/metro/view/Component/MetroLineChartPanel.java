package org.metro.view.Component;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.DateTickUnitType;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.*;
import org.metro.model.ThongKeModel;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.function.Supplier;
import java.util.function.Function;

public class MetroLineChartPanel extends MetroChartPanel {
    private final TimeSeriesCollection dataset;
    private final Supplier<List<ThongKeModel>> dataSupplier;
    private final Function<String, Date> dateParser;
    private final String period;

    public MetroLineChartPanel(String title,
                               String timeAxisLabel,
                               String valueAxisLabel,
                               Supplier<List<ThongKeModel>> dataSupplier,
                               Function<String, Date> dateParser,
                               String period) {
        super(createLineChart(title, timeAxisLabel, valueAxisLabel, period), title);

        // Tìm ChartPanel con bên trong MetroChartPanel
        ChartPanel cp = findChartPanel(this);
        if (cp == null) {
            throw new IllegalStateException("ChartPanel not found in MetroChartPanel");
        }

        // Lấy dataset từ chart
        XYPlot plot = (XYPlot) cp.getChart().getPlot();
        this.dataset = (TimeSeriesCollection) plot.getDataset();

        this.dataSupplier = dataSupplier;
        this.dateParser   = dateParser;
        this.period       = period;

        refreshData();
    }

    private static JFreeChart createLineChart(String title,
                                              String timeAxisLabel,
                                              String valueAxisLabel,
                                              String period) {
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                title, timeAxisLabel, valueAxisLabel,
                dataset, true, true, false
        );

        XYPlot plot = chart.getXYPlot();
        DateAxis dateAxis = (DateAxis) plot.getDomainAxis();
        switch (period.toUpperCase()) {
            case "YEAR" -> {
                dateAxis.setDateFormatOverride(new SimpleDateFormat("yyyy"));
                dateAxis.setTickUnit(new DateTickUnit(DateTickUnitType.YEAR, 1));
            }
            case "MONTH" -> {
                dateAxis.setDateFormatOverride(new SimpleDateFormat("MM-yyyy"));
                dateAxis.setTickUnit(new DateTickUnit(DateTickUnitType.MONTH, 10));
            }
            default -> { // "DAY"
                dateAxis.setDateFormatOverride(new SimpleDateFormat("dd-MM"));
                dateAxis.setTickUnit(new DateTickUnit(DateTickUnitType.DAY, 180)); // Cách 5 ngày 1 nhãn để tránh dày
            }
        }


        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) plot.getRenderer();
        renderer.setDefaultShapesVisible(true);
        renderer.setDefaultShapesFilled(true);

        return chart;
    }

    public void refreshData() {
        dataset.removeAllSeries();
        TimeSeries series = new TimeSeries("Hành khách");

        for (ThongKeModel m : dataSupplier.get()) {
            Date d = dateParser.apply(m.getLabel());
            RegularTimePeriod p;
            switch (period) {
                case "MONTH": p = new Month(d); break;
                case "YEAR":  p = new Year(d);  break;
                default:      p = new Day(d);
            }
            series.addOrUpdate(p, m.getValue());
        }
        dataset.addSeries(series);
    }


    private ChartPanel findChartPanel(Container c) {
        for (Component comp : c.getComponents()) {
            if (comp instanceof ChartPanel) {
                return (ChartPanel) comp;
            }
            if (comp instanceof Container) {
                ChartPanel cp = findChartPanel((Container) comp);
                if (cp != null) return cp;
            }
        }
        return null;
    }
}
