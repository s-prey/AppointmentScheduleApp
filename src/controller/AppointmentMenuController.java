package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    public TableColumn customerIDCol;
    @FXML
    public TableColumn customerNameCol;
    @FXML
    public TableColumn customerAddressCol;
    @FXML
    public TableColumn customerPostalCol;
    @FXML
    public TableColumn customerCountryCol;
    @FXML
    public TableColumn customerDivisionCol;
    @FXML
    public TableColumn customerDivisionCol1;
    @FXML
    public TableColumn customerPhoneCol;
    @FXML
    public TableColumn customerPhoneCol1;
    @FXML
    public TableColumn customerPhoneCol11;
    @FXML
    public Button addAppointmentButton;
    @FXML
    public Button updateAppointmentButton;
    @FXML
    public Button deleteAppointmentButton;
    @FXML
    public RadioButton allAppointmentsRadioBtn;
    @FXML
    public ToggleGroup appointmentFilterTG;
    @FXML
    public RadioButton appointmentsByMoRadioBtn;
    @FXML
    public RadioButton appointmentsByWkRadioBtn;
    @FXML
    public Button addCustomerButton;
    @FXML
    public Button updateCustomerButton;
    @FXML
    public Button reportsButton;
    @FXML
    public Button logoutbutton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
