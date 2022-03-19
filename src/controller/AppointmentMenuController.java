package controller;

import DBAccess.DBAppointments;
import DBAccess.DBContacts;
import DBAccess.DBCustomers;
import DBAccess.DBUsers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.*;
import java.io.IOException;
import java.net.URL;
import java.time.*;
import java.util.Optional;
import java.util.ResourceBundle;

/** This class is used as a java fx controller for the appointment schedule application appointment menu GUI screen.*/
public class AppointmentMenuController implements Initializable {

    Stage stage;
    Parent scene;
    private LocalTime businessStart = LocalTime.of(8, 0);
    private LocalTime businessEnd = LocalTime.of(22, 0);
    private int weekStart = DayOfWeek.MONDAY.getValue();
    private int weekEnd = DayOfWeek.FRIDAY.getValue();
    private LocalDateTime startLocalDateTime;
    private LocalDateTime endLocalDateTime;
    private int customerID;
    private int apptID;



    @FXML private TableView<Appointments> appointmentsTableView;
    @FXML private TableColumn<Appointments, Integer> apptIDCol;
    @FXML private TableColumn<Appointments, String> apptTitleCol;
    @FXML private TableColumn<Appointments, String> apptDescriptionCol;
    @FXML private TableColumn<Appointments, String> apptLocationCol;
    @FXML private TableColumn<Appointments, String> contactCol;
    @FXML private TableColumn<Appointments, String> apptTypeCol;
    @FXML private TableColumn<Appointments, LocalDateTime> apptStartDateTime;
    @FXML private TableColumn<Appointments, LocalDateTime> apptEndDateTime;
    @FXML private TableColumn<Appointments, Integer> customerIDCol;
    @FXML private TableColumn<Appointments, Integer> userIDCol;


    @FXML private TextField apptTitleTxtField;
    @FXML private TextField apptDescriptionTxtField;
    @FXML private TextField apptLocationTxtField;
    @FXML private ComboBox<Contacts> contactCmboBox;
    @FXML private TextField apptTypeTxtField;
    @FXML private ComboBox<Customers> customerIDCmboBox;
    @FXML private ComboBox<Users> userIDCmboBox;
    @FXML private DatePicker startDatePicker;
    @FXML private ComboBox<LocalTime> startTimeCmboBox;
    @FXML private DatePicker endDatePicker;
    @FXML private ComboBox<LocalTime> endTimeCmboBox;


    @FXML private TextField appointmentIDTxtField;
    @FXML private RadioButton allAppointmentsRadioBtn;
    @FXML private RadioButton appointmentsByMoRadioBtn;
    @FXML private RadioButton appointmentsByWkRadioBtn;


    private static ObservableList<LocalTime> localStartTimes = FXCollections.observableArrayList();
    private static ObservableList<LocalTime> localEndTimes = FXCollections.observableArrayList();
    public static final ZonedDateTime EST_START_TIME = ZonedDateTime.of(LocalDate.now(), LocalTime.of(8,0), ZoneId.of("America/New_York"));
    public static final ZonedDateTime EST_END_TIME = ZonedDateTime.of(LocalDate.now(), LocalTime.of(22,0), ZoneId.of("America/New_York"));

    /** This is the initialize method.
     This method is used to initialize data for the appointment menu controller.
     @param url uniform resource locator to initialize
     @param resourceBundle resource bundle to initialize
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        appointmentsTableView.setItems(DBAppointments.getAllAppointments());
        apptIDCol.setCellValueFactory(new PropertyValueFactory<>("apptID"));
        apptTitleCol.setCellValueFactory(new PropertyValueFactory<>("apptTitle"));
        apptDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("apptDescription"));
        apptLocationCol.setCellValueFactory(new PropertyValueFactory<>("apptLocation"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        apptTypeCol.setCellValueFactory(new PropertyValueFactory<>("apptType"));
        apptStartDateTime.setCellValueFactory(new PropertyValueFactory<>("apptStartDateTime"));
        apptEndDateTime.setCellValueFactory(new PropertyValueFactory<>("apptEndDateTime"));
        customerIDCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        userIDCol.setCellValueFactory(new PropertyValueFactory<>("userID"));

        contactCmboBox.setItems(DBContacts.getAllContacts());
        customerIDCmboBox.setItems(DBCustomers.getAllCustomers());
        userIDCmboBox.setItems(DBUsers.getAllUsers());
        startTimeCmboBox.setItems(AppointmentMenuController.getLocalStartTimes());
        endTimeCmboBox.setItems(AppointmentMenuController.getLocalEndTimes());

    }

    /** This is the populate appointment local time lists method.
     This method creates a list of available business appointment hours shown at user local time by converting business
     Eastern Standard time time hours to user system default time hours. The times lists are shown as 15 minute intervals
     to provide 15 minute minimum appointment duration.
     */
    public static void populateApptLocalTimeLists() {
        localStartTimes.clear();
        localEndTimes.clear();
        ZonedDateTime start = EST_START_TIME.withZoneSameInstant(ZoneId.systemDefault());
        ZonedDateTime end = EST_END_TIME.withZoneSameInstant(ZoneId.systemDefault());

        while (start.isBefore(end)) {
            localStartTimes.add(start.toLocalTime());
            start = start.plusMinutes(15);
            localEndTimes.add(start.toLocalTime());
        }
    }

