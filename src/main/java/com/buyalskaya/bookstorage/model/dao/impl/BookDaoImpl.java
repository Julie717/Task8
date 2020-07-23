package com.buyalskaya.bookstorage.model.dao.impl;

import com.buyalskaya.bookstorage.model.connection.WrapperConnector;
import com.buyalskaya.bookstorage.model.dao.BookDao;
import com.buyalskaya.bookstorage.model.entity.CustomBook;
import com.buyalskaya.bookstorage.exception.DaoException;
import com.buyalskaya.bookstorage.utility.DataParser;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

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
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_AUTHOR = "author";
    private static final String COLUMN_EDITION = "edition";
    private static final String COLUMN_YEAR = "publishing_year";
    private static final String COLUMN_PAGE = "number_of_pages";
    private WrapperConnector wrapperConnector;

    public BookDaoImpl() {
        wrapperConnector = new WrapperConnector();
    }

    @Override
    public void add(CustomBook entity) throws DaoException {
        PreparedStatement preparedStatement = wrapperConnector.receivePreparedStatement(SQL_INSERT_BOOK);
        try {
            preparedStatement.setString(1, entity.getName());
            String author = entity.getAuthor().stream().collect(Collectors.joining(","));
            preparedStatement.setString(2, author);
            preparedStatement.setString(3, entity.getEdition());
            preparedStatement.setInt(4, entity.getYear());
            preparedStatement.setInt(5, entity.getPage());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new DaoException("SQL exception (request or table failed)", ex);
        } finally {
            wrapperConnector.closeStatement(preparedStatement);
        }
    }

    @Override
    public void removeById(Long id) throws DaoException {
        PreparedStatement preparedStatement = wrapperConnector.receivePreparedStatement(SQL_DELETE_BOOK_BY_ID);
        try {
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
        PreparedStatement preparedStatement = wrapperConnector.receivePreparedStatement(SQL_DELETE_BOOK_BY_NAME);
        try {
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
        PreparedStatement preparedStatement = wrapperConnector.receivePreparedStatement(SQL_SELECT_BOOK_BY_ID);
        try {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            CustomBook book = new CustomBook();
            if (resultSet.next()) {
                book.setBookId(resultSet.getLong(COLUMN_ID));
                book.setName(resultSet.getString(COLUMN_NAME));
                String author = resultSet.getString(COLUMN_AUTHOR);
                DataParser dataParser = new DataParser();
                List<String> authors = dataParser.authorParser(author);
                book.setAuthor(authors);
                book.setEdition(resultSet.getString(COLUMN_EDITION));
                book.setYear(resultSet.getInt(COLUMN_YEAR));
                book.setPage(resultSet.getInt(COLUMN_PAGE));
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
        PreparedStatement preparedStatement = wrapperConnector.receivePreparedStatement(SQL_SELECT_BOOK_BY_NAME);
        List<CustomBook> books = new ArrayList<>();
        try {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                CustomBook book = new CustomBook();
                book.setBookId(resultSet.getLong(COLUMN_ID));
                book.setName(resultSet.getString(COLUMN_NAME));
                String author = resultSet.getString(COLUMN_AUTHOR);
                DataParser dataParser = new DataParser();
                List<String> authors = dataParser.authorParser(author);
                book.setAuthor(authors);
                book.setEdition(resultSet.getString(COLUMN_EDITION));
                book.setYear(resultSet.getInt(COLUMN_YEAR));
                book.setPage(resultSet.getInt(COLUMN_PAGE));
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
        PreparedStatement preparedStatement = wrapperConnector.receivePreparedStatement(SQL_SELECT_BOOK_BY_AUTHOR);
        List<CustomBook> books = new ArrayList<>();
        try {
            preparedStatement.setString(1, author);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                CustomBook book = new CustomBook();
                book.setBookId(resultSet.getLong(COLUMN_ID));
                book.setName(resultSet.getString(COLUMN_NAME));
                String authorString = resultSet.getString(COLUMN_AUTHOR);
                DataParser dataParser = new DataParser();
                List<String> authors = dataParser.authorParser(authorString);
                book.setAuthor(authors);
                book.setEdition(resultSet.getString(COLUMN_EDITION));
                book.setYear(resultSet.getInt(COLUMN_YEAR));
                book.setPage(resultSet.getInt(COLUMN_PAGE));
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
        List<CustomBook> books = new ArrayList<>();
        Statement statement = wrapperConnector.receiveStatement();
        try {
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_BOOKS);
            while (resultSet.next()) {
                CustomBook book = new CustomBook();
                book.setBookId(resultSet.getLong(COLUMN_ID));
                book.setName(resultSet.getString(COLUMN_NAME));
                String author = resultSet.getString(COLUMN_AUTHOR);
                DataParser dataParser = new DataParser();
                List<String> authors = dataParser.authorParser(author);
                book.setAuthor(authors);
                book.setEdition(resultSet.getString(COLUMN_EDITION));
                book.setYear(resultSet.getInt(COLUMN_YEAR));
                book.setPage(resultSet.getInt(COLUMN_PAGE));
                books.add(book);
            }
            if (books.isEmpty()) {
                throw new DaoException("The book isn't found");
            }
            return books;
        } catch (SQLException ex) {
            throw new DaoException("SQL exception (request or table failed)", ex);
        } finally {
            wrapperConnector.closeStatement(statement);
        }
    }

    @Override
    public void close() {
        wrapperConnector.closeConnection();
    }
}