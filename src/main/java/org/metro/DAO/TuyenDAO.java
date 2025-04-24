package org.metro.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.metro.model.TuyenDuongModel;
import org.metro.util.DatabaseUtils;

public class TuyenDAO implements IBaseDAO<TuyenDuongModel> {

    @Override
    public int insert(TuyenDuongModel t) {
        String query = "INSERT INTO tuyen(matuyen,trambatdau,tramketthuc,thoigian,trangthai) VALUES (?,?,?,?,?)";
        try (Connection conn = DatabaseUtils.getConnection();
                PreparedStatement prs = conn.prepareStatement(query)) {
            prs.setInt(1, t.getMatuyen());
            prs.setInt(2, t.getTramdau());
            prs.setInt(3, t.getTramdich());
            prs.setInt(4, t.getThoigiandichuyen());
            prs.setString(5, t.getTrangthaituyen());
            return prs.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int update(TuyenDuongModel t) {
        String query = "UPDATE tuyen SET trambatdau = ?, tramketthuc = ?, thoigian = ?, trangthai = ? WHERE matuyen = ?";
        try (Connection conn = DatabaseUtils.getConnection();
                PreparedStatement prs = conn.prepareStatement(query)) {
            prs.setInt(1, t.getTramdau());
            prs.setInt(2, t.getTramdich());
            prs.setInt(3, t.getThoigiandichuyen());
            prs.setString(4, t.getTrangthaituyen());
            prs.setInt(5, t.getMatuyen());
            return prs.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int delete(int id) {
        String query = "DELETE FROM tuyen WHERE matuyen = ?";
        try (Connection conn = DatabaseUtils.getConnection();
                PreparedStatement prs = conn.prepareStatement(query)) {
            prs.setInt(1, id);
            return prs.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<TuyenDuongModel> selectAll() {
        List<TuyenDuongModel> list = new ArrayList<>();
        String query = "SELECT * FROM tuyen";
        try (Connection conn = DatabaseUtils.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                TuyenDuongModel tuyen = new TuyenDuongModel(rs.getInt("matuyen"), rs.getInt("trambatdau"),
                        rs.getInt("tramketthuc"), rs.getInt("thoigian"), rs.getString("trangthai"));
                list.add(tuyen);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public TuyenDuongModel selectById(int id) {
        String query = "SELECT * FROM tuyen WHERE matuyen = ?";
        try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement prs = conn.prepareStatement(query)) {
            prs.setInt(1, id);
            ResultSet rs = prs.executeQuery();
            if (rs.next()) {
                return new TuyenDuongModel(rs.getInt("matuyen"), rs.getInt("trambatdau"),
                        rs.getInt("tramketthuc"), rs.getInt("thoigian"), rs.getString("trangthai"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<TuyenDuongModel> search(String txt, String type) {
        List<TuyenDuongModel> list = this.selectAll();
        if (txt == null || txt.trim().isEmpty()) {
            return list;
        }
        String lowerTxt = txt.toLowerCase();
        return list.stream().filter(kh -> {
            switch (type) {
                case "Tất cả":
                    return String.valueOf(kh.getMatuyen()).contains(lowerTxt)
                            || String.valueOf(kh.getTramdau()).contains(lowerTxt)
                            || String.valueOf(kh.getTramdich()).contains(lowerTxt)
                            || String.valueOf(kh.getThoigiandichuyen()).contains(lowerTxt)
                            || kh.getTrangthaituyen().toLowerCase().contains(lowerTxt);
                case "Mã tuyến":
                    return String.valueOf(kh.getMatuyen()).contains(lowerTxt);
                case "Trạm bắt đầu":
                    return String.valueOf(kh.getTramdau()).contains(lowerTxt);
                case "Trạm đích":
                    return String.valueOf(kh.getTramdich()).contains(lowerTxt);
                case "Thời gian":
                    return String.valueOf(kh.getThoigiandichuyen()).contains(lowerTxt);
                case "Trạng thái":
                    return kh.getTrangthaituyen().toLowerCase().contains(lowerTxt);
                default:
                    return false;
            }
        }).collect(Collectors.toCollection(ArrayList::new));

    }

    public int getAutoIncrement() {
        String query = "SELECT AUTO_INCREMENT FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = ? AND TABLE_NAME = ?";
        try (Connection conn = DatabaseUtils.getConnection();
                PreparedStatement prs = conn.prepareStatement(query)) {
            prs.setString(1, "quanlymetro");
            prs.setString(2, "tuyen");

            try (ResultSet rs = prs.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("AUTO_INCREMENT");
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

}
