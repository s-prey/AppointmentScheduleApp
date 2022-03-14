package model;

/** This class creates Contacts objects for the appointment schedule application.*/
public class Contacts {
    public int contactID;
    public String contactName;
    public String contactEmail;

    /** This is the Contacts object constructor method.
     This method is used to setup parameters for an Contacts object.
     @param contactID contact ID
     @param contactName contact name
     @param contactEmail contact email
     */
    public Contacts (int contactID, String contactName, String contactEmail) {
        this.contactID = contactID;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
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

    /** This is the get contact email method.
     This method returns the contact email.
     @return Returns the contact email
     */
    public String getContactEmail() {
        return contactEmail;
    }

    /** This is the Override to string method.
     This method overrides the default String method and returns the contact name.
     @return Returns contact name
     */
    @Override
    public String toString() {
        return contactName;
    }
}
