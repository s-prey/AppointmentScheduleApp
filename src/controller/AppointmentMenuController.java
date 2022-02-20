package controller;

import static DBAccess.DBAppointments.getAllAppointments;

import DBAccess.DBAppointments;
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
import model.Appointments;
import model.Countries;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.SortedMap;

import static DBAccess.DBAppointments.getAllAppointments;

public class AppointmentMenuController implements Initializable {

    Stage stage;
    Parent scene;


    @FXML private TableView<Appointments> appointmentsTableView;
    @FXML private TableColumn<Appointments, Integer> apptIDCol;
    @FXML private TableColumn<Appointments, String> apptTitleCol;
    @FXML private TableColumn<Appointments, String> apptDescriptionCol;
    @FXML private TableColumn<Appointments, String> apptLocationCol;
    @FXML private TableColumn<Appointments, String> apptTypeCol;
    @FXML private TableColumn<Appointments, LocalDateTime> apptStartDateTime;
    @FXML private TableColumn<Appointments, LocalDateTime> apptEndDateTime;
    @FXML private TableColumn<Appointments, Integer> customerIDCol;
    @FXML private TableColumn<Appointments, Integer> userIDCol;
    @FXML private TableColumn<Appointments, Integer> contactIDCol;

    @FXML private Button addNewAppointmentButton;

    @FXML private RadioButton allAppointmentsRadioBtn;

    @FXML private ToggleGroup appointmentFilterTG;

    @FXML private TextField appointmentIDTxtField;

    @FXML private TextField appointmentTypeCmboBox;

    @FXML private RadioButton appointmentsByMoRadioBtn;

    @FXML private RadioButton appointmentsByWkRadioBtn;










    @FXML private Button clearInformationFieldsButton;

    @FXML private ComboBox<?> contactCmboBox;



    @FXML private ComboBox<?> customerIDCmboBox;



    @FXML private Button customerMenuButton;

    @FXML private Button deleteAppointmentButton;

    @FXML private TextField descriptionTxtField;

    @FXML private DatePicker endDatePicker;

    @FXML private ComboBox<?> endTimeCmboBox;

    @FXML private TextField locationTxtField;

    @FXML private Button reportsMenuButton;

    @FXML private DatePicker startDatePicker;

    @FXML private ComboBox<?> startTimeCmboBox;

    @FXML private TextField titleTxtField;

    @FXML private Button updateAppointmentButton;

    @FXML private ComboBox<?> userIDCmboBox;


    public ObservableList<Appointments> appointmentData = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //ObservableList<Appointments> appointmentData = getAllAppointments();
        appointmentsTableView.setItems(DBAppointments.getAllAppointments());

        apptIDCol.setCellValueFactory(new PropertyValueFactory<>("apptID"));
        apptTitleCol.setCellValueFactory(new PropertyValueFactory<>("apptTitle"));
        apptDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("apptDescription"));
        apptLocationCol.setCellValueFactory(new PropertyValueFactory<>("apptLocation"));
        apptTypeCol.setCellValueFactory(new PropertyValueFactory<>("apptType"));
        apptStartDateTime.setCellValueFactory(new PropertyValueFactory<>("apptStartDateTime"));
        apptEndDateTime.setCellValueFactory(new PropertyValueFactory<>("apptEndDateTime"));
        customerIDCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        userIDCol.setCellValueFactory(new PropertyValueFactory<>("userID"));
        contactIDCol.setCellValueFactory(new PropertyValueFactory<>("contactID"));

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



}
