package com.pogtech.pogtech.SSR0TI;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

public class UsernameIsTakenTest {
    private static final String DB_URL = "jdbc:h2:mem:testdb";
    private static final String CREATE_TABLE_SQL = "CREATE TABLE USERS (" +
            "id INT PRIMARY KEY, " +
            "username VARCHAR(255), " +
            "password VARCHAR(255), " +
            "email VARCHAR(255), " +
            "name VARCHAR(255), " +
            "isAdmin INT)";
    private static final String INSERT_USER_SQL = "INSERT INTO USERS (id, username, password, email, name, isAdmin) VALUES (?, ?, ?, ?, ?, ?)";

    private Connection conn;

    @BeforeEach
    void setUp() throws SQLException {
        conn = DriverManager.getConnection(DB_URL);
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(CREATE_TABLE_SQL);
        }

        try (PreparedStatement stmt = conn.prepareStatement(INSERT_USER_SQL)) {
            stmt.setInt(1, 1);
            stmt.setString(2, "testuser");
            stmt.setString(3, "password123");
            stmt.setString(4, "testuser@example.com");
            stmt.setString(5, "Test User");
            stmt.setInt(6, 0);
            stmt.executeUpdate();
        }
    }

    @AfterEach
    void tearDown() throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("DROP TABLE USERS");
        }
        conn.close();
    }

    @Test
    void testIsUsernameTakenTrue() throws DatabaseException {
        boolean isTaken = isUsernameTaken("testuser");
        assertTrue(isTaken);
    }

    @Test
    void testIsUsernameTakenFalse() throws DatabaseException {
        boolean isTaken = isUsernameTaken("newuser");
        assertFalse(isTaken);
    }


    private boolean isUsernameTaken(String username) throws DatabaseException {
        String query = "SELECT COUNT(*) FROM users WHERE username = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                } else {
                    return false;
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Hiba az adatbáziskapcsolatban: " + e.getMessage(), e);
        }
    }

    static class DatabaseException extends Exception {
        public DatabaseException(String message, Throwable cause) {
            super(message, cause);
        }
    }

}
