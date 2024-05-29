package com.pogtech.pogtech.controllers;

import com.pogtech.pogtech.Dao.UserDAO;
import com.pogtech.pogtech.MessageHandler;
import com.pogtech.pogtech.data.Users;
import com.pogtech.pogtech.DatabaseException;
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
            int id = generateUniqueId();
            String name = nameText.getText();
            String username = usernameText.getText();
            String email = emailText.getText();
            String password = passwordText.getText();
            String passwordAgain = cpasswordText.getText();
            int isAdmin = 0;

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
                displayErrorDialog("A jelszavak nem egyeznek");
                return;
            }

            try {
                if (UserDAO.isUsernameTaken(username)) {
                    displayErrorDialog("A felhasználónév foglalt");
                    return;
                }

                Users newUser = new Users(id, name, username, email, password, isAdmin);
                UserDAO.registerUser(newUser);

                System.out.println("Felhasználó regisztrálva: " + username);
                AppController.loadLogin();
            } catch (DatabaseException e) {
                displayErrorDialog("Hiba a regisztráció közben: " + e.getMessage());
            }
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
