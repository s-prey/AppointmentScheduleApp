package DBAccess;

import Database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointments;

import java.sql.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;
import static java.sql.Timestamp.valueOf;

public class DBAppointments {


    public static ObservableList<Appointments> getAllAppointments() {
        ObservableList<Appointments> allAppointments = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * from appointments";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int apptID = rs.getInt("Appointment_ID");
                String apptTitle = rs.getString("Title");
                String apptDescription = rs.getString("Description");
                String apptLocation = rs.getString("Location");
                String apptType = rs.getString("Type");
                LocalDateTime apptStartDateTime = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime apptEndDateTime = rs.getTimestamp("End").toLocalDateTime();
                int customerID = rs.getInt("Customer_ID");
                int userID = rs.getInt("User_ID");
                int contactID = rs.getInt("Contact_ID");
                String contactName = rs.getString("Contact_Name");

                Appointments appointment = new Appointments(apptID, apptTitle, apptDescription, apptLocation, apptType,
                        apptStartDateTime, apptEndDateTime, customerID, userID, contactID, contactName);
                allAppointments.add(appointment);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return allAppointments;
    }


    public static void addAppointment(String apptTitle, String apptDesc, String apptLocation, String apptType,
                                      LocalDateTime apptStartDatetime, LocalDateTime apptEndDateTime, int customerID,
                                      int userID, int contactID) {
        try {
            String sql = "INSERT INTO client_schedule.appointments VALUES(NULL, ?, ?, ?, ?, ?, ?, NULL, NULL, NULL, NULL, ?, ?, ?)";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ps.setString(1, apptTitle);
            ps.setString(2, apptDesc);
            ps.setString(3, apptLocation);
            ps.setString(4, apptType);
            ps.setTimestamp(5, valueOf(apptStartDatetime));
            ps.setTimestamp(6, valueOf(apptEndDateTime));
            ps.setInt(7, customerID);
            ps.setInt(8, userID);
            ps.setInt(9, customerID);
            ps.execute();


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void updateAppointment(int apptID, String apptTitle, String apptDesc, String apptLocation,
                                         String apptType, LocalDateTime apptStartDatetime, LocalDateTime apptEndDateTime,
                                         int customerID, int userID, int contactID) {
        try {
            String sql = "UPDATE client_schedule.appointments set Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Customer_ID = ?, User_ID = ?, Contact_ID " +
                    "WHERE Appointment_ID = ?";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ps.setString(1, apptTitle);
            ps.setString(2, apptDesc);
            ps.setString(3, apptLocation);
            ps.setString(4, apptType);
            ps.setTimestamp(5, valueOf(apptStartDatetime));
            ps.setTimestamp(6, valueOf(apptEndDateTime));
            ps.setInt(7, customerID);
            ps.setInt(8, userID);
            ps.setInt(9, customerID);
            ps.setInt(10, apptID);
            ps.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void deleteAppointment(int apptID) {

        try {
            String sql = "DELETE FROM client_schedule.appointments WHERE Appointment_ID = ?";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ps.setInt(1, apptID);
            ps.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}


