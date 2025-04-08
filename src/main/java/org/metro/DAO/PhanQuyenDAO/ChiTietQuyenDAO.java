package org.metro.DAO.PhanQuyenDAO;

import org.metro.DAO.IBaseDAO;
import org.metro.model.PhanQuyenModel.ChiTietPhanQuyenModel;
import org.metro.util.DatabaseUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ChiTietQuyenDAO implements IBaseDAO<ChiTietPhanQuyenModel> {
    @Override
    public int insert(ChiTietPhanQuyenModel chiTietPhanQuyenModel) {
        return 0;
    }

    @Override
    public int update(ChiTietPhanQuyenModel chiTietPhanQuyenModel) {
        return 0;
    }

    @Override
    public int delete(int id) {
        return 0;
    }

    @Override
    public List<ChiTietPhanQuyenModel> selectAll() {
        return List.of();
    }

    @Override
    public ChiTietPhanQuyenModel selectById(int id) {
        return null;
    }
    //manv trong tai khoan
    public List<ChiTietPhanQuyenModel> getQuyen(int manv) {
            List<ChiTietPhanQuyenModel> listquyenchucnang = new ArrayList<>();
            String query = "select ctq.manhomquyen,ncn.machucnang,ncn.tenchucnang,ctq.hanhdong\n" +
                    "from taikhoan tk join nhomquyen nq on tk.manhomquyen = nq.manhomquyen join chitietquyen ctq on nq.manhomquyen = ctq.manhomquyen join nhomchucnang ncn on ctq.machucnang = ncn.machucnang\n" +
                    "where tk.manv = ? and tk.trangthai = 1";
            try(Connection c = DatabaseUtils.getConnection();
                PreparedStatement prs = c.prepareStatement(query);) {
                prs.setInt(1, manv);
                ResultSet rs = prs.executeQuery();
                while(rs.next()) {
                    listquyenchucnang.add(new ChiTietPhanQuyenModel(rs.getInt("manhomquyen"),rs.getInt("machucnang"),rs.getString("hanhdong")));
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
            return listquyenchucnang;
    }
}
