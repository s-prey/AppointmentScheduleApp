package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private Button clearLoginButton;

    @FXML
    private Button exitLoginButton;

    @FXML
    private Button loginButton;

    @FXML
    private TextField loginPasswordTxtField;

    @FXML
    private TextField loginUsernameTxtField;

    @FXML
    private Label titleLabel;

    @FXML
    void onActionClearLogin(ActionEvent event) {

    }

    @FXML
    void onActionExitLogin(ActionEvent event) {

    }

    @FXML
    void onActionUserLogin(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
