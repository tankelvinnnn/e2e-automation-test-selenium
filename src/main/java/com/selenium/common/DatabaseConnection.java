package com.selenium.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {

    private static final String USERNAME = "xxxxg"; // Ganti dengan username Anda
    private static final String PASSWORD = "xxxxx1"; // Ganti dengan password Anda
    private static Connection connection;

    public static void openConnection() throws SQLException {
        String url = "jdbc:mysql://xxxxx.xxx:3601/";
        connection = DriverManager.getConnection(url, USERNAME, PASSWORD);
    }

    // //used for select
    // public static ResultSet executeGetQuery(String query) throws SQLException {
    //     Statement statement = connection.createStatement();
    //     return statement.executeQuery(query);
    // }

    //used for update and delete
    public static int executeQuery(String sql) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeUpdate(sql);
    }

    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
