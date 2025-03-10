package org.metro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.metro.model.KhachHangDTO;
import org.metro.model.NhanVienDTO;
import org.metro.util.DatabaseUtils;

public class NhanVienDAO implements IBaseDAO<NhanVienDTO> {

    @Override
    public int insert(NhanVienDTO t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }

    @Override
    public int update(NhanVienDTO t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public int delete(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");

    }

    @Override
    public List<NhanVienDTO> selectAll() {
        List<NhanVienDTO> listnv = new ArrayList<>();
        String query = "select * from nhanvien;";
        try(Connection c = DatabaseUtils.getConnection();
            PreparedStatement prs = c.prepareStatement(query);
            ResultSet rs = prs.executeQuery()){
            
            while(rs.next()) {
                NhanVienDTO nvd = new NhanVienDTO(
                    rs.getInt("manv"), rs.getString("tennv"), rs.getString("sodienthoai"), rs.getString("gioitinh"), rs.getString("chucvu")); 
                    listnv.add(nvd);  
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listnv; 
    }

    @Override
    public NhanVienDTO selectById(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectById'");
    }

    
}
