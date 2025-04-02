package org.metro.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.metro.model.NhanVienModel;
import org.metro.util.DatabaseUtils;

import javax.xml.crypto.Data;

public class NhanVienDAO implements IBaseDAO<NhanVienModel> {

    @Override
    public int insert(NhanVienModel t) {
        String queryInsert = "insert into nhanvien(tennv,sodienthoai,gioitinh,chucvu) values(?,?,?,?)";
        try(Connection c = DatabaseUtils.getConnection();
            PreparedStatement prs = c.prepareStatement(queryInsert);) {
            prs.setString(1,t.getTennv());
            prs.setString(2, t.getSdtnv());
            prs.setString(3, t.getGioitinh());
            prs.setString(4, t.getChucvu());
            return prs.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int update(NhanVienModel t) {
        String query = "UPDATE nhanvien SET tennv = ?, sodienthoai = ?, gioitinh = ?, chucvu = ? where manv = ?";
        try(Connection c = DatabaseUtils.getConnection();
            PreparedStatement prs = c.prepareStatement(query)) {
                prs.setString(1, t.getTennv());
                prs.setString(2,t.getSdtnv());
                prs.setString(3,t.getGioitinh());
                prs.setString(4,t.getChucvu());
                prs.setInt(5, t.getManv());
                return prs.executeUpdate();
        } catch(Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int delete(int id) {
        String query = "delete from nhanvien where manv = ?";
        try(Connection c = DatabaseUtils.getConnection();
        PreparedStatement prs = c.prepareStatement(query);) {
            prs.setInt(1,id);
            return prs.executeUpdate();
        }catch(Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<NhanVienModel> selectAll() {
        List<NhanVienModel> listnv = new ArrayList<>();
        String query = "select * from nhanvien;";
        try(Connection c = DatabaseUtils.getConnection();
            PreparedStatement prs = c.prepareStatement(query);
            ResultSet rs = prs.executeQuery()){
            
            while(rs.next()) {
                NhanVienModel nvd = new NhanVienModel(
                    rs.getInt("manv"), rs.getString("tennv"), rs.getString("sodienthoai"), rs.getString("gioitinh"), rs.getString("chucvu")); 
                    listnv.add(nvd);  
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listnv; 
    }

    @Override
    public NhanVienModel selectById(int id) {
        String query = "select * from nhanvien where manv = ?";
        try(Connection c = DatabaseUtils.getConnection();
            PreparedStatement prs = c.prepareStatement(query); ) {
            prs.setInt(1,id);
            try (ResultSet rs = prs.executeQuery()) {
                if (rs.next()) {
                    return new NhanVienModel(rs.getInt("manv"),
                                                            rs.getString("tennv"),
                                                            rs.getString("sodienthoai"),
                                                            rs.getString("gioitinh"),
                                                            rs.getString("chucvu"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    
}
