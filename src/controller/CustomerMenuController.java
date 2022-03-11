package controller;

import DBAccess.DBCountries;
import DBAccess.DBCustomers;
import DBAccess.DBFirstLevelDivisions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Date;
import java.util.function.Consumer;

import static DBAccess.DBFirstLevelDivisions.*;

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

    @FXML
    private TextField customerIDTxtField;
    @FXML
    private TextField customerNameTxtField;
    @FXML
    private TextField customerAddressTxtField;
    @FXML
    private TextField postalCodeTxtField;
    @FXML
    private TextField phoneNumberTxtField;

    @FXML
    private Button addNewCustomerButton;

    @FXML
    private Button appointmentMenuButton;

    @FXML
    private Button clearInformationFieldsButton;

    @FXML
    private Button deleteCustomerButton;

    @FXML
    private Button reportsMenuButton;

    @FXML
    private Button updateCustomerButton;

    public ObservableList<Customers> customerData = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //firstLevelDivisionCmboBox.setItems(DBFirstLevelDivisions.getAllFirstLevelDivisions());
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



/*
    public void refreshTableView() {
        firstLevelDivisionCmboBox.setItems(DBFirstLevelDivisions.getAllFirstLevelDivisions());
        countryCmboBox.setItems(DBCountries.getAllCountries());

        customerTableView.setItems(DBCustomers.getAllCustomers());
        customerIDCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        customerNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        customerAddressCol.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
        customerPostalCol.setCellValueFactory(new PropertyValueFactory<>("customerPostal"));
        customerDivisionCol.setCellValueFactory(new PropertyValueFactory<>("divisionID"));
        customerPhoneCol.setCellValueFactory(new PropertyValueFactory<>("customerPhone"));
    }

 */


    public void selectedCustomerData() throws IOException {

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
            //return false;
            emptyField = true;
        }
        else if (postalCodeTxtField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Empty Field");
            alert.setHeaderText("Postal Code Empty");
            alert.setContentText("Input Customer Postal Code");
            alert.showAndWait();
            //return false;
            emptyField = true;
        }

        else if (firstLevelDivisionCmboBox.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Empty Box");
            alert.setHeaderText("First Level Division Box Empty");
            alert.setContentText("Select Customer First Level Division");
            alert.showAndWait();
            //return false;
            emptyField = true;
        }

        else if (countryCmboBox.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Empty Box");
            alert.setHeaderText("Country Box Empty");
            alert.setContentText("Select Customer Country");
            alert.showAndWait();
            //return false;
            emptyField = true;
        }

        else if (phoneNumberTxtField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Empty Field");
            alert.setHeaderText("Phone Number Empty");
            alert.setContentText("Input Customer Phone Number");
            alert.showAndWait();
            //return false;
            emptyField = true;
        } else {
            emptyField = false;
        }
        return emptyField;
    }

    @FXML
    public void onActionAddNewCustomer(ActionEvent event) throws IOException, SQLException {
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
    }

    public void clearInfomrationFields () {
        customerIDTxtField.setText("Auto-Generated");
        customerNameTxtField.clear();
        customerAddressTxtField.clear();
        postalCodeTxtField.clear();
        countryCmboBox.getSelectionModel().clearSelection();
        countryCmboBox.setValue(null);
        firstLevelDivisionCmboBox.getSelectionModel().clearSelection();
        firstLevelDivisionCmboBox.setValue(null);
        phoneNumberTxtField.clear();
    }


    @FXML
    void onActionClearInformationFields(ActionEvent event) {

        try {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/View/CustomerMenu.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

/*
    public void deleteCustomersAppointments() {
        try {
            String sql = "DELETE FROM client_schedule.appointments WHERE Customer_ID = ?";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ps.setString(1, String.valueOf(customerTableView.getSelectionModel().getSelectedItem().getCustomerID()));
            //int result = ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        refreshTableView();
        clearInfomrationFields();
    }

 */


    @FXML
    void onActionDeleteCustomer(ActionEvent event) {
        Customers selectedCustomer = customerTableView.getSelectionModel().getSelectedItem();

        if (selectedCustomer == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No customer selected for delete.");
            alert.setContentText("Select customer to delete.");
            alert.showAndWait();
            return;
        }

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

/*
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Deleting selected customer and associated appointments. Do you wish to continue?");
        Optional<ButtonType> results = alert.showAndWait();

        if (results.isPresent() && results.get() == ButtonType.OK) {
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

 */

    }


    @FXML
    void onActionSwitchToAppointmentMenu(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/AppointmentMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }


    @FXML
    void onActionSwitchToReportsMenu(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/ReportsMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }


    @FXML
    void onActionUpdateCustomer(ActionEvent event) throws SQLException, IOException {

        String customerName = customerNameTxtField.getText();
        String customerAddress = customerAddressTxtField.getText();
        String customerPostal = postalCodeTxtField.getText();
        String customerPhone = phoneNumberTxtField.getText();

        FirstLevelDivisions divisions = firstLevelDivisionCmboBox.getSelectionModel().getSelectedItem();

        int divisionID = divisions.getDivisionID();

        DBCustomers.updateCustomer(customerID, customerName, customerAddress, customerPostal, customerPhone, divisionID);

        System.out.println("Update Customer successful.");
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/CustomerMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }


    public void onActionFilterCountryCmboBox(ActionEvent event) throws IOException {

       Countries country = countryCmboBox.getSelectionModel().getSelectedItem();
       int countryID = country.getCountryID();
       firstLevelDivisionCmboBox.setItems(getDivisionsByCountry(countryID));
    }

}
