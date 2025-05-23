package org.metro.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.metro.model.TramModel;
import org.metro.util.DatabaseUtils;

public class TramDAO implements IBaseDAO<TramModel> {

    @Override
    public int insert(TramModel t) {
        String query = "INSERT INTO tram(tentram,diachi,x,y,chiphitram) VALUES (?,?,?,?,?)";
        try (Connection conn = DatabaseUtils.getConnection();
                PreparedStatement prs = conn.prepareStatement(query)) {
            prs.setString(1, t.getTentram());
            prs.setString(2, t.getDiachi());
            prs.setInt(3, t.getX());
            prs.setInt(4, t.getY());
            prs.setDouble(5, t.getChiphitram());
            return prs.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int update(TramModel t) {
        String query = "UPDATE tram SET tentram = ?, diachi = ?, x = ?, y = ?, chiphitram = ? WHERE matram = ?";
        try (Connection conn = DatabaseUtils.getConnection();
                PreparedStatement prs = conn.prepareStatement(query)) {
            prs.setString(1, t.getTentram());
            prs.setString(2, t.getDiachi());
            prs.setInt(3, t.getX());
            prs.setInt(4, t.getY());
            prs.setDouble(5, t.getChiphitram());
            prs.setInt(6, t.getMatram());
            // System.out.println(prs.executeUpdate());
            return prs.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int delete(int id) {
        String query = "DELETE FROM tram WHERE matram = ?";
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
    public List<TramModel> selectAll() {

        String query = "SELECT * FROM tram";
        List<TramModel> dsTram = new ArrayList<>();
        try (Connection conn = DatabaseUtils.getConnection();
                PreparedStatement prs = conn.prepareStatement(query);
                ResultSet rs = prs.executeQuery()) {
            while (rs.next()) {
                int ma = rs.getInt("matram");
                String tentram = rs.getString("tentram");
                String diachi = rs.getString("diachi");
                int x = rs.getInt("x");
                int y = rs.getInt("y");
                double chiphi = rs.getDouble("chiphitram");
                dsTram.add(new TramModel(ma, tentram, diachi, x, y, chiphi));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsTram;
    }

    @Override
    public TramModel selectById(int id) {
        return null;
    }

    public int getAutoIncrement() {
        String query = "SELECT AUTO_INCREMENT FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = ? AND TABLE_NAME = ?";
        try (Connection conn = DatabaseUtils.getConnection();
                PreparedStatement prs = conn.prepareStatement(query)) {
            prs.setString(1, "quanlymetro");
            prs.setString(2, "tram");

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
