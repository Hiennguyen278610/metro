package org.metro.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtils {
    private static final String url = "jdbc:mysql://localhost:3306/quanlymetro";
    private static final String user = "root";
    private static final String password = "261105";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,user,password);
    }
}
