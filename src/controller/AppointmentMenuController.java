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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;
import java.util.SortedMap;

import static DBAccess.DBAppointments.getAllAppointments;

public class AppointmentMenuController implements Initializable {

    Stage stage;
    Parent scene;
    private LocalTime startTime = LocalTime.of(8, 0);
    private LocalTime endTime = LocalTime.of(22, 0);
    private LocalDateTime startLocalDateTime;
    private LocalDateTime endLocalDateTime;
    private int customerIDCombo;


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

        LocalTime startTime1 = startTime;
        LocalTime endTime1 = endTime.minusMinutes(15);
        while (startTime1.isBefore(endTime1.plusSeconds(1))) {
            startTimeCmboBox.getItems().add(startTime1);
            startTime1 = startTime1.plusMinutes(15);
        }

        LocalTime startTime2 = startTime.plusMinutes(15);
        LocalTime endTime2 = endTime;
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
        System.out.println("working");
        ObservableList<Appointments> appointmentsObservableList = getAllAppointments();
        for (Appointments A : appointmentsObservableList) {
            System.out.println("Appointment ID : " + A.getApptID() + " Title : " + A.getApptTitle());
        }
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
