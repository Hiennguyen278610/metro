package org.metro.DAO.PhanQuyenDAO;

import org.metro.DAO.IBaseDAO;
import org.metro.model.PhanQuyenModel.NhomQuyenModel;
import org.metro.util.DatabaseUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class NhomQuyenDAO implements IBaseDAO<NhomQuyenModel> {

    @Override
    public int insert(NhomQuyenModel nhomQuyenModel) {
       String query = "insert into nhomquyen(tennhomquyen) values(?)";
       try(Connection c = DatabaseUtils.getConnection();
       PreparedStatement ps = c.prepareStatement(query,Statement.RETURN_GENERATED_KEYS)) {
           ps.setString(1, nhomQuyenModel.getTennhomquyen());
           int row = ps.executeUpdate();
           if(row > 0) {
               ResultSet rs = ps.getGeneratedKeys();
               if(rs.next()) {
                   return rs.getInt(1); // tra ve ma nhom quyen moi tao tu auto increasement
               }
           }
           return 0;
       } catch (Exception e) {
           e.printStackTrace();
           return 0;
       }
    }

    @Override
    public int update(NhomQuyenModel nhomQuyenModel) {
        String query = "update nhomquyen set tennhomquyen = ? where manhomquyen = ?";
        try(Connection c = DatabaseUtils.getConnection();
            PreparedStatement ps = c.prepareStatement(query)) {
            ps.setString(1, nhomQuyenModel.getTennhomquyen());
            ps.setInt(2,nhomQuyenModel.getManhomquyen());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int delete(int id) {
        return 0;
    }

    @Override
    public List<NhomQuyenModel> selectAll() {
        List<NhomQuyenModel> listnq = new ArrayList<>();
        String query = "select * from nhomquyen";
        try(Connection c = DatabaseUtils.getConnection();
            PreparedStatement prs = c.prepareStatement(query);
            ResultSet rs = prs.executeQuery();) {
            while(rs.next()) {
                NhomQuyenModel nqm = new NhomQuyenModel(rs.getInt("manhomquyen"), rs.getString("tennhomquyen"));
                listnq.add(nqm);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return listnq;
    }

    @Override
    public NhomQuyenModel selectById(int id) {
        return null;
    }
}

