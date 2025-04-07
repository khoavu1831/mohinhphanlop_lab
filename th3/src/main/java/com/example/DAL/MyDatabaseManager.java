package com.example.DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyDatabaseManager {
    private Connection conn;
    private Statement stmt;
    private PreparedStatement pstmt;
    private String host, port, dbName, dbUser, dbPassword;

    MyDatabaseManager() {
        host = "localhost";
        port = "3306";
        dbUser = "root";
        dbName = "school";
        dbPassword = "";
    }

    public void connectDB() {

        String dbPath = "jdbc:mysql://" + host + ":" + port + "/" + dbName + "?useUnicode=yes&characterEncoding=UTF-8";
        try {
            conn = (Connection) DriverManager.getConnection(dbPath, dbUser, dbPassword);
            stmt = conn.createStatement();
            System.out.print("Connected");
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
        }
    }

    public Connection getConnection() {
        return conn;
    }

    public ResultSet doReadQuery(String sql) {
        ResultSet result = null;
        try {
            result = stmt.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(MyDatabaseManager.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public void doUpdateQuery() throws SQLException {
    }

    public static void main(String[] args) {
        new MyDatabaseManager().connectDB();
    }
}
