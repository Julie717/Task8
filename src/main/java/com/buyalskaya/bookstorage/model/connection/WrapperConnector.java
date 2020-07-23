package com.buyalskaya.bookstorage.model.connection;

import com.buyalskaya.bookstorage.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class WrapperConnector {
    private Connection connection;

    public WrapperConnector() {
        ConnectionCreator connectionCreator = new ConnectionCreator();
        connection = connectionCreator.getConnection();
    }

    public Connection getConnection() {
        return connection;
    }

    public Statement receiveStatement() throws DaoException {
        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                return statement;
            } catch (SQLException ex) {
                throw new DaoException("Statement is absent", ex);
            }
        }
        throw new DaoException("Connection is null");
    }

    public PreparedStatement receivePreparedStatement(String sqlQuery) throws DaoException {
        if (connection != null) {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
                return preparedStatement;
            } catch (SQLException ex) {
                throw new DaoException("Statement is absent", ex);
            }
        }
        throw new DaoException("Connection is null");
    }

    public void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                System.err.println("Statement not closed");
            }
        }
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Connection not closed");
            }
        }
    }
}
