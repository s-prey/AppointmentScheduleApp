package controller;

import DBAccess.DBCountries;
import DBAccess.DBCustomers;
import DBAccess.DBFirstLevelDivisions;
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
    public ObservableList<Countries> countriesList = DBCountries.getAllCountries();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        divisions = DBFirstLevelDivisions.getAllFirstLevelDivisions();

        countryCmboBox.setItems(DBCountries.getAllCountries());
        firstLevelDivisionCmboBox.setItems(divisions);

        customerTableView.setItems(DBCustomers.getAllCustomers());
        customerIDCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        customerNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        customerAddressCol.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
        customerPostalCol.setCellValueFactory(new PropertyValueFactory<>("customerPostal"));
        customerDivisionCol.setCellValueFactory(new PropertyValueFactory<>("divisionID"));
        customerPhoneCol.setCellValueFactory(new PropertyValueFactory<>("customerPhone"));


    }

    public void selectedCustomerData() throws SQLException {
        Customers selectedCustomer = customerTableView.getSelectionModel().getSelectedItem();
        customerIDTxtField.setText(String.valueOf(selectedCustomer.getCustomerID()));
        customerNameTxtField.setText(selectedCustomer.getCustomerName());
        customerAddressTxtField.setText(selectedCustomer.getCustomerAddress());
        postalCodeTxtField.setText(selectedCustomer.getCustomerPostal());
        phoneNumberTxtField.setText(selectedCustomer.getCustomerPhone());

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
    }


    @FXML
    void onActionAddNewCustomer(ActionEvent event) {

    }

    @FXML
    void onActionClearInformationFields(ActionEvent event) {
        customerIDTxtField.setText("Auto-Generated");
        customerNameTxtField.clear();
        customerAddressTxtField.clear();
        postalCodeTxtField.clear();
        countryCmboBox.getSelectionModel().clearSelection();
        firstLevelDivisionCmboBox.getSelectionModel().clearSelection();
        phoneNumberTxtField.clear();

    }

    @FXML
    void onActionDeleteCustomer(ActionEvent event) {

    }

   /* @FXML
    void onActionFilterCountryCmboBox(MouseEvent event) throws SQLException {
        String sql = "SELECT * from countries";
        PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Countries countries = new Countries();
            countries.setCountryName(rs.getString("Country"));
            countryCmboBox.setItems(countriesList);
            firstLevelDivisionCmboBox.setValue(null);
        }


    }

    */

    public ObservableList<FirstLevelDivisions> filterDivisions() {
        ObservableList<FirstLevelDivisions> divisionsList = DBFirstLevelDivisions.getAllFirstLevelDivisions();
        FilteredList<FirstLevelDivisions> filteredDivisionsList = new FilteredList<>(divisionsList, i -> i.getCountryID() == countryCmboBox.getSelectionModel().getSelectedItem().getCountryID());
        return filteredDivisionsList;



        /*divisions.clear();
        int country_ID = countryCmboBox.getSelectionModel().getSelectedItem().getCountryID();
        for (FirstLevelDivisions div : DBFirstLevelDivisions.getAllFirstLevelDivisions()) {
            if (country_ID == div.getCountryID()) {
                //divisions.add(div);
                firstLevelDivisionCmboBox.setItems(divisions);
            }
        }
        //firstLevelDivisionCmboBox.setItems(divisions);
        //firstLevelDivisionCmboBox.getSelectionModel().selectFirst();

         */

    }

   /* @FXML
    void onActionFilterFirstLevelDivisionCmboBox(javafx.scene.input.MouseEvent event) {

    }

    */



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

    private void clearInformationFields() {
        customerIDTxtField.setText("Auto-Generated");
        customerNameTxtField.clear();
        customerAddressTxtField.clear();
        postalCodeTxtField.clear();
        countryCmboBox.getSelectionModel().clearSelection();
        firstLevelDivisionCmboBox.getSelectionModel().clearSelection();
        phoneNumberTxtField.clear();
    }


    public void onActionFilterCountryCmboBox(javafx.scene.input.MouseEvent mouseEvent) throws SQLException {
        String sql = "SELECT * from countries";
        PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Countries countries = new Countries();
            countries.setCountryName(rs.getString("Country"));
            countryCmboBox.setItems(countriesList);
            firstLevelDivisionCmboBox.setValue(null);
        }
    }


    public void onActionFilterFirstLevelDivisionCmboBox(javafx.scene.input.MouseEvent mouseEvent) {
        try {
            divisions.clear();
            int countryID = countryCmboBox.getSelectionModel().getSelectedItem().getCountryID();
            for (FirstLevelDivisions fld : DBFirstLevelDivisions.getAllFirstLevelDivisions()) {
                if (fld.getCountryID() == countryID) {
                    divisions.add(fld);
                }
            }
            firstLevelDivisionCmboBox.setItems(divisions);
        } catch (NumberFormatException e) {

        }
    }
}
