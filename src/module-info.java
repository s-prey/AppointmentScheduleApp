module AppointmentScheduleApp {

    requires javafx.fxml;
    requires javafx.controls;
    requires java.sql;

    opens controller;
    opens view;
    opens C195.AppointmentScheduleApp;
}