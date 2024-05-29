package com.pogtech.pogtech.C3DFEJ;

import com.pogtech.pogtech.data.Cars;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddCarTest {
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
    void testAddCar() throws DatabaseException, SQLException {
        Cars car = new Cars(1, "Toyota", "Sedan", 2020, "Red", "Leather seats", 25000, LocalDate.of(2023, 5, 29));
        addCar(car);

        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM CARS WHERE id = ?")) {
            stmt.setInt(1, 1);
            try (ResultSet rs = stmt.executeQuery()) {
                assertTrue(rs.next());
                assertEquals("Toyota", rs.getString("brand"));
                assertEquals("Sedan", rs.getString("type"));
                assertEquals(2020, rs.getInt("year"));
                assertEquals("Red", rs.getString("design"));
                assertEquals("Leather seats", rs.getString("extra"));
                assertEquals(25000, rs.getInt("price"));
                assertEquals(Date.valueOf(LocalDate.of(2023, 5, 29)), rs.getDate("rendezvousDate"));
            }
        }
    }

    private void addCar(Cars car) throws DatabaseException {
        String INSERT_INTO_CARS = "INSERT INTO CARS (id, brand, type, year, design, extra, price, rendezvousDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            PreparedStatement stmt = conn.prepareStatement(INSERT_INTO_CARS);
            stmt.setInt(1, car.getId());
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

    static class DatabaseException extends Exception {
        public DatabaseException(String message) {
            super(message);
        }
    }

}
