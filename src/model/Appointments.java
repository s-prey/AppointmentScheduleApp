package model;

import java.time.LocalDateTime;

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



    public int getApptID() {
        return apptID;
    }

    public String getApptTitle() {
        return apptTitle;
    }

    public String getApptDescription() {
        return apptDescription;
    }

    public String getApptLocation() {
        return apptLocation;
    }

    public String getContactName() {
        return contactName;
    }

    public String getApptType() {
        return apptType;
    }

    public LocalDateTime getApptStartDateTime() {
        return apptStartDateTime;
    }

    public LocalDateTime getApptEndDateTime() {
        return apptEndDateTime;
    }

    public int getCustomerID() {
        return customerID;
    }

    public int getUserID() {
        return userID;
    }

    public int getContactID() {
        return contactID;
    }

    public void setApptID(int apptID) {
        this.apptID = apptID;
    }

    public void setApptTitle(String apptTitle) {
        this.apptTitle = apptTitle;
    }

    public void setApptDescription(String apptDescription) {
        this.apptDescription = apptDescription;
    }

    public void setApptLocation(String apptLocation) {
        this.apptLocation = apptLocation;
    }

    public void setApptType(String apptType) {
        this.apptType = apptType;
    }

    public void setApptStartDateTime(LocalDateTime apptStartDateTime) {
        this.apptStartDateTime = apptStartDateTime;
    }

    public void setApptEndDateTime(LocalDateTime apptEndDateTime) {
        this.apptEndDateTime = apptEndDateTime;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
}
