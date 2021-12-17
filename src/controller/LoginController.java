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
    public Label titleLabel;
    @FXML
    public TextField loginUsernameTxtField;
    @FXML
    public TextField loginPasswordTxtField;
    @FXML
    public Button loginButton;
    @FXML
    public Button clearLoginButton;
    @FXML
    public Button exitLoginButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
