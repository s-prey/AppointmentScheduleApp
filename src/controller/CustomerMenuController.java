package controller;

import DBAccess.DBCountries;
import DBAccess.DBCustomers;
import DBAccess.DBFirstLevelDivisions;
import DBAccess.DBUsers;
import Database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
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
import model.Users;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

import static DBAccess.DBCountries.getAllCountries;
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


    //public ObservableList<FirstLevelDivisions> divisions = FXCollections.observableArrayList();
    //public ObservableList<Countries> countriesList = DBCountries.getAllCountries();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        firstLevelDivisionCmboBox.setItems(DBFirstLevelDivisions.getAllFirstLevelDivisions());
        countryCmboBox.setItems(DBCountries.getAllCountries());
        customerTableView.setItems(DBCustomers.getAllCustomers());
        customerIDCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        customerNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        customerAddressCol.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
        customerPostalCol.setCellValueFactory(new PropertyValueFactory<>("customerPostal"));
        customerDivisionCol.setCellValueFactory(new PropertyValueFactory<>("divisionID"));
        customerPhoneCol.setCellValueFactory(new PropertyValueFactory<>("customerPhone"));

        //refreshTableView();

    }

    public void refreshTableView() {
        //divisions = DBFirstLevelDivisions.getAllFirstLevelDivisions();

        //countryCmboBox.setItems(DBCountries.getAllCountries());
        //firstLevelDivisionCmboBox.setItems(divisions);

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
        /*
        divisions = DBFirstLevelDivisions.getAllFirstLevelDivisions();
        for (FirstLevelDivisions FLD : divisions) {
            if (selectedCustomer.getDivisionID() == FLD.getDivisionID()) {
                System.out.println(FLD);
                firstLevelDivisionCmboBox.setValue(FLD);
                for (Countries countries : countriesList) {
                    if (FLD.getCountryID() == countries.getCountryID()) {
                        System.out.println(countries);
                        countryCmboBox.setValue(countries);
                    }
                }
            }
        }

         */
    }

    public boolean errorCheck(/*String customer_ID*/) {
        //ObservableList<Customers> customersList = DBCustomers.getAllCustomers();
        boolean errorCheck = false;

        if (customerNameTxtField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Empty Field");
            alert.setHeaderText("Name Empty");
            alert.setContentText("Input Customer Name");
            alert.showAndWait();
            errorCheck = true;
        }
        else if  (customerAddressTxtField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Empty Field");
            alert.setHeaderText("Address Empty");
            alert.setContentText("Input Customer Address");
            alert.showAndWait();
            //return false;
            errorCheck = true;
        }
        else if (postalCodeTxtField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Empty Field");
            alert.setHeaderText("Postal Code Empty");
            alert.setContentText("Input Customer Postal Code");
            alert.showAndWait();
            //return false;
            errorCheck = true;
        }

        else if (firstLevelDivisionCmboBox.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Empty Box");
            alert.setHeaderText("First Level Division Box Empty");
            alert.setContentText("Select Customer First Level Division");
            alert.showAndWait();
            //return false;
            errorCheck = true;
        }

        else if (countryCmboBox.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Empty Box");
            alert.setHeaderText("Country Box Empty");
            alert.setContentText("Select Customer Country");
            alert.showAndWait();
            //return false;
            errorCheck = true;
        }

        else if (phoneNumberTxtField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Empty Field");
            alert.setHeaderText("Phone Number Empty");
            alert.setContentText("Input Customer Phone Number");
            alert.showAndWait();
            //return false;
            errorCheck = true;
        } else {
            errorCheck = false;
        }

        return errorCheck;
    }

    @FXML
    public void onActionAddNewCustomer(ActionEvent event) throws IOException, SQLException {


        //Boolean noError = errorCheck(customerIDTxtField.getText());
        if (errorCheck() == true) {
            return;

        } else {
            String customerName = customerNameTxtField.getText();
            String customerAddress = customerAddressTxtField.getText();
            String customerPostal = postalCodeTxtField.getText();
            String customerPhone = phoneNumberTxtField.getText();

            //FirstLevelDivisions divisions = firstLevelDivisionCmboBox.getValue();
            FirstLevelDivisions divisions = firstLevelDivisionCmboBox.getSelectionModel().getSelectedItem();
            int divisionID = divisions.getDivisionID();

            DBCustomers.addCustomer(customerName, customerAddress, customerPostal, customerPhone, divisionID);




/*
            //DBCustomers.addCustomer(customerName, customerAddress, customerPostal, customerPhone, divisions.getDivisionID());
            ObservableList<FirstLevelDivisions> flDivisions = FXCollections.observableArrayList();
            flDivisions = DBFirstLevelDivisions.getAllFirstLevelDivisions();

            countryCmboBox.setItems(DBCountries.getAllCountries());
            firstLevelDivisionCmboBox.setItems(flDivisions);

            LocalDateTime localDateTimeToAdd = LocalDateTime.now();
            String createdByToAdd = "admin";
            Timestamp lastUpdateToAdd = Timestamp.valueOf(LocalDateTime.now());
            String lastUpdatedByToAdd = "admin";



            String sql = "INSERT INTO client_schedule.customers (Customer_Name, Address, Postal_code, Phone,  " +
                    "Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ps.setString(1, customerName);
            ps.setString(2, customerAddress);
            ps.setString(3, customerPostal);
            ps.setString(4, customerPhone);
            ps.setTimestamp(5, Timestamp.valueOf(localDateTimeToAdd));
            ps.setString(6, createdByToAdd);
            ps.setTimestamp(7, lastUpdateToAdd);
            ps.setString(8, lastUpdatedByToAdd);
            ps.setInt(9, divisions.getDivisionID());
            ps.execute();

 */

            //customerTableView.setItems(DBCustomers.getAllCustomers());
            //customerIDCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
            //customerNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
            //customerAddressCol.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
            //customerPostalCol.setCellValueFactory(new PropertyValueFactory<>("customerPostal"));
            //customerDivisionCol.setCellValueFactory(new PropertyValueFactory<>("divisionID"));
            //customerPhoneCol.setCellValueFactory(new PropertyValueFactory<>("customerPhone"));



            //refreshTableView();
            //clearInfomrationFields();
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
        clearInfomrationFields();
    }

    public void deleteCustomersAppointments() {
        try {
            String sql = "DELETE FROM client_schedule.appointments WHERE Customer_ID = ?";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ps.setString(1, String.valueOf(customerTableView.getSelectionModel().getSelectedItem().getCustomerID()));
            int result = ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        refreshTableView();
        clearInfomrationFields();
    }

    @FXML
    void onActionDeleteCustomer(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Deleting selected customer and associated appointments. Do you wish to continue?");
        Optional<ButtonType> results = alert.showAndWait();
        try {
            if (results.isPresent() && results.get() == ButtonType.OK) {
                int customerID = customerTableView.getSelectionModel().getSelectedItem().getCustomerID();
                String sql = "DELETE FROM client_schedule.customers WHERE Customer_ID = ?";
                PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
                ps.setInt(1, customerID);
                int result = ps.executeUpdate();
                deleteCustomersAppointments();

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
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

        //Boolean noError = errorCheck(customerIDTxtField.getText());
        /*
        if (errorCheck() == true) {
            return;

        } else {

         */

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
        /*
        Boolean noError = errorCheck(customerIDTxtField.getText());
        if (noError) {

            int customerID = Integer.parseInt(customerIDTxtField.getText());
            String customerName = customerNameTxtField.getText();
            String customerAddress = customerAddressTxtField.getText();
            String customerPostal = postalCodeTxtField.getText();
            String customerPhone = phoneNumberTxtField.getText();
            FirstLevelDivisions divisions = firstLevelDivisionCmboBox.getValue();
            System.out.println(divisions);

            int firstLevelDivisionsToUpdate = 0;

         */

           /* ObservableList<FirstLevelDivisions> flDivisions = DBFirstLevelDivisions.getAllFirstLevelDivisions();
            for (FirstLevelDivisions firstLevelDivision : flDivisions) {
                if (Objects.equals(firstLevelDivisionsToUpdate, firstLevelDivision.getDivisionName())) {
                    firstLevelDivisionsToUpdate = firstLevelDivision.getDivisionID();
                    System.out.println(firstLevelDivisionsToUpdate);
                }

            */


                //for (FirstLevelDivisions firstLevelDivisions : FLD) {
                //if (Objects.equals(firstLevelDivisionsToUpdate, firstLevelDivisions.getDivisionName())) {
                //firstLevelDivisionsToUpdate = firstLevelDivisions.getDivisionID();
                //}
                //}

            /*
                Timestamp lastUpdateToAdd = Timestamp.valueOf(LocalDateTime.now());
                String lastUpdatedByToAdd = "admin";
                String sql = "UPDATE client_schedule.customers SET Customer_ID = ?, Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Last_Update = ?, Last_Updated_By = ?, Division_ID = ? WHERE Customer_ID = ?";
                PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
                ps.setInt(1, customerID);
                ps.setString(2, customerName);
                ps.setString(3, customerAddress);
                ps.setString(4, customerPostal);
                ps.setString(5, customerPhone);
                ps.setTimestamp(6, lastUpdateToAdd);
                ps.setString(7, lastUpdatedByToAdd);
                ps.setInt(8, firstLevelDivisionsToUpdate);
                ps.setInt(9, customerID);
                ps.execute();
                refreshTableView();
                clearInfomrationFields();

             */


        /*Connection connection = DBConnection.startConnection();

        Customers customer = new Customers();

        customer.setCustomerID(Integer.valueOf(customerIDTxtField.getText()));
        customer.setCustomerName(customerNameTxtField.getText());
        customer.setCustomerAddress(customerAddressTxtField.getText());
        customer.setCustomerPostal(postalCodeTxtField.getText());
        customer.setCustomerPhone(phoneNumberTxtField.getText());
        customer.setUpdatedBy(DBUsers.loggedUser.getUserName());
        customer.setDivisionID(firstLevelDivisionCmboBox.getSelectionModel().getSelectedItem().getDivisionID());

        int update = DBCustomers.updateCustomer(customer, connection);
        customerTableView.setItems(DBCustomers.getAllCustomers(connection));

         */

    }







    public void onActionFilterCountryCmboBox(ActionEvent event) throws IOException {

       Countries country = countryCmboBox.getSelectionModel().getSelectedItem();

       int countryID = country.getCountryID();

       firstLevelDivisionCmboBox.setItems(getDivisionsByCountry(countryID));

        /*try {

           String sql = "SELECT * from countries";
           PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
           ResultSet rs = ps.executeQuery();
           while (rs.next()) {
               Countries countries = new Countries();
               countries.setCountryName(rs.getString("Country"));
               countryCmboBox.setItems(countriesList);
               firstLevelDivisionCmboBox.setValue(null);
           }
       } catch (SQLException ex) {
           ex.printStackTrace();
       }

        */
    }


   //public void onActionFilterFirstLevelDivisionCmboBox(javafx.scene.input.MouseEvent mouseEvent) {
        /*(try {
            //boolean isComboBoxEmpty = countryCmboBox.getSelectionModel().isEmpty();
            if (countryCmboBox.getValue() == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Selection Error");
                alert.setContentText("A Country must be selected before First Level Division selection");
                alert.showAndWait();
                return;
            } else {
                divisions.clear();
                int countryID = countryCmboBox.getSelectionModel().getSelectedItem().getCountryID();
                for (FirstLevelDivisions fld : DBFirstLevelDivisions.getAllFirstLevelDivisions()) {
                    if (fld.getCountryID() == countryID) {
                        divisions.add(fld);
                    }
                }
                firstLevelDivisionCmboBox.setItems(divisions);
            }
        } catch (NumberFormatException e) {

        }

         */
    //}


}
