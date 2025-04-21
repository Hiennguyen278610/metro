package org.metro.DAO;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.metro.model.LichBaoTriModel;
import org.metro.util.DatabaseUtils;

public class LichBaoTriDAO implements IBaseDAO<LichBaoTriModel> {

    @Override
    public int insert(LichBaoTriModel t) {
        String query = "INSERT INTO lichbaotri(matau,ngaybaotri,trangthaibaotri,ngaytao,chiphibaotri) VALUES (?,?,?,?,?)";
        try (Connection conn = DatabaseUtils.getConnection();
                PreparedStatement prs = conn.prepareStatement(query)) {
            prs.setInt(1, t.getMatau());
            prs.setDate(2, Date.valueOf(t.getNgaybaotri()));
            prs.setString(3, t.getTrangthaibaotri());
            prs.setTimestamp(4, Timestamp.valueOf(t.getNgaytao()));
            prs.setDouble(5, t.getChiphibaotri());
            return prs.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int update(LichBaoTriModel t) {
        String query = "UPDATE lichbaotri SET matau = ?,ngaybaotri = ?,trangthaibaotri = ?, chiphibaotri = ? WHERE mabaotri = ? ";
        try (Connection conn = DatabaseUtils.getConnection();
                PreparedStatement prs = conn.prepareStatement(query)) {
            prs.setInt(1, t.getMatau());
            prs.setDate(2, Date.valueOf(t.getNgaybaotri()));
            prs.setString(3, t.getTrangthaibaotri());
            prs.setDouble(4, t.getChiphibaotri());
            prs.setInt(5, t.getMabaotri());
            return prs.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int delete(int id) {
        String query = "DELETE FROM lichbaotri WHERE mabaotri = ?";
        try (Connection conn = DatabaseUtils.getConnection();
                PreparedStatement prs = conn.prepareStatement(query)) {
            prs.setInt(1, id);
            return prs.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<LichBaoTriModel> selectAll() {
        String query = "SELECT * FROM lichbaotri";
        List<LichBaoTriModel> dsBaoTri = new ArrayList<>();
        try (Connection conn = DatabaseUtils.getConnection();
                PreparedStatement prs = conn.prepareStatement(query);
                ResultSet rs = prs.executeQuery()) {
            while (rs.next()) {
                LichBaoTriModel lbt = new LichBaoTriModel();
                lbt.setMabaotri(rs.getInt(1));
                lbt.setMatau(rs.getInt(2));
                java.sql.Date date = rs.getDate(3);
                if (date != null) {
                    lbt.setNgaybaotri(date.toLocalDate());
                }
                lbt.setTrangthaibaotri(rs.getString(4));
                dsBaoTri.add(lbt);
                LocalDateTime localDateTime = rs.getTimestamp(5).toLocalDateTime();
                lbt.setChiphibaotri(rs.getDouble("chiphibaotri"));
                lbt.setNgaytao(localDateTime);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsBaoTri;
    }

    @Override
    public LichBaoTriModel selectById(int id) {
        String query = "SELECT * FROM lichbaotri WHERE mabaotri = ?";
        try (Connection conn = DatabaseUtils.getConnection();
                PreparedStatement prs = conn.prepareStatement(query)) {
            prs.setInt(1, id);
            try (ResultSet rs = prs.executeQuery();) {
                if (rs.next()) {
                    LichBaoTriModel lbt = new LichBaoTriModel();
                    lbt.setMabaotri(rs.getInt(1));
                    lbt.setMatau(rs.getInt(2));
                    java.sql.Timestamp timestamp = rs.getTimestamp(3);
                    if (timestamp != null) {
                        // lbt.setNgaybaotri(timestamp.toLocalDateTime());
                    }
                    lbt.setTrangthaibaotri(rs.getString(4));
                    return lbt;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getAutoIncrement() {
        String query = "SELECT AUTO_INCREMENT FROM information_schema.TABLES WHERE TABLE_SCHEMA = ? AND TABLE_NAME = ?";
        try (Connection conn = DatabaseUtils.getConnection();
                PreparedStatement prs = conn.prepareStatement(query)) {

            prs.setString(1, "quanlymetro");
            prs.setString(2, "lichbaotri");

            try (ResultSet rs = prs.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("AUTO_INCREMENT");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

}
