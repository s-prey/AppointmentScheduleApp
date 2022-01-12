module AppointmentScheduleApp {

    requires javafx.fxml;
    requires javafx.controls;

    requires java.sql;
    requires java.desktop;

    opens controller;
    opens model;
    opens DBAccess;
    opens Database;
    opens C195.AppointmentScheduleApp;
}