package com.pogtech.pogtech;

import com.pogtech.pogtech.controllers.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class App extends Application {
    private static final Logger log = LogManager.getLogger(App.class);
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
        log.info("A Main ablak betöltött");
    }


    public static void main(String[] args) {
        launch();
    }
}
