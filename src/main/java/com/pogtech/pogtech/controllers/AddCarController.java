package com.pogtech.pogtech.controllers;

import com.pogtech.pogtech.MessageHandler;
import com.pogtech.pogtech.command.AddCarCommand;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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
