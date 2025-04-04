package org.metro.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.metro.model.TauModel;
import org.metro.util.DatabaseUtils;

public class TauDAO implements IBaseDAO<TauModel> {
    @Override
    public int delete(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public List<TauModel> selectAll() {
        List<TauModel> list = new ArrayList<>();
        String query = "SELECT * FROM tau";
        try (Connection conn = DatabaseUtils.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                TauModel kh = new TauModel(rs.getString("matau"), rs.getInt("soghe"), rs.getString("trangthaitau"),
                        rs.getDate("ngaynhap").toLocalDate());
                list.add(kh);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public TauModel selectById(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectById'");
    }

    @Override
    public int insert(TauModel t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }

    @Override
    public int update(TauModel t) {
        try (Connection conn = DatabaseUtils.getConnection()) {
            String sql = "UPDATE sanpham SET ten=?, ngaynhap=? , trangthai=?, soghe=? WHERE ma=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            // stmt.setString(1, ten);
            // stmt.setString(2, ngayNhap);
            // stmt.setString(3, ma);

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
