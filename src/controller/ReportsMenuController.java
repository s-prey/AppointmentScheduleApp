package controller;

import DBAccess.DBAppointments;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointments;
import model.Contacts;
import model.Customers;
import model.Reports;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.ResourceBundle;

public class ReportsMenuController implements Initializable {

    Stage stage;
    Parent scene;

    //Report 1 - Customer Appointment total by Month
    @FXML private Tab appointmentTotalTab;
    @FXML private TableView<Reports> customerApptMonthTotalTableView;
    @FXML private TableColumn<Reports, String> customerApptTotalMonthCol;
    @FXML private TableColumn<Reports, String> customerApptMonthTotalCol;

    //Report 1 - Customer Appointment total by Type
    @FXML private TableView<Appointments> customerApptTypeTotalTableView;
    @FXML private TableColumn<Appointments, String> customerApptTotalTypeCol;
    @FXML private TableColumn<Appointments, Integer> customerApptTypeTotalCol;
    //@FXML private TableView<Appointments> customerApptTotalTableView;
    //@FXML private TableColumn<Appointments, Integer> customerApptTotalCol;


    //Report 2
    @FXML private Tab appointmentsByContactTab;
    @FXML private ComboBox<Contacts> contactComboBox;
    @FXML private TableView<Appointments> appointmentsByContactTableView;
    @FXML private TableColumn<Appointments, Integer> apptByContactApptID;
    @FXML private TableColumn<Appointments, String> apptByContactApptTitleCol;
    @FXML private TableColumn<Appointments, String> apptByContactApptTypeCol;
    @FXML private TableColumn<Appointments, String> apptByContactApptDescCol;
    @FXML private TableColumn<Appointments, LocalDateTime> apptByContactStartDateTimeCol;
    @FXML private TableColumn<Appointments, LocalDateTime> apptByContactEndDateTimeCol;
    @FXML private TableColumn<Appointments, Integer> apptByContactCustomerIDCol;


    //Report 3
    @FXML private Tab totalCustomersByCountryTab;
    @FXML private TableView<Customers> totalCustomersByCntryTableView;
    @FXML private TableColumn<Customers, String> totalCustomersByCountryCountryCol;
    @FXML private TableColumn<Customers, Integer> totalCustomersByCountryCustomerTotalCol;



    @FXML private Button customerMenuButton;
    @FXML private Button appointmentMenuButton;





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        customerApptMonthTotalTableView.setItems(DBAppointments.getAppointmentTotalByMonth());
        customerApptTotalMonthCol.setCellValueFactory(new PropertyValueFactory<>("month"));
        customerApptMonthTotalCol.setCellValueFactory(new PropertyValueFactory<>("count"));
        //customerApptTypeTotalTableView.setItems(DBAppointments.g);
        customerApptTotalTypeCol.setCellValueFactory(new PropertyValueFactory<>("apptType"));
        customerApptTypeTotalCol.setCellValueFactory(new PropertyValueFactory<>("apptTypeTotal"));

        apptByContactApptID.setCellValueFactory(new PropertyValueFactory<>("apptID"));
        apptByContactApptTitleCol.setCellValueFactory(new PropertyValueFactory<>("apptTitle"));
        apptByContactApptTypeCol.setCellValueFactory(new PropertyValueFactory<>("apptType"));
        apptByContactApptDescCol.setCellValueFactory(new PropertyValueFactory<>("apptDescription"));
        apptByContactStartDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("apptStartDateTime"));
        apptByContactEndDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("apptEndDateTime"));
        apptByContactCustomerIDCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));

        totalCustomersByCountryCountryCol.setCellValueFactory(new PropertyValueFactory<>("customerCountry"));
        totalCustomersByCountryCustomerTotalCol.setCellValueFactory(new PropertyValueFactory<>("customerCountryTotal"));

    }

    @FXML
    void onActionSwitchToAppointmentMenu(ActionEvent event) {
        try {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/View/AppointmentMenu.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onActionSwitchToCustomerMenu(ActionEvent event) {
        try {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/View/CustomerMenu.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void queryAppointmentsByContact(ActionEvent event) {

    }

    @FXML
    void queryCustomerAppointmentTotal(ActionEvent event) {

    }

    @FXML
    void queryTotalCustomersByCountry(ActionEvent event) {

    }

    @FXML
    void selectContact(ActionEvent event) {

    }



}
