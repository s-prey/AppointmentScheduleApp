package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ReportsMenuController implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private Button appointmentMenuButton;

    @FXML
    private Tab appointmentTotalTab;

    @FXML
    private Tab appointmentsByContactTab;

    @FXML
    private TableColumn<?, ?> apptByContactApptDescCol;

    @FXML
    private TableColumn<?, ?> apptByContactApptID;

    @FXML
    private TableColumn<?, ?> apptByContactApptTitleCol;

    @FXML
    private TableColumn<?, ?> apptByContactApptTypeCol;

    @FXML
    private TableColumn<?, ?> apptByContactCustomerIDCol;

    @FXML
    private TableColumn<?, ?> apptByContactEndDateTimeCol;

    @FXML
    private TableColumn<?, ?> apptByContactStartDateTimeCol;

    @FXML
    private ComboBox<?> contactComboBox;

    @FXML
    private TableColumn<?, ?> customerApptTotalCol;

    @FXML
    private TableColumn<?, ?> customerApptTotalMonthCol;

    @FXML
    private TableColumn<?, ?> customerApptTotalTypeCol;

    @FXML
    private Button customerMenuButton;

    @FXML
    private TableColumn<?, ?> totalCustomersByCountryCountryCol;

    @FXML
    private TableColumn<?, ?> totalCustomersByCountryCustomerTotalCol;

    @FXML
    private Tab totalCustomersByCountryTab;

    @FXML
    void onActionSwitchToAppointmentMenu(ActionEvent event) {

    }

    @FXML
    void onActionSwitchToCustomerMenu(ActionEvent event) {

    }

    @FXML
    void queryAppointmentsByContact(ActionEvent event) {

    }

    @FXML
    void queryCustomerAppointmentTotal(ActionEvent event) {

    }

    @FXML
    void queryTotalCustomersByCountry(ActionEvent event) {

    }

    @FXML
    void selectContact(ActionEvent event) {

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
