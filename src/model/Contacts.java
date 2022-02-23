package model;

public class Contacts {
    public int contactID;
    public String contactName;
    public String contactEmail;

    public Contacts (int contactID, String contactName, String contactEmail) {
        this.contactID = contactID;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
    }

    public int getContactID() {
        return contactID;
    }

    public String getContactName() {
        return contactName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    @Override
    public String toString() {
        return contactName;
    }
}
