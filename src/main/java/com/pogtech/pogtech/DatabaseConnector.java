package com.pogtech.pogtech;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {

    public static  String DATABASE_NAME = "dealership";
    public static  String URL = "jdbc:mysql://localhost:3306/" + DATABASE_NAME;
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";


    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}