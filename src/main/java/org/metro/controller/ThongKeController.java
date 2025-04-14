package org.metro.controller;


import org.metro.model.ThongKeModel;
import org.metro.service.ThongKeService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ThongKeController {
    private final ThongKeService svc = new ThongKeService();

    private void fillTable(JTable table, List<ThongKeModel> data, String c1, String c2){
        DefaultTableModel m = new DefaultTableModel(new String[]{c1, c2}, 0);
        for(ThongKeModel tk : data){
            m.addRow(new Object[]{tk.getLabel(), tk.getValue()});
        }
        table.setModel(m);
    }
    // 1. khach
    public void loadCustomerByRoute(JTable tbl) {
        fillTable(tbl, svc.getCustomerByRoute(), "Tuyến", "Khách");
    }
    public void loadCustomerByStation(JTable tbl) {
        fillTable(tbl, svc.getCustomerByStation(), "Trạm", "Khách");
    }
    public void loadCustomerByPeakOffPeak(JTable tbl) {
        fillTable(tbl, svc.getCustomerByPeakOffPeak(), "Giai đoạn", "Khách");
    }
    public void loadCustomerByPeriod(JTable tbl, String p) {
        fillTable(tbl, svc.getCustomerByPeriod(p), p, "Khách");
    }
    // 2. tuyen
    public long getTotalRoutes(){
        return svc.getTotalRoutes();
    }
    public long getTripsToday(){
        return svc.getTripsToday();
    }
    public void loadFrequencyPerRoute(JTable table){
        fillTable(table, svc.getFrequencyPerRoute(), "Tuyến", "Tuần suất");
    }
    public void loadOnTimeVsDelayed(JTable table) {
        fillTable(table, svc.getOnTimeVsDelayed(), "Trạng thái", "Số chuyến");
    }
    // 3. Trạm
    public void loadStationEntries(JTable tbl) {
        fillTable(tbl, svc.getStationEntries(), "Trạm", "Vào");
    }
    public void loadStationExits(JTable tbl) {
        fillTable(tbl, svc.getStationExits(), "Trạm", "Ra");
    }
    public void loadOverloadedStations(JTable tbl, int th) {
        fillTable(tbl, svc.getOverloadedStations(th), "Trạm", "Lượt");
    }

    // 4. Tau
    public void loadTrainStatus(JTable tbl) {
        fillTable(tbl, svc.getTrainStatus(), "Trạng thái", "Số tàu");
    }
    public void loadTrainUsage(JTable tbl) {
        fillTable(tbl, svc.getTrainUsage(), "Mã tàu", "Phút hoạt động");
    }

    // 5 Doanh thu
    public double getRevenue(String p) {
        return svc.getRevenueByPeriod(p);
    }
    public void loadRevenueByRoute(JTable tbl) {
        fillTable(tbl, svc.getRevenueByRoute(), "Tuyến", "Doanh thu");
    }

}
