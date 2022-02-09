package DBAccess;

import Database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customers;

import java.sql.*;
import java.time.LocalDateTime;

public class DBCustomers {

    public static ObservableList<Customers> getAllCustomers() {
        ObservableList<Customers> customersObservableList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * from customers";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int customerID = rs.getInt("Customer_ID");
                String customerName = rs.getString("Customer_Name");
                String customerAddress = rs.getString("Address");
                String customerPostal = rs.getString("Postal_Code");
                String customerPhone = rs.getString("Phone");
                int divisionID = rs.getInt("Division_ID");

                Customers customer = new Customers(customerID, customerName, customerAddress, customerPostal, customerPhone,
                        divisionID);
                customersObservableList.add(customer);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return customersObservableList;

    }


    public static void addCustomer(String customerName, String customerAddress, String customerPostal, String customerPhone, Integer divisionID) {
        try {
            LocalDateTime localDateTimeToAdd = LocalDateTime.now();
            String createdByToAdd = "admin";
            Timestamp lastUpdateToAdd = Timestamp.valueOf(LocalDateTime.now());
            String lastUpdatedByToAdd = "admin";



            String sql = "INSERT INTO client_schedule.customers (Customer_Name, Address, Postal_code, Phone,  " +
                    "Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ps.setString(1, customerName);
            ps.setString(2, customerAddress);
            ps.setString(3, customerPostal);
            ps.setString(4, customerPhone);
            ps.setTimestamp(5, Timestamp.valueOf(createdByToAdd));
            ps.setString(6, createdByToAdd);
            ps.setTimestamp(7, lastUpdateToAdd);
            ps.setString(8, lastUpdatedByToAdd);
            ps.setInt(5, divisionID);
            ps.execute();

            ps.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

   public static void updateCustomer(String customerName, String customerAddress, String customerPostal, String customerPhone, Integer divisionID, String customerID) {
        try {
            String sql = "UPDATE client_schedule.customers SET Customer_Name=?, Address=?, Postal_Code=?, phone=?, Division_ID=? WHERE Customer_ID=?";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            //ps.setInt(7, customer.getCustomerID());
            ps.setString(1, customerName);
            ps.setString(2, customerAddress);
            ps.setString(3, customerPostal);
            ps.setString(4, customerPhone);
            //ps.setString(5, customer.getUpdatedBy());
            ps.setInt(5, divisionID);
            ps.setString(6, customerID);
            ps.execute();

            ps.close();



        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }



}
