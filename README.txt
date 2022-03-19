

APPLICATION TITLE
-----------------
Appointment Schedule Application

PURPOSE
-------
The purpose of the application is to provide a software solution for maintaining a database of business customers and scheduled appointments with the customer base.
Customer information records are created and saved in a database that include the customer’s name, street address, postal code, country and first level division along with phone number.
Customer appointment information is created and saved in a database using the Appointment Schedule Application.
Appointment information is saved with an appointment ID, title, description, appointment location, contact name,
appointment type, appointment start and end time, user ID, and customer ID. All customer and appointment information can be added, updated or deleted.
Appointment and customer reports can be generated using the integrated Reports menu.

AUTHOR and CONTACT INFORMATION
------------------------------
Author: Steven Prey
Student ID: 000989885
Email: sprey@wgu.edu
Application version: 1.0.0
Date: 3/14/2022

IDE VERSION
-----------
IntelliJ Community 2020.3

JDK VERSION
-----------
Corretto-11.0.10

JAVAFX VERSION
--------------
JavaFX-SDK-11.0.2

APPLICATION INSTRUCTIONS
------------------------
Database Setup:
1.	Ensure test database is setup on test machine with the following database connection parameters;
    DB Name: client_schedule
    DB URL: jdbc:mysql://localhost:3306
    DB Username: sqlUser
    DB Password: Passw0rd!

2.	Unzip application file to designated folder.

MySQL Driver:
3.	Ensure mysql-connector-java-8.0.22.jar file is available to add to the project library. MySQL driver version can be downloaded from "https://dev.mysql.com/downloads/connector/j/".
4.	Open application using IntelliJ IDEA and Install mysql connector. Navigate to File>Project Structure>Libraries and select the “+” New Project Library icon.
    Select “From Maven” and type “mysql” in the search field. Click the OK button once the mysql-connector-java-8.0.22 driver has been found.

JavaFX Setup:
5.	Ensure JavaFX SDK 11.0.2 library is installed on machine. Library can be downloaded from "https://gluonhq.com/products/javafx/".
6.	In IntelliJ IDEA, Navigate to File>Project Structure>Libraries and select the “+” New Project Library icon.
    Navigate to the location of the JavaFX library files are located and add all JavaFX .jar files into library.

Run Application:
7.	Build Project
8.	Navigate to src/C195.AppointmentScheduleApp/Main.java and run the main method.
9.	Once the Login menu appears, use the following credentials to login to the application;
    Username: test
    Password: test

ADDITIONAL REPORT FEATURE (from requirement A3f)
------------------------------------------------
In addition to the required “Customer Appointment Total by Month & Type” report and “Appointments by Contact” report,
a third “Total Customers by Country” report has been added to the report menu. This report shows the total count of customers broken out by Country location.

MYSQL CONNECTOR DRIVER VERSION
------------------------------
Mysql-connector-java-8.0.22
