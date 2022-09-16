import com.sun.jdi.connect.spi.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/workshop2?useSSL=false&characterEncoding=utf8";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "coderslab";

    public static Connection getConnection() throws SQLException {
        return (Connection) DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    }
}

