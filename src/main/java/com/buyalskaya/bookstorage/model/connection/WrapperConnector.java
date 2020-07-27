package com.buyalskaya.bookstorage.model.connection;

import com.buyalskaya.bookstorage.exception.DaoException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class WrapperConnector {
    private Connection connection;

    public WrapperConnector() throws DaoException {
        ConnectionCreator connectionCreator = new ConnectionCreator();
        connection = connectionCreator.getConnection();
        if (connection == null) {
            throw new DaoException("Connection is null");
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                System.err.println("Statement isn't closed");
            }
        }
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Connection isn't closed");
            }
        }
    }
}