module AppointmentScheduleApp {

    requires javafx.fxml;
    requires javafx.controls;

    requires java.sql;
    requires java.desktop;

    opens controller;
    opens model;
    opens C195.AppointmentScheduleApp;
}