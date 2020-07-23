package com.buyalskaya.bookstorage.model.dao;

import com.buyalskaya.bookstorage.exception.DaoException;
import com.buyalskaya.bookstorage.model.entity.CustomEntity;

import java.util.*;

public interface AbstractDao<K, T extends CustomEntity> {
    void add(T entity) throws DaoException;

    void removeById(K id) throws DaoException;

    T findById(K id) throws DaoException;

    List<T> findAll() throws DaoException;

    void close();
}