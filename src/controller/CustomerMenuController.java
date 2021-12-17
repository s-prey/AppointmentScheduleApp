package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


public class CustomerMenuController implements Initializable {

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
    public TableColumn customerPhoneCol;
    @FXML
    public Button customerAddButton;
    @FXML
    public Button customerUpdateButton;
    @FXML
    public Button customerDeleteButton;
    @FXML
    public Button customerBackButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
