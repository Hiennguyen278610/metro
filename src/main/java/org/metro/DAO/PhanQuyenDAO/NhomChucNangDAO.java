package org.metro.DAO.PhanQuyenDAO;

import org.metro.DAO.IBaseDAO;
import org.metro.model.PhanQuyenModel.ChiTietPhanQuyenModel;
import org.metro.model.PhanQuyenModel.NhomChucNangModel;
import org.metro.model.PhanQuyenModel.NhomQuyenModel;
import org.metro.util.DatabaseUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NhomChucNangDAO implements IBaseDAO<NhomChucNangModel> {
    @Override
    public int insert(NhomChucNangModel nhomChucNangModel) {
        return 0;
    }

    @Override
    public int update(NhomChucNangModel nhomChucNangModel) {
        return 0;
    }

    @Override
    public int delete(int id) {
        return 0;
    }

    @Override
    public List<NhomChucNangModel> selectAll() {
        List<NhomChucNangModel> listncnm = new ArrayList<NhomChucNangModel>();
        String query = "select * from nhomchucnang";
        try(Connection c = DatabaseUtils.getConnection();
            PreparedStatement prs = c.prepareStatement(query);
            ResultSet rs = prs.executeQuery();) {
            while(rs.next()) {
                listncnm.add(new NhomChucNangModel(rs.getInt("machucnang"),rs.getString("tenchucnang")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listncnm;
    }

    @Override
    public NhomChucNangModel selectById(int id) {
        return null;
    }
}
