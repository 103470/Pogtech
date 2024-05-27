package com.pogtech.pogtech.data;

import com.pogtech.pogtech.Cars.Cars;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;

public class CarDataProvider {

    public ObservableList<Cars> getCarData() {
        return FXCollections.observableArrayList(
                new Cars(1, "Toyota", "Sedan", 2020, "Modern", "Sunroof", 25000, LocalDate.of(2021, 5, 15)),
                new Cars(2, "Honda", "SUV", 2018, "Classic", "Leather seats", 30000, LocalDate.of(2020, 6, 20)),
                new Cars(3, "Ford", "Truck", 2022, "Sport", "Off-road package", 35000, LocalDate.of(2022, 3, 10))
        );
    }
}

