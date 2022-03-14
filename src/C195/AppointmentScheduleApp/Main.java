package C195.AppointmentScheduleApp;

import Database.DBConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Steven Prey
 */

/** This class creates an application for scheduling appointments.*/
public class Main extends Application{

    /** This is the start stage method.
     this method starts the javafx stage and scene load
     @param primaryStage primary javafx stage to start
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Appointment Schedule Application ");
        Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    /** This is the main method.
     * This is the first method that gets called when the java program is started.
     @param args supplied command-line arguments
     */
    public static void main(String[] args) {
        try {
            DBConnection.startConnection();
            System.out.println("startConnection successful.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        launch(args);
        DBConnection.closeConnection();
    }
}
