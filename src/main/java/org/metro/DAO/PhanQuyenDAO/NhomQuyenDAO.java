package org.metro.DAO.PhanQuyenDAO;

import org.metro.DAO.IBaseDAO;
import org.metro.model.PhanQuyenModel.NhomQuyenModel;
import org.metro.util.DatabaseUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class NhomQuyenDAO implements IBaseDAO<NhomQuyenModel> {

    @Override
    public int insert(NhomQuyenModel nhomQuyenModel) {
        return 0;
    }

    @Override
    public int update(NhomQuyenModel nhomQuyenModel) {
        return 0;
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

