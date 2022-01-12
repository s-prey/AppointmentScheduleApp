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
import java.time.ZoneId;
import java.util.Locale;
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
    private Label loginPasswordLabel;

    @FXML
    private TextField loginPasswordTxtField;

    @FXML
    private Label loginTitleLabel;

    @FXML
    private Label loginUsernameLabel;

    @FXML
    private TextField loginUsernameTxtField;

    @FXML
    private Label userZoneLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ZoneId currentZone = ZoneId.systemDefault();
        userZoneLabel.setText("User Location Time: " + currentZone);
        Locale french = new Locale("fr", "FR");
        ResourceBundle rb = ResourceBundle.getBundle("Controller/Nat", Locale.FRENCH);
        if (Locale.getDefault().getLanguage().equals("fr")) {
            Locale.setDefault(french);
            loginTitleLabel.setText((rb.getString("Customer Appointment Scheduler Login")).replaceAll(",",
                    " "));
            loginUsernameLabel.setText((rb.getString("Username")).replaceAll(",", " "));
            loginPasswordLabel.setText((rb.getString("Password")).replaceAll(",", " "));
            loginButton.setText("Login");
            clearLoginButton.setText("Clear");
            exitLoginButton.setText("Exit");
            int indexOfZoneDiff = (currentZone.toString()).indexOf("/");
            String countryToDisplay = (currentZone.toString()).substring(0, indexOfZoneDiff);
            String countryToDisplayFR;
            if (countryToDisplay.equals("Pacific") || countryToDisplay.equals("America") ||
                    countryToDisplay.equals("Europe")) {
                countryToDisplayFR = (rb.getString(countryToDisplay));
                int indexOfEnd = (currentZone.toString()).length();
                String locationToDisplay = (currentZone.toString()).substring(indexOfZoneDiff, indexOfEnd);
                userZoneLabel.setText((rb.getString("User Location Zone")).replaceAll(",", " ") +
                         ": " + countryToDisplayFR + locationToDisplay);
            } else {
                userZoneLabel.setText((rb.getString("User Location Zone")).replaceAll(",", " ") +
                        ": " + currentZone);
            }
            logInErrorTitle = rb.getString("Login Failed").replaceAll(",", " ");
            logInErrorMessage = rb.getString("Invalid Username or Password").replaceAll(",", " ");
            //logInFormTitleLabel.setLayoutX(190);
            //userLocationLabel.setLayoutX(350);
        }

    }



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
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/View/AppointmentMenu.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();

            //Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/AppointmentMenu.fxml")));
            //Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            //primaryStage.setScene(new Scene(root/*, 900, 600*/));
            //AppointmentMenuController login = new AppointmentMenuController();
            //login.checkForUpcomingAppointment();
            //primaryStage.show();
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

}
