package com.pogtech.pogtech.controllers;

import com.pogtech.pogtech.App;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    private App app;
    public void setApp(App app) {
        this.app = app;

    }

    public TextField usernameText;
    public PasswordField passwordText;
}
