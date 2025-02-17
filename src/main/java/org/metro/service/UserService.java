package org.metro.service;

import org.metro.util.DatabaseUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserService {
        public boolean DangKy(String tenDangNhap, String matkhau) {
            String user_sql = "INSERT INTO users(username,password) Values (?,?)";
            try(Connection connection = DatabaseUtils.getConnection();
                PreparedStatement pst = connection.prepareStatement(user_sql)) {
                pst.setString(1, tenDangNhap);//ten dang nhap la primary key
                pst.setString(2, matkhau);
                int row = pst.executeUpdate();
                return row > 0;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
}
