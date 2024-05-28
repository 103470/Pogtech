package com.pogtech.pogtech.Dao;

import com.pogtech.pogtech.data.Users;
import com.pogtech.pogtech.database.DatabaseException;

import java.sql.*;

public class UserDAO {
    private static final String DB_URL = "jdbc:mysql:db.dealership";

    public static boolean isUsernameTaken(String username) throws DatabaseException {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String query = "SELECT COUNT(*) FROM users WHERE username = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            return rs.getInt(1) > 0;
        } catch (SQLException e) {
            throw new DatabaseException("Hiba az adatbáziskapcsolatban: " + e.getMessage());
        }
    }

    public static void registerUser(Users user) throws DatabaseException {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String query = "INSERT INTO users (name, username, email, password, role_id) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPassword());
            stmt.setInt(5, 2); // Például a role_id: 2 - felhasználó szerepkör
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Hiba a felhasználó regisztrálásakor: " + e.getMessage());
        }
    }
}
