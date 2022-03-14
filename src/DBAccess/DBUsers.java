package DBAccess;

import Database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.Users;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

/** This class is used to contain methods for database users table reading and data querying for the GUI menu controllers.*/
public class DBUsers {

    /** This is the get all users method.
     This method returns a list of all users from the users table within the client_schedule database.
     @return Returns list of all users from users table
     */
    public static ObservableList<Users> getAllUsers() {
        ObservableList<Users> usersObservableList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * from users";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int userID = rs.getInt("User_ID");
                String userName = rs.getString("User_Name");
                String userPassword = rs.getString("Password");

                Users user = new Users(userID, userName, userPassword);
                usersObservableList.add(user);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return usersObservableList;
    }

    /** This is the get user method.
     this method is used to verify user entered username and password matches username and password from the users table within the client_schedule database to trigger a login status message.
     @param userName user name to verify
     @param userPassword user password to verify
     @return Returns boolean value for user match
     */
    public static int getUser(String userName, String userPassword) {

        int userMatch = 0;

        try {
            String sql = "SELECT User_Name, Password FROM client_schedule.users WHERE User_Name = ? AND Password = ?";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ps.setString(1, userName);
            ps.setString(2, userPassword);
            ResultSet rs = ps.executeQuery();

            ResourceBundle rb = ResourceBundle.getBundle("C195.AppointmentScheduleApp/Nat", Locale.getDefault());

            if (!rs.next()) {
                if (Locale.getDefault().getLanguage().equals("fr")) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle(rb.getString("loginErrorTitle"));
                    alert.setContentText(rb.getString("loginErrorMessage"));
                    alert.showAndWait();

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle(rb.getString("loginErrorTitle"));
                    alert.setContentText(rb.getString("loginErrorMessage"));
                    alert.showAndWait();
                }
                userMatch = 0;

            } else {

                if (Locale.getDefault().getLanguage().equals("fr")) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle(rb.getString("loginStatusTitle"));
                    alert.setContentText(rb.getString("loginStatusMessage"));
                    alert.showAndWait();

                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle(rb.getString("loginStatusTitle"));
                    alert.setContentText(rb.getString("loginStatusMessage"));
                    alert.setHeaderText(null);
                    alert.showAndWait();
                }
                userMatch = 1;

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
       return userMatch;
    }

    /** This is the get database user match method.
     This method returns the user ID, user name, and user password from user database verification via user name and user password.
     @param userName user name
     @param userPassword user password
     @return Returns user ID, user name, and password from match verification
     */
    public static ObservableList<Users> getDBUserMatch(String userName, String userPassword) {
        ObservableList<Users> userDBMatchList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT User_ID, User_Name, Password FROM client_schedule.users WHERE User_Name = ? AND Password = ?";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ps.setString(1, userName);
            ps.setString(2, userPassword);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                int userID = rs.getInt("User_ID");
                String user_Name = rs.getString("User_Name");
                String user_Password = rs.getString("Password");

                Users user = new Users(userID, user_Name, user_Password);
                userDBMatchList.add(user);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return userDBMatchList;
    }

}
