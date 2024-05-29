package com.pogtech.pogtech;


import com.pogtech.pogtech.database.DatabaseConnector;
import org.junit.jupiter.api.Test;


import java.sql.Connection;
import java.sql.SQLException;


import static org.junit.jupiter.api.Assertions.*;


class ConnectionTest {

    @Test
    public void testConnect_Success() throws SQLException {
        Connection connection = DatabaseConnector.connect();
        assertNotNull(connection, "Connection should not be null");
        connection.close();
    }






}