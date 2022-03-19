package model;

import java.time.LocalDateTime;

/** This class creates Appointments objects for the appointment schedule application.*/
public class Appointments {

    private int apptID;
    private String apptTitle;
    private String apptDescription;
    private String apptLocation;
    private String apptType;
    private LocalDateTime apptStartDateTime;
    private LocalDateTime apptEndDateTime;
    public int customerID;
    public int userID;
    public int contactID;
    public String contactName;
    public String month;
    public String type;
    public String count;

    /** This is the Appointments object default constructor method.
     This method is used to setup parameters for an Appointments object.
     @param apptID appointment ID
     @param apptTitle appointment title
     @param apptDescription appointment description
     @param apptLocation appointment location
     @param contactName appointment contact name
     @param apptType appointment type
     @param apptStartDateTime appointment start date and time
     @param apptEndDateTime appointment end date and time
     @param customerID appointment customer ID
     @param userID appointment user ID
     @param contactID appointment contact ID
     */
    public Appointments(int apptID, String apptTitle, String apptDescription, String apptLocation, String contactName,
                        String apptType, LocalDateTime apptStartDateTime, LocalDateTime apptEndDateTime, int customerID, int userID, int contactID) {
        this.apptID = apptID;
        this.apptTitle = apptTitle;
        this.apptDescription = apptDescription;
        this.apptLocation = apptLocation;
        this.contactName = contactName;
        this.apptType = apptType;
        this.apptStartDateTime = apptStartDateTime;
        this.apptEndDateTime = apptEndDateTime;
        this.customerID = customerID;
        this.userID = userID;
        this.contactID = contactID;
    }

    /** This is the Appointments object override constructor method.
     This method is used to setup the parameters for an Appointments object.
     @param month Appointments month
     @param type Appointments type
     @param count Appointments count
     */
    public Appointments(String month, String type, String count) {
        this.month = month;
        this.type = type;
        this.count = count;
    }

    /** This is the set appointment ID method.
     This method sets the appointment ID value.
     @param apptID The appointment ID to set
     */
    public void setApptID(int apptID) {
        this.apptID = apptID;
    }

    /** This is the set appointment title method.
     This method sets the appointment title value.
     @param apptTitle The appointment title to set
     */
    public void setApptTitle(String apptTitle) {
        this.apptTitle = apptTitle;
    }

    /** This is the set appointment description method.
     This method sets the appointment description value.
     @param apptDescription The appointment description to set
     */
    public void setApptDescription(String apptDescription) {
        this.apptDescription = apptDescription;
    }

    /** This is the set appointment location method.
     This method sets the appointment location value.
     @param apptLocation The appointment location to set
     */
    public void setApptLocation(String apptLocation) {
        this.apptLocation = apptLocation;
    }

    /** This is the set appointment type method.
     This method sets the appointment type value.
     @param apptType The appointment type to set
     */
    public void setApptType(String apptType) {
        this.apptType = apptType;
    }

    /** This is the set appointment start date and time method.
     This method sets the appointment start date and time value.
     @param apptStartDateTime The appointment start datetime to set
     */
    public void setApptStartDateTime(LocalDateTime apptStartDateTime) {
        this.apptStartDateTime = apptStartDateTime;
    }

    /** This is the set appointment end date and time method.
     This method sets the appointment end date and time value.
     @param apptEndDateTime The appointment end datetime to set
     */
    public void setApptEndDateTime(LocalDateTime apptEndDateTime) {
        this.apptEndDateTime = apptEndDateTime;
    }

    /** This is the set customer ID method.
     This method sets the customer ID value.
     @param customerID The customer ID to set
     */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    /** This is the set user ID method.
     This method sets the user ID value.
     @param userID The user ID to set
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /** This is the set contact ID method.
     This method sets the contact ID value.
     @param contactID The contact ID to set
     */
    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    /** This is the set contact name method.
     This method sets the contact name value.
     @param contactName The contact name to set
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /** This is the set month method.
     This method sets the appointment month value.
     @param month The appointment month to set
     */
    public void setMonth(String month) {
        this.month = month;
    }

    /** This is the set type method.
     This method sets the appointment type value.
     @param type The appointment type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /** This is the set count method.
     This method sets the appointment count value.
     @param count The appointment count to set
     */
    public void setCount(String count) {
        this.count = count;
    }

    /** This is the get appointment ID method.
     This method returns the appointment ID value.
     @return Returns the appointment ID
     */
    public int getApptID() {
        return apptID;
    }

    /** This is the get appointment title method.
     This method returns the appointment title.
     @return Returns the appointment title
     */
    public String getApptTitle() {
        return apptTitle;
    }

    /** This is the get appointment description method.
     This method returns the appointment description.
     @return Returns the appointment description
     */
    public String getApptDescription() {
        return apptDescription;
    }

    /** This is the get appointment location method.
     This method returns the appointment location.
     @return Returns the appointment location
     */
    public String getApptLocation() {
        return apptLocation;
    }

    /** This is the get appointment type method.
     This method returns the appointment type.
     @return Returns the appointment type
     */
    public String getApptType() {
        return apptType;
    }

    /** This is the get appointment start datetime method.
     This method return the appointment start datetime.
     @return Returns the appointment start datetime
     */
    public LocalDateTime getApptStartDateTime() {
        return apptStartDateTime;
    }

    /** This is the get appointment end datetime method.
     This method returns the appointment end datetime.
     @return Returns the appointment end datetime
     */
    public LocalDateTime getApptEndDateTime() {
        return apptEndDateTime;
    }

    /** This is the get customer ID method.
     This method returns the customer ID.
     @return Returns the customer ID
     */
    public int getCustomerID() {
        return customerID;
    }

    /** This is the get user ID method.
     This method returns the user ID.
     @return Returns the user ID
     */
    public int getUserID() {
        return userID;
    }

    /** This is the get contact ID method.
     This method returns the contact ID.
     @return Returns the contact ID
     */
    public int getContactID() {
        return contactID;
    }

    /** This is the get contact name method.
     This method returns the contact name.
     @return Returns the contact name
     */
    public String getContactName() {
        return contactName;
    }

    /** This is the get month method.
     This method returns the appointment month.
     @return Returns the appointment month
     */
    public String getMonth() {
        return month;
    }

    /** This is the get type method.
     This method returns the appointment type.
     @return Returns the appointment type
     */
    public String getType() {
        return type;
    }

    /** This is the get count method.
     This method returns the appointment count.
     @return Returns the appointment count
     */
    public String getCount() {
        return count;
    }
}
