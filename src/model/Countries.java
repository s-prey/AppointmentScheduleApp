package model;

import DBAccess.DBCountries;
import javafx.collections.ObservableList;

/** This class creates Countries objects for the appointment schedule application.*/
public class Countries {
    private int countryID;
    private String countryName;

    /** This is the Countries object constructor method.
     This method is used to setup parameters for an Countries object.
     @param countryID country ID
     @param countryName country name
     */
    public Countries(int countryID, String countryName) {
        this.countryID = countryID;
        this.countryName = countryName;
    }

    /** This is the get country ID method.
     This method returns the country ID.
     @return Returns the country ID
     */
    public int getCountryID() {
        return countryID;
    }

    /** This is the get country name method.
     This method returns the country name.
     @return Returns the country name
     */
    public String getCountryName() {
        return countryName;
    }

    /** This is the set country ID method.
     This method sets the country ID value.
     @param countryID The country ID to set
     */
    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    /** This is the set country name method.
     This method sets the country name value.
     @param countryName The country name to set
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /** This is the get division and country match method.
     This method returns the country ID based on first level division ID value match.
     @param countryID country ID
     @return Returns the country ID match
     */
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

    /** This is the Override to string method.
     This method overrides the default String method and returns the country name.
     @return Returns the country name
     */
    @Override
    public String toString() {
        return countryName;
    }
}
