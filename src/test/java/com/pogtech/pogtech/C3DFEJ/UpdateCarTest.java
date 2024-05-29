package com.pogtech.pogtech.C3DFEJ;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class UpdateCarTest {
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
    private static final String UPDATE_CARS = "UPDATE CARS SET brand=?, type=?, year=?, design=?, extra=?, price=?, rendezvousDate=? WHERE id=?";

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
    void testUpdateCar() throws SQLException {
        // Insert initial car
        try (PreparedStatement stmt = conn.prepareStatement(INSERT_INTO_CARS)) {
            stmt.setInt(1, 1);
            stmt.setString(2, "Toyota");
            stmt.setString(3, "Sedan");
            stmt.setInt(4, 2020);
            stmt.setString(5, "Red");
            stmt.setString(6, "Leather seats");
            stmt.setInt(7, 25000);
            stmt.setDate(8, Date.valueOf(LocalDate.of(2023, 5, 29)));
            stmt.executeUpdate();
        }

        // Update car details
        updateCar(1, "Honda", "SUV", 2021, "Blue", "Sunroof", 30000, LocalDate.of(2023, 6, 30));

        // Verify the car details are updated
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM CARS WHERE id = ?")) {
            stmt.setInt(1, 1);
            try (ResultSet rs = stmt.executeQuery()) {
                assertTrue(rs.next());
                assertEquals("Honda", rs.getString("brand"));
                assertEquals("SUV", rs.getString("type"));
                assertEquals(2021, rs.getInt("year"));
                assertEquals("Blue", rs.getString("design"));
                assertEquals("Sunroof", rs.getString("extra"));
                assertEquals(30000, rs.getInt("price"));
                assertEquals(Date.valueOf(LocalDate.of(2023, 6, 30)), rs.getDate("rendezvousDate"));
            }
        }
    }


    private void updateCar(int id, String brand, String type, int year, String design, String extra, int price, LocalDate date) throws SQLException {
        String UPDATE_CARS = "UPDATE CARS SET brand=?, type=?, year=?, design=?, extra=?, price=?, rendezvousDate=? WHERE id=?";
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CARS)) {
            preparedStatement.setString(1, brand);
            preparedStatement.setString(2, type);
            preparedStatement.setInt(3, year);
            preparedStatement.setString(4, design);
            preparedStatement.setString(5, extra);
            preparedStatement.setInt(6, price);
            preparedStatement.setDate(7, Date.valueOf(date));
            preparedStatement.setInt(8, id);
            preparedStatement.executeUpdate();
        }
    }

}
