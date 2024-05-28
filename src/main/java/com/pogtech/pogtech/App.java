package com.pogtech.pogtech;

import com.pogtech.pogtech.controllers.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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

    public void loadUser()  {

        try {

            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/userMain.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            MainController mainController = fxmlLoader.getController();
            mainController.setApp(this);
            mainController.initalize();

            Stage stage = new Stage();
            stage.setTitle("Autókereskedés");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public void loadAddCar(){

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/addCar.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            AddCarController addCarController = fxmlLoader.getController();
            addCarController.setApp(this);
            //addCarController.initialize();

            Stage stage = new Stage();
            stage.setTitle("Jármű hozzáadása");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void loadUpdateCar(){

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/updateCar.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            UpdateCarController updateCarController = fxmlLoader.getController();
            updateCarController.setApp(this);
            updateCarController.updateB();

            Stage stage = new Stage();
            stage.setTitle("Jármű hozzáadása");
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
