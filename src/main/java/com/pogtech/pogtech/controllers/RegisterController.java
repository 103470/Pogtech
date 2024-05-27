package com.pogtech.pogtech.controllers;

import com.pogtech.pogtech.App;
import com.pogtech.pogtech.MessageHandler;
import com.pogtech.pogtech.database.DatabaseException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;

public class RegisterController {



    private App app;
    public void setApp(App app) {
        this.app = app;

    }

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
            String name = nameText.getText();
            String username = usernameText.getText();
            String email = emailText.getText();
            String password = passwordText.getText();
            String passwordAgain = cpasswordText.getText();

            if (name.isEmpty() || username.isEmpty() || email.isEmpty() || password.isEmpty() || passwordAgain.isEmpty()) {
                displayErrorDialog("Kérjük töltse ki az összes mezőt!");
                return;
            }

            if (!isValidEmail(email)) {
                displayErrorDialog("Érvénytelen email cím.");
                return;
            }

            if (password.length() < 5) {
                displayErrorDialog("A jelszó túl rövid!");
                return;
            }

            if (!password.equals(passwordAgain)) {
                displayErrorDialog("A két jelszó nem egyezik!");
                return;
            }

            try {
                if (userDAO.isUsernameTaken(username)) {
                    displayErrorDialog("Ezzel a felhasználónévvel már regisztráltak.");
                    return;
                }

                User newUser = new User(name, username, email, password);
                userDAO.registerUser(newUser);

                System.out.println("Felhasználó regisztrálva: " + username);
                app.loadLogin();
            } catch (DatabaseException e) {
                displayErrorDialog("Hiba a regisztráció közben: " + e.getMessage());
            }
        });

        goToLogin.setOnAction(event -> {
            App.loadLogin();
        });
    }

    private boolean isValidEmail(String email) {
        String regex = "^[a-zA-Z0-9_]+@[a-zA-Z0-9]+\\.[a-zA-Z]{2,3}$";
        return email.matches(regex);
    }

    private void displayErrorDialog(String message) {
        MessageHandler.showError(message);
    }



}
