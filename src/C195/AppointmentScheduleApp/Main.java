package C195.AppointmentScheduleApp;

import Database.DBConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("C195 - Software II");
        Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        try {
            DBConnection.startConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        launch(args);
        DBConnection.closeConnection();
    }
}
