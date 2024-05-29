package com.pogtech.pogtech.C3DFEJ;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class DeleteCarTest {
    private static final String DB_URL = "jdbc:h2:mem:testdb";
    private static final String CREATE_TABLE_SQL = "CREATE TABLE CARS (" +
            "id INT PRIMARY KEY, " +
            "brand VARCHAR(255), " +
            "type VARCHAR(255), " +
            "year INT, " +
            "design VARCHAR(255), " +
            "extra VARCHAR(255), " +
            "price INT, " +
            "rendezvousDate DATE)";
    private static final String INSERT_INTO_CARS = "INSERT INTO CARS (id, brand, type, year, design, extra, price, rendezvousDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String DELETE_CAR = "DELETE FROM CARS WHERE id = ?";

    private Connection conn;

    @BeforeEach
    void setUp() throws SQLException {
        conn = DriverManager.getConnection(DB_URL);
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(CREATE_TABLE_SQL);
        }
    }

    @AfterEach
    void tearDown() throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("DROP TABLE CARS");
        }
        conn.close();
    }

    @Test
    void testDeleteCar() throws SQLException {
        // Insert a car into the database
        try (PreparedStatement stmt = conn.prepareStatement(INSERT_INTO_CARS)) {
            stmt.setInt(1, 1);
            stmt.setString(2, "Toyota");
            stmt.setString(3, "Sedan");
            stmt.setInt(4, 2020);
            stmt.setString(5, "Red");
            stmt.setString(6, "Leather seats");
            stmt.setInt(7, 25000);
            stmt.setDate(8, Date.valueOf("2023-05-29"));
            stmt.executeUpdate();
        }

        // Delete the car
        deleteCar(1);

        // Verify that the car is deleted from the database
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM CARS WHERE id = ?")) {
            stmt.setInt(1, 1);
            try (ResultSet rs = stmt.executeQuery()) {
                assertFalse(rs.next()); // No rows should be returned, as the car is deleted
            }
        }
    }

    private void deleteCar(int id) throws SQLException {
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CAR)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }

}
