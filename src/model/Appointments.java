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


    public Appointments(int apptID, String apptTitle, String apptDescription, String apptLocation, String apptType,
            LocalDateTime apptStartDateTime, LocalDateTime apptEndDateTime, int customerID, int userID, int contactID, String contactName) {
        this.apptID = apptID;
        this.apptTitle = apptTitle;
        this.apptDescription = apptDescription;
        this.apptLocation = apptLocation;
        this.apptType = apptType;
        this.apptStartDateTime = apptStartDateTime;
        this.apptEndDateTime = apptEndDateTime;
        this.customerID = customerID;
        this.userID = userID;
        this.contactID = contactID;
        this.contactName = contactName;
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
}