    /** This is the get local start times method.
     This method returns a list of available appointment start times that were converted from business EST zone to user
     local time and populates the appointment start time combo box.
     @return Returns local appointment start times
     */
    public static ObservableList<LocalTime> getLocalStartTimes () {
        if (localStartTimes.size()<1) {
            populateApptLocalTimeLists();
        }
        return localStartTimes;
    }

    /** This is the get local end times method.
     This method returns a list of available appointment end times that were converted from business EST zone to user
     local time and populates the appointment end time combo box.
     @return Returns local appointment end times
     */
    public static ObservableList<LocalTime> getLocalEndTimes () {
        if (localEndTimes.size()<1) {
            populateApptLocalTimeLists();
        }
        return localEndTimes;
    }

    /** This is the selected appointment data method.
     This method takes the selected appointment data from the menu tableview and populates the appointment data fields
     for updating or deletion.
     */
    public void selectedAppointmentData() {
        Appointments selectedAppointment = appointmentsTableView.getSelectionModel().getSelectedItem();
        appointmentIDTxtField.setText(String.valueOf(selectedAppointment.getApptID()));
        apptID = selectedAppointment.getApptID();
        apptTitleTxtField.setText(selectedAppointment.getApptTitle());
        apptDescriptionTxtField.setText(selectedAppointment.getApptDescription());
        apptLocationTxtField.setText(selectedAppointment.getApptLocation());

        for (Contacts contact : contactCmboBox.getItems()) {
            if (contact.getContactID() == selectedAppointment.getContactID()) {
                contactCmboBox.setValue(contact);
                break;
            }
        }

        apptTypeTxtField.setText(selectedAppointment.getApptType());

        for (Customers customer : customerIDCmboBox.getItems()) {
            if (customer.getCustomerID() == selectedAppointment.getCustomerID()) {
                customerIDCmboBox.setValue(customer);
            }
        }

        for (Users user : userIDCmboBox.getItems()) {
            if (user.getUserID() == selectedAppointment.getUserID()) {
                userIDCmboBox.setValue(user);
            }
        }

        LocalDateTime apptStart = selectedAppointment.getApptStartDateTime();
        LocalDateTime apptEnd = selectedAppointment.getApptEndDateTime();
        LocalDate apptStartDate = apptStart.toLocalDate();
        LocalTime apptStartTime = apptStart.toLocalTime();
        LocalDate apptEndDate = apptEnd.toLocalDate();
        LocalTime apptEndTime = apptEnd.toLocalTime();

        startDatePicker.setValue(apptStartDate);
        startTimeCmboBox.setValue(apptStartTime);
        endDatePicker.setValue(apptEndDate);
        endTimeCmboBox.setValue(apptEndTime);
    }

