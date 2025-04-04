package org.metro.DAO;

import org.metro.model.LichTrinhModel;
import org.metro.util.DatabaseUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LichTrinhDAO implements IBaseDAO<LichTrinhModel> {

    public List<LichTrinhModel> getAll() {
        List<LichTrinhModel> list = new ArrayList<>();
        String query = "SELECT * FROM lichtrinh";
        try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                LichTrinhModel lt = new LichTrinhModel(
                        rs.getInt("machuyen"),
                        rs.getInt("manv"),
                        rs.getInt("matau"),
                        rs.getInt("matuyen"),
                        rs.getBoolean("huongdi"),
                        rs.getTimestamp("tgkhoihanh").toLocalDateTime(),
                        rs.getTimestamp("tgdenthucte").toLocalDateTime(),
                        rs.getString("trangthailichtrinh")
                );
                list.add(lt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int insert(LichTrinhModel t) {
        String sql = "INSERT INTO lichtrinh (manv, matau, matuyen, huongdi, tgkhoihanh, tgdenthucte, trangthailichtrinh) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, t.getManv());
            pstmt.setInt(2, t.getMatau());
            pstmt.setInt(3, t.getMatuyen());
            pstmt.setBoolean(4, t.isHuongdi());
            pstmt.setTimestamp(5, Timestamp.valueOf(t.getTgkhoihanh()));
            pstmt.setTimestamp(6, Timestamp.valueOf(t.getTgdenthucte()));
            pstmt.setString(7, t.getTrangthailichtrinh());
            return pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    @Override
    public int update(LichTrinhModel t) {
        String sql = "UPDATE lichtrinh SET manv = ?, matau = ?, matuyen = ?, huongdi = ?, tgkhoihanh = ?, tgdenthucte = ?, trangthailichtrinh = ? WHERE machuyen = ?";
        try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, t.getManv());
            pstmt.setInt(2, t.getMatau());
            pstmt.setInt(3, t.getMatuyen());
            pstmt.setBoolean(4, t.isHuongdi());
            pstmt.setTimestamp(5, Timestamp.valueOf(t.getTgkhoihanh()));
            pstmt.setTimestamp(6, Timestamp.valueOf(t.getTgdenthucte()));
            pstmt.setString(7, t.getTrangthailichtrinh());
            pstmt.setInt(8, t.getMachuyen());
            return pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    @Override
    public int delete(int machuyen) {
        String sql = "DELETE FROM lichtrinh WHERE machuyen = ?";
        try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, machuyen);
            return pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<LichTrinhModel> selectAll() {
        return getAll();
    }

    @Override
    public LichTrinhModel selectById(int machuyen) {
        String sql = "SELECT * FROM lichtrinh WHERE machuyen = ?";
        try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, machuyen);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new LichTrinhModel(
                            rs.getInt("machuyen"),
                            rs.getInt("manv"),
                            rs.getInt("matau"),
                            rs.getInt("matuyen"),
                            rs.getBoolean("huongdi"),
                            rs.getTimestamp("tgkhoihanh").toLocalDateTime(),
                            rs.getTimestamp("tgdenthucte").toLocalDateTime(),
                            rs.getString("trangthailichtrinh")
                    );
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
