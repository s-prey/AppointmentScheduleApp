package DBAccess;

import Database.DBConnection;
import controller.LoginController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointments;
//import model.Reports;

import java.net.PortUnreachableException;
import java.sql.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;
import static java.sql.Timestamp.valueOf;

public class DBAppointments {


    public static ObservableList<Appointments> getAllAppointments() {
        ObservableList<Appointments> allAppointments = FXCollections.observableArrayList();

        try {
            String sql = "SELECT Appointment_ID, Title, Description, Location, contacts.Contact_Name, contacts.Contact_ID, Type, Start, End, customers.Customer_ID, User_ID " +
                    "FROM appointments, contacts, customers WHERE appointments.Contact_ID=contacts.Contact_ID AND appointments.Customer_ID=customers.Customer_ID";
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

//************************* NEED TO CHECK IF THIS CAN ADJUSTED / THIS IS A LAMBDA EXPRESSSION *************************************************************
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
    




//************** not being u *********************************************************************
   /* public static ObservableList<Appointments> getTodaysAppointments() {
        ObservableList<Appointments> todaysAppointmentsList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT Appointment_ID, Title, Description, Location, contacts.Contact_ID, contacts.Contact_Name, Type, Start, End, customers.Customer_ID, User_ID " +
                    "FROM appointments, contacts, customers WHERE appointments.Contact_ID=contacts.Contact_ID AND appointments.Customer_ID=customers.Customer_ID AND " +
                    "Start >= ? AND Start <= ?";

            LocalDate todaysDate = LocalDate.now();
            LocalTime midnightTime = LocalTime.MIDNIGHT;
            LocalTime twelveFiftyNine = LocalTime.MIDNIGHT.minusMinutes(1);
            LocalDateTime midnightToday = LocalDateTime.of(todaysDate, midnightTime);
            Timestamp timeStampStart = valueOf(midnightToday);

            LocalDateTime lastMinToday = LocalDateTime.of(todaysDate, twelveFiftyNine);
            Timestamp timeStampEnd = valueOf(lastMinToday);

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ps.setTimestamp(1, timeStampStart);
            ps.setTimestamp(2, timeStampEnd);
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
                    todaysAppointmentsList.add(appointment);
                }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return todaysAppointmentsList;
    }

    */

    //*************************** SEE IF SQL STATEMENT CAN BE ADJUSTED ***************************
    public static ObservableList<Appointments> getAppointmentsByMonth() {
        ObservableList<Appointments> apptByMonthList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT Appointment_ID, Title, Description, Location, contacts.Contact_ID, contacts.Contact_Name, " +
                    "Type, Start, End, customers.Customer_ID, User_ID FROM appointments, contacts, customers " +
                    "WHERE appointments.Contact_ID=contacts.Contact_ID AND appointments.Customer_ID=customers.Customer_ID " +
                    "AND month(Start) = month(now())";

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

    public static ObservableList<Appointments> getAppointmentsByWeek() {
        ObservableList<Appointments> apptByWeekList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT Appointment_ID, Title, Description, Location, contacts.Contact_ID, contacts.Contact_Name, Type, Start, End, customers.Customer_ID, User_ID " +
                    "FROM appointments, contacts, customers WHERE appointments.Contact_ID=contacts.Contact_ID AND appointments.Customer_ID=customers.Customer_ID AND " +
                    "Start >= ? AND Start <= date_add(?, interval 7 day)";

            // ************** Gets Monday before date now ************

            // Gets midnight time as of today
            LocalDate today = LocalDate.now();
            LocalTime midnight = LocalTime.MIDNIGHT;

            // Go's back in time from today to reach Monday
            LocalDate monday = today;
            while (monday.getDayOfWeek() != DayOfWeek.MONDAY) {
                monday = monday.minusDays(1);
            }

            LocalDateTime mondayMidnight = LocalDateTime.of(monday, midnight);
            Timestamp timestamp = valueOf(mondayMidnight);
            // ************* Ends get Monday code***********

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
                LocalDateTime dateTimeStart = rs.getTimestamp("Start").toLocalDateTime();       //UTC
                LocalDateTime dateTimeEnd = rs.getTimestamp("End").toLocalDateTime();          //UTC
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

    //*********************************** SEE IF SQL STATEMENT CAN BE ADJUSTED **************************************************
    public static ObservableList<Appointments> getAppointmentsByContact(int contact_ID) {
        ObservableList<Appointments> apptByContactList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT Appointment_ID, Title, Description, Location, contacts.Contact_ID, contacts.Contact_Name, Type, Start, End, customers.Customer_ID, User_ID " +
                   "FROM appointments, contacts, customers WHERE appointments.Contact_ID=contacts.Contact_ID AND appointments.Customer_ID=customers.Customer_ID AND contacts.Contact_ID = ?";

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

    //******************* METHOD FOR APPOINTMENT TOTAL BY MONTH ***************************************************************
    public static ObservableList<Appointments> getAppointmentTotalByMonth() {
        ObservableList<Appointments> apptByMonthTotalList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT date_format(Start, '%M') AS Month,count(Start) AS Count \n" +
                    "FROM client_schedule.appointments \n" +
                    "group by date_format(Start, '%M') \n" +
                    "order by date_format(Start, '%M')";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String month = rs.getString("Month");
                String count = rs.getString("Count");

                Appointments appointment = new Appointments(month, count);
                apptByMonthTotalList.add(appointment);
                //apptByMonthTotalList.add(new Reports(month, count));



            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return apptByMonthTotalList;
    }

    public static ObservableList<Appointments> getAppointmentTotalByType() {
        ObservableList<Appointments> apptByTypeTotalList = FXCollections.observableArrayList();


        try {
            String sql = "SELECT Type,count(Type) AS Type_Count FROM client_schedule.appointments GROUP BY Type";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();


            while (rs.next()) {
                String apptType = rs.getString("Type");
                int apptTypeTotal = Integer.parseInt(rs.getString("Type_Count"));
                System.out.println(apptType + " " + apptTypeTotal);

                Appointments appointment = new Appointments(apptType, apptTypeTotal);
                apptByTypeTotalList.add(appointment);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return apptByTypeTotalList;
    }

//********************* SEE IF SQL STATEMENT CAN BE AJDUSTED *********************************************************************
    public static ObservableList<Appointments> getAppointmentsByCustomer(int customer_ID) {
        ObservableList<Appointments> apptByCustomerList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT Appointment_ID, Title, Description, Location, contacts.Contact_ID, contacts.Contact_Name, Type, Start, End, customers.Customer_ID, User_ID " +
                    "FROM appointments, contacts, customers WHERE appointments.Contact_ID=contacts.Contact_ID AND appointments.Customer_ID=customers.Customer_ID " +
                    "AND customers.Customer_ID = ?";

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


