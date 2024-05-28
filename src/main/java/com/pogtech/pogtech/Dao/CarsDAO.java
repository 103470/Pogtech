package com.pogtech.pogtech.Dao;


import com.pogtech.pogtech.data.Cars;
import com.pogtech.pogtech.data.Users;
import com.pogtech.pogtech.database.DatabaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarsDAO {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/dealership";
    private static final String SELECT_ALL_CARS_QUERY = "SELECT id, brand, type, year, design, extra, price, rendezvous_date FROM cars";

    private static final String INSERT_INTO_CARS = "INSERT INTO cars (id, brand, type, year, design, extra, price, rendezvous_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

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

    public static void addCar(Cars car) throws DatabaseException {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            PreparedStatement stmt = conn.prepareStatement(INSERT_INTO_CARS);
            stmt.setInt(1,car.getId());
            stmt.setString(2, car.getBrand());
            stmt.setString(3, car.getType());
            stmt.setInt(4, car.getYear());
            stmt.setString(5, car.getDesign());
            stmt.setString(6, car.getExtra());
            stmt.setInt(7, car.getPrice());
            stmt.setDate(8, Date.valueOf(car.getRendezvousDate()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Hiba a felhasználó regisztrálásakor: " + e.getMessage());
        }
    }
}
