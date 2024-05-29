package com.pogtech.pogtech.command;

import com.pogtech.pogtech.Dao.CarsDAO;
import com.pogtech.pogtech.MessageHandler;
import com.pogtech.pogtech.controllers.AppController;
import com.pogtech.pogtech.data.Cars;
import com.pogtech.pogtech.database.DatabaseException;

import java.time.LocalDate;

public class AddCarCommand implements Command{

    private final String brand;
    private final String type;
    private final int year;
    private final String design;
    private final String extra;
    private final int ar;
    private final LocalDate date;

    public AddCarCommand(String brand, String type, int year, String design, String extra, int ar, LocalDate date) {
        this.brand = brand;
        this.type = type;
        this.year = year;
        this.design = design;
        this.extra = extra;
        this.ar = ar;
        this.date = date;
    }

    @Override
    public void execute() {
        Cars car = new Cars(generateUniqueId(), brand, type, year, design, extra, ar, date);
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

    }

    private int generateUniqueId() {

        return (int) (Math.random() * 1000000);
    }

    private void displayErrorDialog(String message) {
        MessageHandler.showError(message);
    }
}
