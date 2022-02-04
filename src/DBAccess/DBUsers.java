package DBAccess;

import Database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUsers {
    public static Users loggedUser;
    static ResultSet resultSet = null;
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
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return usersObservableList;
    }

    public static Users getUser(int userID, Connection connection) throws SQLException {
        Users user = new Users();
        String sql = "SELECT * from users WHERE User_ID=?";
        PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
        ps.setInt(1, userID);
        ps.execute();
        resultSet = ps.getResultSet();

        if (resultSet.next()) {
            user = userResultSet(resultSet);
        }
        resultSet.close();

        return user;
    }

    private static Users userResultSet(ResultSet resultSet) throws SQLException {
        Users user = new Users();

        user.setUserID(resultSet.getInt("User_ID"));
        user.setUserName(resultSet.getString("User_Name"));
        user.setUserPassword(resultSet.getString("Password"));

        return user;
    }
}
