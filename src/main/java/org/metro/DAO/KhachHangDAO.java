package org.metro.DAO;

import org.metro.model.KhachHangModel;
import org.metro.util.DatabaseUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KhachHangDAO implements IBaseDAO<KhachHangModel> {

    // Lấy toàn bộ danh sách khách hàng còn hiển thị
    public List<KhachHangModel> getAll() {
        List<KhachHangModel> list = new ArrayList<>();
        String query = "SELECT * FROM khachhang WHERE isVisible = 1";  // 👈 Thêm điều kiện
        try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                KhachHangModel kh = new KhachHangModel(
                        rs.getInt("makh"),
                        rs.getString("tenkh"),
                        rs.getString("sdt"),
                        rs.getInt("solan"));
                list.add(kh);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int insert(KhachHangModel t) {
        String sql = "INSERT INTO khachhang (tenkh, sdt, solan, isVisible) VALUES (?, ?, ?, 1)";
        try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, t.getTenKh());
            pstmt.setString(2, t.getSdt());
            pstmt.setInt(3, t.getSolan());
            return pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    @Override
    public int update(KhachHangModel t) {
        String sql = "UPDATE khachhang SET tenkh = ?, sdt = ?, solan = ? WHERE makh = ?";
        try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, t.getTenKh());
            pstmt.setString(2, t.getSdt());
            pstmt.setInt(3, t.getSolan());
            pstmt.setInt(4, t.getMaKh());
            return pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    @Override
    public int delete(int maKh) {
        String sql = "UPDATE khachhang SET isVisible = 0 WHERE makh = ?";
        try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, maKh);
            return pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("SQL Error during delete: " + ex.getMessage());
            ex.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<KhachHangModel> selectAll() {
        return getAll();
    }

    @Override
    public KhachHangModel selectById(int maKh) {
        String sql = "SELECT * FROM khachhang WHERE makh = ? AND isVisible = 1";  // 👈 Thêm điều kiện
        try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, maKh);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new KhachHangModel(
                            rs.getInt("makh"),
                            rs.getString("tenkh"),
                            rs.getString("sdt"),
                            rs.getInt("solan"));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
