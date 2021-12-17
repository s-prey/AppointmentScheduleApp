package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.ParallelCamera;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AppointmentMenuController implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private Button addAppointmentButton;

    @FXML
    private Button addCustomerButton;

    @FXML
    private RadioButton allAppointmentsRadioBtn;

    @FXML
    private ToggleGroup appointmentFilterTG;

    @FXML
    private RadioButton appointmentsByMoRadioBtn;

    @FXML
    private RadioButton appointmentsByWkRadioBtn;

    @FXML
    private TableColumn<?, ?> customerAddressCol;

    @FXML
    private TableColumn<?, ?> customerCountryCol;

    @FXML
    private TableColumn<?, ?> customerDivisionCol;

    @FXML
    private TableColumn<?, ?> customerDivisionCol1;

    @FXML
    private TableColumn<?, ?> customerIDCol;

    @FXML
    private TableColumn<?, ?> customerNameCol;

    @FXML
    private TableColumn<?, ?> customerPhoneCol;

    @FXML
    private TableColumn<?, ?> customerPhoneCol1;

    @FXML
    private TableColumn<?, ?> customerPhoneCol11;

    @FXML
    private TableColumn<?, ?> customerPostalCol;

    @FXML
    private Button deleteAppointmentButton;

    @FXML
    private Button logoutbutton;

    @FXML
    private Button reportsButton;

    @FXML
    private Button updateAppointmentButton;

    @FXML
    private Button updateCustomerButton;

    @FXML
    void onActionAppointmentsByMonth(ActionEvent event) {

    }

    @FXML
    void onActionAppointmentsByWeek(ActionEvent event) {

    }

    @FXML
    void onActionDeleteAppointment(ActionEvent event) {

    }

    @FXML
    void onActionLogout(ActionEvent event) {

    }

    @FXML
    void onActionShowAddAppointment(ActionEvent event) {

    }

    @FXML
    void onActionShowAddCustomer(ActionEvent event) {

    }

    @FXML
    void onActionShowAllAppointments(ActionEvent event) {

    }

    @FXML
    void onActionShowReports(ActionEvent event) {

    }

    @FXML
    void onActionShowUpdateAppointment(ActionEvent event) {

    }

    @FXML
    void onActionShowUpdateCustomer(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
