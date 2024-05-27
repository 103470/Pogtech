package com.pogtech.pogtech.controllers;

import com.pogtech.pogtech.App;
import javafx.fxml.FXML;
import javafx.scene.control.Button;



public class MainController {

   private App app;
    public void setApp(App app) {
        this.app = app;

    }

    @FXML
    private void loginB() {
        app.loadLogin();
    }

    @FXML
    private void registerB() {
        app.loadRegister();
    }
}
