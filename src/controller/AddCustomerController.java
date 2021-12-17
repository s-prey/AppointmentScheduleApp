package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AddCustomerController implements Initializable {
    @FXML
    public TextField cutomerNameTxtField;
    @FXML
    public TextField customerAddressTxtField;
    @FXML
    public TextField cutomerPostalTxtField;
    @FXML
    public ComboBox customerCountryCmboBox;
    @FXML
    public ComboBox customerDivisionCmboBox;
    @FXML
    public TextField customerPhoneTxtField;
    @FXML
    public Button customerSaveButton;
    @FXML
    public Button customerClearButton;
    @FXML
    public Button customerAddCancelButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
