package com.pogtech.pogtech.controllers;

import com.pogtech.pogtech.App;

import com.pogtech.pogtech.Dao.CarsDAO;
import com.pogtech.pogtech.Dao.UserDAO;
import com.pogtech.pogtech.MessageHandler;
import com.pogtech.pogtech.data.Cars;
import com.pogtech.pogtech.data.Users;
import com.pogtech.pogtech.database.DatabaseException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.util.Date;

public class AddCarController {

    private App app;
    public void setApp(App app) {
        this.app = app;
    }

    @FXML
    private TextField brandText;
    @FXML
    private TextField typeText;
    @FXML
    private TextField yearText;
    @FXML
    private TextField designText;
    @FXML
    private ChoiceBox extraChoice;
    @FXML
    private TextField arText;
    @FXML
    private TextField dateText;
    @FXML
    private Button saveB;



    public void initialize() {
        int id = generateUniqueId();
       String brand = brandText.getText();
       String type = typeText.getText();
       int year = Integer.parseInt(yearText.getText());
       String design = designText.getText();
       String extra = extraChoice.getValue().toString();
       int ar = Integer.parseInt(arText.getText());
       LocalDate date = LocalDate.parse(dateText.getText());

      Cars car = new Cars(id, brand, type, year, design, extra, ar, date);


        saveB.setOnAction(event -> {
            if (brand.isEmpty() || type.isEmpty() ||  design.isEmpty() || extra.isEmpty() || date.isEqual(null) ) {
                displayErrorDialog("Kérjük töltse ki az összes mezőt!");
                return;
            }

            try {
                CarsDAO.addCar(car);

                System.out.println("Jármű hozzáadva" + brand);
                app.loadAdmin();
            } catch (DatabaseException e) {
                displayErrorDialog("Hiba a hozzáadás közben " + e.getMessage());
            }
        });


    }

    private int generateUniqueId() {
        // Ez egy egyszerű példányosítás. Lehetőség van egy komplexebb logikára is.
        return (int) (Math.random() * 1000000);
    }

    private void displayErrorDialog(String message) {
        MessageHandler.showError(message);
    }

}