    /** This is the add new appointment method.
     This method takes the appointment data entered in the GUI appointment data information fields and adds the
     appointment data to the client_schedule database via DBAppointment.addAppointments class method.
     @param event java fxml method trigger event
     */
    @FXML
    public void onActionAddNewAppointment(ActionEvent event) {
        if (emptyFieldCheck()) {
            return;
        }

        String apptTitle = apptTitleTxtField.getText();
        String apptDescription = apptDescriptionTxtField.getText();
        String apptLocation = apptLocationTxtField.getText();
        Contacts contact = contactCmboBox.getSelectionModel().getSelectedItem();
        int contactID = contact.getContactID();
        String apptType = apptTypeTxtField.getText();

        Customers customer = customerIDCmboBox.getSelectionModel().getSelectedItem();
        customerID = customer.getCustomerID();

        Users user = userIDCmboBox.getSelectionModel().getSelectedItem();
        int userID = user.getUserID();

        LocalDate startDate = startDatePicker.getValue();
        LocalTime startTime = startTimeCmboBox.getValue();
        LocalDate endDate = endDatePicker.getValue();
        LocalTime endTime = endTimeCmboBox.getValue();

        startLocalDateTime = LocalDateTime.of(startDate, startTime);
        endLocalDateTime = LocalDateTime.of(endDate, endTime);

        if (timeVerification()) {
            return;
        }

        if (overlapApptVerification()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Appointment Scheduling Error.");
            alert.setHeaderText("Scheduled time overlaps with another appointment for the customer.");
            alert.setContentText("Please select a different appointment date/time for the customer.");
            alert.showAndWait();
            return;

        } if (verifyStartTimeBeforeEndTime()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Appointment Scheduling Error.");
            alert.setHeaderText("Scheduled end date or time is BEFORE scheduled start date or time.");
            alert.setContentText("Please select an end date/time that occurs AFTER the start date/time.");
            alert.showAndWait();
            return;

        } else {
            DBAppointments.addAppointment(apptTitle, apptDescription, apptLocation, apptType, startLocalDateTime,
                    endLocalDateTime, customerID, userID, contactID);

            try {
                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/View/AppointmentMenu.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }


    /** This is the show all appointments method.
     This method populates the All Appointments menu tab tableview with all appointments from the database.
     @param event java fxml method trigger event
     */
    @FXML
    public void onActionShowAllAppointments(ActionEvent event) {
        if (allAppointmentsRadioBtn.isSelected()) {
            appointmentsTableView.setItems(DBAppointments.getAllAppointments());
        }
    }

    /** This is the appointments by month method.
     This method populates the Appointments by Month menu tab tableview with all appointments for the month from the database.
     @param event java fxml method trigger event
     */
    @FXML
    public void onActionAppointmentsByMonth(ActionEvent event) {
        if (appointmentsByMoRadioBtn.isSelected()) {
            appointmentsTableView.setItems(DBAppointments.getAppointmentsByMonth());
        }
    }

    /** This is the appointments by week method.
     This method populates the Appointments by Week menu tab tableview with all appointments for the week from the database.
     @param event java fxml method trigger event
     */
    @FXML
    public void onActionAppointmentsByWeek(ActionEvent event) {
        if (appointmentsByWkRadioBtn.isSelected()) {
            appointmentsTableView.setItems(DBAppointments.getAppointmentsByWeek());
        }
    }

    /** This is the clear information fields method.
     This method clears the appointment information fields in the appointment menu.
     @param event java fxml method trigger event
     */
    @FXML
    public void onActionClearInformationFields(ActionEvent event) {
        try {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/View/AppointmentMenu.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** This is the delete appointment method.
     This method deletes a selected appointment from the tableview and provides a confirmation message for deletion.
     @param event java fxml method trigger event
     */
    @FXML
    public void onActionDeleteAppointment(ActionEvent event) {
        Appointments selectedAppointment = appointmentsTableView.getSelectionModel().getSelectedItem();

        if (selectedAppointment == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No appointment selected for delete.");
            alert.setContentText("Select appointment to delete.");
            alert.showAndWait();
            return;
        }

        int appointmentID = selectedAppointment.getApptID();
        String appointmentType = selectedAppointment.getApptType();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Deleting Appointment\n" + "Appointment ID: " + appointmentID + "\n" +
                "Type of Appointment: " + appointmentType + "\n" +"\nDo you wish to continue?");
        Optional<ButtonType> results = alert.showAndWait();

        if (results.isPresent() && results.get() == ButtonType.OK) {
            DBAppointments.deleteAppointment(appointmentID);

            try {
                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/View/AppointmentMenu.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /** This is the switch to customer menu method.
     This method loads the customer menu screen when the customer menu button is selected from the appointment menu.
     @param event java fxml method trigger event
     */
    @FXML
    public void onActionSwitchToCustomerMenu(ActionEvent event) {
        try {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/View/CustomerMenu.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

        /** This is the switch to reports menu method.
     This method loads the reports menu screen when the reports menu button is selected from the appointment menu.
     @param event java fxml method trigger event
     */
    @FXML
    public void onActionSwitchToReportsMenu(ActionEvent event) {
        try {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/View/ReportsMenu.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

        /** This is the update appointment method.
     This method takes the appointment data entered in the GUI appointment data information fields and updates the
     appointment data to the client_schedule database via DBAppointment.updateAppointments class method.
     @param event java fxml method trigger event
     */
    @FXML
    public void onActionUpdateAppointment(ActionEvent event) {

        String apptTitle = apptTitleTxtField.getText();
        String apptDescription = apptDescriptionTxtField.getText();
        String apptLocation = apptLocationTxtField.getText();

        Contacts contact = contactCmboBox.getSelectionModel().getSelectedItem();
        int contactID = contact.getContactID();
        String apptType = apptTypeTxtField.getText();
        Customers customer = customerIDCmboBox.getSelectionModel().getSelectedItem();
        customerID = customer.getCustomerID();
        Users user = userIDCmboBox.getSelectionModel().getSelectedItem();
        int userID = user.getUserID();

        LocalDate startDate = startDatePicker.getValue();
        LocalTime startTime = startTimeCmboBox.getValue();

        LocalDate endDate = endDatePicker.getValue();
        LocalTime endTime = endTimeCmboBox.getValue();

        startLocalDateTime = LocalDateTime.of(startDate, startTime);
        endLocalDateTime = LocalDateTime.of(endDate, endTime);

        if (timeVerification()) {
            return;
        }

        if (overlapApptVerification()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Appointment Scheduling Error.");
            alert.setHeaderText("Scheduled time overlaps with another appointment for the customer.");
            alert.setContentText("Please select a different appointment date/time for the customer.");
            alert.showAndWait();
            return;

        } if (verifyStartTimeBeforeEndTime()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Appointment Scheduling Error.");
            alert.setHeaderText("Scheduled end time is BEFORE scheduled start time.");
            alert.setContentText("Please select an end time that occurs AFTER the start time.");
            alert.showAndWait();
            return;

        } if (emptyFieldCheck()) {
            return;

        } else {
            DBAppointments.updateAppointment(apptID, apptTitle, apptDescription, apptLocation, apptType, startLocalDateTime,
                    endLocalDateTime, customerID, userID, contactID);

            try {
                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/View/AppointmentMenu.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /** This is the timer verification method.
     This method provides an error message if the selected appointment time is outside of business hour or on a weekend.
     @return Returns boolean value for appointment time outside of business hours
     */
    public boolean timeVerification() {
        boolean outsideBusinessHours = false;

        ZoneId systemZoneID = ZoneId.systemDefault();

        ZonedDateTime systemStartZoneDateTime = ZonedDateTime.of(startLocalDateTime, systemZoneID);
        ZonedDateTime systemEndZoneDateTime = ZonedDateTime.of(endLocalDateTime, systemZoneID);

        ZoneId estZoneID = ZoneId.of("America/New_York");

        ZonedDateTime estZoneStartDateTime = systemStartZoneDateTime.withZoneSameInstant(estZoneID);
        ZonedDateTime estZoneEndDateTime = systemEndZoneDateTime.withZoneSameInstant(estZoneID);

        LocalTime selectedStartEST = estZoneStartDateTime.toLocalDateTime().toLocalTime();
        LocalTime selectedEndEST = estZoneEndDateTime.toLocalDateTime().toLocalTime();

        if (selectedStartEST.isBefore(businessStart) || selectedStartEST.isAfter(businessEnd) ||
            selectedEndEST.isBefore(businessStart) || selectedEndEST.isAfter(businessEnd)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Selected appointment is outside of business hours.");
            alert.setContentText("Please select a time between 8:00 and 22:00 EST.");
            alert.showAndWait();
            outsideBusinessHours = true;
        }
        return outsideBusinessHours;
    }

    /** This is the overlap appointment verification method.
     This method verifies if the entered customer appointment time overlaps with another appointment for the same customer.
     @return Returns boolean value for customer times overlap
     */
    public  boolean overlapApptVerification() {
        ObservableList<Appointments> apptOverlaps = DBAppointments.getAppointmentsByCustomer(customerID);
        boolean overlap = false;

        for (int i = 0; i < apptOverlaps.size(); i++) {
            Appointments appointment = apptOverlaps.get(i);
            int appt_ID = appointment.getApptID();
            LocalDateTime apptStart = appointment.getApptStartDateTime();
            LocalDateTime apptEnd = appointment.getApptEndDateTime();

            if (appt_ID == apptID) {
                break;
            }

            if (startLocalDateTime.isBefore(apptStart.plusMinutes(1)) && endLocalDateTime.isAfter(apptEnd.minusMinutes(1))) {
                overlap = true;
                break;

            } else if (startLocalDateTime.isBefore(apptEnd.plusMinutes(1)) && startLocalDateTime.isAfter(apptStart.minusMinutes(1))) {
                overlap = true;
                break;

            } else if (endLocalDateTime.isBefore(apptEnd.plusMinutes(1)) &&  endLocalDateTime.isAfter(apptStart.minusMinutes(1))) {
                overlap = true;
                break;

            } else {
                overlap = false;
                continue;
            }
        }
        return overlap;
    }

    /** This is the empty field check method.
     This method checks for any empty appointment data GUI fields before adding appointment information and displays
     an error message to the user for which field is empty.
     @return Returns boolean value for empty fields
     */
    public boolean emptyFieldCheck() {
        boolean emptyField = false;

        if (apptTitleTxtField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Empty Field");
            alert.setHeaderText("Title Empty");
            alert.setContentText("Input Title");
            alert.showAndWait();
            emptyField = true;
        }

        if (apptDescriptionTxtField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Empty Field");
            alert.setHeaderText("Description Empty");
            alert.setContentText("Input Description");
            alert.showAndWait();
            emptyField = true;
        }

        if (apptLocationTxtField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Empty Field");
            alert.setHeaderText("Location Empty");
            alert.setContentText("Input Location");
            alert.showAndWait();
            emptyField = true;
        }

        if (contactCmboBox.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Empty Box");
            alert.setHeaderText("Contact Box Empty");
            alert.setContentText("Select Contact");
            alert.showAndWait();
            emptyField = true;
        }

        if (apptTypeTxtField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Empty Field");
            alert.setHeaderText("Appointment Type Empty");
            alert.setContentText("Input Appointment Type");
            alert.showAndWait();
            emptyField = true;
        }

        if (customerIDCmboBox.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Empty Box");
            alert.setHeaderText("Customer ID Box Empty");
            alert.setContentText("Select Customer ID");
            alert.showAndWait();
            emptyField = true;
        }

        if (userIDCmboBox.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Empty Box");
            alert.setHeaderText("User ID Box Empty");
            alert.setContentText("Select User ID");
            alert.showAndWait();
            emptyField = true;
        }

        if (startDatePicker.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Empty Date");
            alert.setHeaderText("Start Date Empty");
            alert.setContentText("Select Start Date");
            alert.showAndWait();
            emptyField = true;
        }

        if (startTimeCmboBox.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Empty Box");
            alert.setHeaderText("Start Time Box Empty");
            alert.setContentText("Select Start Time");
            alert.showAndWait();
            emptyField = true;
        }

        if (endDatePicker.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Empty Date");
            alert.setHeaderText("End Date Empty");
            alert.setContentText("Select End Date");
            alert.showAndWait();
            emptyField = true;
        }

        if (endTimeCmboBox.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Empty Box");
            alert.setHeaderText("End Time Box Empty");
            alert.setContentText("Select End Time");
            alert.showAndWait();
            emptyField = true;
        }

        return emptyField;
    }

    /** This is the verify start time before end time method.
     This method verifies that the appointment end time does not occur before the appointment start time.
     @return Returns boolean value for end before start
     */
    public boolean verifyStartTimeBeforeEndTime() {
        ObservableList<Appointments> apptTimesStartEndList = DBAppointments.getAppointmentsByCustomer(customerID);
        boolean endBeforeStart = false;

        for (int i = 0; i < apptTimesStartEndList.size(); i++) {
            Appointments appointment = apptTimesStartEndList.get(i);
            int appt_ID = appointment.getApptID();

            if (appt_ID == apptID) {
                break;
            }

            if (endLocalDateTime.isBefore(startLocalDateTime)) {
                endBeforeStart = true;
                break;

            } else {
                endBeforeStart = false;
                continue;
            }
        }
        return endBeforeStart;
    }
}
