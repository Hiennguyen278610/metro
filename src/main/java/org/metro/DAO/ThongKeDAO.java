package org.metro.DAO;

import org.metro.model.ThongKeModel;
import org.metro.model.ThongKeBaCotModel;
import org.metro.util.DatabaseUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ThongKeDAO {

    // 1. HÀNH KHÁCH

    public List<ThongKeModel> getCustomerByRoute() throws SQLException {
        String sql = """
            SELECT l.matuyen AS label, COUNT(*) AS cnt
            FROM vetau v
            JOIN lichtrinh l ON v.machuyen = l.machuyen
            GROUP BY l.matuyen
            """;
        return queryList(sql);
    }

    public List<ThongKeModel> getCustomerByStation() throws SQLException {
        String sql = """
            SELECT t.tentram AS label, COUNT(*) AS cnt
            FROM vetau v
            JOIN lichtrinh l    ON v.machuyen = l.machuyen
            JOIN tuyen tn       ON l.matuyen  = tn.matuyen
            JOIN tram t         ON tn.trambatdau = t.matram
            GROUP BY t.tentram
            """;
        return queryList(sql);
    }

    public List<ThongKeModel> getCustomerByPeakOffPeak() throws SQLException {
        String sql = """
            SELECT CASE
                     WHEN TIME(l.thoigiankh) BETWEEN '07:00:00' AND '09:00:00'
                       OR TIME(l.thoigiankh) BETWEEN '17:00:00' AND '19:00:00'
                     THEN 'Cao điểm'
                     ELSE 'Thấp điểm'
                   END AS label,
                   COUNT(*) AS cnt
            FROM lichtrinh l
            GROUP BY label
            """;
        return queryList(sql);
    }

    public List<ThongKeModel> getCustomerByPeriod(String period) throws SQLException {
        String sql;
        switch (period.toUpperCase()) {
            case "MONTH" -> sql = """
            SELECT DATE_FORMAT(l.thoigiankh, '%Y-%m') AS label, COUNT(*) AS cnt
            FROM vetau v
            JOIN lichtrinh l ON v.machuyen = l.machuyen
            GROUP BY DATE_FORMAT(l.thoigiankh, '%Y-%m')
            ORDER BY label
            """;
            case "YEAR" -> sql = """
            SELECT YEAR(l.thoigiankh) AS label, COUNT(*) AS cnt
            FROM vetau v
            JOIN lichtrinh l ON v.machuyen = l.machuyen
            GROUP BY YEAR(l.thoigiankh)
            ORDER BY label
            """;
            default /* DAY */ -> sql = """
            SELECT DATE_FORMAT(l.thoigiankh, '%Y-%m-%d') AS label, COUNT(*) AS cnt
            FROM vetau v
            JOIN lichtrinh l ON v.machuyen = l.machuyen
            GROUP BY DATE_FORMAT(l.thoigiankh, '%Y-%m-%d')
            ORDER BY label
            """;
        }
        return queryList(sql);
    }



    // 2. TUYẾN

    public long getTotalRoutes() throws SQLException {
        String sql = "SELECT COUNT(*) FROM tuyen";
        try (Connection c = DatabaseUtils.getConnection();
             Statement s = c.createStatement();
             ResultSet r = s.executeQuery(sql)) {
            return r.next() ? r.getLong(1) : 0L;
        }
    }

    public long getTripsToday() throws SQLException {
        String sql = """
            SELECT COUNT(*)
            FROM lichtrinh l
            WHERE DATE(l.thoigiankh) = CURDATE()
            """;
        try (Connection c = DatabaseUtils.getConnection();
             Statement s = c.createStatement();
             ResultSet r = s.executeQuery(sql)) {
            return r.next() ? r.getLong(1) : 0L;
        }
    }

    public List<ThongKeModel> getFrequencyPerRoute() throws SQLException {
        String sql = """
            SELECT l.matuyen AS label,
                   COUNT(*) / GREATEST(DATEDIFF(CURDATE(), MIN(DATE(l.thoigiankh))), 1) AS cnt
            FROM lichtrinh l
            GROUP BY l.matuyen
            """;
        return queryList(sql);
    }

    public List<ThongKeModel> getOnTimeVsDelayed() throws SQLException {
        String sql = """
            SELECT CASE
                     WHEN TIMESTAMPDIFF(MINUTE, l.thoigiankh, l.tgdenthucte) <= 0 THEN 'Đúng giờ'
                     ELSE 'Trễ giờ'
                   END AS label,
                   COUNT(*) AS cnt
            FROM lichtrinh l
            GROUP BY label
            """;
        return queryList(sql);
    }

    // 3. TRẠM

    public List<ThongKeModel> getStationEntries() throws SQLException {
        String sql = """
        SELECT REPLACE(SUBSTRING_INDEX(t.tentram, ' ', -2), ' ', '\\n') AS label, COALESCE(COUNT(v.mave), 0) AS cnt
        FROM tram t
        LEFT JOIN tuyen tn        ON t.matram = tn.trambatdau
        LEFT JOIN lichtrinh l    ON l.matuyen = tn.matuyen
        LEFT JOIN vetau v        ON v.machuyen = l.machuyen
        GROUP BY t.tentram
        ORDER BY t.tentram
        """;
        return queryList(sql);
    }


    public List<ThongKeModel> getStationExits() throws SQLException {
        String sql = """
        SELECT REPLACE(SUBSTRING_INDEX(t.tentram, ' ', -2), ' ', '\\n') AS label, COALESCE(COUNT(v.mave), 0) AS cnt
        FROM tram t
        LEFT JOIN tuyen tn        ON t.matram = tn.tramketthuc
        LEFT JOIN lichtrinh l    ON l.matuyen = tn.matuyen
        LEFT JOIN vetau v        ON v.machuyen = l.machuyen
        GROUP BY t.tentram
        ORDER BY t.tentram
        """;
        return queryList(sql);
    }


    public List<ThongKeModel> getOverloadedStations(int threshold) throws SQLException {
        String sql = """
            SELECT label, cnt
            FROM (
                SELECT REPLACE(SUBSTRING_INDEX(t.tentram, ' ', -2), ' ', '\\n') AS label,
                       COUNT(*) AS cnt
                FROM vetau v
                LEFT JOIN lichtrinh l ON v.machuyen = l.machuyen
                LEFT JOIN tuyen tn ON l.matuyen = tn.matuyen
                LEFT JOIN tram t ON tn.trambatdau = t.matram
                               OR tn.tramketthuc = t.matram
                GROUP BY t.tentram
            ) AS sub
            WHERE cnt > ?
            """;
        try (Connection c = DatabaseUtils.getConnection();
             PreparedStatement p = c.prepareStatement(sql)) {
            p.setInt(1, threshold);
            try (ResultSet r = p.executeQuery()) {
                List<ThongKeModel> list = new ArrayList<>();
                while (r.next()) {
                    list.add(new ThongKeModel(r.getString("label"), r.getLong("cnt")));
                }
                return list;
            }
        }
    }

    // 4. TÀU

    public List<ThongKeModel> getTrainStatus() throws SQLException {
        String sql = """
            SELECT trangthaitau AS label, COUNT(*) AS cnt
            FROM tau
            GROUP BY trangthaitau
            """;
        return queryList(sql);
    }

    public List<ThongKeModel> getTrainUsage() throws SQLException {
        String sql = """
            SELECT matau AS label,
                   SUM(TIMESTAMPDIFF(MINUTE, thoigiankh, tgdenthucte)) AS cnt
            FROM lichtrinh
            GROUP BY matau
            """;
        return queryList(sql);
    }

    // 5. DOANH THU


    public List<ThongKeBaCotModel> getFinanceByPeriod(String period) throws SQLException {
        String sql;
        switch (period.toUpperCase()) {
            case "MONTH" -> sql = """
            SELECT DATE_FORMAT(l.thoigiankh, '%Y-%m') AS label,
                   COALESCE(SUM(t.chiphitau + tr.chiphitram), 0) AS cost,
                   COALESCE(SUM(v.giave), 0) AS revenue
            FROM lichtrinh l
            LEFT JOIN tau t   ON l.matau    = t.matau
            LEFT JOIN tuyen tn ON l.matuyen = tn.matuyen
            LEFT JOIN tram tr ON tn.trambatdau = tr.matram
            LEFT JOIN vetau v ON l.machuyen = v.machuyen
            GROUP BY DATE_FORMAT(l.thoigiankh, '%Y-%m')
            ORDER BY DATE_FORMAT(l.thoigiankh, '%Y-%m')
            """;
            case "YEAR" -> sql = """
            SELECT YEAR(l.thoigiankh) AS label,
                   COALESCE(SUM(t.chiphitau + tr.chiphitram), 0) AS cost,
                   COALESCE(SUM(v.giave), 0) AS revenue
            FROM lichtrinh l
            LEFT JOIN tau t   ON l.matau    = t.matau
            LEFT JOIN tuyen tn ON l.matuyen = tn.matuyen
            LEFT JOIN tram tr ON tn.trambatdau = tr.matram
            LEFT JOIN vetau v ON l.machuyen = v.machuyen
            GROUP BY YEAR(l.thoigiankh)
            ORDER BY YEAR(l.thoigiankh)
            """;
            default -> sql = """
            SELECT DATE(l.thoigiankh) AS label,
                   COALESCE(SUM(t.chiphitau + tr.chiphitram), 0) AS cost,
                   COALESCE(SUM(v.giave), 0) AS revenue
            FROM lichtrinh l
            LEFT JOIN tau t   ON l.matau    = t.matau
            LEFT JOIN tuyen tn ON l.matuyen = tn.matuyen
            LEFT JOIN tram tr ON tn.trambatdau = tr.matram
            LEFT JOIN vetau v ON l.machuyen = v.machuyen
            GROUP BY DATE(l.thoigiankh)
            ORDER BY DATE(l.thoigiankh)
            """;
        }

        List<ThongKeBaCotModel> list = new ArrayList<>();
        try (Connection c = DatabaseUtils.getConnection();
             PreparedStatement p = c.prepareStatement(sql);
             ResultSet r = p.executeQuery()) {

            while (r.next()) {
                String label   = r.getString("label");
                double cost    = r.getDouble("cost");
                double revenue = r.getDouble("revenue");
                list.add(new ThongKeBaCotModel(label, cost, revenue));
            }
        }
        return list;
    }


    // Doanh thu theo tuyến (chỉ revenue)
    public List<ThongKeModel> getRevenueByRoute() throws SQLException {
        String sql = """
        SELECT CONCAT('Tuyến ', t.matuyen) AS label,
               COALESCE(SUM(v.giave), 0) AS cnt
        FROM tuyen t
        LEFT JOIN lichtrinh l ON t.matuyen = l.matuyen
        LEFT JOIN vetau v     ON l.machuyen = v.machuyen
        GROUP BY t.matuyen
        ORDER BY t.matuyen
        """;
        return queryList(sql);
    }

    // Chi phí vận hành theo tuyến (cost)
    public List<ThongKeModel> getOperatingCostByRoute() throws SQLException {
        String sql = """
        SELECT CONCAT('Tuyến ', t.matuyen) AS label,
               COALESCE(SUM(tau.chiphitau + tr.chiphitram), 0) AS cnt
        FROM tuyen t
        LEFT JOIN lichtrinh l ON t.matuyen = l.matuyen
        LEFT JOIN tau tau     ON l.matau    = tau.matau
        LEFT JOIN tram tr     ON l.matuyen  = tr.matram
        GROUP BY t.matuyen
        ORDER BY t.matuyen
        """;
        return queryList(sql);
    }
    public double getCostByPeriod(String period) throws SQLException {
        String sql;
        switch (period.toUpperCase()) {
            case "MONTH" -> sql = """
                SELECT COALESCE(SUM(t.chiphitau + tr.chiphitram),0)
                FROM lichtrinh l
                LEFT JOIN tau t  ON l.matau    = t.matau
                LEFT JOIN tuyen tn ON l.matuyen = tn.matuyen
                LEFT JOIN tram tr ON tn.trambatdau = tr.matram
                WHERE MONTH(l.thoigiankh)=MONTH(CURDATE())
                  AND YEAR(l.thoigiankh)=YEAR(CURDATE())
                """;
            case "YEAR" -> sql = """
                SELECT COALESCE(SUM(t.chiphitau + tr.chiphitram),0)
                FROM lichtrinh l
                LEFT JOIN tau t  ON l.matau    = t.matau
                LEFT JOIN tuyen tn ON l.matuyen = tn.matuyen
                LEFT JOIN tram tr ON tn.trambatdau = tr.matram
                WHERE YEAR(l.thoigiankh)=YEAR(CURDATE())
                """;
            default -> sql = """
                SELECT COALESCE(SUM(t.chiphitau + tr.chiphitram),0)
                FROM lichtrinh l
                LEFT JOIN tau t  ON l.matau    = t.matau
                LEFT JOIN tuyen tn ON l.matuyen = tn.matuyen
                LEFT JOIN tram tr ON tn.trambatdau = tr.matram
                WHERE DATE(l.thoigiankh)=CURDATE()
                """;
        }
        try (Connection c = DatabaseUtils.getConnection();
             Statement s = c.createStatement();
             ResultSet r = s.executeQuery(sql)) {
            return r.next() ? r.getDouble(1) : 0.0;
        }
    }
    public double getRevenueByPeriod(String period) throws SQLException {
        String sql;
        switch (period.toUpperCase()) {
            case "MONTH" -> sql = """
                SELECT COALESCE(SUM(v.giave), 0)
                FROM vetau v
                JOIN lichtrinh l ON v.machuyen = l.machuyen
                WHERE MONTH(l.thoigiankh) = MONTH(CURDATE())
                  AND YEAR(l.thoigiankh)  = YEAR(CURDATE())
                """;
            case "YEAR" -> sql = """
                SELECT COALESCE(SUM(v.giave), 0)
                FROM vetau v
                JOIN lichtrinh l ON v.machuyen = l.machuyen
                WHERE YEAR(l.thoigiankh) = YEAR(CURDATE())
                """;
            default -> sql = """
                SELECT COALESCE(SUM(v.giave), 0)
                FROM vetau v
                JOIN lichtrinh l ON v.machuyen = l.machuyen
                WHERE DATE(l.thoigiankh) = CURDATE()
                """;
        }
        try (Connection c = DatabaseUtils.getConnection();
             Statement s    = c.createStatement();
             ResultSet r    = s.executeQuery(sql)) {
            return r.next() ? r.getDouble(1) : 0.0;
        }
    }
    public double getProfitByPeriod(String period) throws SQLException {
        // profit = revenue – cost
        double revenue = getRevenueByPeriod(period);
        double cost    = getCostByPeriod(period);
        return revenue - cost;
    }


    private List<ThongKeModel> queryList(String sql) throws SQLException {
        List<ThongKeModel> list = new ArrayList<>();
        try (Connection c = DatabaseUtils.getConnection();
             Statement s = c.createStatement();
             ResultSet r = s.executeQuery(sql)) {
            while (r.next()) {
                list.add(new ThongKeModel(r.getString("label"), r.getLong("cnt")));
            }
        }
        return list;
    }
}
