package controller;

import static DBAccess.DBAppointments.getAllAppointments;

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
import java.sql.SQLException;
import java.time.*;
import java.util.ResourceBundle;

public class AppointmentMenuController implements Initializable {

    Stage stage;
    Parent scene;
    private final LocalTime businessStart = LocalTime.of(8, 0);
    private final LocalTime businessEnd = LocalTime.of(22, 0);
    private LocalDateTime startLocalDateTime;
    private LocalDateTime endLocalDateTime;
    private int customerIDCombo; //RENAME VARIABLE!!!


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


    @FXML private Button addNewAppointmentButton;

    @FXML private RadioButton allAppointmentsRadioBtn;

    @FXML private ToggleGroup appointmentFilterTG;

    @FXML private TextField appointmentIDTxtField;



    @FXML private RadioButton appointmentsByMoRadioBtn;

    @FXML private RadioButton appointmentsByWkRadioBtn;



    @FXML private Button clearInformationFieldsButton;
    @FXML private Button customerMenuButton;
    @FXML private Button deleteAppointmentButton;

    @FXML private Button reportsMenuButton;
    @FXML private Button updateAppointmentButton;




    public ObservableList<Appointments> appointmentData = FXCollections.observableArrayList();

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

        LocalTime startTime1 = businessStart;
        LocalTime endTime1 = businessEnd.minusMinutes(15);
        while (startTime1.isBefore(endTime1.plusSeconds(1))) {
            startTimeCmboBox.getItems().add(startTime1);
            startTime1 = startTime1.plusMinutes(15);
        }

        LocalTime startTime2 = businessStart.plusMinutes(15);
        LocalTime endTime2 = businessEnd;
        while (startTime2.isBefore(endTime2.plusMinutes(15))) {
            endTimeCmboBox.getItems().add(startTime2);
            startTime2 = startTime2.plusMinutes(15);
        }





