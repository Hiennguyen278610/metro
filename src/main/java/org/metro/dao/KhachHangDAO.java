package org.metro.dao;

import org.metro.model.KhachHangDTO;
import org.metro.util.DatabaseUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KhachHangDAO implements IBaseDAO<KhachHangDTO> {

    // Lấy toàn bộ danh sách khách hàng
    public List<KhachHangDTO> getAll() {
        List<KhachHangDTO> list = new ArrayList<>();
        String query = "SELECT * FROM khachhang";
        try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                KhachHangDTO kh = new KhachHangDTO(
                        rs.getInt("makh"),
                        rs.getString("tenkh"),
                        rs.getString("sdt"),
                        rs.getInt("solan")
                );
                list.add(kh);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int insert(KhachHangDTO t) {
        // Giả sử cột makh là AUTO_INCREMENT nên không cần truyền vào
        String sql = "INSERT INTO khachhang (tenkh, sdt, solan) VALUES (?, ?, ?)";
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
    public int update(KhachHangDTO t) {
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
        String sql = "DELETE FROM khachhang WHERE makh = ?";
        try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, maKh);
            return pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<KhachHangDTO> selectAll() {
        return getAll();
    }

    @Override
    public KhachHangDTO selectById(int maKh) {
        String sql = "SELECT * FROM khachhang WHERE makh = ?";
        try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, maKh);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new KhachHangDTO(
                            rs.getInt("makh"),
                            rs.getString("tenkh"),
                            rs.getString("sdt"),
                            rs.getInt("solan")
                    );
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
