package DBAccess;

import Database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customers;
import model.Users;

import java.net.PortUnreachableException;
import java.sql.*;
import java.time.LocalDateTime;

public class DBCustomers {

    public static ObservableList<Customers> getAllCustomers() {
        ObservableList<Customers> customersList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT Customer_ID, Customer_Name, Address, Postal_Code, Phone, c.Division_ID, " +
                    "COUNTRY_ID FROM customers AS c, first_level_divisions AS f WHERE c.Division_ID=f.Division_ID";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int customerID = rs.getInt("Customer_ID");
                String customerName = rs.getString("Customer_Name");
                String customerAddress = rs.getString("Address");
                String customerPostal = rs.getString("Postal_Code");
                String customerPhone = rs.getString("Phone");
                int divisionID = rs.getInt("Division_ID");
                int countryID = rs.getInt("Country_ID");

                Customers customer = new Customers(customerID, customerName, customerAddress, customerPostal, customerPhone,
                        divisionID, countryID);
                customersList.add(customer);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return customersList;
    }


    public static void addCustomer(String customerName, String customerAddress, String customerPostal, String customerPhone, Integer divisionID) {

        try {
            LocalDateTime localDateTimeToAdd = LocalDateTime.now();
            String createdByToAdd = "test";
            Timestamp lastUpdateToAdd = Timestamp.valueOf(LocalDateTime.now());
            String lastUpdatedByToAdd = "test";

            String sql = "INSERT INTO client_schedule.customers VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ps.setString(1, customerName);
            ps.setString(2, customerAddress);
            ps.setString(3, customerPostal);
            ps.setString(4, customerPhone);
            ps.setTimestamp(5, Timestamp.valueOf(localDateTimeToAdd));
            ps.setString(6, createdByToAdd);
            ps.setTimestamp(7, lastUpdateToAdd);
            ps.setString(8, lastUpdatedByToAdd);
            ps.setInt(9, divisionID);
            ps.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


   public static void updateCustomer(int customerID, String customerName, String customerAddress, String customerPostal, String customerPhone, Integer divisionID) {

        try {
            Timestamp lastUpdateToAdd = Timestamp.valueOf(LocalDateTime.now());
            String lastUpdatedByToAdd = "test";

            String sql = "UPDATE client_schedule.customers SET Customer_Name =?, Address=?, Postal_Code=?, phone=?, Last_Update=?, Last_Updated_By=?, Division_ID=? WHERE Customer_ID=?;";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ps.setString(1, customerName);
            ps.setString(2, customerAddress);
            ps.setString(3, customerPostal);
            ps.setString(4, customerPhone);
            ps.setTimestamp(5, lastUpdateToAdd);
            ps.setString(6, lastUpdatedByToAdd);
            ps.setInt(7, divisionID);
            ps.setInt(8, customerID);
            ps.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    public static void deleteCustomer(int customerID) {

        try {
            String sqlDelAppt = "DELETE FROM client_schedule.appointments WHERE Customer_ID = ?";
            PreparedStatement psAppt = DBConnection.getConnection().prepareStatement(sqlDelAppt);
            psAppt.setInt(1, customerID);
            psAppt.execute();

            String sqlDelCustomer = "DELETE FROM client_schedule.customers WHERE Customer_ID = ?";
            PreparedStatement psCustomer = DBConnection.getConnection().prepareStatement(sqlDelCustomer);
            psCustomer.setInt(1, customerID);
            psCustomer.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }




    public static ObservableList<Customers> getCustomerCountByCountry() {
        ObservableList<Customers> customersByCountryList = FXCollections.observableArrayList();

        try {
            String sql = "Select count(Customer_ID) AS Customer_Count, Country FROM client_schedule.customers AS c " +
                    "JOIN first_level_divisions AS f ON c.Division_ID=f.Division_ID JOIN countries o " +
                    "ON f.Country_ID=o.Country_ID group by Country";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String countryName = rs.getString("Country");
                String customerCount = rs.getString("Customer_Count");

                Customers customer = new Customers(countryName, customerCount);
                customersByCountryList.add(customer);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return customersByCountryList;
    }
}
