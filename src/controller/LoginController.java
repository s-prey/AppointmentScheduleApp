package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.stage.Stage;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    Stage stage;
    Parent scene;
    String logInErrorMessage; //= "Invalid Username or Password";
    String logInErrorTitle; //= "Login Failed";
    boolean successfulLogIn = false;


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
        ResourceBundle rb = ResourceBundle.getBundle("C195.AppointmentScheduleApp/Nat", Locale.getDefault());
        ZoneId currentZone = ZoneId.systemDefault();

        loginTitleLabel.setText(rb.getString("loginTitle"));
        loginUsernameLabel.setText(rb.getString("Username"));
        loginPasswordLabel.setText(rb.getString("Password"));
        loginButton.setText(rb.getString("Login"));
        exitLoginButton.setText(rb.getString("Exit"));
        userZoneLabel.setText(rb.getString("zoneLabel") + ": " + currentZone);
        logInErrorTitle = rb.getString("loginErrorTitle");
        logInErrorMessage = rb.getString("loginErrorMessage");

    }


    @FXML
    void onActionExitLogin(ActionEvent event) {
        System.exit(0);
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


            successfulLogIn = true;
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Failed");
            alert.setHeaderText("Incorrect Username or Password");
            alert.setContentText("Please try again");
            alert.showAndWait();
            /*JOptionPane.showMessageDialog(null, logInErrorMessage, logInErrorTitle,
                    JOptionPane.ERROR_MESSAGE);

             */
            successfulLogIn = false;
        }
        loginActivityTracker();
    }

    public void loginActivityTracker() throws IOException {
        LocalDate attemptDate = LocalDateTime.now().toLocalDate();
        Timestamp attemptTimeStamp = Timestamp.valueOf(LocalDateTime.now());
        FileWriter fileWriter = new FileWriter("login_activity.txt", true);
        PrintWriter outputFile = new PrintWriter(fileWriter);
        //outputFile.print("Date: " + attemptDate + " -- ");
        outputFile.print("Timestamp: " + attemptTimeStamp + " -- ");
        if (successfulLogIn) {
            outputFile.print("Login Attempt Successful\n");
        }
        else {
            outputFile.print("Login Attempt Unsuccessful\n");
        }

        outputFile.close();

    }

}
