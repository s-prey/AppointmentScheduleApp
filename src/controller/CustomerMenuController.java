package controller;

import DBAccess.DBCountries;
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
import model.Countries;
import model.Customers;
import model.FirstLevelDivisions;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.*;
import java.util.function.Consumer;
import static DBAccess.DBFirstLevelDivisions.*;

/** This class is used as a java fx controller for the appointment schedule application customer menu GUI screen.*/
public class CustomerMenuController implements Initializable {

    Stage stage;
    Parent scene;
    private int customerID;

    @FXML private TableView<Customers> customerTableView;
    @FXML private TableColumn<Customers, Integer> customerIDCol;
    @FXML private TableColumn<Customers, String> customerNameCol;
    @FXML private TableColumn<Customers, String> customerAddressCol;
    @FXML private TableColumn<Customers, String> customerPostalCol;
    @FXML private TableColumn<Customers, String> customerCountryCol;
    @FXML private TableColumn<Customers, Integer> customerDivisionCol;
    @FXML private TableColumn<Customers, String> customerPhoneCol;

    @FXML private ComboBox<Countries> countryCmboBox;
    @FXML private ComboBox<FirstLevelDivisions> firstLevelDivisionCmboBox;

    @FXML private TextField customerIDTxtField;
    @FXML private TextField customerNameTxtField;
    @FXML private TextField customerAddressTxtField;
    @FXML private TextField postalCodeTxtField;
    @FXML private TextField phoneNumberTxtField;

    /** This is the initialize method.
     This method is used to initialize data for the customer menu controller.
     @param url uniform resource locator to initialize
     @param resourceBundle resource bundle to initialize
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        countryCmboBox.setItems(DBCountries.getAllCountries());
        customerTableView.setItems(DBCustomers.getAllCustomers());
        customerIDCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        customerNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        customerAddressCol.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
        customerPostalCol.setCellValueFactory(new PropertyValueFactory<>("customerPostal"));
        customerCountryCol.setCellValueFactory(new PropertyValueFactory<>("countryID"));
        customerDivisionCol.setCellValueFactory(new PropertyValueFactory<>("divisionID"));
        customerPhoneCol.setCellValueFactory(new PropertyValueFactory<>("customerPhone"));
    }

    /** This is the selected customer data method.
     This method takes the selected customer data from the menu tableview and populates the customer data fields
     for updating or deleting.
     */
    public void selectedCustomerData() {
        Customers selectedCustomer = customerTableView.getSelectionModel().getSelectedItem();
        customerIDTxtField.setText(String.valueOf(selectedCustomer.getCustomerID()));
        customerID = selectedCustomer.getCustomerID();
        customerNameTxtField.setText(selectedCustomer.getCustomerName());
        customerAddressTxtField.setText(selectedCustomer.getCustomerAddress());
        postalCodeTxtField.setText(selectedCustomer.getCustomerPostal());
        phoneNumberTxtField.setText(selectedCustomer.getCustomerPhone());

        countryCmboBox.setValue(Countries.getDivisionAndCountryMatch(selectedCustomer.getCountryID()));
        onActionFilterCountryCmboBox(null);
        firstLevelDivisionCmboBox.setValue(FirstLevelDivisions.getDivisionIDMatch(selectedCustomer.getDivisionID()));

    }

    /** This is the empty field check method.
     This method checks for any empty customer GUI data fields before adding customer information and displays
     an error message to the user for which field is empty.
     @return Returns boolean value for empty fields
     */
    public boolean emptyFieldCheck() {
        boolean emptyField = false;

        if (customerNameTxtField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Empty Field");
            alert.setHeaderText("Name Empty");
            alert.setContentText("Input Customer Name");
            alert.showAndWait();
            emptyField = true;
        }
        else if  (customerAddressTxtField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Empty Field");
            alert.setHeaderText("Address Empty");
            alert.setContentText("Input Customer Address");
            alert.showAndWait();
            emptyField = true;
        }
        else if (postalCodeTxtField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Empty Field");
            alert.setHeaderText("Postal Code Empty");
            alert.setContentText("Input Customer Postal Code");
            alert.showAndWait();
            emptyField = true;
        }

        else if (firstLevelDivisionCmboBox.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Empty Box");
            alert.setHeaderText("First Level Division Box Empty");
            alert.setContentText("Select Customer First Level Division");
            alert.showAndWait();
            emptyField = true;
        }

        else if (countryCmboBox.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Empty Box");
            alert.setHeaderText("Country Box Empty");
            alert.setContentText("Select Customer Country");
            alert.showAndWait();
            emptyField = true;
        }

        else if (phoneNumberTxtField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Empty Field");
            alert.setHeaderText("Phone Number Empty");
            alert.setContentText("Input Customer Phone Number");
            alert.showAndWait();
            emptyField = true;

        } else {
            emptyField = false;
        }
        return emptyField;
    }

