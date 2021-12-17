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

public class AddAppointmentController implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    public TextField appointmentIDTxtField;

    @FXML
    public TextField apptTitleTxtField;

    @FXML
    public TextField apptDescriptionTxtField;

    @FXML
    public TextField apptLocationTxtField;

    @FXML
    public TextField apptTypeTxtField;

    @FXML
    public ComboBox apptContactNameCmboBox;

    @FXML
    public ComboBox apptCustomerIDCmboBox;

    @FXML
    public ComboBox apptUserIDCmboBox;

    @FXML
    public DatePicker apptDatePicker;

    @FXML
    public TextField apptStartTimeTxtField;

    @FXML
    public TextField apptEndTimeTxtField;

    @FXML
    public Button customerSaveButton;

    @FXML
    public Button customerClearButton;

    @FXML
    public Button customerBackButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
