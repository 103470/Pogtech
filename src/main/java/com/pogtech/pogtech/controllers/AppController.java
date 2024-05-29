package com.pogtech.pogtech.controllers;

import com.pogtech.pogtech.App;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AppController extends App {
    public static void loadLogin()  {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/login.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
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

    public static void loadAdmin(){

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/adminMain.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            AdminMainController adminMainController = fxmlLoader.getController();
            adminMainController.initalize();

            Stage stage = new Stage();
            stage.setTitle("Admin panel");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void loadUser()  {

        try {

            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/userMain.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            UserMainController userMainControllermain = fxmlLoader.getController();
            userMainControllermain.initialize();

            Stage stage = new Stage();
            stage.setTitle("Autókereskedés");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public static void loadAddCar(){

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/addCar.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            AddCarController addCarController = fxmlLoader.getController();
            addCarController.initialize();

            Stage stage = new Stage();
            stage.setTitle("Jármű hozzáadása");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void loadUpdateCar(){

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/updateCar.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            UpdateCarController updateCarController = fxmlLoader.getController();
            updateCarController.updateB();

            Stage stage = new Stage();
            stage.setTitle("Jármű hozzáadása");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
