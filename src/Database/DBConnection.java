package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/** This class is used to contain methods for connecting to the client_schedule database.*/
public class DBConnection {
    private static final String databaseName = "client_schedule";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/"+databaseName;
    private static final String username = "sqlUser";
    private static final String password = "Passw0rd!";
    private static Connection conn = null;
    private static final String MYSQLJBCDriver = "com.mysql.jdbc.Driver";

    /** This is the start connection method.
     This method establishes an initial connection to the database.
     @return Returns database connection URL, database connection username, and database connection password
     */
    public static Connection startConnection() {
        try {
            Class.forName(MYSQLJBCDriver);
            conn = DriverManager.getConnection(DB_URL, username, password);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /** This is the get connection method.
     This method returns the established connection to the database.
     @return Returns established database connection URL, database connection username, and database connection password
     */
    public static Connection getConnection() {
        return conn;
    }

    /** This is the close connection method.
     This method closes the database connection.
     */
    public static void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {

        }
    }
}
