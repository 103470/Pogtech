package com.pogtech.pogtech.controllers;

import com.pogtech.pogtech.App;
import com.pogtech.pogtech.Dao.UserDAO;
import com.pogtech.pogtech.MessageHandler;
import com.pogtech.pogtech.command.RegisterCommand;
import com.pogtech.pogtech.data.Users;
import com.pogtech.pogtech.database.DatabaseException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;

public class RegisterController {


    @FXML
    private TextField nameText;
    @FXML
    private TextField usernameText;
    @FXML
    private TextField emailText;
    @FXML
    private TextField passwordText;
    @FXML
    private TextField cpasswordText;
    @FXML
    private Button registerButton;
    @FXML
    private Hyperlink goToLogin;

    public void initialize() {
        registerButton.setOnAction(event -> {
            RegisterCommand command = new RegisterCommand(
            nameText.getText(),
            usernameText.getText(),
            emailText.getText(),
            passwordText.getText(),
            cpasswordText.getText()
            );


            command.execute();

            System.out.println("Felhasználó regisztrálva: " + nameText.getText());
            AppController.loadLogin();
        });

        goToLogin.setOnAction(event -> {
            AppController.loadLogin();
        });
    }

    private int generateUniqueId() {
        // Ez egy egyszerű példányosítás. Lehetőség van egy komplexebb logikára is.
        return (int) (Math.random() * 1000000);
    }

    private boolean isValidEmail(String email) {
        String regex = "^[a-zA-Z0-9_]+@[a-zA-Z0-9]+\\.[a-zA-Z]{2,3}$";
        return email.matches(regex);
    }

    private void displayErrorDialog(String message) {
        MessageHandler.showError(message);
    }



}
