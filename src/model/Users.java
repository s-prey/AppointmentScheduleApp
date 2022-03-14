package model;

/** This class creates Users objects for the appointment schedule application.*/
public class Users {
    public int userID;
    public String userName;
    public String userPassword;

    /** This is the Users object constructor method.
     This method is used to setup parameters for a Users object.
     @param userID user ID
     @param userName user name
     @param userPassword user password
     */
    public Users(int userID, String userName, String userPassword) {
        this.userID = userID;
        this.userName = userName;
        this.userPassword = userPassword;
    }

    /** This is the get user ID method.
     This method returns the user ID.
     @return Returns the user ID
     */
    public int getUserID() {
        return userID;
    }

    /** This is the get user name method.
     This method returns the user name.
     @return Returns the user name
     */
    public String getUserName() {
        return userName;
    }

    /** This is the get user password method.
     This method returns the user password.
     @return Returns the user password
     */
    public String getUserPassword() {
        return userPassword;
    }

    /** This is the set user ID method.
     This method sets the user ID value.
     @param userID The user ID to set
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /** This is the set user name method.
     This method sets the user name value.
     @param userName The user name to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /** This is the set user password method.
     This method sets the user password value.
     @param userPassword The user password to set
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    /** This is the override to string method.
     This method overrides the default String method and returns the user ID and user name for combobox.
     @return Returns the user ID and user name
     */
    @Override
    public String toString() {
        return "(" + userID + ") " + userName;
    }
}
