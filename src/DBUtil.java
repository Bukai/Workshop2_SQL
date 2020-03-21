package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    private static final String URL =
            "jdbc:mysql://localhost:3306/Szkoła_Programowania?useSSL=false&characterEncoding=utf8";
    private static final String USER = "root";
    private static final String PASSWORD = "coderslab";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
