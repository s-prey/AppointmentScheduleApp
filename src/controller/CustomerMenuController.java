package controller;

import DBAccess.DBCountries;
import DBAccess.DBCustomers;
import DBAccess.DBFirstLevelDivisions;
import Database.DBConnection;
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

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CustomerMenuController implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private TableView<Customers> customerTableView;
    @FXML
    private TableColumn<Customers, Integer> customerIDCol;
    @FXML
    private TableColumn<Customers, String> customerNameCol;
    @FXML
    private TableColumn<Customers, String> customerAddressCol;
    @FXML
    private TableColumn<Customers, String> customerPostalCol;
    @FXML
    private TableColumn<Customers, Integer> customerDivisionCol;
    @FXML
    private TableColumn<Customers, String> customerPhoneCol;

    @FXML
    private ComboBox<Countries> countryCmboBox;
    @FXML
    private ComboBox<FirstLevelDivisions> firstLevelDivisionCmboBox;

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

    public ObservableList<FirstLevelDivisions> divisions = FXCollections.observableArrayList();
    public ObservableList<Countries> countriesData = DBCountries.getAllCountries();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        customerTableView.setItems(DBCustomers.getAllCustomers());
        customerIDCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        customerNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        customerAddressCol.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
        customerPostalCol.setCellValueFactory(new PropertyValueFactory<>("customerPostal"));
        customerDivisionCol.setCellValueFactory(new PropertyValueFactory<>("divisionID"));
        customerPhoneCol.setCellValueFactory(new PropertyValueFactory<>("customerPhone"));

        ObservableList<Countries> allCountries = DBCountries.getAllCountries();
        ObservableList<String> countryNames = FXCollections.observableArrayList();

        countryCmboBox.setItems(allCountries);
        countryCmboBox.getSelectionModel().selectFirst();
        filterDivisions();

        //for (Countries country : allCountries) {
            //countryNames.add(country.getCountryName());
        //}
        //countryCmboBox.setItems(countryNames);
        //countryCmboBox.setEditable(true);
        //countryCmboBox.getEditor().setEditable(false);
        //ObservableList<FirstLevelDivisions> allFirstLevelDivisions = DBFirstLevelDivisions.getAllFirstLevelDivisions();
        //ObservableList<String> allFirstLevelDivisionNames = FXCollections.observableArrayList();

        //allFirstLevelDivisions.forEach(firstLevelDivisions -> allFirstLevelDivisionNames.add(firstLevelDivisions.getDivisionName()));


    }

    public void selectedCustomerData() throws SQLException {
       /* Customers selectedCustomer = customerTableView.getSelectionModel().getSelectedItem();
        if (selectedCustomer != null) {
            ObservableList<Countries> allCountries = DBCountries.getAllCountries();
            ObservableList<FirstLevelDivisions> allFirstLevelDivisions = DBFirstLevelDivisions.getAllFirstLevelDivisions();
            ObservableList<String> firstLevelDivisionName = FXCollections.observableArrayList();
            for (FirstLevelDivisions firstLevelDivisions : allFirstLevelDivisions) {
                firstLevelDivisionName.add(firstLevelDivisions.getDivisionName());
            }
            firstLevelDivisionCmboBox.setItems(firstLevelDivisionName);
            customerIDTxtField.setText(String.valueOf(selectedCustomer.getCustomerID()));
            customerNameTxtField.setText(selectedCustomer.getCustomerName());
            customerAddressTxtField.setText(selectedCustomer.getCustomerAddress());
            postalCodeTxtField.setText(selectedCustomer.getCustomerPostal());
            phoneNumberTxtField.setText(selectedCustomer.getCustomerPhone());

            int divisionID = selectedCustomer.getDivisionID();
            String divisionName = "";
            int countryID;
            String countryName = "";
            for (FirstLevelDivisions firstLevelDivisions : allFirstLevelDivisions) {
                if (divisionID == firstLevelDivisions.getDivisionID()) {
                    divisionName = firstLevelDivisions.getDivisionName();
                    countryID = firstLevelDivisions.getCountryID();
                    for (Countries countries : allCountries) {
                        if (countryID == countries.getCountryID()) {
                            countryName = countries.getCountryName();
                        }
                    }
                }
            }
            firstLevelDivisionCmboBox.setValue(divisionName);
            countryCmboBox.setValue(countryName);

        }

        */
    }




    @FXML
    void onActionAddNewCustomer(ActionEvent event) {

    }

    @FXML
    void onActionClearInformationFields(ActionEvent event) {

    }

    @FXML
    void onActionDeleteCustomer(ActionEvent event) {

    }

    @FXML
    void onActionFilterCountryCmboBox(ActionEvent event) {
        filterDivisions();
    }

    public void filterDivisions() {
        divisions.clear();
        int country_ID = countryCmboBox.getSelectionModel().getSelectedItem().getCountryID();
        for (FirstLevelDivisions div : DBFirstLevelDivisions.getAllFirstLevelDivisions()) {
            if (country_ID == div.getCountryID()) {
                divisions.add(div);
            }
        }
        firstLevelDivisionCmboBox.setItems(divisions);
        firstLevelDivisionCmboBox.getSelectionModel().selectFirst();
    }

    @FXML
    void onActionFilterFirstLevelDivisionCmboBox(ActionEvent event) {


        /*ObservableList<FirstLevelDivisions> allFirstLevelDivisions = DBFirstLevelDivisions.getAllFirstLevelDivisions();
        ObservableList<String> firstLevelDivisionsUS = FXCollections.observableArrayList();
        ObservableList<String> firstLevelDivisionsUK = FXCollections.observableArrayList();
        ObservableList<String> firstLevelDivisionsCA = FXCollections.observableArrayList();

        for (FirstLevelDivisions firstLevelDivisions : allFirstLevelDivisions) {
            if (firstLevelDivisions.getCountryID() == 1) {
                firstLevelDivisionsUS.add(firstLevelDivisions.getDivisionName());
            } else if (firstLevelDivisions.getCountryID() == 2) {
                firstLevelDivisionsUK.add(firstLevelDivisions.getDivisionName());
            } else if (firstLevelDivisions.getCountryID() == 3) {
                firstLevelDivisionsCA.add(firstLevelDivisions.getDivisionName());
            }
        }
        String selectedCountry = countryCmboBox.getSelectionModel().getSelectedItem();
        if (selectedCountry.equals("U.S")) {
            firstLevelDivisionCmboBox.setItems(firstLevelDivisionsUS);
        } else if (selectedCountry.equals("UK")) {
            firstLevelDivisionCmboBox.setItems(firstLevelDivisionsUK);
        } else if (selectedCountry.equals("Canada")) {
            firstLevelDivisionCmboBox.setItems(firstLevelDivisionsCA);
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
    void onActionUpdateCustomer(ActionEvent event) {

    }








}
