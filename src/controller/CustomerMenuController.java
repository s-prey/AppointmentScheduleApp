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
    private Button customerAddButton;

    @FXML
    private TableColumn<?, ?> customerAddressCol;

    @FXML
    private Button customerBackButton;

    @FXML
    private TableColumn<?, ?> customerCountryCol;

    @FXML
    private Button customerDeleteButton;

    @FXML
    private TableColumn<?, ?> customerDivisionCol;

    @FXML
    private TableColumn<?, ?> customerIDCol;

    @FXML
    private TableColumn<?, ?> customerNameCol;

    @FXML
    private TableColumn<?, ?> customerPhoneCol;

    @FXML
    private TableColumn<?, ?> customerPostalCol;

    @FXML
    private Button customerUpdateButton;

    @FXML
    void onActionAddCustomer(ActionEvent event) {

    }

    @FXML
    void onActionBack(ActionEvent event) {

    }

    @FXML
    void onActionDeleteCustomer(ActionEvent event) {

    }

    @FXML
    void onActionUpdateCustomer(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
