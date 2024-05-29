package com.pogtech.pogtech.C3DFEJ;

import com.pogtech.pogtech.Dao.CarsDAO;
import com.pogtech.pogtech.data.Cars;
import com.pogtech.pogtech.database.DatabaseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ListCarsTest {
    private static final String SELECT_ALL_CARS_QUERY = "SELECT * FROM CARS";

    @Test
    void testGetAllCars() throws SQLException, DatabaseException {
        // Mocking the database connection and result set
        Connection connectionMock = mock(Connection.class);
        PreparedStatement preparedStatementMock = mock(PreparedStatement.class);
        ResultSet resultSetMock = mock(ResultSet.class);

        // Mocking the DriverManager.getConnection() method to return the mocked connection
        when(DriverManager.getConnection(anyString())).thenReturn(connectionMock);

        // Mocking the PreparedStatement and ResultSet
        when(connectionMock.prepareStatement(SELECT_ALL_CARS_QUERY)).thenReturn(preparedStatementMock);
        when(preparedStatementMock.executeQuery()).thenReturn(resultSetMock);

        // Creating a list of test cars
        List<Cars> expectedCars = new ArrayList<>();
        expectedCars.add(new Cars(1, "Toyota", "Sedan", 2020, "Red", "Leather seats", 25000, LocalDate.of(2023, 5, 29)));
        expectedCars.add(new Cars(2, "Honda", "SUV", 2021, "Blue", "Sunroof", 30000, LocalDate.of(2023, 6, 30)));

        // Mocking the ResultSet data
        when(resultSetMock.next()).thenReturn(true, true, false);
        when(resultSetMock.getInt("id")).thenReturn(1, 2);
        when(resultSetMock.getString("brand")).thenReturn("Toyota", "Honda");
        when(resultSetMock.getString("type")).thenReturn("Sedan", "SUV");
        when(resultSetMock.getInt("year")).thenReturn(2020, 2021);
        when(resultSetMock.getString("design")).thenReturn("Red", "Blue");
        when(resultSetMock.getString("extra")).thenReturn("Leather seats", "Sunroof");
        when(resultSetMock.getInt("price")).thenReturn(25000, 30000);
        when(resultSetMock.getDate("rendezvous_date")).thenReturn(Date.valueOf("2023-05-29"), Date.valueOf("2023-06-30"));

        // Testing the method
        CarsDAO carsDAO = new CarsDAO();
        List<Cars> actualCars = carsDAO.getAllCars();

        // Verifying the results
        assertEquals(expectedCars.size(), actualCars.size());
        for (int i = 0; i < expectedCars.size(); i++) {
            assertEquals(expectedCars.get(i), actualCars.get(i));
        }
    }
}