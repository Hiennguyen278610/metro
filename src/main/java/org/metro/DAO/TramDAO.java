package org.metro.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.metro.model.TramModel;
import org.metro.util.DatabaseUtils;

public class TramDAO implements IBaseDAO<TramModel> {

    @Override
    public int insert(TramModel t) {
        return 0;
    }

    @Override
    public int update(TramModel t) {
        return 0;
    }

    @Override
    public int delete(int id) {
        return 0;
    }

    @Override
    public List<TramModel> selectAll() {
        List<TramModel> list = new ArrayList<>();
        String query = "SELECT * FROM tram";
        try (Connection conn = DatabaseUtils.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                TramModel tram = new TramModel(rs.getInt("matram"), rs.getString("tentram"), rs.getInt("x"),
                        rs.getInt("y"));
                list.add(tram);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public TramModel selectById(int id) {
        return null;
    }

}
