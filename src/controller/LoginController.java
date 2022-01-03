package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    Stage stage;
    Parent scene;
    String logInErrorMessage = "Invalid Username or Password";
    String logInErrorTitle = "Login Failed";
    boolean successfulLogIn = false;

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
    private Label userZoneLabel;

    @FXML
    void onActionClearLogin(ActionEvent event) {

    }

    @FXML
    void onActionExitLogin(ActionEvent event) {

    }

    @FXML
    void onActionUserLogin(ActionEvent event) throws Exception{
        String userID = loginUsernameTxtField.getText();
        String password = loginPasswordTxtField.getText();

        if (userID.equals("test") && password.equals("test")) {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/AppointmentMenu.fxml")));
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryStage.setScene(new Scene(root/*, 900, 600*/));
            CustomerMenuController login = new CustomerMenuController();
            //login.checkForUpcomingAppointment();
            successfulLogIn = true;
        }
        else {
            JOptionPane.showMessageDialog(null,
                    logInErrorMessage,
                    logInErrorTitle,
                    JOptionPane.ERROR_MESSAGE);
            successfulLogIn = false;
        }
        //trackUserActivity();

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
