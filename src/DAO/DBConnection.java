package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {
    private static final String databaseName = "C195DBClient";
    private static final String DB_URL = "jdbc:myql://localhost:3306/"+databaseName;
    private static final String username = "sqlUser";
    private static final String password = "Passw0rd!";
    private static Connection conn = null;

    private static final String MYSQLJBCDriver = "com.mysql.jdbc.Driver";


    public static Connection startConnection() { //throws ClassNotFoundException, SQLException, Exception {
        try {
            Class.forName(MYSQLJBCDriver);
            conn = (Connection) DriverManager.getConnection(DB_URL, username, password);
            System.out.println("Connection successful");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return conn;
    }

    public static void closeConnection() {//throws ClassNotFoundException, SQLException, Exception {
        try {
            conn.close();
            System.out.println("Connection closed");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
