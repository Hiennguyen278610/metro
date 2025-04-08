package org.metro.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.metro.model.TauModel;
import org.metro.util.DatabaseUtils;

public class TauDAO implements IBaseDAO<TauModel> {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Use delete(String matau) instead");
    }

    public int delete(String matau) {
        String sql = "DELETE FROM tau WHERE matau = ?";
        try (Connection conn = DatabaseUtils.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, matau);
            return pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("SQL Error during delete: " + ex.getMessage());
            ex.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<TauModel> selectAll() {
        List<TauModel> list = new ArrayList<>();
        String query = "SELECT * FROM tau";
        try (Connection conn = DatabaseUtils.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                TauModel tau = new TauModel(
                        rs.getString("matau"),
                        rs.getInt("soghe"),
                        rs.getString("trangthaitau"),
                        rs.getDate("ngaynhap").toLocalDate());
                list.add(tau);
            }
        } catch (SQLException e) {
            System.err.println("SQL Error during selectAll: " + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public TauModel selectById(int id) {
        throw new UnsupportedOperationException("Use selectById(String matau) instead");
    }

    public TauModel selectById(String matau) {
        String sql = "SELECT * FROM tau WHERE matau = ?";
        try (Connection conn = DatabaseUtils.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, matau);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new TauModel(
                            rs.getString("matau"),
                            rs.getInt("soghe"),
                            rs.getString("trangthaitau"),
                            rs.getDate("ngaynhap").toLocalDate());
                }
            }
        } catch (SQLException ex) {
            System.err.println("SQL Error during selectById: " + ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public int insert(TauModel t) {
        String sql = "INSERT INTO tau (soghe, trangthaitau, ngaynhap) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseUtils.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, t.getSoghe());
            pstmt.setString(2, t.getTrangthaitau());

            // Parse the date string and convert to SQL Date
            LocalDate ngaynhap = LocalDate.parse(t.getNgaynhap(), FORMATTER);
            pstmt.setDate(3, Date.valueOf(ngaynhap));

            int result = pstmt.executeUpdate();

            // Get the generated ID and set it on the model
            if (result > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        // Assuming the ID is auto-generated and returned as a string
                        String generatedId = generatedKeys.getString(1);
                        t.setMatau(generatedId);
                    }
                }
            }

            return result;
        } catch (SQLException ex) {
            System.err.println("SQL Error during insert: " + ex.getMessage());
            ex.printStackTrace();
            return 0;
        } catch (Exception e) {
            System.err.println("General Error during insert: " + e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int update(TauModel t) {
        String sql = "UPDATE tau SET soghe=?, trangthaitau=?, ngaynhap=? WHERE matau=?";
        try (Connection conn = DatabaseUtils.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, t.getSoghe());
            pstmt.setString(2, t.getTrangthaitau());

            // Parse the date string and convert to SQL Date
            LocalDate ngaynhap = LocalDate.parse(t.getNgaynhap(), FORMATTER);
            pstmt.setDate(3, Date.valueOf(ngaynhap));

            pstmt.setString(4, t.getMatau());

            return pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("SQL Error during update: " + ex.getMessage());
            ex.printStackTrace();
            return 0;
        } catch (Exception e) {
            System.err.println("General Error during update: " + e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }

    public List<TauModel> search(String txt, String type) {
        List<TauModel> allTau = this.selectAll();
        if (txt == null || txt.trim().isEmpty()) {
            return allTau;
        }
        String lowerTxt = txt.toLowerCase();
        return allTau.stream().filter(kh -> {
            switch (type) {
                case "Tất cả":
                    return String.valueOf(kh.getMatau()).contains(lowerTxt)
                            || String.valueOf(kh.getSoghe()).contains(lowerTxt)
                            || kh.getTrangthaitau().toLowerCase().contains(lowerTxt)
                            || kh.getNgaynhap().toLowerCase().contains(lowerTxt);
                case "Mã tàu":
                    return String.valueOf(kh.getMatau()).contains(lowerTxt);
                case "Sức chứa":
                    return String.valueOf(kh.getSoghe()).contains(lowerTxt);
                case "Trạng thái":
                    return kh.getTrangthaitau().toLowerCase().contains(lowerTxt);
                case "Ngày nhập":

                    return kh.getNgaynhap().toLowerCase().contains(lowerTxt);
                default:
                    return false;
            }
        }).collect(Collectors.toCollection(ArrayList::new));
    }
}