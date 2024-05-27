module com.pogtech.pogtech {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;


    opens com.pogtech.pogtech to javafx.fxml;
    exports com.pogtech.pogtech;
    exports com.pogtech.pogtech.controllers;
    opens com.pogtech.pogtech.controllers to javafx.fxml;

}