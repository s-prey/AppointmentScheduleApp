package DBAccess;

import Database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contacts;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** This class is used to contain methods for database contact table read and data querying for the GUI menu controllers.*/
public class DBContacts {

    /** This is the get all contacts method.
     This method returns a list of contacts from the contacts table within the client_schedule database.
     @return Returns list of all contacts from the contacts table
     */
    public static ObservableList<Contacts> getAllContacts() {
        ObservableList<Contacts> contactsList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * from contacts";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int contactID = rs.getInt("Contact_ID");
                String contactName = rs.getString("Contact_Name");
                String contactEmail = rs.getString("Email");

                Contacts contact = new Contacts(contactID, contactName, contactEmail);
                contactsList.add(contact);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return contactsList;
    }
}