        //appointmentsTableView.setItems(appointmentData);
        System.out.println("Completed Appointment Menu Controller initialize.");

    }

    /*public ResultSet accessDB() {
        Connection conn = null;
        boolean res = false;
        ResultSet rs = null;

        Statement stmt;

        try {

            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT city, country, start FROM city, country, appointment WHERE city.countryID = country.countryID");
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }



        return rs;
    }

     */

    public void addDataToTableView() throws SQLException {
        ObservableList<Appointments> allAppointments = getAllAppointments();
        appointmentsTableView.setItems(allAppointments);
    }



    @FXML
    void onActionAddNewAppointment(ActionEvent event) {
        if (emptyFieldCheck()) {
            return;

        }

        String apptTitle = apptTitleTxtField.getText();
        String apptDescription = apptDescriptionTxtField.getText();
        String apptLocation = apptLocationTxtField.getText();

        Contacts contact = contactCmboBox.getSelectionModel().getSelectedItem();
        //NOT SURE IF I WANT TO USE THIS, Obtain String contact name based on combo box selection.
        int contactID = contact.getContactID();

        String apptType = apptTypeTxtField.getText();

        Customers customer = customerIDCmboBox.getSelectionModel().getSelectedItem();
        //SAME - NOT SURE IF I WANT TO USE THIS, Obtain String contact name based on combo box selection.
        customerIDCombo = customer.getCustomerID();

        Users user = userIDCmboBox.getSelectionModel().getSelectedItem();
        int userID = user.getUserID();

        //Gets appointment start/end dates and times from form date pickers and combo boxes.
        LocalDate startDate = startDatePicker.getValue();
        LocalTime startTime = startTimeCmboBox.getValue();
        LocalDate endDate = endDatePicker.getValue();
        LocalTime endTime = endTimeCmboBox.getValue();

        //Combines date and time into single variables for start and end datetimes.
        startLocalDateTime = LocalDateTime.of(startDate, startTime);
        endLocalDateTime = LocalDateTime.of(endDate, endTime);

        //Assigns users system default time
        ZoneId systemZoneID = ZoneId.systemDefault();
        //assigns selected times to user default times.
        ZonedDateTime systemStartZoneDateTime = ZonedDateTime.of(startLocalDateTime, systemZoneID);
        ZonedDateTime systemEndZoneDateTime = ZonedDateTime.of(endLocalDateTime, systemZoneID);

        //Assigns EST zone for business hours to a variable.
        ZoneId estZoneID = ZoneId.of("US/Eastern");
        //Converts selected system default time to Eastern Time Business hours zone.
        ZonedDateTime estZoneStartDateTime = systemStartZoneDateTime.withZoneSameInstant(estZoneID);
        ZonedDateTime estZoneEndDateTime = systemEndZoneDateTime.withZoneSameInstant(estZoneID);

        //Converts EST zone to user LocalDateTime
        LocalTime selectedStartEST = estZoneStartDateTime.toLocalDateTime().toLocalTime();
        LocalTime selectedEndEST = estZoneEndDateTime.toLocalDateTime().toLocalTime();

        // move this to separate time verification method?
        if (selectedEndEST.isAfter(businessEnd)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Selected appointment end time is after business hours.");
            alert.setContentText("Please select a time before end of business hours.");
            alert.showAndWait();
            return;
        }

        if (selectedStartEST.isBefore(businessStart)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Selected appointment start time is before business hours.");
            alert.setContentText("Please select a time after start of business hours.");
            alert.showAndWait();
            return;
        }

        if (overlapApptVerify()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Appointment Scheduling Error.");
            alert.setHeaderText("Scheduled time overlaps with another appointment for the customer.");
            alert.setContentText("Please select a different appointment date/time for the customer.");
            alert.showAndWait();
            return;

        }  else {
            DBAppointments.addAppointment(apptTitle, apptDescription, apptLocation, apptType, startLocalDateTime,
                    endLocalDateTime, customerIDCombo, userID, contactID);

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

    private boolean overlapApptVerify() {
        ObservableList<Appointments> apptOverlaps = DBAppointments.getAppointmentsByCustomer(customerIDCombo);
         boolean overlap = false;

         for (int i = 0; i < apptOverlaps.size(); i++) {
             Appointments appointment = apptOverlaps.get(i);
             LocalDateTime apptStart = appointment.getApptStartDateTime();
             LocalDateTime apptEnd = appointment.getApptEndDateTime();

             if (startLocalDateTime.isBefore(apptStart.plusMinutes(1)) && endLocalDateTime.isAfter(apptEnd.minusMinutes(1))) {
                 overlap = true;
                 break;

             } else if (startLocalDateTime.isAfter(apptStart.minusMinutes(1)) && startLocalDateTime.isBefore(apptEnd.plusMinutes(1))) {
                 overlap = true;
                 break;

             } else if (endLocalDateTime.isAfter(apptStart.minusMinutes(1)) && endLocalDateTime.isBefore(apptEnd.plusMinutes(1))) {
                 overlap = true;
                 break;

             } else {
                 overlap = false;
                 continue;
             }
         }
         return overlap;
    }

    @FXML
    void onActionAppointmentsByMonth(ActionEvent event) {

    }

    @FXML
    void onActionAppointmentsByWeek(ActionEvent event) {

    }

    @FXML
    void onActionClearInformationFields(ActionEvent event) {

    }

    @FXML
    void onActionDeleteAppointment(ActionEvent event) {

    }

    @FXML
    void onActionEndDate(ActionEvent event) {

    }

    @FXML
    void onActionFilterContactCmboBox(ActionEvent event) {

    }

    @FXML
    void onActionFilterCustomerIDCmboBox(ActionEvent event) {

    }

    @FXML
    void onActionFilterEndTimeCmboBox(ActionEvent event) {

    }

    @FXML
    void onActionFilterStartTimeCmboBox(ActionEvent event) {

    }

    @FXML
    void onActionFilterUserIDCmboBox(ActionEvent event) {

    }

    @FXML
    void onActionShowAllAppointments(ActionEvent event) {

    }

    @FXML
    void onActionStartDate(ActionEvent event) {

    }

    @FXML
    void onActionSwitchToCustomerMenu(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/CustomerMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionSwitchToReportsMenu(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/ReportsMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionUpdateAppointment(ActionEvent event) {

    }

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


}
