package com.pogtech.pogtech.controllers;

import com.pogtech.pogtech.MessageHandler;
import com.pogtech.pogtech.data.Cars;
import com.pogtech.pogtech.Dao.CarsDAO;
import com.pogtech.pogtech.database.DatabaseException;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

public class AdminMainController {

    @FXML
    private void addCar() {
        AppController.loadAddCar();
    }

    @FXML
    private void registerB() {
        AppController.loadRegister();
    }

    private static final Logger logger = Logger.getLogger(MainController.class.getName());

    private CarsDAO carsDAO = new CarsDAO();
    private Button updateCar;
    private Cars selectedItem;

    @FXML
    protected TableView<Cars> tableMain;
    @FXML
    private TableColumn<Cars, Integer> id;
    @FXML
    private TableColumn<Cars, String> brand;
    @FXML
    private TableColumn<Cars, String> type;
    @FXML
    private TableColumn<Cars, Integer> year;
    @FXML
    private TableColumn<Cars, String> design;
    @FXML
    private TableColumn<Cars, String> extra;
    @FXML
    private TableColumn<Cars, Integer> price;
    @FXML
    private TableColumn<Cars, LocalDate> rendezvousDate;

    @FXML
    public void initalize(){
        try{
            List<Cars> cars = carsDAO.getAllCars();

            ObservableList<Cars> observableCars = FXCollections.observableArrayList(cars);
            id.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
            brand.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBrand()));
            type.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType()));
            year.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getYear()).asObject());
            design.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDesign()));
            extra.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getExtra()));
            price.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getPrice()).asObject());
            rendezvousDate.setCellValueFactory(cellData -> {
                LocalDate date = cellData.getValue().getRendezvousDate();
                return new SimpleObjectProperty<>(date);
            });

            tableMain.setItems((observableCars));
            /*tableMain.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                if (newSelection != null) {
                    updateCar.setDisable(false);
                } else {
                    updateCar.setDisable(true);
                }
            });

            updateCar.setOnAction(event -> {
                selectedItem = tableMain.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    updateCar();
                } else {
                    MessageHandler.showError("Please select an item to update.");
                }
            });
             */


        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        }

    }
    @FXML
    private void deleteCar() {
        Cars selectedCar = tableMain.getSelectionModel().getSelectedItem();
        if (selectedCar != null) {
            CarsDAO carDAO = new CarsDAO();
            try {
                carDAO.deleteCar(selectedCar.getId());
                MessageHandler.showError("Az autó törlésre került.");
                initalize();
            } catch (SQLException e) {
                e.printStackTrace();
                MessageHandler.showError("Hiba történt az autó törlésekor.");
            }
        } else {
            MessageHandler.showError("Kérjük, válasszon ki egy autót a törléshez.");
        }
    }

    @FXML
    private void updateCar(){
        AppController.loadUpdateCar();
    }

}
