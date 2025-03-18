package org.metro.DAO;

import org.metro.model.VeTauModel;
import org.metro.util.DatabaseUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VeTauDAO implements IBaseDAO<VeTauModel>{

    public List<VeTauModel> getAll() {
        List<VeTauModel> list = new ArrayList<>();
        String query = "SELECT * FROM vetau";
        try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                VeTauModel vetau = new VeTauModel(
                        rs.getInt("mave"),
                        rs.getInt("machuyen"),
                        rs.getInt("makh"),
                        rs.getDouble("giave"));
                list.add(vetau);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int insert(VeTauModel t) {
        String sql = "INSERT INTO vetau (mave, machuyen, makh, giave) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, t.getMave());
            pstmt.setInt(2, t.getMachuyen());
            pstmt.setInt(3, t.getMakh());
            pstmt.setDouble(4, t.getGiave());
            return pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    @Override
    public int update(VeTauModel t) {
        // Cập nhật các trường thông tin ứng với mave đó
        String sql = "UPDATE vetau SET machuyen = ?, makh = ?, giave = ? WHERE mave = ?";
        try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, t.getMachuyen());
            pstmt.setInt(2, t.getMakh());
            pstmt.setDouble(3, t.getGiave());
            pstmt.setInt(4, t.getMave());
            return pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    @Override
    public int delete(int mave) {
        String sql = "DELETE FROM vetau WHERE mave = ?";
        try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, mave);
            return pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<VeTauModel> selectAll() {
        return getAll();
    }

    @Override
    public VeTauModel selectById(int mave) {
        String sql = "SELECT * FROM vetau WHERE mave = ?";
        try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, mave);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new VeTauModel(
                            rs.getInt("mave"),
                            rs.getInt("machuyen"),
                            rs.getInt("makh"),
                            rs.getDouble("giave"));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
