package model;

import DBAccess.DBFirstLevelDivisions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FirstLevelDivisions {


    private int divisionID;
    private String divisionName;
    public int countryID;

    public FirstLevelDivisions(int divisionID, String divisionName/*, int countryID*/) {
        this.divisionID = divisionID;
        this.divisionName = divisionName;
        this.countryID = countryID;
    }

    //********************** MAY WANT TO CHANGE SOME VARIABLE/METHOD NAMES *****************************************************
    //*********************** THIS MAY WORK IN CONTROLLERS, BUT IF REDUNDUNT KEEP IN HERE **************************************

    public static FirstLevelDivisions getDivisionIDMatch(int flDivisionID) {
        ObservableList<FirstLevelDivisions> divisions = DBFirstLevelDivisions.getAllFirstLevelDivisions();

        FirstLevelDivisions division = null;

        for (int i = 0; i < divisions.size(); i++) {
            FirstLevelDivisions firstLevelDivisions = divisions.get(i);
            if (firstLevelDivisions.getDivisionID() != flDivisionID) {
                continue;
            } else {
                division = firstLevelDivisions;
                break;
            }
        }
        return division;
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

    @Override
    public String toString() {
        return "(" + divisionID + ") " + divisionName;
    }

}
