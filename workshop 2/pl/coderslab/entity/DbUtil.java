package pl.coderslab.entity;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/%s?useSSL=false&characterEncoding=utf8";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "coderslab";

    public static Connection connect () throws SQLException {
        return connect ("workshop2");
    }

    public static Connection connect (String databaseName) throws SQLException {
        String url = String.format (DB_URL, databaseName);
        Connection conn = (Connection) DriverManager.getConnection (url, DB_USER, DB_PASS);
        return conn;
    }
}



