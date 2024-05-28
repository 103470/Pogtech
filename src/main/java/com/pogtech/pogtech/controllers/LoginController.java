package com.pogtech.pogtech.controllers;

import com.pogtech.pogtech.App;
import com.pogtech.pogtech.Dao.UserDAO;
import com.pogtech.pogtech.MessageHandler;
import com.pogtech.pogtech.database.DatabaseConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    private App app;
    public void setApp(App app) {
        this.app = app;

    }
    @FXML
    private TextField usernameText;
    @FXML
    private PasswordField passwordText;
    @FXML
    private Button loginButton;
    @FXML
    private Hyperlink goToRegister;
    public void initialize(){
        loginButton.setOnAction(event -> {
            String username = usernameText.getText();
            String password = passwordText.getText();

            try {
                UserDAO.loginUser(username, password);
                //app.loadUserMain();
            }catch(Exception e){
                MessageHandler.showError("ezsejó");
            }
        });



    }


    public void goToRegister(ActionEvent actionEvent) {
        goToRegister.setOnAction(event ->{
            app.loadRegister();
        });
    }
}
