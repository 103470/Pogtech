package com.pogtech.pogtech.Dao;

import com.pogtech.pogtech.data.Users;
import com.pogtech.pogtech.database.DatabaseException;

import java.sql.*;



public class UserDAO {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/dealership";
    private static final String INSERT_INTO_USERS = "INSERT INTO users (id, username, password, email, name, isAdmin) VALUES (?, ?, ?, ?, ?, ?)";

    public static boolean isUsernameTaken(String username) throws DatabaseException {
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

    public static void registerUser(Users user) throws DatabaseException {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String query = INSERT_INTO_USERS;
            PreparedStatement stmt = conn.prepareStatement(INSERT_INTO_USERS);
            stmt.setInt(1,user.getId());
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getEmail());
            stmt.setString(5, user.getName());
            stmt.setInt(6, 0);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Hiba a felhasználó regisztrálásakor: " + e.getMessage());
        }
    }
}
