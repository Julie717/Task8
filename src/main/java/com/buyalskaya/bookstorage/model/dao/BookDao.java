package com.buyalskaya.bookstorage.model.dao;

import com.buyalskaya.bookstorage.exception.DaoException;
import com.buyalskaya.bookstorage.model.entity.CustomBook;

import java.util.List;

public interface BookDao extends AbstractDao<Long, CustomBook> {
    void removeByName(String name) throws DaoException;

    List<CustomBook> findByName(String name) throws DaoException;

    List<CustomBook> findByAuthor(String author) throws DaoException;

    List<CustomBook> findByEdition(String edition) throws DaoException;

    List<CustomBook> findByYear(int... year) throws DaoException;

    List<CustomBook> findByPage(int... page) throws DaoException;

    List<CustomBook> findOldBook() throws DaoException;
}
