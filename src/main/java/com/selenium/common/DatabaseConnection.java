package com.selenium.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {

    private static final String USERNAME = "sq_staging"; // Ganti dengan username Anda
    private static final String PASSWORD = "Ckpsqu@d1"; // Ganti dengan password Anda
    private static Connection connection;

    public static void openConnection() throws SQLException {
        String url = "jdbc:mysql://gwdev.cakap.com:32200/";
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
