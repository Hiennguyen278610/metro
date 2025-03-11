package org.metro.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.metro.model.KhachHangModal;
import org.metro.model.NhanVienModal;
import org.metro.util.DatabaseUtils;

public class NhanVienDAO implements IBaseDAO<NhanVienModal> {

    @Override
    public int insert(NhanVienModal t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }

    @Override
    public int update(NhanVienModal t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public int delete(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");

    }

    @Override
    public List<NhanVienModal> selectAll() {
        List<NhanVienModal> listnv = new ArrayList<>();
        String query = "select * from nhanvien;";
        try(Connection c = DatabaseUtils.getConnection();
            PreparedStatement prs = c.prepareStatement(query);
            ResultSet rs = prs.executeQuery()){
            
            while(rs.next()) {
                NhanVienModal nvd = new NhanVienModal(
                    rs.getInt("manv"), rs.getString("tennv"), rs.getString("sodienthoai"), rs.getString("gioitinh"), rs.getString("chucvu")); 
                    listnv.add(nvd);  
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listnv; 
    }

    @Override
    public NhanVienModal selectById(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectById'");
    }

    
}
