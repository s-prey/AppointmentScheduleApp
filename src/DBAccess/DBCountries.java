package DBAccess;

import Database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Countries;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** This class is used to contain methods for database countries table reading and data querying for the GUI menu controllers.*/
public class DBCountries {

    /** This is the get all countries method.
     This method returns a list of all countries from the countries table within the client_schedule database.
     @return Returns all countries from the countries table
     */
    public static ObservableList<Countries> getAllCountries() {
        ObservableList<Countries> countriesList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * from countries";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int countryID = rs.getInt("Country_ID");
                String  countryName = rs.getString("Country");

                Countries country = new Countries(countryID, countryName);
                countriesList.add(country);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return countriesList;
    }
}
