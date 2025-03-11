package org.metro.DAO;

import org.metro.model.TaiKhoanModel;
import org.metro.util.DatabaseUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaiKhoanDAO {

    // Lấy tất cả tài khoản
    public List<TaiKhoanModel> getAll() {
        List<TaiKhoanModel> list = new ArrayList<>();
        String query = "SELECT * FROM taikhoan";
        try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                TaiKhoanModel tk = new TaiKhoanModel(
                        rs.getInt("manv"),
                        rs.getString("matkhau"),
                        rs.getInt("manhomquyen"),
                        rs.getInt("trangthai")
                );
                list.add(tk);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Thêm tài khoản mới
    public int insert(TaiKhoanModel tk) {
        String sql = "INSERT INTO taikhoan (manv, matkhau, manhomquyen, trangthai) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, tk.getManv());
            pstmt.setString(2, tk.getMatkhau());
            pstmt.setInt(3, tk.getManhomquyen());
            pstmt.setInt(4, tk.getTrangthai());
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    // Cập nhật tài khoản
    public int update(TaiKhoanModel tk) {
        String sql = "UPDATE taikhoan SET matkhau = ?, manhomquyen = ?, trangthai = ? WHERE manv = ?";
        try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, tk.getMatkhau());
            pstmt.setInt(2, tk.getManhomquyen());
            pstmt.setInt(3, tk.getTrangthai());
            pstmt.setInt(4, tk.getManv());
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    // Xóa tài khoản theo manv
    public int delete(int manv) {
        String sql = "DELETE FROM taikhoan WHERE manv = ?";
        try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, manv);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    // Lấy tài khoản theo manv
    public TaiKhoanModel selectById(int manv) {
        String sql = "SELECT * FROM taikhoan WHERE manv = ?";
        try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, manv);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new TaiKhoanModel(
                            rs.getInt("manv"),
                            rs.getString("matkhau"),
                            rs.getInt("manhomquyen"),
                            rs.getInt("trangthai")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
