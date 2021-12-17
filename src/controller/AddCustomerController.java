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

    Stage stage;
    Parent scene;

    @FXML
    private Button customerAddCancelButton;

    @FXML
    private TextField customerAddressTxtField;

    @FXML
    private Button customerClearButton;

    @FXML
    private ComboBox<?> customerCountryCmboBox;

    @FXML
    private ComboBox<?> customerDivisionCmboBox;

    @FXML
    private TextField customerPhoneTxtField;

    @FXML
    private Button customerSaveButton;

    @FXML
    private TextField cutomerNameTxtField;

    @FXML
    private TextField cutomerPostalTxtField;

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
