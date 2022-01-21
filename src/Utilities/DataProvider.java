package Utilities;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.FirstLevelDivisions;
import model.Users;

public class DataProvider {

    private static ObservableList<Users> allUsers = FXCollections.observableArrayList();
    private static ObservableList<FirstLevelDivisions> allDivisions = FXCollections.observableArrayList();

    public static void addUser (Users user) {
        allUsers.add(user);
    }

    public static ObservableList<Users> getAllUsers(){
        return allUsers;
    }

    public static void addDivisions (FirstLevelDivisions div) {
        allDivisions.add(div);
    }

    public static ObservableList<FirstLevelDivisions> getAllDivisions() {
        return allDivisions;
    }
}
