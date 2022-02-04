package model;

public class Customers {
    private int customerID;
    private String customerName;
    private String customerAddress;
    private String customerPostal;
    private String customerPhone;
    private String createdBy;
    private String updatedBy;
    private int divisionID;

    public Customers() {

    }

    public Customers(int customerID, String customerName, String customerAddress, String customerPostal,
                     String customerPhone, int divisionID) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPostal = customerPostal;
        this.customerPhone = customerPhone;
        this.divisionID = divisionID;
    }

    public Customers(String customerName, String customerAddress, String customerPostal, String customerPhone,
                     String createdBy, String updatedBy, int divisionID) {
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPostal = customerPostal;
        this.customerPhone = customerPhone;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
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

    public String getCreatedBy() {
        return createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public int getDivisionID() {
        return divisionID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public void setCustomerPostal(String customerPostal) {
        this.customerPostal = customerPostal;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }

    @Override
    public String toString() {
        return (customerName);
    }
}
