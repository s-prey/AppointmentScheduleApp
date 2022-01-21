package DBAccess;

import Database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Countries;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBCountries {

    public static ObservableList<Countries> getAllCountries() {
        ObservableList<Countries> countriesObservableList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * from countries";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int countryID = rs.getInt("Country_ID");
                String  countryName = rs.getString("Country");

                Countries country = new Countries(countryID, countryName);
                countriesObservableList.add(country);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return countriesObservableList;
    }
}
