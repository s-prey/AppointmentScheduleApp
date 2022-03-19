package DBAccess;

import Database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointments;
import java.sql.*;
import java.time.*;
import static java.sql.Timestamp.valueOf;

/** This class is used to contain methods for Database appointment table CRUD and data querying for GUI menu controllers.*/
public class DBAppointments {

    /** This is the get all appointments method.
     This method returns all appointments data from the appointments, contacts, and customer tables withing the client_schedule database.
     @return Returns all appointments
     */
    public static ObservableList<Appointments> getAllAppointments() {
        ObservableList<Appointments> allAppointments = FXCollections.observableArrayList();

        try {
            String sql = "SELECT Appointment_ID, Title, Description, Location, o.Contact_Name, o.Contact_ID, Type, Start, End, c.Customer_ID, User_ID " +
                    "FROM appointments AS a, contacts AS o, customers AS c WHERE a.Contact_ID=o.Contact_ID AND a.Customer_ID=c.Customer_ID";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int apptID = rs.getInt("Appointment_ID");
                String apptTitle = rs.getString("Title");
                String apptDescription = rs.getString("Description");
                String apptLocation = rs.getString("Location");
                String apptType = rs.getString("Type");
                String contactName = rs.getString("Contact_Name");
                LocalDateTime apptStartDateTime = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime apptEndDateTime = rs.getTimestamp("End").toLocalDateTime();
                int customerID = rs.getInt("Customer_ID");
                int userID = rs.getInt("User_ID");
                int contactID = rs.getInt("Contact_ID");

                Appointments appointment = new Appointments(apptID, apptTitle, apptDescription, apptLocation,
                        contactName, apptType, apptStartDateTime, apptEndDateTime, customerID, userID, contactID);
                allAppointments.add(appointment);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return allAppointments;
    }

    /** This is the add appointment method.
     This method creates an appointment table row in the appointments table, within the client_schedule database.
     @param apptTitle appointment title to add
     @param apptDesc appointment description to add
     @param apptLocation appointment location to add
     @param apptType appointment type to add
     @param apptStartDatetime appointment start datetime to add
     @param apptEndDateTime appointment end datetime to add
     @param customerID appointment customer ID to add
     @param userID user ID to add
     @param contactID appointment contact ID to add
     */
    public static void addAppointment(String apptTitle, String apptDesc, String apptLocation, String apptType,
                                      LocalDateTime apptStartDatetime, LocalDateTime apptEndDateTime, int customerID,
                                      int userID, int contactID) {
        try {
            LocalDateTime localDateTimeToAdd = LocalDateTime.now();
            String createdByToAdd = "test";
            Timestamp lastUpdateToAdd = Timestamp.valueOf(LocalDateTime.now());
            String lastUpdatedByToAdd = "test";

            String sql = "INSERT INTO client_schedule.appointments VALUES(NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ps.setString(1, apptTitle);
            ps.setString(2, apptDesc);
            ps.setString(3, apptLocation);
            ps.setString(4, apptType);
            ps.setTimestamp(5, valueOf(apptStartDatetime));
            ps.setTimestamp(6, valueOf(apptEndDateTime));
            ps.setTimestamp(7, Timestamp.valueOf(localDateTimeToAdd));
            ps.setString(8, createdByToAdd);
            ps.setTimestamp(9, lastUpdateToAdd);
            ps.setString(10, lastUpdatedByToAdd);

            ps.setInt(11, customerID);
            ps.setInt(12, userID);
            ps.setInt(13, contactID);
            ps.execute();


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /** This is the update appointment method.
     This method updates an appointment table row in the appointments table, within the client_schedule database.
     @param apptID appointment ID to update
     @param apptTitle appointment title to update
     @param apptDesc appointment description to update
     @param apptLocation appointment location to update
     @param apptType appointment type to update
     @param apptStartDatetime appointment start datetime to update
     @param apptEndDateTime appointment end datetime to update
     @param customerID appointment customer ID to update
     @param userID user ID to update
     @param contactID appointment contact ID to update
     */
    public static void updateAppointment(int apptID, String apptTitle, String apptDesc, String apptLocation,
                                         String apptType, LocalDateTime apptStartDatetime, LocalDateTime apptEndDateTime,
                                         int customerID, int userID, int contactID) {
        try {
            Timestamp lastUpdateToAdd = Timestamp.valueOf(LocalDateTime.now());
            String lastUpdatedByToAdd = "test";

            String sql = "UPDATE client_schedule.appointments set Title = ?, Description = ?, Location = ?, Type = ?, " +
                        "Start = ?, End = ?, Last_Update = ?, Last_Updated_By = ?,Customer_ID = ?, User_ID = ?, " +
                        "Contact_ID = ? WHERE Appointment_ID = ?";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ps.setString(1, apptTitle);
            ps.setString(2, apptDesc);
            ps.setString(3, apptLocation);
            ps.setString(4, apptType);
            ps.setTimestamp(5, valueOf(apptStartDatetime));
            ps.setTimestamp(6, valueOf(apptEndDateTime));
            ps.setTimestamp(7, lastUpdateToAdd);
            ps.setString(8, lastUpdatedByToAdd);
            ps.setInt(9, customerID);
            ps.setInt(10, userID);
            ps.setInt(11, contactID);
            ps.setInt(12, apptID);
            ps.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /** This is the delete appointment method.
     This method deletes an appointment table row via appointment ID in the appointments table, within the client_schedule database.
     @param apptID appointment ID for deletion*/
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

    /** This is the get appointments 15 minutes method.
     This method returns all appointments that are withing 15 minutes of the user login time.
     LAMBDA EXPRESSION: A lambda expression has been utilized in this method to provide easier reading of code for checking appointment start and end time is within 15 minutes of timeNow.
     @param userID user ID to check appointments
     @return Returns all appointments with the next 15 minutes if appointment start or end date time is within 15 minutes of timeNow.
     */
    public static ObservableList<Appointments> getApppointments15Minutes(int userID) {
        ObservableList<Appointments> allApptList = getAllAppointments();

        LocalDate todaysDate = LocalDate.now();
        LocalTime timeNow = LocalTime.now();
        LocalTime nowPlus15Mins = LocalTime.now().plusMinutes(15);
        LocalDateTime dateTimeNow = LocalDateTime.of(todaysDate, timeNow);
        LocalDateTime todayAnd15Min = LocalDateTime.of(todaysDate, nowPlus15Mins);

        ObservableList<Appointments> apptsWithin15MinsList = allApptList.filtered(appointment -> {
            if (appointment.getUserID() == userID &&
                    (appointment.getApptStartDateTime().isAfter(dateTimeNow.minusMinutes(1)) && appointment.getApptStartDateTime().isBefore(todayAnd15Min.plusMinutes(1))) ||
                    (appointment.getApptEndDateTime().isAfter(dateTimeNow.minusMinutes(1)) && appointment.getApptEndDateTime().isBefore(todayAnd15Min.plusMinutes(1))) ||
                    (appointment.getApptStartDateTime().isBefore(dateTimeNow) && appointment.getApptEndDateTime().isAfter(todayAnd15Min))) {

                return true;
            } else {
                return false;
            }
        });
            return apptsWithin15MinsList;
    }

    /** This is the get appointments by month method.
     This method reads appointment information from the appointments, contacts, and customers tables within the client_schedule database and filters by current month.
     @return Returns list of appointments for the current month
     */
    public static ObservableList<Appointments> getAppointmentsByMonth() {
        ObservableList<Appointments> apptByMonthList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT Appointment_ID, Title, Description, Location, o.Contact_ID, o.Contact_Name, Type, " +
                    "Start, End, c.Customer_ID, User_ID FROM appointments AS a, contacts AS o, customers AS c " +
                    "WHERE a.Contact_ID=o.Contact_ID AND a.Customer_ID=c.Customer_ID AND month(Start) = month(now())";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int apptID = rs.getInt("Appointment_ID");
                String apptTitle = rs.getString("Title");
                String apptDesc = rs.getString("Description");
                String apptLocation = rs.getString("Location");
                String apptType = rs.getString("Type");
                LocalDateTime dateTimeStart = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime dateTimeEnd = rs.getTimestamp("End").toLocalDateTime();
                int customerID = rs.getInt("Customer_ID");
                int userID = rs.getInt("User_ID");
                int contactID = rs.getInt("Contact_ID");
                String contactName = rs.getString("Contact_Name");

                Appointments appointment = new Appointments(apptID, apptTitle, apptDesc, apptLocation, contactName,
                        apptType, dateTimeStart, dateTimeEnd, customerID, userID, contactID);
                apptByMonthList.add(appointment);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return apptByMonthList;
    }

    /** This is the get appointments by week method.
     This method reads appointment information from the appointments, contacts, and customers tables within the client_schedule database and filters by current week.
     @return Returns list of appointments for the current week
     */
    public static ObservableList<Appointments> getAppointmentsByWeek() {
        ObservableList<Appointments> apptByWeekList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT Appointment_ID, Title, Description, Location, o.Contact_ID, o.Contact_Name, Type, " +
                    "Start, End, c.Customer_ID, User_ID FROM appointments AS a, contacts AS o, customers AS c " +
                    "WHERE a.Contact_ID=o.Contact_ID AND a.Customer_ID=c.Customer_ID AND Start >= ? AND " +
                    "Start <= date_add(?, interval 7 day)";


            LocalDate today = LocalDate.now();
            LocalTime midnight = LocalTime.MIDNIGHT;

            LocalDate monday = today;
            while (monday.getDayOfWeek() != DayOfWeek.MONDAY) {
                monday = monday.minusDays(1);
            }

            LocalDateTime mondayMidnight = LocalDateTime.of(monday, midnight);
            Timestamp timestamp = valueOf(mondayMidnight);

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ps.setTimestamp(1, timestamp);
            ps.setTimestamp(2, timestamp);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int apptID = rs.getInt("Appointment_ID");
                String apptTitle = rs.getString("Title");
                String apptDesc = rs.getString("Description");
                String apptLocation = rs.getString("Location");
                String apptType = rs.getString("Type");
                LocalDateTime dateTimeStart = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime dateTimeEnd = rs.getTimestamp("End").toLocalDateTime();
                int customerID = rs.getInt("Customer_ID");
                int userID = rs.getInt("User_ID");
                int contactID = rs.getInt("Contact_ID");
                String contactName = rs.getString("Contact_Name");

                Appointments appointment = new Appointments(apptID, apptTitle, apptDesc, apptLocation, contactName,
                        apptType, dateTimeStart, dateTimeEnd, customerID, userID, contactID);
                apptByWeekList.add(appointment);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return apptByWeekList;
    }

    /** This is the get appointment by contact method.
     This method reads and returns appointment information from the appointments, contacts, and customers tables within the client_schedule database and returns a list of appointment via selected contact.
     @param contact_ID selected contact ID
     @return Returns appointments list by selected contact
     */
    public static ObservableList<Appointments> getAppointmentsByContact(int contact_ID) {
        ObservableList<Appointments> apptByContactList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT Appointment_ID, Title, Description, Location, o.Contact_ID, o.Contact_Name, Type, " +
                    "Start, End, c.Customer_ID, User_ID FROM appointments AS a, contacts AS o, customers AS c " +
                    "WHERE a.Contact_ID=o.Contact_ID AND a.Customer_ID=c.Customer_ID AND o.Contact_ID = ?";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ps.setInt(1, contact_ID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int apptID = rs.getInt("Appointment_ID");
                String apptTitle = rs.getString("Title");
                String apptDesc = rs.getString("Description");
                String apptLocation = rs.getString("Location");
                String apptType = rs.getString("Type");
                LocalDateTime dateTimeStart = rs.getTimestamp("Start").toLocalDateTime();       //UTC
                LocalDateTime dateTimeEnd = rs.getTimestamp("End").toLocalDateTime();          //UTC
                int customerID = rs.getInt("Customer_ID");
                int userID = rs.getInt("User_ID");
                int contactID = rs.getInt("Contact_ID");
                String contactName = rs.getString("Contact_Name");

                Appointments appointment = new Appointments(apptID, apptTitle, apptDesc, apptLocation, contactName,
                        apptType, dateTimeStart, dateTimeEnd, customerID, userID, contactID);
                apptByContactList.add(appointment);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return apptByContactList;
    }

    /** This is the get appointment total by month and type method.
     This method reads appointment information from the appointments table within the client_schedule database and returns of total appointments by month and appointment type.
     @return Returns appointment count list by month and appointment type
     */
    public static ObservableList<Appointments> getAppointmentTotalByMonthType() {
        ObservableList<Appointments> apptByMonthTotalList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT date_format(Start, '%M') AS Month, Type, count(Start) AS Count " +
                    "FROM appointments group by date_format(Start, '%M'), " +
                    "Type order by date_format(Start, '%M')";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String month = rs.getString("Month");
                String type = rs.getString("Type");
                String count = rs.getString("Count");

                Appointments appointment = new Appointments(month, type, count);
                apptByMonthTotalList.add(appointment);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return apptByMonthTotalList;
    }

    /** This is the get appointments by customer method.
     This method reads appointment information from the appointments, contacts, and customers tables within the client_schedule database and returns a list of appointments by customer.
     @param customer_ID selected customer ID
     @return Returns list of appointments by selected customer
     */
    public static ObservableList<Appointments> getAppointmentsByCustomer(int customer_ID) {
        ObservableList<Appointments> apptByCustomerList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT Appointment_ID, Title, Description, Location, o.Contact_ID, o.Contact_Name, Type, Start, End, c.Customer_ID, User_ID " +
                    "FROM appointments AS a, contacts AS o, customers AS c WHERE a.Contact_ID=o.Contact_ID AND a.Customer_ID=c.Customer_ID " +
                    "AND c.Customer_ID = ?";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ps.setInt(1, customer_ID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int apptID = rs.getInt("Appointment_ID");
                String apptTitle = rs.getString("Title");
                String apptDesc = rs.getString("Description");
                String apptLocation = rs.getString("Location");
                String apptType = rs.getString("Type");
                LocalDateTime dateTimeStart = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime dateTimeEnd = rs.getTimestamp("End").toLocalDateTime();
                int customerID = rs.getInt("Customer_ID");
                int userID = rs.getInt("User_ID");
                int contactID = rs.getInt("Contact_ID");
                String contactName = rs.getString("Contact_Name");

                Appointments appointment = new Appointments(apptID, apptTitle, apptDesc, apptLocation, contactName,
                        apptType, dateTimeStart, dateTimeEnd, customerID, userID, contactID);
                apptByCustomerList.add(appointment);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return apptByCustomerList;
    }
}


