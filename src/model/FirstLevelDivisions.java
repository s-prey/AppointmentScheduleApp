package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FirstLevelDivisions {


    private int divisionID;
    private String divisionName;
    public int countryID;

    public FirstLevelDivisions(int divisionID, String divisionName, int countryID) {
        this.divisionID = divisionID;
        this.divisionName = divisionName;
        this.countryID = countryID;
    }



    public int getDivisionID() {
        return divisionID;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public int getCountryID() {
        return countryID;
    }


}
