package com.pogtech.pogtech.controllers;

import com.pogtech.pogtech.App;

import com.pogtech.pogtech.Dao.CarsDAO;
import com.pogtech.pogtech.Dao.UserDAO;
import com.pogtech.pogtech.MessageHandler;
import com.pogtech.pogtech.command.AddCarCommand;
import com.pogtech.pogtech.data.Cars;
import com.pogtech.pogtech.data.Users;
import com.pogtech.pogtech.database.DatabaseException;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

public class AddCarController {


    @FXML
    private TextField brandText;
    @FXML
    private TextField typeText;
    @FXML
    private Spinner yearText;
    @FXML
    private TextField designText;
    @FXML
    private TextField extraText;
    @FXML
    private Spinner arText;
    @FXML
    private DatePicker dateText;
    @FXML
    private Button saveB;



    public void initialize() {
        saveB.setOnAction(event -> {
            AddCarCommand command = new AddCarCommand(
            brandText.getText(),
            typeText.getText(),
            (int)yearText.getValue(),
            designText.getText(),
            extraText.getText(),
            (int)arText.getValue(),
            dateText.getValue()
            );

            command.execute();
            System.out.println("Jármű hozzáadva: " + brandText.getText());
            AppController.loadAdmin();


        });

    }

    private void displayErrorDialog(String message) {
        MessageHandler.showError(message);
    }


}