    /** This is the add new customer method.
     This method takes the customer data entered in the GUI customer data information fields and adds the
     customer data to the client_schedule database via DBCustomers.addCustomer class method.
     @param event java fxml method trigger event
     */
    @FXML
    public void onActionAddNewCustomer(ActionEvent event) {

       try {
           if (emptyFieldCheck() == true) {
               return;

           } else {
               String customerName = customerNameTxtField.getText();
               String customerAddress = customerAddressTxtField.getText();
               String customerPostal = postalCodeTxtField.getText();
               String customerPhone = phoneNumberTxtField.getText();

               FirstLevelDivisions divisions = firstLevelDivisionCmboBox.getSelectionModel().getSelectedItem();
               int divisionID = divisions.getDivisionID();

               DBCustomers.addCustomer(customerName, customerAddress, customerPostal, customerPhone, divisionID);

               System.out.println("Add Customer successful.");
               stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
               scene = FXMLLoader.load(getClass().getResource("/View/CustomerMenu.fxml"));
               stage.setScene(new Scene(scene));
               stage.show();
           }
       } catch (IOException ex) {
           ex.printStackTrace();
       }
    }

    /** This is the clear information fields method.
     This method clears the customer information fields in the customer menu.
     @param event java fxml method trigger event
     */
    @FXML
    public void onActionClearInformationFields(ActionEvent event) {
        try {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/View/CustomerMenu.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /** This is the delete customer method.
     This method deletes a selected customer from the tableview and provides a confirmation message for deletion.
     LAMBDA EXPRESSION: A lambda expression utilized for the confirmation OK button response to provide improved code readability.
     @param event java fxml method trigger event
     */
    @FXML
    public void onActionDeleteCustomer(ActionEvent event) {
        Customers selectedCustomer = customerTableView.getSelectionModel().getSelectedItem();

        if (selectedCustomer == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No customer selected for delete.");
            alert.setContentText("Select customer to delete.");
            alert.showAndWait();
            return;
        }
        // Lambda Expression
        int customerID = selectedCustomer.getCustomerID();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Delete");
        alert.setHeaderText("Deleting selected customer and associated appointments. Do you wish to continue?");
        alert.showAndWait().filter(response -> response == ButtonType.OK).ifPresent(new Consumer<ButtonType>() {

            @Override
            public void accept(ButtonType response) {
                DBCustomers.deleteCustomer(customerID);
                try {
                    stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                    scene = FXMLLoader.load(getClass().getResource("/View/CustomerMenu.fxml"));
                    stage.setScene(new Scene(scene));
                    stage.show();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    /** This is the switch to appointment menu method.
     This method loads the appointment menu screen when the appointment menu button is selected from the customer menu.
     @param event java fxml method trigger event
     */
    @FXML
    public void onActionSwitchToAppointmentMenu(ActionEvent event) {
        try {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/View/AppointmentMenu.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

        /** This is the switch to reports menu method.
     This method loads the reports menu screen when the reports menu button is selected from the appointment menu.
     @param event java fxml method trigger event
     */
    @FXML
    public void onActionSwitchToReportsMenu(ActionEvent event) {
        try {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/View/ReportsMenu.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

        /** This is the update customer method.
     This method takes the customer data entered in the GUI customer data information fields and updates the
     customer data to the client_schedule database via DBCustomers.updateCustomer class method.
     @param event java fxml method trigger event
     */
    @FXML
    public void onActionUpdateCustomer(ActionEvent event) {

        String customerName = customerNameTxtField.getText();
        String customerAddress = customerAddressTxtField.getText();
        String customerPostal = postalCodeTxtField.getText();
        String customerPhone = phoneNumberTxtField.getText();

        FirstLevelDivisions divisions = firstLevelDivisionCmboBox.getSelectionModel().getSelectedItem();

        int divisionID = divisions.getDivisionID();

        DBCustomers.updateCustomer(customerID, customerName, customerAddress, customerPostal, customerPhone, divisionID);

        try {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/View/CustomerMenu.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

        /** This is the filter country combo box method.
     This method takes the country combo box selection and filters the first level divisions for that country to set
     the first level division combo box.
     @param event java fxml method trigger event
     */
    public void onActionFilterCountryCmboBox(ActionEvent event) {
        try {
            Countries country = countryCmboBox.getSelectionModel().getSelectedItem();
            int countryID = country.getCountryID();
            firstLevelDivisionCmboBox.setItems(getDivisionsByCountry(countryID));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
