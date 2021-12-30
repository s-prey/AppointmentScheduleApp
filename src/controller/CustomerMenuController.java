package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerMenuController implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private Button addNewCustomerButton;

    @FXML
    private Button appointmentMenuButton;

    @FXML
    private Button clearInformationFieldsButton;

    @FXML
    private ComboBox<?> countryCmboBox;

    @FXML
    private TableColumn<?, ?> customerAddressCol;

    @FXML
    private TextField customerAddressTxtField;

    @FXML
    private TableColumn<?, ?> customerCountryCol;

    @FXML
    private TableColumn<?, ?> customerDivisionCol;

    @FXML
    private TableColumn<?, ?> customerIDCol;

    @FXML
    private TextField customerIDTxtField;

    @FXML
    private TableColumn<?, ?> customerNameCol;

    @FXML
    private TextField customerNameTxtField;

    @FXML
    private TableColumn<?, ?> customerPhoneCol;

    @FXML
    private TableColumn<?, ?> customerPostalCol;

    @FXML
    private Button deleteCustomerButton;

    @FXML
    private ComboBox<?> firstLevelDivisionCmboBox;

    @FXML
    private TextField phoneNumberTxtField;

    @FXML
    private TextField postalCodeTxtField;

    @FXML
    private Button reportsMenuButton;

    @FXML
    private Button updateCustomerButton;

    @FXML
    void onActionAddNewCustomer(ActionEvent event) {

    }

    @FXML
    void onActionClearInformationFields(ActionEvent event) {

    }

    @FXML
    void onActionDeleteCustomer(ActionEvent event) {

    }

    @FXML
    void onActionFilterCountryCmboBox(ActionEvent event) {

    }

    @FXML
    void onActionFilterFirstLevelDivisionCmboBox(ActionEvent event) {

    }

    @FXML
    void onActionSwitchToAppointmentMenu(ActionEvent event) {

    }

    @FXML
    void onActionSwitchToReportsMenu(ActionEvent event) {

    }

    @FXML
    void onActionUpdateCustomer(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
