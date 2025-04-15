package org.metro.service;

import org.metro.DAO.ThongKeDAO;
import org.metro.model.ThongKeBaCotModel;
import org.metro.model.ThongKeModel;

import java.util.Collections;
import java.util.List;

public class ThongKeService {
    private final ThongKeDAO thongKeDAO = new ThongKeDAO();
    // 1. Khach
    public List<ThongKeModel> getCustomerByRoute() {
        try {
            return thongKeDAO.getCustomerByRoute();
        }
        catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
    public List<ThongKeModel> getCustomerByStation() {
        try {
            return thongKeDAO.getCustomerByStation();
        }
        catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();

        }
    }
    public List<ThongKeModel> getCustomerByPeakOffPeak() {
        try {
            return thongKeDAO.getCustomerByPeakOffPeak();
        }

        catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList(); }
    }
    public List<ThongKeModel> getCustomerByPeriod(String p) {
        try {
            return thongKeDAO.getCustomerByPeriod(p);
        }
        catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList(); }
    }

    // 2. TUYEN
    public long getTotalRoutes() {
        try {
            return thongKeDAO.getTotalRoutes();
        }
        catch (Exception e) {
            e.printStackTrace();
            return 0L; }
    }
    public long getTripsToday() {
        try {
            return thongKeDAO.getTripsToday();
        }
        catch (Exception e) {
            e.printStackTrace();
            return 0L; }
    }
    public List<ThongKeModel> getFrequencyPerRoute() {
        try {
            return thongKeDAO.getFrequencyPerRoute();
        }
        catch (Exception e) { e.printStackTrace(); return Collections.emptyList(); }
    }
    public List<ThongKeModel> getOnTimeVsDelayed() {
        try {
            return thongKeDAO.getOnTimeVsDelayed();
        }
        catch (Exception e) { e.printStackTrace(); return Collections.emptyList(); }
    }

    // 3. TRAM
    public List<ThongKeModel> getStationEntries() {
        try {
            return thongKeDAO.getStationEntries();
        }
        catch (Exception e) {
            e.printStackTrace(); return Collections.emptyList();
        }
    }
    public List<ThongKeModel> getStationExits() {
        try {
            return thongKeDAO.getStationExits();
        }
        catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
    public List<ThongKeModel> getOverloadedStations(int th) {
        try {
            return thongKeDAO.getOverloadedStations(th);
        }
        catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    // 4. TAU
    public List<ThongKeModel> getTrainStatus() {
        try {
            return thongKeDAO.getTrainStatus();
        }
        catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
    public List<ThongKeModel> getTrainUsage() {
        try {
            return thongKeDAO.getTrainUsage();
        }
        catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    // 6. DOANH THU
    public List<ThongKeBaCotModel> getFinanceByPeriod(String p) {
        try { return thongKeDAO.getFinanceByPeriod(p); }
        catch (Exception e) { e.printStackTrace(); return Collections.emptyList(); }
    }

    public List<ThongKeModel> getRevenueByRoute() {
        try { return thongKeDAO.getRevenueByRoute(); }
        catch (Exception e) { e.printStackTrace(); return Collections.emptyList(); }
    }

    public List<ThongKeModel> getOperatingCostByRoute() {
        try { return thongKeDAO.getOperatingCostByRoute(); }
        catch (Exception e) { e.printStackTrace(); return Collections.emptyList(); }
    }
    public double getRevenueByPeriod(String p) {
        try { return thongKeDAO.getRevenueByPeriod(p); }
        catch (Exception e) { e.printStackTrace(); return 0.0; }
    }

    public double getCostByPeriod(String p) {
        try { return thongKeDAO.getCostByPeriod(p); }
        catch (Exception e) { e.printStackTrace(); return 0.0; }
    }

    public double getProfitByPeriod(String p) {
        try { return thongKeDAO.getProfitByPeriod(p); }
        catch (Exception e) { e.printStackTrace(); return 0.0; }
    }

}

