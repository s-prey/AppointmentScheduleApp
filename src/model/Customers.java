package model;

public class Customers {
    private int customerID;
    private String customerName;
    private String customerAddress;
    private String customerPostal;
    private String customerPhone;
    private int divisionID;

    public Customers(int customerID, String customerName, String customerAddress, String customerPostal,
                     String customerPhone, int divisionID) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPostal = customerPostal;
        this.customerPhone = customerPhone;
        this.divisionID = divisionID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public String getCustomerPostal() {
        return customerPostal;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public int getDivisionID() {
        return divisionID;
    }
}
