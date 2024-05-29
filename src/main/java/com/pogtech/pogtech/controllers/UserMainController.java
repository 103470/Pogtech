package com.pogtech.pogtech.controllers;

import com.pogtech.pogtech.App;
import com.pogtech.pogtech.Dao.CarsDAO;
import com.pogtech.pogtech.data.Cars;
import com.pogtech.pogtech.DatabaseException;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

public class UserMainController {

    private App app;
    public void setApp(App app) {
        this.app = app;
    }

    private static final Logger logger = Logger.getLogger(MainController.class.getName());

    public CarsDAO carsDAO = new CarsDAO();

    @FXML
    public TableView<Cars> tableMain;
    @FXML
    public TableColumn<Cars, Integer> id;
    @FXML
    public TableColumn<Cars, String> brand;
    @FXML
    public TableColumn<Cars, String> type;
    @FXML
    public TableColumn<Cars, Integer> year;
    @FXML
    public TableColumn<Cars, String> design;
    @FXML
    public TableColumn<Cars, String> extra;
    @FXML
    public TableColumn<Cars, Integer> price;
    @FXML
    public TableColumn<Cars, LocalDate> rendezvousDate;
    @FXML
    public void initialize(){
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

        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        }

    }

    public void timeB(){
        //rendezvousDate = tableMain.getSelectionModel().getSelectedItem();

    }
}
