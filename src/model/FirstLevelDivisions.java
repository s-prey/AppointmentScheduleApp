package model;

import DBAccess.DBFirstLevelDivisions;
import javafx.collections.ObservableList;

/** This class creates FirstLevelDivisions objects for the appointment schedule application.*/
public class FirstLevelDivisions {


    private int divisionID;
    private String divisionName;

    /** This is the FirstLevelDivisions object constructor method.
     This method is used to setup parameters for a FirstLevelDivision object.
     @param divisionID first level division ID
     @param divisionName first level division name
     */
    public FirstLevelDivisions(int divisionID, String divisionName) {
        this.divisionID = divisionID;
        this.divisionName = divisionName;
    }

    /** This is the get division ID match method.
     This method returns the first level division ID and name from the FirstLevelDivisions observable list via division ID.
     @param flDivisionID first level division ID
     @return Returns fist level division ID and first level division name based on fist level division ID
     */
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

    /** This is the get division ID method.
     This method returns the first level division ID.
     @return Returns first level division ID
     */
    public int getDivisionID() {
        return divisionID;
    }

    /** This is the get division name method.
     This method returns the first level division name.
     @return Returns the first level division name
     */
    public String getDivisionName() {
        return divisionName;
    }

    /** This is the override to string method.
     This method overrides the default String method and returns the fist level division ID and
     first level division name for combobox.
     @return Returns the fist level division ID and first level division name
     */
    @Override
    public String toString() {
        return "(" + divisionID + ") " + divisionName;
    }

}
