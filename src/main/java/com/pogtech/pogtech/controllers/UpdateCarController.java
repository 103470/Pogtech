package com.pogtech.pogtech.controllers;

import com.pogtech.pogtech.App;
import com.pogtech.pogtech.Dao.CarsDAO;
import com.pogtech.pogtech.MessageHandler;
import com.pogtech.pogtech.data.Cars;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.time.LocalDate;

public class UpdateCarController {


    public static TextField brandText;
    public static TextField typeText;
    public static TextField designText;
    public static TextField extraText;
    public static Spinner arSpin;
    public static Spinner yearSpin;

    public static DatePicker rendezvousDate;

    @FXML
    private Button updateB;

    @FXML
    private Button cancelButton;
    static Cars selectedItem;



    public void setSelectedItem(Cars selectedItem) {
        this.selectedItem = selectedItem;
        initializeFields();
    }



    private void initializeFields() {
        if (selectedItem != null) {
            brandText.setText(selectedItem.getBrand());
            typeText.setText(selectedItem.getType());
            designText.setText(selectedItem.getDesign());
            extraText.setText(selectedItem.getExtra());
            arSpin.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000000000, selectedItem.getPrice()));
            yearSpin.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000000000, selectedItem.getYear()));
            rendezvousDate.setValue(selectedItem.getRendezvousDate());
        }
    }

    @FXML
    public void updateB() {
        if (selectedItem == null) {
            MessageHandler.showError("Nincs kiválasztott jármű!");
            return;
        }

        int id = selectedItem.getId();
        String brand = brandText.getText();
        String type = typeText.getText();
        int year = (int) yearSpin.getValue();
        String design = designText.getText();
        String extra = extraText.getText();
        int price = (int) arSpin.getValue();
        LocalDate date = rendezvousDate.getValue();

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
                MessageHandler.showError("Adatbázis hiba: " + e.getMessage());
            }
        }
    }

    @FXML
    public void cancel() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }


}
