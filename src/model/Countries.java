package model;

import DBAccess.DBCountries;
import javafx.collections.ObservableList;

public class Countries {
    private int countryID;
    private String countryName;

    public Countries(int countryID, String countryName) {
        this.countryID = countryID;
        this.countryName = countryName;
    }

    public Countries() {

    }

    public int getCountryID() {
        return countryID;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public static Countries getDivisionAndCountryMatch(int countryID) {
        ObservableList<Countries> divisions = DBCountries.getAllCountries();

        Countries country = null;

        for (int i = 0; i < divisions.size(); i++) {
            Countries selectCountry = divisions.get(i);

            if (selectCountry.getCountryID() != countryID) {
                continue;
            } else {
                country = selectCountry;
                break;
            }
        }
        return country;
    }

    @Override
    public String toString() {
        return countryName;
    }
}
