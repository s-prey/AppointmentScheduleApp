package model;

/** This class creates Customers objects for the appointment schedule application.*/
public class Customers {
    private int customerID;
    private String customerName;
    private String customerAddress;
    private String customerPostal;
    private String customerPhone;
    private int divisionID;
    private int countryID;
    private String countryName;
    private String customerCount;

    /** This is the Customers object default constructor method.
     This method is used to setup parameters for an Customers object.
     @param customerID customer ID
     @param customerName customer name
     @param customerAddress customer address
     @param customerPostal customer postal code
     @param customerPhone customer phone number
     @param divisionID customer first level division ID
     @param countryID customer country ID
     */
    public Customers(int customerID, String customerName, String customerAddress, String customerPostal,
                     String customerPhone, int divisionID, int countryID) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPostal = customerPostal;
        this.customerPhone = customerPhone;
        this.divisionID = divisionID;
        this.countryID = countryID;
    }

    /** This is the Customers object override constructor method.
     This method is used to setup the parameters for an Customers object.
     @param countryName customer country name
     @param customerCount customer count
     */
    public Customers(String countryName, String customerCount) {
        this.countryName = countryName;
        this.customerCount = customerCount;
    }

    /** This is the get customer ID method.
     This method returns the customer ID.
     @return Returns the customer ID
     */
    public int getCustomerID() {
        return customerID;
    }

    /** This is the get customer name method.
     This method returns the customer name.
     @return Returns the customer name
     */
    public String getCustomerName() {
        return customerName;
    }

    /** This is the get customer address method.
     This method returns the customer address.
     @return Returns the customer address
     */
    public String getCustomerAddress() {
        return customerAddress;
    }

    /** This is the get customer postal method.
     This method returns the customer postal code.
     @return Returns the customer postal code
     */
    public String getCustomerPostal() {
        return customerPostal;
    }

    /** This is the get customer phone method.
     This method returns the customer phone number.
     @return Returns the customer phone number
     */
    public String getCustomerPhone() {
        return customerPhone;
    }

    /** This is the get division ID method.
     This method returns the customer first level division ID.
     @return Returns the customer first level division ID
     */
    public int getDivisionID() {
        return divisionID;
    }

    /** This is the get country ID method.
     This method returns the customer country ID.
     @return Returns customer country ID
     */
    public int getCountryID() {
        return countryID;
    }

    /** This method is the set customer ID method.
     This method sets the customer ID value.
     @param customerID The customer ID to set
     */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    /** This is the set customer name method.
     This method sets the customer name value.
     @param customerName The customer name to set
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /** This is the set customer address method.
     This method sets the customer address value.
     @param customerAddress The customer address to set
     */
    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    /** This is the set customer postal method.
     This method sets the customer postal code value.
     @param customerPostal The customer postal code to set
     */
    public void setCustomerPostal(String customerPostal) {
        this.customerPostal = customerPostal;
    }

    /** This is the set customer phone method.
     This method sets the customer phone number value.
     @param customerPhone The customer phone number to set
     */
    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    /** This is the set division ID method.
     This method sets the customer first level division ID value.
     @param divisionID The first level division to set
     */
    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }

    /** This is the set country ID method.
     This method sets the customer country ID value.
     @param countryID The country ID to set
     */
    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    /** This is the get country name method.
     This method returns the customer country name.
     @return Returns the country name
     */
    public String getCountryName() {
        return countryName;
    }

    /** This is the set country name method.
     This method sets the customer country name value.
     @param countryName The country name to set
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /** This is the get customer count method.
     This method returns the customer count.
     @return Returns the customer count
     */
    public String getCustomerCount() {
        return customerCount;
    }

    /** This is the set customer count method.
     This method sets the customer count value.
     @param customerCount The customer count to set
     */
    public void setCustomerCount(String customerCount) {
        this.customerCount = customerCount;
    }

    /** This is the Override to string method.
     This method overrides the default String method and returns the customer ID and customer name for combobox.
     @return Returns the customer ID and customer name
     */
    @Override
    public String toString() {
        return "(" + customerID + ") " + customerName;
    }
}
