package org.metro.view.Panel;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import org.jfree.chart.ChartPanel;
import org.metro.controller.ThongKeController;
import org.metro.model.ThongKeModel;
import org.metro.service.ThongKeService;
import org.metro.view.Chart.MetroChartFactory;
import org.metro.view.Component.MetroChartPanel;
import org.metro.view.Component.MetroLineChartPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThongKe extends JPanel {
    private static final int FONT_SIZE_TITLE = 24;
    private static final int FONT_SIZE_METRIC = 20;
    private static final int DIVIDER_LOCATION = 400;
    private static final double SPLIT_WEIGHT = 0.6;

    private CardLayout cards;
    private JPanel cardPanel;
    private final ThongKeController ctrl = new ThongKeController();
    private final ThongKeService svc = new ThongKeService();
    private final MetroChartFactory factory;
//    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private JButton selectedButton = null;

    public ThongKe() {
        factory = new MetroChartFactory(svc);
        cards = new CardLayout();
        cardPanel = new JPanel(cards);
        initComponents();
//        scheduler.scheduleAtFixedRate(this::refreshAllData, 5, 5, TimeUnit.MINUTES);
    }


    private void initComponents() {
        setLayout(new BorderLayout(0, 0));
        add(createNavigationPanel(), BorderLayout.NORTH);
        add(cardPanel, BorderLayout.CENTER);

        // Add all sub-panels to CardLayout
        cardPanel.add(createOverViewPanel(), NavItem.OVERVIEW.cardName);
        cardPanel.add(createCustomersPanel(), NavItem.CUSTOMERS.cardName);
        cardPanel.add(createRoutePanel(), NavItem.ROUTES.cardName);
        cardPanel.add(createStationPanel(), NavItem.STATIONS.cardName);
        cardPanel.add(createTrainPanel(), NavItem.TRAINS.cardName);
        cardPanel.add(createRevenuePanel(), NavItem.REVENUE.cardName);
    }

    private JPanel createNavigationPanel() {
        JPanel nav = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(
                        0, 0, Color.decode("#93BFCF"),
                        0, getHeight(), new Color(240, 240, 240)
                );
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        nav.setLayout(new BoxLayout(nav, BoxLayout.X_AXIS));
        nav.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        nav.add(Box.createHorizontalGlue());
        for (NavItem item : NavItem.values()) {
            JButton btn = createNavButton(item.text, item.cardName);
            nav.add(btn);
            nav.add(Box.createHorizontalStrut(5));
        }

//        JButton refreshBtn = new JButton();
//        refreshBtn.setFocusPainted(false);
//        refreshBtn.addActionListener(e -> refreshAllData());
//        refreshBtn.setIcon(new FlatSVGIcon(getClass().getResource("/svg/refresh.svg")).derive(30, 30));
//
//        nav.add(refreshBtn);
        nav.add(Box.createHorizontalGlue());
        return nav;
    }

    private JButton createNavButton(String text, String cardName) {
        JButton btn = new JButton(text) {
            private boolean hover = false;
            private float animationProgress = 0f;
            private Timer hoverTimer;

            {
                setOpaque(false);
                setContentAreaFilled(false);
                setBorderPainted(false);
                setFocusPainted(false);
                setFont(new Font("Arial", Font.BOLD, FONT_SIZE_METRIC));
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                setMargin(new Insets(0, 0, 0, 0));

                // Hover animation
                addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        if (hoverTimer != null && hoverTimer.isRunning()) hoverTimer.stop();
                        hover = true;
                        hoverTimer = new Timer(15, evt -> {
                            animationProgress += 0.05f;
                            if (animationProgress >= 1f) {
                                animationProgress = 1f;
                                ((Timer) evt.getSource()).stop();
                            }
                            repaint();
                        });
                        hoverTimer.start();
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        if (!Boolean.TRUE.equals(getClientProperty("selected"))) {
                            if (hoverTimer != null && hoverTimer.isRunning()) hoverTimer.stop();
                            hover = false;
                            hoverTimer = new Timer(15, evt-> {
                                animationProgress -= 0.05f;
                                if (animationProgress <= 0f) {
                                    animationProgress = 0f;
                                    ((Timer) evt.getSource()).stop();
                                }
                                repaint();
                            });
                            hoverTimer.start();
                        }
                    }
                });

                addActionListener(e -> {
                    // Fix for deselecting previous button
                    if (selectedButton != null) {
                        selectedButton.putClientProperty("selected", false);
                        if (selectedButton != this) {
                            if (selectedButton instanceof JButton) {
                                try {
                                    Field field = selectedButton.getClass().getDeclaredField("animationProgress");
                                    field.setAccessible(true);
                                    field.set(selectedButton, 0f);
                                } catch (Exception ex) {

                                    selectedButton.repaint();
                                }
                            }
                        }
                        selectedButton.repaint();
                    }

                    selectedButton = this;
                    putClientProperty("selected", true);
                    animationProgress = 1f;
                    repaint();
                    cards.show(cardPanel, cardName);
                });
            }

            @Override
            public void paintComponent(Graphics g) {
                if(hover || Boolean.TRUE.equals(getClientProperty("selected"))) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    float alpha = Boolean.TRUE.equals(getClientProperty("selected")) ? 1f : animationProgress;
                    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
                    GradientPaint gp = new GradientPaint(
                            0, 0, new Color(80, 144, 255),
                            0, getHeight(),  new Color(234, 234, 234)
                    );
                    g2.setPaint(gp);
                    g2.fillRect(0, 0, getWidth(), getHeight());
                    g2.dispose();
                }

                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                g2.setFont(getFont());
                FontMetrics fm = g2.getFontMetrics();
                String buttonText = getText();
                int textWidth = fm.stringWidth(buttonText);
                int textHeight = fm.getHeight();

                int x = (getWidth() - textWidth) / 2;
                int y = (getHeight() - textHeight) / 2 + fm.getAscent();

                // Set text color
                if (Boolean.TRUE.equals(getClientProperty("selected"))) {
                    g2.setColor(new Color(0, 40, 53));
                } else {
                    g2.setColor(getForeground());
                }

                g2.drawString(buttonText, x, y);
                g2.dispose();
            }
        };

        return btn;
    }

    private JPanel createOverViewPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel headerPanel = new JPanel(new BorderLayout());
        JLabel title = new JLabel("TỔNG QUAN THỐNG KÊ HỆ THỐNG METRO", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, FONT_SIZE_TITLE));
        headerPanel.add(title, BorderLayout.CENTER);

        long totalRoutes = svc.getTotalRoutes();
        long tripsToday = svc.getTripsToday();
        double revenueToday = svc.getRevenueByPeriod("DAY");
        double costToday    = svc.getCostByPeriod("DAY");
        double profitToday  = svc.getProfitByPeriod("DAY");

        long customersToday = svc.getCustomerByPeriod("DAY").stream().mapToLong(ThongKeModel::getValue).sum();

        JPanel metricsPanel = new JPanel(new GridLayout(2, 2, 15, 30));

        metricsPanel.add(createMetricPanel("Tổng số tuyến", String.valueOf(totalRoutes), new Color(70, 130, 180)));
        metricsPanel.add(createMetricPanel("Chuyến hôm nay", String.valueOf(tripsToday), new Color(46, 139, 87)));
        metricsPanel.add(createMetricPanel("Khách hôm nay", String.valueOf(customersToday), new Color(255, 127, 80)));
        metricsPanel.add(createMetricPanel("Doanh thu hôm nay", String.format("%.0f VND", revenueToday), new Color(220, 20, 60)));

        headerPanel.add(metricsPanel, BorderLayout.SOUTH);
        panel.add(headerPanel, BorderLayout.CENTER);


        return panel;
    }

    private JPanel createMetricPanel(String name, String value, Color color) {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(color, 2, true),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        JLabel labelName = new JLabel(name, JLabel.CENTER);
        labelName.setFont(new Font("Arial", Font.BOLD, FONT_SIZE_METRIC));
        JLabel labelValue = new JLabel(value, JLabel.CENTER);
        labelValue.setFont(new Font("Arial", Font.BOLD, FONT_SIZE_METRIC + 2));
        labelValue.setForeground(color);

        panel.add(labelName, BorderLayout.NORTH);
        panel.add(labelValue, BorderLayout.CENTER);
        return panel;
    }

    private JTabbedPane createCustomersPanel() {
        JTabbedPane tabs = new JTabbedPane();

        // Tab: Theo tuyến
        JPanel routePanel = new JPanel(new BorderLayout(10, 10));
        routePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JTable routeTable = new JTable();
        ctrl.loadCustomerByRoute(routeTable);
        JSplitPane routeSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT, factory.createCustomerByRouteChart(), new JScrollPane(routeTable));
        routeSplit.setResizeWeight(SPLIT_WEIGHT);
        routeSplit.setDividerLocation(DIVIDER_LOCATION);
        routePanel.add(routeSplit, BorderLayout.CENTER);
        tabs.addTab("Theo tuyến", routePanel);

        // Tab: Theo trạm
        JPanel stationPanel = new JPanel(new BorderLayout(10, 10));
        stationPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JTable stationTable = new JTable();
        ctrl.loadCustomerByStation(stationTable);
        JSplitPane stationSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT, factory.createStationEntriesChart(), new JScrollPane(stationTable));
        stationSplit.setResizeWeight(SPLIT_WEIGHT);
        stationSplit.setDividerLocation(DIVIDER_LOCATION);
        stationPanel.add(stationSplit, BorderLayout.CENTER);
        tabs.addTab("Theo trạm", stationPanel);

        // Tab: Cao/Thấp điểm
        JPanel peakPanel = new JPanel(new BorderLayout(10, 10));
        peakPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JTable peakTable = new JTable();
        ctrl.loadCustomerByPeakOffPeak(peakTable);
        JSplitPane peakSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT, factory.createCustomerByPeakOffPeekChart(), new JScrollPane(peakTable));
        peakSplit.setResizeWeight(SPLIT_WEIGHT);
        peakSplit.setDividerLocation(DIVIDER_LOCATION);
        peakPanel.add(peakSplit, BorderLayout.CENTER);
        tabs.addTab("Cao/Thấp điểm", peakPanel);

        // Tab: Ngày/Tuần/Tháng
        JPanel periodPanel = new JPanel(new BorderLayout(10, 10));
        periodPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JComboBox<String> periodCombo = new JComboBox<>(new String[]{"DAY", "MONTH", "YEAR"});
        JButton applyBtn = new JButton("Apply");
        filterPanel.add(new JLabel("Period: "));
        filterPanel.add(periodCombo);
        filterPanel.add(applyBtn);

        JTable periodTable = new JTable();
        ctrl.loadCustomerByPeriod(periodTable, "DAY");
        JScrollPane periodScroll = new JScrollPane(periodTable);
        JPanel chartContainer = new JPanel(new BorderLayout());
        chartContainer.add(createCustomerChartForPeriod("DAY"), BorderLayout.CENTER);

        applyBtn.addActionListener(e -> {
            String selectedPeriod = (String) periodCombo.getSelectedItem();
            ctrl.loadCustomerByPeriod(periodTable, selectedPeriod);
            chartContainer.removeAll();
            chartContainer.add(createCustomerChartForPeriod(selectedPeriod), BorderLayout.CENTER);
            chartContainer.revalidate();
            chartContainer.repaint();
        });

        JSplitPane periodSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT, chartContainer, periodScroll);
        periodSplit.setResizeWeight(SPLIT_WEIGHT);
        periodSplit.setDividerLocation(DIVIDER_LOCATION);
        periodPanel.add(filterPanel, BorderLayout.NORTH);
        periodPanel.add(periodSplit, BorderLayout.CENTER);
        tabs.addTab("Ngày/Tháng/Năm", periodPanel);

        return tabs;
    }

    private JPanel createCustomerChartForPeriod(String period) {
        String title, timeAxisLabel;
        SimpleDateFormat dateFormat;

        switch (period.toUpperCase()){
            case "MONTH":
                title = "Số lương hành khách theo tháng";
                timeAxisLabel = "Tháng";
                dateFormat = new SimpleDateFormat("yyyy-MM");
                break;
            case "YEAR" :
                title = "Số lượng hành khách theo năm";
                timeAxisLabel = "Năm";
                dateFormat = new SimpleDateFormat("yyyy");
                break;
            default:
                title = "Số lượng hành khách theo ngày";
                timeAxisLabel = "Ngày";
                dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                break;
        }
        return new MetroLineChartPanel(
                title,
                timeAxisLabel,
                "Số lượng hành khách",
                () -> svc.getCustomerByPeriod(period.toUpperCase()),
                label -> {
                    try {
                        return dateFormat.parse(label);
                    }catch (java.text.ParseException ex){
                        return new java.util.Date();
                    }
                },
                period.toUpperCase()
        );
    }

    private JTabbedPane createRoutePanel() {
        JTabbedPane tabs = new JTabbedPane();

        // Tab: Tổng quan
        JPanel overviewPanel = new JPanel(new BorderLayout(10, 10));
        overviewPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JPanel infoPanel = new JPanel(new GridLayout(2, 1, 0, 10));
        infoPanel.add(createMetricPanel("Tổng số tuyến", String.valueOf(ctrl.getTotalRoutes()), new Color(70, 130, 180)));
        infoPanel.add(createMetricPanel("Chuyến hôm nay", String.valueOf(ctrl.getTripsToday()), new Color(46, 139, 87)));
        overviewPanel.add(infoPanel, BorderLayout.NORTH);
        overviewPanel.add(factory.createOnTimeVsDelayedChart(), BorderLayout.CENTER);
        tabs.addTab("Tổng quan", overviewPanel);

        // Tab: Tần suất tuyến
        JPanel freqPanel = new JPanel(new BorderLayout(10, 10));
        freqPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JTable freqTable = new JTable();
        ctrl.loadFrequencyPerRoute(freqTable);
        JSplitPane freqSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT, factory.createBarChart("Tần suất tuyến", "Tuyến", "Số tuyến trên ngày", svc::getFrequencyPerRoute), new JScrollPane(freqTable));
        freqSplit.setResizeWeight(SPLIT_WEIGHT);
        freqSplit.setDividerLocation(DIVIDER_LOCATION);
        freqPanel.add(freqSplit, BorderLayout.CENTER);
        tabs.addTab("Tần suất tuyến", freqPanel);

        // Tab: Đúng/Trễ giờ
        JPanel otdPanel = new JPanel(new BorderLayout(10, 10));
        otdPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JTable otdTable = new JTable();
        ctrl.loadOnTimeVsDelayed(otdTable);
        JSplitPane otdSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT, factory.createOnTimeVsDelayedChart(), new JScrollPane(otdTable));
        otdSplit.setResizeWeight(SPLIT_WEIGHT);
        otdSplit.setDividerLocation(DIVIDER_LOCATION);
        otdPanel.add(otdSplit, BorderLayout.CENTER);
        tabs.addTab("Đúng/Trễ giờ", otdPanel);

        return tabs;
    }

    private JTabbedPane createStationPanel() {
        JTabbedPane tabs = new JTabbedPane();

        // Tab: Lượt vào
        JPanel entriesPanel = new JPanel(new BorderLayout(10, 10));
        entriesPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JTable entriesTable = new JTable();
        ctrl.loadStationEntries(entriesTable);
        JSplitPane entriesSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT, factory.createStationEntriesChart(), new JScrollPane(entriesTable));
        entriesSplit.setResizeWeight(SPLIT_WEIGHT);
        entriesSplit.setDividerLocation(DIVIDER_LOCATION);
        entriesPanel.add(entriesSplit, BorderLayout.CENTER);
        tabs.addTab("Lượt vào", entriesPanel);

        // Tab: Lượt ra
        JPanel exitsPanel = new JPanel(new BorderLayout(10, 10));
        exitsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JTable exitsTable = new JTable();
        ctrl.loadStationExits(exitsTable);
        JSplitPane exitsSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT, factory.createBarChart("Lượt hành khách rời trạm", "Trạm", "Số lượng khách rời", svc::getStationExits), new JScrollPane(exitsTable));
        exitsSplit.setResizeWeight(SPLIT_WEIGHT);
        exitsSplit.setDividerLocation(DIVIDER_LOCATION);
        exitsPanel.add(exitsSplit, BorderLayout.CENTER);
        tabs.addTab("Lượt ra", exitsPanel);

        // Tab: Quá tải
        JPanel overloadPanel = new JPanel(new BorderLayout(10, 10));
        overloadPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JPanel thresholdPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        thresholdPanel.add(new JLabel("Threshold:"));
        JSpinner thresholdSpinner = new JSpinner(new SpinnerNumberModel(50, 10, 1000, 10));
        thresholdPanel.add(thresholdSpinner);
        JButton applyBtn = new JButton("Apply");
        thresholdPanel.add(applyBtn);

        JTable overloadTable = new JTable();
        ctrl.loadOverloadedStations(overloadTable, 50);
        JScrollPane overloadScroll = new JScrollPane(overloadTable);
        JPanel overloadChartContainer = new JPanel(new BorderLayout());
        overloadChartContainer.add(factory.createBarChart("Trạm quá tải (>50)", "Trạm", "Số lượng hành khách", () -> svc.getOverloadedStations(50)), BorderLayout.CENTER);

        applyBtn.addActionListener(e -> {
            int threshold = (int) thresholdSpinner.getValue();
            ctrl.loadOverloadedStations(overloadTable, threshold);
            overloadChartContainer.removeAll();
            overloadChartContainer.add(factory.createBarChart("Trạm quá tải (>" + threshold + ")", "Trạm", "Số lượng hành khách", () -> svc.getOverloadedStations(threshold)), BorderLayout.CENTER);
            overloadChartContainer.revalidate();
            overloadChartContainer.repaint();
        });

        JSplitPane overloadSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT, overloadChartContainer, overloadScroll);
        overloadSplit.setResizeWeight(SPLIT_WEIGHT);
        overloadSplit.setDividerLocation(DIVIDER_LOCATION);
        overloadPanel.add(thresholdPanel, BorderLayout.NORTH);
        overloadPanel.add(overloadSplit, BorderLayout.CENTER);
        tabs.addTab("Quá tải >50", overloadPanel);

        return tabs;
    }

    private JTabbedPane createTrainPanel() {
        JTabbedPane tabs = new JTabbedPane();

        // Tab: Tình trạng
        JPanel statusPanel = new JPanel(new BorderLayout(10, 10));
        statusPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JTable statusTable = new JTable();
        ctrl.loadTrainStatus(statusTable);
        JSplitPane statusSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT, factory.createTrainStatusChart(), new JScrollPane(statusTable));
        statusSplit.setResizeWeight(SPLIT_WEIGHT);
        statusSplit.setDividerLocation(DIVIDER_LOCATION);
        statusPanel.add(statusSplit, BorderLayout.CENTER);
        tabs.addTab("Tình trạng", statusPanel);

        // Tab: Thời gian hoạt động
        JPanel usagePanel = new JPanel(new BorderLayout(10, 10));
        usagePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JTable usageTable = new JTable();
        ctrl.loadTrainUsage(usageTable);
        JSplitPane usageSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT, factory.createBarChart("Thời gian tàu hoạt động", "Mã tàu", "Minutes", svc::getTrainUsage), new JScrollPane(usageTable));
        usageSplit.setResizeWeight(SPLIT_WEIGHT);
        usageSplit.setDividerLocation(DIVIDER_LOCATION);
        usagePanel.add(usageSplit, BorderLayout.CENTER);
        tabs.addTab("Thời gian hoạt động", usagePanel);

        return tabs;
    }

    private JTabbedPane createRevenuePanel() {
        JTabbedPane tabs = new JTabbedPane();

        // Tab 1: Tài chính theo thời gian
        JPanel periodPanel = new JPanel(new BorderLayout(10,10));
        periodPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JPanel ctrlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ctrlPanel.add(new JLabel("Period:"));
        JComboBox<String> combo = new JComboBox<>(new String[]{"DAY","MONTH","YEAR"});
        ctrlPanel.add(combo);
        periodPanel.add(ctrlPanel, BorderLayout.NORTH);

        JPanel chartContainer = new JPanel(new BorderLayout());
        chartContainer.add(factory.createFinanceByPeriodPanel("DAY"), BorderLayout.CENTER);
        periodPanel.add(chartContainer, BorderLayout.CENTER);

        combo.addActionListener(e -> {
            String p = (String) combo.getSelectedItem();
            chartContainer.removeAll();
            chartContainer.add(factory.createFinanceByPeriodPanel(p), BorderLayout.CENTER);
            chartContainer.revalidate();
            chartContainer.repaint();
        });

        tabs.addTab("Theo thời gian", periodPanel);

        // --- Tab 2: Doanh thu theo tuyến ---
        JPanel revenueRoutePanel = new JPanel(new BorderLayout(10,10));
        revenueRoutePanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        revenueRoutePanel.add(factory.createRevenueByRoutePanel(), BorderLayout.CENTER);
        tabs.addTab("Theo tuyến", revenueRoutePanel);

        // --- Tab 3: Chi phí vận hành ---
        JPanel costRoutePanel = new JPanel(new BorderLayout(10,10));
        costRoutePanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        costRoutePanel.add(factory.createCostByRoutePanel(), BorderLayout.CENTER);
        tabs.addTab("Chi phí vận hành", costRoutePanel);

        return tabs;
    }


    private void refreshAllData() {
        SwingUtilities.invokeLater(() -> {
            try {
                refreshCardPanel(cardPanel);
                showRefreshNotification();
            } catch (Exception e) {
                System.err.println("lỗi làm mới dữ liệu: " + e.getMessage());
            }
        });
    }

    private void refreshCardPanel(Container container) {

        for (Component component : container.getComponents()) {
            if (component instanceof MetroChartPanel) {
                ((MetroChartPanel) component).refreshData();
            } else if (component instanceof Container) {
                refreshCardPanel((Container) component);
            }
        }
    }

    private void showRefreshNotification() {
        JWindow notification = new JWindow();
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panel.setBackground(new Color(240, 240, 240));
        JLabel label = new JLabel("Data refreshed");
        label.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panel.add(label);

        notification.add(panel);
        notification.pack();
        Point location = this.getLocationOnScreen();
        notification.setLocation(location.x + getWidth() - notification.getWidth() - 20, location.y + getHeight() - notification.getHeight() - 20);
        notification.setVisible(true);

        Timer timer = new Timer(3000, e -> notification.dispose());
        timer.setRepeats(false);
        timer.start();
    }

    private enum NavItem {
        OVERVIEW("Tổng quan", "Overview"),
        CUSTOMERS("Hành Khách", "Customers"),
        ROUTES("Tuyến", "Routes"),
        STATIONS("Trạm", "Stations"),
        TRAINS("Tàu", "Trains"),
        REVENUE("Doanh thu", "Revenue");

        final String text;
        final String cardName;

        NavItem(String text, String cardName) {
            this.text = text;
            this.cardName = cardName;
        }
    }
}