package com.pogtech.pogtech.command;

import com.pogtech.pogtech.Dao.UserDAO;
import com.pogtech.pogtech.MessageHandler;
import com.pogtech.pogtech.data.Users;
import com.pogtech.pogtech.database.DatabaseException;

public class RegisterCommand implements Command{
    private final String name;
    private final String username;
    private final String email;
    private final String password;
    private final String cpassword;

    public RegisterCommand(String name, String username, String email, String password, String cpassword) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.cpassword = cpassword;
    }
    @Override
    public void execute() {

        if (name.isEmpty() || username.isEmpty() || email.isEmpty() || password.isEmpty() || cpassword.isEmpty()) {
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

        if (!password.equals(cpassword)) {
            displayErrorDialog("A jelszavak nem egyeznek");
            return;
        }

        try {
            if (UserDAO.isUsernameTaken(username)) {
                displayErrorDialog("A felhasználónév foglalt");
                return;
            }
        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        }

        Users newUser = new Users(generateUniqueId(), name, username, email, password, 0);
        try {
            UserDAO.registerUser(newUser);
        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        }


    }
    private void displayErrorDialog(String message) {
        MessageHandler.showError(message);
    }
    private int generateUniqueId() {
        // Ez egy egyszerű példányosítás. Lehetőség van egy komplexebb logikára is.
        return (int) (Math.random() * 1000000);
    }

    private boolean isValidEmail(String email) {
        String regex = "^[a-zA-Z0-9_]+@[a-zA-Z0-9]+\\.[a-zA-Z]{2,3}$";
        return email.matches(regex);
    }
}
