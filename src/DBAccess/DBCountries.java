package DBAccess;

import Database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Countries;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBCountries {

    public static ObservableList<Countries> getAllCountries() throws SQLException {
        ObservableList<Countries> countriesObservableList = FXCollections.observableArrayList();
        String query = "SELECT * from countries";
        PreparedStatement ps = DBConnection.getConnection().prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int countryID = rs.getInt("Country_ID");
            String countryName = rs.getString("Country");

            Countries country = new Countries(countryID, countryName);
            countriesObservableList.add(country);
        }
        return countriesObservableList;
    }
}
