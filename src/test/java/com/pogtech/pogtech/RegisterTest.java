package com.pogtech.pogtech;

import com.pogtech.pogtech.Dao.UserDAO;
import com.pogtech.pogtech.data.Users;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

public class RegisterTest {
    private static final String DB_URL = "jdbc:h2:mem:testdb";
    private static final String CREATE_TABLE_SQL = "CREATE TABLE USERS (" +
            "id INT PRIMARY KEY, " +
            "username VARCHAR(255), " +
            "password VARCHAR(255), " +
            "email VARCHAR(255), " +
            "name VARCHAR(255), " +
            "role INT)";
    private static final String INSERT_INTO_USERS = "INSERT INTO USERS (id, username, password, email, name, role) VALUES (?, ?, ?, ?, ?, ?)";

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
            stmt.execute("DROP TABLE USERS");
        }
        conn.close();
    }

    @Test
    void testRegisterUser() throws DatabaseException, SQLException {
        Users user = new Users(1, "testuser", "password123", "testuser@example.com", "Test User", 0);

        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            PreparedStatement stmt = conn.prepareStatement(INSERT_INTO_USERS);
            stmt.setInt(1, user.getId());
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getEmail());
            stmt.setString(5, user.getName());
            stmt.setInt(6, 0);
            stmt.executeUpdate();
        }

        // Verify user is inserted
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM USERS WHERE id = ?")) {
            stmt.setInt(1, user.getId());
            ResultSet rs = stmt.executeQuery();

            assertTrue(rs.next());
            assertEquals(user.getId(), rs.getInt("id"));
            assertEquals(user.getUsername(), rs.getString("username"));
            assertEquals(user.getPassword(), rs.getString("password"));
            assertEquals(user.getEmail(), rs.getString("email"));
            assertEquals(user.getName(), rs.getString("name"));
            assertEquals(0, rs.getInt("role"));
        }
    }

}


