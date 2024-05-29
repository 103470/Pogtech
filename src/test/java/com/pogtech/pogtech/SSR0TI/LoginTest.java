package com.pogtech.pogtech.SSR0TI;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {
    private static final String DB_URL = "jdbc:h2:mem:testdb";
    private static final String CREATE_TABLE_SQL = "CREATE TABLE USERS (" +
            "id INT PRIMARY KEY, " +
            "username VARCHAR(255), " +
            "password VARCHAR(255), " +
            "email VARCHAR(255), " +
            "name VARCHAR(255), " +
            "isAdmin INT)";
    private static final String INSERT_USER_SQL = "INSERT INTO USERS (id, username, password, email, name, isAdmin) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_FROM_USERS = "SELECT * FROM USERS WHERE username = ? AND password = ?";

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
    void testLoginUserSuccess() throws DatabaseException {
        CurrentUser user = loginUser("testuser", "password123");

        assertNotNull(user);
        assertEquals("testuser", user.getUsername());
        assertEquals("testuser@example.com", user.getEmail());
        assertEquals("Test User", user.getName());
        assertEquals(0, user.getIsAdmin());
    }

    @Test
    void testLoginUserFailure() throws DatabaseException {
        CurrentUser user = loginUser("invaliduser", "wrongpassword");

        assertNull(user);
    }


    private CurrentUser loginUser(String usernameText, String passwordText) throws DatabaseException {
        try (Connection conn = DriverManager.getConnection(DB_URL)){
            PreparedStatement stmt = conn.prepareStatement(SELECT_FROM_USERS);
            stmt.setString(1, usernameText);
            stmt.setString(2, passwordText);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String username = rs.getString("username");
                    String email = rs.getString("email");
                    String name = rs.getString("name");
                    int isAdmin = rs.getInt("isAdmin");
                    return new CurrentUser(username, email, name, isAdmin);
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Database error: " + e.getMessage());
        }
    }

    static class CurrentUser {
        private String username;
        private String email;
        private String name;
        private int isAdmin;

        public CurrentUser(String username, String email, String name, int isAdmin) {
            this.username = username;
            this.email = email;
            this.name = name;
            this.isAdmin = isAdmin;
        }

        public String getUsername() {
            return username;
        }

        public String getEmail() {
            return email;
        }

        public String getName() {
            return name;
        }

        public int getIsAdmin() {
            return isAdmin;
        }
    }

    static class DatabaseException extends Exception {
        public DatabaseException(String message) {
            super(message);
        }
    }
}

