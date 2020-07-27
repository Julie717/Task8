package com.buyalskaya.bookstorage.model.dao.impl;

import com.buyalskaya.bookstorage.model.connection.WrapperConnector;
import com.buyalskaya.bookstorage.model.dao.BookDao;
import com.buyalskaya.bookstorage.model.dao.ColumnName;
import com.buyalskaya.bookstorage.model.entity.CustomBook;
import com.buyalskaya.bookstorage.exception.DaoException;
import com.buyalskaya.bookstorage.util.DataParser;

import java.sql.*;
import java.util.*;

public class BookDaoImpl implements BookDao {
    private static final String SQL_INSERT_BOOK = "INSERT INTO book (name, author, edition, " +
            "publishing_year, number_of_pages) VALUES(?, ?, ?, ?, ?)";
    private static final String SQL_DELETE_BOOK_BY_ID = "DELETE FROM book WHERE (id = ?)";
    private static final String SQL_DELETE_BOOK_BY_NAME = "DELETE FROM book " +
            "WHERE (SELECT INSTR(name, ?) > 0)";
    private static final String SQL_SELECT_BOOK_BY_ID = "SELECT id, name, author, edition, " +
            "publishing_year, number_of_pages FROM book WHERE (id = ?)";
    private static final String SQL_SELECT_BOOK_BY_NAME = "SELECT id, name, author, edition, " +
            "publishing_year, number_of_pages FROM book WHERE (SELECT INSTR(name, ?) > 0)";
    private static final String SQL_SELECT_BOOK_BY_AUTHOR = "SELECT id, name, author, edition, " +
            "publishing_year, number_of_pages FROM book WHERE (SELECT INSTR(author, ?) > 0)";
    private static final String SQL_SELECT_ALL_BOOKS = "SELECT id, name, author, edition, " +
            "publishing_year, number_of_pages FROM book";
    private static final String SQL_SELECT_OLD_BOOK = "SELECT id, name, author, edition, " +
            "publishing_year, number_of_pages FROM book WHERE publishing_year IN " +
            "(SELECT MIN(publishing_year) FROM book)";
    private WrapperConnector wrapperConnector;

    public BookDaoImpl() throws DaoException {
        wrapperConnector = new WrapperConnector();
    }

