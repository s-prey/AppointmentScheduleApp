package DBAccess;

import Database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.FirstLevelDivisions;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** This class is used to contain methods for database first_level_division table reading and data querying for the GUI menu controllers.*/
public class DBFirstLevelDivisions {

    /** This is the get all first level divisions method.
     This method returns a list of all first level divisions from the first_level_divisions table within the client_schedule database.
     @return Returns list of all first level divisions
     */
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

    /** This is the get divisions by country method.
     This method returns a list of first level divisions based on selected country ID from the first_level_divisions table within the client_schedule database.
     @param countryID selected country ID
     @return Returns list of first level divisions by country
     */
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
