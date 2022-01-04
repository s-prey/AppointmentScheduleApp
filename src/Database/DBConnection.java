package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {
    private static final String databaseName = "client_schedule";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/"+databaseName;
    private static final String username = "sqlUser";
    private static final String password = "Passw0rd!";
    private static Connection conn = null;

    private static final String MYSQLJBCDriver = "com.mysql.jdbc.Driver";


    public static Connection startConnection() {

        try {
            Class.forName(MYSQLJBCDriver);
            conn = DriverManager.getConnection(DB_URL, username, password);

            System.out.println("Connection successful");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;


        }



    public static Connection getConnection() {
        return conn;
    }


    public static void closeConnection() {//throws ClassNotFoundException, SQLException, Exception {
        try {
            conn.close();
        } catch (SQLException e) {

        }
    }
}