    @Override
    public void add(CustomBook entity) throws DaoException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = wrapperConnector.getConnection().prepareStatement(SQL_INSERT_BOOK);
            preparedStatement.setString(1, entity.getName());
            String author = String.join(",", entity.getAuthor());
            preparedStatement.setString(2, author);
            preparedStatement.setString(3, entity.getEdition());
            preparedStatement.setInt(4, entity.getPublishingYear());
            preparedStatement.setInt(5, entity.getNumberOfPages());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new DaoException("SQL exception (request or table failed)", ex);
        } finally {
            wrapperConnector.closeStatement(preparedStatement);
        }
    }

    @Override
    public void removeById(Long id) throws DaoException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = wrapperConnector.getConnection().prepareStatement(SQL_DELETE_BOOK_BY_ID);
            preparedStatement.setLong(1, id);
            int rowsUpdate = preparedStatement.executeUpdate();
            if (rowsUpdate == 0) {
                throw new DaoException("The book isn't found");
            }
        } catch (SQLException ex) {
            throw new DaoException("SQL exception (request or table failed)", ex);
        } finally {
            wrapperConnector.closeStatement(preparedStatement);
        }
    }

    @Override
    public void removeByName(String name) throws DaoException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = wrapperConnector.getConnection().prepareStatement(SQL_DELETE_BOOK_BY_NAME);
            preparedStatement.setString(1, name);
            int rowsUpdate = preparedStatement.executeUpdate();
            if (rowsUpdate == 0) {
                throw new DaoException("The book isn't found");
            }
        } catch (SQLException ex) {
            throw new DaoException("SQL exception (request or table failed)", ex);
        } finally {
            wrapperConnector.closeStatement(preparedStatement);
        }
    }

    @Override
    public CustomBook findById(Long id) throws DaoException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = wrapperConnector.getConnection().prepareStatement(SQL_SELECT_BOOK_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                CustomBook book = createBookFromResetSet(resultSet);
                return book;
            } else {
                throw new DaoException("The book isn't found");
            }
        } catch (SQLException ex) {
            throw new DaoException("SQL exception (request or table failed)", ex);
        } finally {
            wrapperConnector.closeStatement(preparedStatement);
        }
    }

    @Override
    public List<CustomBook> findByName(String name) throws DaoException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = wrapperConnector.getConnection().prepareStatement(SQL_SELECT_BOOK_BY_NAME);
            List<CustomBook> books = new ArrayList<>();
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                CustomBook book = createBookFromResetSet(resultSet);
                books.add(book);
            }
            if (books.isEmpty()) {
                throw new DaoException("The book isn't found");
            }
            return books;
        } catch (SQLException ex) {
            throw new DaoException("SQL exception (request or table failed)", ex);
        } finally {
            wrapperConnector.closeStatement(preparedStatement);
        }
    }

    @Override
    public List<CustomBook> findByAuthor(String author) throws DaoException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = wrapperConnector.getConnection().prepareStatement(SQL_SELECT_BOOK_BY_AUTHOR);
            List<CustomBook> books = new ArrayList<>();
            preparedStatement.setString(1, author);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                CustomBook book = createBookFromResetSet(resultSet);
                books.add(book);
            }
            if (books.isEmpty()) {
                throw new DaoException("The book isn't found");
            }
            return books;
        } catch (SQLException ex) {
            throw new DaoException("SQL exception (request or table failed)", ex);
        } finally {
            wrapperConnector.closeStatement(preparedStatement);
        }
    }

    @Override
    public List<CustomBook> findAll() throws DaoException {
        Statement statement = null;
        try {
            statement = wrapperConnector.getConnection().createStatement();
            List<CustomBook> books = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_BOOKS);
            while (resultSet.next()) {
                CustomBook book = createBookFromResetSet(resultSet);
                books.add(book);
            }
            if (books.isEmpty()) {
                throw new DaoException("Books aren't found");
            }
            return books;
        } catch (SQLException ex) {
            throw new DaoException("SQL exception (request or table failed)", ex);
        } finally {
            wrapperConnector.closeStatement(statement);
        }
    }

    @Override
    public List<CustomBook> findOldBook() throws DaoException {
        Statement statement = null;
        try {
            statement = wrapperConnector.getConnection().createStatement();
            List<CustomBook> books = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_OLD_BOOK);
            while (resultSet.next()) {
                CustomBook book = createBookFromResetSet(resultSet);
                books.add(book);
            }
            if (books.isEmpty()) {
                throw new DaoException("Books aren't found");
            }
            return books;
        } catch (SQLException ex) {
            throw new DaoException("SQL exception (request or table failed)", ex);
        } finally {
            wrapperConnector.closeStatement(statement);
        }
    }

    private CustomBook createBookFromResetSet(ResultSet resultSet) throws SQLException {
        CustomBook book = new CustomBook();
        book.setBookId(resultSet.getLong(ColumnName.BOOK_ID));
        book.setName(resultSet.getString(ColumnName.BOOK_NAME));
        String author = resultSet.getString(ColumnName.BOOK_AUTHOR);
        DataParser dataParser = new DataParser();
        List<String> authors = dataParser.authorParser(author);
        book.setAuthor(authors);
        book.setEdition(resultSet.getString(ColumnName.BOOK_EDITION));
        book.setPublishingYear(resultSet.getInt(ColumnName.BOOK_YEAR));
        book.setNumberOfPages(resultSet.getInt(ColumnName.BOOK_PAGE));
        return book;
    }

    @Override
    public void close() {
        wrapperConnector.closeConnection();
    }
}