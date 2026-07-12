package util;

import exception.DatabaseConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    private final static  String URL="jdbc:postgresql://localhost:5432/library";
    private final static  String USERNAME="postgres";
    private final static  String PASSWORD="ZqX.7391";

    public Connection getConnection() throws DatabaseConnectionException {
        try {
            Connection connection =
                    DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connected");
            return connection;
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Connection Failed!");

        }
    }

}
