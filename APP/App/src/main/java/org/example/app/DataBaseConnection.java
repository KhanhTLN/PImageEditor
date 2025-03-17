package org.example.app;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBaseConnection {
    public Connection databaseLink;

    public Connection getConnection(){
        String databaseName = "UserAuth";
        String databaseUser = "root";
        String databasePassword = "@Khanh2005";
        String databaseUrl = "jdbc:mysql://localhost:3306/" + databaseName;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(databaseUrl, databaseUser, databasePassword);
        } catch(Exception e){
            e.printStackTrace();
        }
        return databaseLink;
    }
}
