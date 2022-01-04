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
        ObservableList<FirstLevelDivisions> firstLevelDivisionsObservableList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * from first_level_divisions";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int divisionID = rs.getInt("Division_ID");
                String divisionName = rs.getString("Division");
                int countryID = rs.getInt("Country_ID");

                FirstLevelDivisions firstLevelDivision = new FirstLevelDivisions(divisionID, divisionName, countryID);
                firstLevelDivisionsObservableList.add(firstLevelDivision);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return firstLevelDivisionsObservableList;
    }
}
