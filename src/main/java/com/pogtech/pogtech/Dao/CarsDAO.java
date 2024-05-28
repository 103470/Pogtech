package com.pogtech.pogtech.Dao;


import com.pogtech.pogtech.data.Cars;
import com.pogtech.pogtech.database.DatabaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarsDAO {

    private static final String SELECT_ALL_CARS_QUERY = "SELECT id, brand, type, year, design, extra, price, rendezvous_date FROM cars";

    public List<Cars> getAllCars() throws DatabaseException {
        List<Cars> cars = new ArrayList<>();
        String username = "root";
        String password = "";
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dealership");
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CARS_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String brand = resultSet.getString("brand");
                String type = resultSet.getString("type");
                int year = resultSet.getInt("year");
                String design = resultSet.getString("design");
                String extra = resultSet.getString("extra");
                int price = resultSet.getInt("price");
                Date rendezvousDate = resultSet.getDate("rendezvous_date");



                cars.add(new Cars(id, brand, type, year, design, extra, price, rendezvousDate.toLocalDate()));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Hiba az adatbázisműveletek közben: " + e.getMessage(), e);
        }
        return cars;
    }
}
