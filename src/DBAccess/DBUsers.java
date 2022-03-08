package DBAccess;

import Database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

public class DBUsers {
    public static Users loggedUser;
    //static ResultSet resultSet = null;


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
                    //alert.setTitle("Success");
                    alert.setContentText(rb.getString("loginStatusMessage"));
                    alert.setHeaderText(null);
                    //alert.setContentText("Log-In Successful!");
                    alert.showAndWait();
                }
                userMatch = 1;

            }



        } catch (SQLException ex) {
            ex.printStackTrace();
        }
       return userMatch;
    }


    private static Users userResultSet(ResultSet resultSet) throws SQLException {
        Users user = new Users();

        user.setUserID(resultSet.getInt("User_ID"));
        user.setUserName(resultSet.getString("User_Name"));
        user.setUserPassword(resultSet.getString("Password"));

        return user;
    }


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
