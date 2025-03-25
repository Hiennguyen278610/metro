package org.metro.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.metro.model.TuyenDuongModel;
import org.metro.util.DatabaseUtils;

public class TuyenDAO implements IBaseDAO<TuyenDuongModel> {

    @Override
    public int insert(TuyenDuongModel t) {
        return 0;
    }

    @Override
    public int update(TuyenDuongModel t) {
        return 0;
    }

    @Override
    public int delete(int id) {
        return 0;
    }

    @Override
    public List<TuyenDuongModel> selectAll() {
        List<TuyenDuongModel> list = new ArrayList<>();
        String query = "SELECT * FROM tuyen";
        try (Connection conn = DatabaseUtils.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                TuyenDuongModel tuyen = new TuyenDuongModel(rs.getInt("matuyen"), rs.getInt("trambatdau"), rs.getInt("tramketthuc"), rs.getInt("thoigian"), rs.getString("trangthai"));
                list.add(tuyen);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public TuyenDuongModel selectById(int id) {
        return null;
    }

}
