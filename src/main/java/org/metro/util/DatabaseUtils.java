package org.metro.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseUtils {
   private static String url;
   private static String user;
   private static String password;

   static {
       try(InputStream inp = DatabaseUtils.class.getClassLoader().getResourceAsStream("config.properties")) {
           Properties pro = new Properties();
           pro.load(inp);
           url = pro.getProperty("db.url");
           user = pro.getProperty("db.user");
           password = pro.getProperty("db.password");

       } catch (IOException e) {
           e.printStackTrace();
           throw new RuntimeException("error.....................");
       }
   }
   public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
   }
}
