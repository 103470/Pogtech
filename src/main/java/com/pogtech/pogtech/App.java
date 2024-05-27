package com.pogtech.pogtech;

import com.pogtech.pogtech.controllers.AdminMainController;
import com.pogtech.pogtech.controllers.LoginController;
import com.pogtech.pogtech.controllers.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        MainController mainController = fxmlLoader.getController();
        mainController.setApp(this);
        mainController.initalize();

        Stage stage = new Stage();
        stage.setTitle("Autókereskedés");
        stage.setScene(scene);
        stage.show();

    }

    public void loadLogin()  {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/login.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            LoginController loginController = fxmlLoader.getController();
            loginController.setApp(this);
            Stage stage = new Stage();
            stage.setTitle("Bejelentkezés");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void loadRegister()  {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/register.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Regisztráció");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void loadAdmin(){

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/adminMain.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            AdminMainController adminMainController = fxmlLoader.getController();
            adminMainController.setApp(this);
            adminMainController.initalize();

            Stage stage = new Stage();
            stage.setTitle("Admin panel");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }



    public static void main(String[] args) {
        launch();
    }
}
