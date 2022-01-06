package controller;

import DBAccess.DBAppointments;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Appointments;
import model.Countries;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.SortedMap;

public class AppointmentMenuController implements Initializable {

    Stage stage;
    Parent scene;



    @FXML private Button addNewAppointmentButton;

    @FXML private RadioButton allAppointmentsRadioBtn;

    @FXML private ToggleGroup appointmentFilterTG;

    @FXML private TextField appointmentIDTxtField;

    @FXML private TextField appointmentTypeCmboBox;

    @FXML private RadioButton appointmentsByMoRadioBtn;

    @FXML private RadioButton appointmentsByWkRadioBtn;

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




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void onActionAddNewAppointment(ActionEvent event) {
        ObservableList<Appointments> appointmentsObservableList = DBAppointments.getAllAppointments();
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
    void onActionSwitchToCustomerMenu(ActionEvent event) {

    }

    @FXML
    void onActionSwitchToReportsMenu(ActionEvent event) {

    }

    @FXML
    void onActionUpdateAppointment(ActionEvent event) {

    }



}
