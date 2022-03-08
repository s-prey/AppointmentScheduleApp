package DBAccess;

import Database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.FirstLevelDivisions;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBFirstLevelDivisions {

    public static ObservableList<FirstLevelDivisions> getAllFirstLevelDivisions() {
        ObservableList<FirstLevelDivisions> firstLevelDivisionsList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * from client_schedule.first_level_divisions";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int divisionID = rs.getInt("Division_ID");
                String divisionName = rs.getString("Division");

                FirstLevelDivisions firstLevelDivision = new FirstLevelDivisions(divisionID, divisionName);
                firstLevelDivisionsList.add(firstLevelDivision);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return firstLevelDivisionsList;
    }


    public static ObservableList<FirstLevelDivisions> getDivisionsByCountry(int countryID) {
        ObservableList<FirstLevelDivisions> firstLevelDivisionsByCountryList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM client_schedule.first_level_divisions WHERE Country_ID = ?";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ps.setInt(1, countryID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int divisionID = rs.getInt("Division_ID");
                String divisionName = rs.getString("Division");
                FirstLevelDivisions firstLevelDivisions = new FirstLevelDivisions(divisionID, divisionName);
                firstLevelDivisionsByCountryList.add(firstLevelDivisions);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return firstLevelDivisionsByCountryList;
    }

}
