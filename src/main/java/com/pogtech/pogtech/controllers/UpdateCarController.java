package com.pogtech.pogtech.controllers;

import com.pogtech.pogtech.App;
import com.pogtech.pogtech.Dao.CarsDAO;
import com.pogtech.pogtech.MessageHandler;
import com.pogtech.pogtech.data.Cars;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.sql.SQLException;
import java.time.LocalDate;

public class UpdateCarController {

    AdminMainController ac;
    @FXML
    public void updateB() {
        Cars selectedCar = ac.tableMain.getSelectionModel().getSelectedItem();
        if (selectedCar == null) {
            MessageHandler.showError("Az autó frissítése sikerült.");
            return;
        }


        int id = selectedCar.getId();
        String brand = selectedCar.getBrand();
        String type = selectedCar.getType();
        int year = selectedCar.getYear();
        String design = selectedCar.getDesign();
        String extra = selectedCar.getExtra();
        int price = selectedCar.getPrice();
        LocalDate date = selectedCar.getRendezvousDate();

        if (brand.isEmpty() || type.isEmpty() || design.isEmpty() || extra.isEmpty()) {
            MessageHandler.showError("Kérjük töltse ki az összes mezőt!");
            return;
        } else {
            CarsDAO carsDAO = new CarsDAO();
            try {
                carsDAO.updateCar(id, brand, type, year, design, extra, price, date);
                MessageHandler.showError("A jármű sikeresen frissítve lett");
            } catch (SQLException e) {
                e.printStackTrace();
                MessageHandler.showError("Adatbázis hiba" + e.getMessage());
            }
        }




    }


}
