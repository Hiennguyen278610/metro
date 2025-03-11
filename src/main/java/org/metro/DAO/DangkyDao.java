    package org.metro.DAO;

    import org.metro.util.DatabaseUtils;

    import java.sql.Connection;
    import java.sql.PreparedStatement;
    import java.sql.SQLException;

    public class DangkyDao {
            public boolean DangKy(String tenDangNhap, String matkhau,String sodienthoai) {
                String user_sql = "INSERT INTO users(username,password,sodienthoai) Values (?,?,?)";
                try(Connection connection = DatabaseUtils.getConnection();
                    // thuc hien cau lenh sql
                    PreparedStatement pst = connection.prepareStatement(user_sql)) {
                    pst.setString(1, tenDangNhap);//ten dang nhap la primary key
                    pst.setString(2, matkhau);
                    pst.setString(3, sodienthoai);
                    int row = pst.executeUpdate(); // thuc thi cau lenh sql + return ve so dong duoc them vao
                    return row > 0;
                } catch (SQLException e) {
                    e.printStackTrace();
                    return false;
                }
            }
    }
