package controller;

import DBAccess.DBAppointments;
import DBAccess.DBUsers;
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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    Stage stage;
    Parent scene;
    String logInErrorMessage;
    String logInErrorTitle;
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
        String userName = loginUsernameTxtField.getText();
        String userPassword = loginPasswordTxtField.getText();

        //LocalDate dateNow = LocalDate.now();
        //LocalTime timeNow = LocalTime.now();


        if (DBUsers.getUser(userName, userPassword) == 0) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Failed");
            alert.setHeaderText("Incorrect Username or Password");
            alert.setContentText("Please try again");
            alert.showAndWait();
            successfulLogIn = false;
            //return;

        } else {

            if (DBUsers.getDBUserMatch(userName, userPassword).size() > 0) {

                int userID = DBUsers.getDBUserMatch(userName, userPassword).get(0).getUserID();

                if (DBAppointments.getApppointments15Minutes(userID).isEmpty()) {

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("No upcoming appointments");
                    alert.setHeaderText(null);
                    alert.setContentText("There are no upcoming appointments within the next 15 minutes.");
                    alert.showAndWait();
                } else {

                    int appointmentID = DBAppointments.getApppointments15Minutes(userID).get(0).getApptID();

                    LocalDateTime apptStart = DBAppointments.getApppointments15Minutes(userID).get(0).getApptStartDateTime();
                    LocalDateTime apptEnd = DBAppointments.getApppointments15Minutes(userID).get(0).getApptEndDateTime();

                    LocalDate startDate = apptStart.toLocalDate();
                    LocalTime startTime = apptStart.toLocalTime();
                    LocalTime endTime = apptEnd.toLocalTime();

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("You have an upcoming appointment");
                    alert.setHeaderText(null);
                    alert.setContentText("Appointment ID: " + appointmentID + "\n" + "Start Date: " + startDate + "\n" +
                                        " Start Time: " + startTime + "\n" + "End Time: " + endTime);
                    alert.showAndWait();
                }

            }
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/View/AppointmentMenu.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
            successfulLogIn = true;
        }
        loginActivityTracker();
    }


    public void loginActivityTracker() throws IOException {

        Timestamp timeStamp = Timestamp.valueOf(LocalDateTime.now());
        String loginTimeStamp = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(timeStamp);

        FileWriter fileWriter = new FileWriter("login_activity.txt", true);
        PrintWriter outputFile = new PrintWriter(fileWriter);
        outputFile.print("Timestamp: " + loginTimeStamp + ": ");
        if (successfulLogIn) {
            outputFile.print("Login Attempt Successful \n");
        }
        else {
            outputFile.print("Login Attempt Unsuccessful \n");
        }
        outputFile.close();
    }
}
