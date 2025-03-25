package org.metro.DAO;

import java.security.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.metro.model.LichBaoTriModel;
import org.metro.util.DatabaseUtils;

public class LichBaoTriDAO implements IBaseDAO<LichBaoTriModel> {

    @Override
    public int insert(LichBaoTriModel t) {
        String query = "INSERT INTO lichbaotri(matau,ngay,trangthai) VALUES (?,?,?)";
        try (Connection conn = DatabaseUtils.getConnection();
                PreparedStatement prs = conn.prepareStatement(query)) {
            prs.setInt(1, t.getMatau());
            // java.sql.Timestamp timestamp = (t.getNgaybaotri() != null) ?
            // Timestamp.valueOf(t.getNgaybaotri()) : null;
            // prs.setTimestamp(2, timestamp);

            prs.setString(3, t.getTrangthaibaotri());
            return prs.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int update(LichBaoTriModel t) {
        String query = "UPDATE lichbaotri SET trangthai = ? WHERE mabaotri = ? ";
        try (Connection conn = DatabaseUtils.getConnection();
                PreparedStatement prs = conn.prepareStatement(query)) {
            prs.setString(1, t.getTrangthaibaotri());
            prs.setInt(2, t.getMabaotri());
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
                java.sql.Timestamp timestamp = rs.getTimestamp(3);
                if (timestamp != null) {
                    lbt.setNgaybaotri(timestamp.toLocalDateTime());
                }
                lbt.setTrangthaibaotri(rs.getString(4));
                dsBaoTri.add(lbt);
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
                        lbt.setNgaybaotri(timestamp.toLocalDateTime());
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

}
