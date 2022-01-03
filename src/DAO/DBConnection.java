package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {
    private static final String databaseName = "C195DBClient";
    private static final String DB_URL = "jdbc:myql://localhost:3306/"+databaseName;
    private static final String username = "sqlUser";
    private static final String password = "Passw0rd!";



    static Connection conn;

    public static void  makeConnection() throws ClassNotFoundException, SQLException, Exception {
        conn = (Connection) DriverManager.getConnection(DB_URL, username, password);
    }

    public static void closeConnection() throws ClassNotFoundException, SQLException, Exception {
        conn.close();
    }
}
