package DBAccess;

import Database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customersObservableList;
    }

    public static int insertCustomer(Customers customers, Connection connection) throws SQLException {
        String sql = "INSERT into customers (Customer_Name, Address, Postal_Code, Phone, Created_By, Last_Updated_By, Division_ID) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

        ps.setString(1, customers.getCustomerName());
        ps.setString(2, customers.getCustomerAddress());
        ps.setString(3, customers.getCustomerPostal());
        ps.setString(4,customers.getCustomerPhone());
        ps.setString(5, customers.getCreatedBy());
        ps.setString(6, customers.getUpdatedBy());


        int result = ps.executeUpdate();
        ps.close();

        return result;
    }
}
