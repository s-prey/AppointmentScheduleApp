package controller;

import DBAccess.DBAppointments;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import model.Appointments;
import model.Countries;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.SortedMap;

public class AppointmentMenuController implements Initializable {

    Stage stage;
    Parent scene;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private Button addNewAppointmentButton;

    @FXML
    private RadioButton allAppointmentsRadioBtn;

    @FXML
    private ToggleGroup appointmentFilterTG;

    @FXML
    private TextField appointmentIDTxtField;

    @FXML
    private TextField appointmentTypeCmboBox;

    @FXML
    private RadioButton appointmentsByMoRadioBtn;

    @FXML
    private RadioButton appointmentsByWkRadioBtn;

    @FXML
    private TableColumn<?, ?> apptContactNameCol;

    @FXML
    private TableColumn<?, ?> apptDescriptionCol;

    @FXML
    private TableColumn<?, ?> apptEndDateTime;

    @FXML
    private TableColumn<?, ?> apptIDCol;

    @FXML
    private TableColumn<?, ?> apptLocationCol;

    @FXML
    private TableColumn<?, ?> apptStartDateTime;

    @FXML
    private TableColumn<?, ?> apptTitleCol;

    @FXML
    private TableColumn<?, ?> apptTypeCol;

    @FXML
    private Button clearInformationFieldsButton;

    @FXML
    private ComboBox<?> contactCmboBox;

    @FXML
    private ComboBox<?> customerIDCmboBox;

    @FXML
    private TableColumn<?, ?> customerIDCol;

    @FXML
    private Button customerMenuButton;

    @FXML
    private TableColumn<?, ?> customerPhoneNumberCol;

    @FXML
    private Button deleteAppointmentButton;

    @FXML
    private TextField descriptionTxtField;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private ComboBox<?> endTimeCmboBox;

    @FXML
    private TextField locationTxtField;

    @FXML
    private Button reportsMenuButton;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private ComboBox<?> startTimeCmboBox;

    @FXML
    private TextField titleTxtField;

    @FXML
    private Button updateAppointmentButton;

    @FXML
    private ComboBox<?> userIDCmboBox;



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
