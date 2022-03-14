package controller;

import DBAccess.DBAppointments;
import DBAccess.DBContacts;
import DBAccess.DBCustomers;
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
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

/** This class is used as a java fx controller for the appointment schedule application reports menu GUI screen.*/
public class ReportsMenuController implements Initializable {

    Stage stage;
    Parent scene;
    private int contactID;

    //Report 1
    @FXML private TableView<Appointments> customerApptMonthTotalTableView;
    @FXML private TableColumn<Appointments, String> customerApptTotalMonthCol;
    @FXML private TableColumn<Appointments, String> customerApptTotalTypeCol;
    @FXML private TableColumn<Appointments, String> customerApptMonthTotalCol;


    //Report 2
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
    @FXML private TableView<Customers> totalCustomersByCntryTableView;
    @FXML private TableColumn<Customers, String> totalCustomersByCountryCountryCol;
    @FXML private TableColumn<Customers, Integer> totalCustomersByCountryCustomerTotalCol;


    /** This is the initialize method.
     This method is used to initialize data for the reports menu controller.
     @param url uniform resource locator to initialize
     @param resourceBundle resource bundle to initialize
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Initialize Report 1
        customerApptMonthTotalTableView.setItems(DBAppointments.getAppointmentTotalByMonthType());
        customerApptTotalMonthCol.setCellValueFactory(new PropertyValueFactory<>("month"));
        customerApptTotalTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        customerApptMonthTotalCol.setCellValueFactory(new PropertyValueFactory<>("count"));

        //Initialize Report 2
        contactComboBox.setItems(DBContacts.getAllContacts());
        apptByContactApptID.setCellValueFactory(new PropertyValueFactory<>("apptID"));
        apptByContactApptTitleCol.setCellValueFactory(new PropertyValueFactory<>("apptTitle"));
        apptByContactApptTypeCol.setCellValueFactory(new PropertyValueFactory<>("apptType"));
        apptByContactApptDescCol.setCellValueFactory(new PropertyValueFactory<>("apptDescription"));
        apptByContactStartDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("apptStartDateTime"));
        apptByContactEndDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("apptEndDateTime"));
        apptByContactCustomerIDCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));

        //Initialize Report 3
        totalCustomersByCntryTableView.setItems(DBCustomers.getCustomerCountByCountry());
        totalCustomersByCountryCountryCol.setCellValueFactory(new PropertyValueFactory<>("countryName"));
        totalCustomersByCountryCustomerTotalCol.setCellValueFactory(new PropertyValueFactory<>("customerCount"));
    }

    /** This is the switch to appointment menu method.
     This method loads the appointment menu screen when the appointment menu button is selected from the customer menu.
     @param event java fxml method trigger event
     */
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

    /** This is the switch to customer menu method.
     This method loads the customer menu screen when the customer menu button is selected from the appointment menu.
     @param event java fxml method trigger event
     */
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

    /** This is the select contact method.
     This method sets the contact ID variable based the contact selected from the contact combo box.
     @param event java fxml method trigger event
     */
    @FXML
    void onActionSelectContact(ActionEvent event) {
        Contacts contact = contactComboBox.getSelectionModel().getSelectedItem();
        contactID = contact.getContactID();
    }

    /** This is the get appointment by contact method.
     This method populates the Appointments by Contact tab tableview with all appointment data based on the contact
     selected in the select contact combo box.
     @param event java fxml method trigger event
     */
    @FXML
    void onActionGetApptsByContact(ActionEvent event) {
        appointmentsByContactTableView.setItems(DBAppointments.getAppointmentsByContact(contactID));
        apptByContactApptID.setCellValueFactory(new PropertyValueFactory<>("apptID"));
        apptByContactApptTitleCol.setCellValueFactory(new PropertyValueFactory<>("apptTitle"));
        apptByContactApptTypeCol.setCellValueFactory(new PropertyValueFactory<>("apptType"));
        apptByContactApptDescCol.setCellValueFactory(new PropertyValueFactory<>("apptDescription"));
        apptByContactStartDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("apptStartDateTime"));
        apptByContactEndDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("apptEndDateTime"));
        apptByContactCustomerIDCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
    }
}
