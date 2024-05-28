package com.pogtech.pogtech.controllers;

import com.pogtech.pogtech.App;

import com.pogtech.pogtech.Dao.CarsDAO;
import com.pogtech.pogtech.Dao.UserDAO;
import com.pogtech.pogtech.MessageHandler;
import com.pogtech.pogtech.data.Cars;
import com.pogtech.pogtech.data.Users;
import com.pogtech.pogtech.database.DatabaseException;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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
            int id = generateUniqueId();
            String brand = brandText.getText();
            String type = typeText.getText();
            int year = (int) yearText.getValue();
            String design = designText.getText();
            String extra = extraText.getText();
            int ar = (int) arText.getValue();
            LocalDate date = dateText.getValue();

            Cars car = new Cars(id, brand, type, year, design, extra, ar, date);
            if (brand.isEmpty() || type.isEmpty() || design.isEmpty() || extra.isEmpty() || date == null) {
                displayErrorDialog("Kérjük töltse ki az összes mezőt!");
                return;
            }

            try {
                CarsDAO.addCar(car);

                System.out.println("Jármű hozzáadva" + brand);
                AppController.loadAdmin();
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
