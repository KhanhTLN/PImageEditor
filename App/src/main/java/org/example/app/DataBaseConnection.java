package org.example.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    private static final String DATABASE_NAME = "UserAuth";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASSWORD = "@Khanh2005";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/" + DATABASE_NAME;

    private static Connection databaseLink;

    public static Connection getConnection() {
        if (databaseLink == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                databaseLink = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Error connecting to the database", e);
            }
        }
        return databaseLink;
    }
}
