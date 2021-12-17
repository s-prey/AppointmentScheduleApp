package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class UpdateAppointmentController implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private TextField appointmentIDTxtField;

    @FXML
    private ComboBox<?> apptContactNameCmboBox;

    @FXML
    private ComboBox<?> apptCustomerIDCmboBox;

    @FXML
    private DatePicker apptDatePicker;

    @FXML
    private TextField apptDescriptionTxtField;

    @FXML
    private TextField apptEndTimeTxtField;

    @FXML
    private TextField apptLocationTxtField;

    @FXML
    private TextField apptStartTimeTxtField;

    @FXML
    private TextField apptTitleTxtField;

    @FXML
    private TextField apptTypeTxtField;

    @FXML
    private ComboBox<?> apptUserIDCmboBox;

    @FXML
    private Button customerClearButton;

    @FXML
    private Button customerSaveButton;

    @FXML
    private Button customerUpdateCancelButton;

    @FXML
    void onActionCancel(ActionEvent event) {

    }

    @FXML
    void onActionClearCustomer(ActionEvent event) {

    }

    @FXML
    void onActionSaveCustomer(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
