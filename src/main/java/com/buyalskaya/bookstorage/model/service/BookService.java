package com.buyalskaya.bookstorage.model.service;

import com.buyalskaya.bookstorage.exception.ServiceException;
import com.buyalskaya.bookstorage.model.dao.AbstractDao;
import com.buyalskaya.bookstorage.model.dao.BookDao;
import com.buyalskaya.bookstorage.model.dao.impl.BookDaoImpl;
import com.buyalskaya.bookstorage.model.entity.CustomBook;
import com.buyalskaya.bookstorage.exception.DaoException;
import com.buyalskaya.bookstorage.utility.DataParser;
import com.buyalskaya.bookstorage.validator.DataValidator;

import java.util.List;

public class BookService {
    public void bookAdd(String name, String author, String edition,
                        String year, String page) throws ServiceException {
        DataParser dataParser = new DataParser();
        DataValidator dataValidator = new DataValidator();
        List<String> authorList = dataParser.authorParser(author);
        if (!dataValidator.isNameValid(name) ||
                !dataValidator.isAuthorValid(authorList) ||
                !dataValidator.isEditionValid(edition) ||
                !dataValidator.isYearValid(year) ||
                !dataValidator.isPageValid(page)) {
            throw new ServiceException("Incorrect book parameters");
        }
        int yearNumber = Integer.parseInt(year);
        int pageNumber = Integer.parseInt(page);
        CustomBook book = new CustomBook(name, authorList, edition, yearNumber, pageNumber);
        AbstractDao bookListDao = new BookDaoImpl();
        try {
            bookListDao.add(book);
        } catch (DaoException ex) {
            throw new ServiceException("DaoException was found", ex);
        } finally {
            bookListDao.close();
        }
    }

    public void removeById(String id) throws ServiceException {
        DataValidator dataValidator = new DataValidator();
        if (!dataValidator.isIdValid(id)) {
            throw new ServiceException("Incorrect book id");
        }
        int idInt = Integer.parseInt(id);
        AbstractDao bookListDao = new BookDaoImpl();
        try {
            bookListDao.removeById(idInt);
        } catch (DaoException ex) {
            throw new ServiceException("DaoException was found: ", ex);
        } finally {
            bookListDao.close();
        }
    }

    public void removeByName(String name) throws ServiceException {
        DataValidator dataValidator = new DataValidator();
        if (!dataValidator.isNameValid(name)) {
            throw new ServiceException("Incorrect book name");
        }
        BookDao bookListDao = new BookDaoImpl();
        try {
            bookListDao.removeByName(name);
        } catch (DaoException ex) {
            throw new ServiceException("DaoException was found: ", ex);
        } finally {
            bookListDao.close();
        }
    }

    public CustomBook findById(String id) throws ServiceException {
        DataValidator dataValidator = new DataValidator();
        if (!dataValidator.isIdValid(id)) {
            throw new ServiceException("Incorrect book id");
        }
        long idLong = Long.parseLong(id);
        BookDao bookListDao = new BookDaoImpl();
        try {
            CustomBook book = bookListDao.findById(idLong);
            return book;
        } catch (DaoException ex) {
            throw new ServiceException("DaoException was found: ", ex);
        } finally {
            bookListDao.close();
        }
    }

    public List<CustomBook> findByName(String name) throws ServiceException {
        DataValidator dataValidator = new DataValidator();
        if (!dataValidator.isNameValid(name)) {
            throw new ServiceException("Incorrect book name");
        }
        BookDao bookListDao = new BookDaoImpl();
        try {
            List<CustomBook> books = bookListDao.findByName(name);
            return books;
        } catch (DaoException ex) {
            throw new ServiceException("DaoException was found: ", ex);
        } finally {
            bookListDao.close();
        }
    }

    public List<CustomBook> findByAuthor(String author) throws ServiceException {
        DataValidator dataValidator = new DataValidator();
        if (!dataValidator.isAuthorValid(author)) {
            throw new ServiceException("Incorrect book author");
        }
        BookDao bookListDao = new BookDaoImpl();
        try {
            List<CustomBook> books = bookListDao.findByAuthor(author);
            return books;
        } catch (DaoException ex) {
            throw new ServiceException("DaoException was found: ", ex);
        } finally {
            bookListDao.close();
        }
    }

    public List<CustomBook> findAll() throws ServiceException {
        BookDao bookListDao = new BookDaoImpl();
        try {
            List<CustomBook> books = bookListDao.findAll();
            return books;
        } catch (DaoException ex) {
            throw new ServiceException("DaoException was found: ", ex);
        } finally {
            bookListDao.close();
        }
    }
}