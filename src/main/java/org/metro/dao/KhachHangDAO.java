package org.metro.dao;

import org.metro.model.KhachHangDTO;
import org.metro.util.DatabaseUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KhachHangDAO implements IBaseDAO<KhachHangDTO> {

    // Lấy toàn bộ danh sách khách hàng
    public List<KhachHangDTO> getAll() {
        List<KhachHangDTO> danhSachKhachHang = new ArrayList<>();
        String query = "SELECT * FROM khachhang";

        try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                danhSachKhachHang.add(mapResultSetToKhachHang(rs));
            }

            if (danhSachKhachHang.isEmpty()) {
                System.out.println(" Không có dữ liệu khách hàng trong database!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return danhSachKhachHang;
    }



    @Override
    public int insert(KhachHangDTO t) {
        int rowsAffected = 0;
        String sql = "INSERT INTO `khachhang` (`makh`, `tenkh`, `sdt`, `matuyen`, `ngaythamgia`) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, t.getMaKh());
            pstmt.setString(2, t.getTenKh());
            pstmt.setString(3, t.getSdt());
            pstmt.setString(4, t.getMaTuyen());
            pstmt.setTimestamp(5, Timestamp.valueOf(t.getNgayThamGia()));
            rowsAffected = pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rowsAffected;
    }

    // Cập nhật thông tin khách hàng
    @Override
    public int update(KhachHangDTO t) {
        int rowsAffected = 0;
        String sql = "UPDATE `khachhang` SET `tenkh` = ?, `sdt` = ?, `matuyen` = ?, `ngaythamgia` = ? WHERE `makh` = ?";
        try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, t.getTenKh());
            pstmt.setString(2, t.getSdt());
            pstmt.setString(3, t.getMaTuyen());
            pstmt.setTimestamp(4, Timestamp.valueOf(t.getNgayThamGia()));
            pstmt.setInt(5, t.getMaKh());
            rowsAffected = pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rowsAffected;
    }

    // Xóa khách hàng theo ID
    @Override
    public int delete(int maKh) {
        int rowsAffected = 0;
        String sql = "DELETE FROM `khachhang` WHERE `makh` = ?";
        try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, maKh);
            rowsAffected = pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rowsAffected;
    }

    @Override
    public List<KhachHangDTO> selectAll() {
        List<KhachHangDTO> customers = new ArrayList<>();
        String sql = "SELECT `makh`, `tenkh`, `sdt`, `matuyen`, `ngaythamgia` FROM `khachhang`";
        try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                KhachHangDTO customer = new KhachHangDTO(
                        rs.getInt("makh"),
                        rs.getString("tenkh"),
                        rs.getString("sdt"),
                        rs.getString("matuyen"),
                        rs.getTimestamp("ngaythamgia").toLocalDateTime()
                );
                customers.add(customer);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return customers;
    }

    @Override
    public KhachHangDTO selectById(int maKh) {
        KhachHangDTO customer = null;
        String sql = "SELECT `makh`, `tenkh`, `sdt`, `matuyen`, `ngaythamgia` FROM `khachhang` WHERE `makh` = ?";
        try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, maKh);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    customer = new KhachHangDTO(
                            rs.getInt("makh"),
                            rs.getString("tenkh"),
                            rs.getString("sdt"),
                            rs.getString("matuyen"),
                            rs.getTimestamp("ngaythamgia").toLocalDateTime()
                    );
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return customer;
    }

    // Chuyển ResultSet thành đối tượng KhachHangDTO
    private KhachHangDTO mapResultSetToKhachHang(ResultSet rs) throws SQLException {
        return new KhachHangDTO(
                rs.getInt("makh"),
                rs.getString("tenkh"),
                rs.getString("sdt"),
                rs.getString("matuyen"),
                rs.getTimestamp("ngaythamgia").toLocalDateTime()
        );
    }
}
