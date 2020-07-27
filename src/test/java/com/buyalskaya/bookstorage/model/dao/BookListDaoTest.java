package com.buyalskaya.bookstorage.model.dao;

import com.buyalskaya.bookstorage.exception.DaoException;
import com.buyalskaya.bookstorage.model.dao.impl.BookDaoImpl;
import com.buyalskaya.bookstorage.model.entity.CustomBook;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class BookListDaoTest {
    BookDaoImpl bookDao;

    @BeforeClass
    public void setUp() throws DaoException {
        bookDao = new BookDaoImpl();
    }

    @Test
    public void findByIdTestPositive() throws DaoException {
        long bookId = 20L;
        CustomBook actual = bookDao.findById(bookId);
        String name = "The Animals at Lockwood Manor";
        List<String> author = new ArrayList<>();
        author.add("Jane Healey");
        String edition = "Houghton Mifflin Harcourt";
        int year = 2020;
        int page = 352;
        CustomBook expected = new CustomBook(bookId, name, author, edition, year, page);
        assertEquals(actual, expected);
    }

    @Test
    public void findByIdTestNegative() {
        long bookId = 89L;
        assertThrows(DaoException.class, () -> bookDao.findById(bookId));
    }

    @Test
    public void findByNameTestPositive() throws DaoException {
        String name = "Animal";
        List<CustomBook> actual = bookDao.findByName(name);
        long bookId = 20L;
        String nameExpected = "The Animals at Lockwood Manor";
        List<String> author = new ArrayList<>();
        author.add("Jane Healey");
        String edition = "Houghton Mifflin Harcourt";
        int year = 2020;
        int page = 352;
        CustomBook expectedBook = new CustomBook(bookId, nameExpected, author, edition, year, page);
        List<CustomBook> expected = new ArrayList<>();
        expected.add(expectedBook);
        assertEquals(actual, expected);
    }

    @Test
    public void findByNameTestNegative() {
        String name = "Sun";
        assertThrows(DaoException.class, () -> bookDao.findByName(name));
    }

    @Test
    public void findByAuthorTestPositive() throws DaoException{
        String author = "Healey";
        List<CustomBook> actual = bookDao.findByAuthor(author);
        long bookId = 20L;
        String name = "The Animals at Lockwood Manor";
        List<String> authorExpected = new ArrayList<>();
        authorExpected.add("Jane Healey");
        String edition = "Houghton Mifflin Harcourt";
        int year = 2020;
        int page = 352;
        CustomBook expectedBook = new CustomBook(bookId, name,
                authorExpected, edition, year, page);
        List<CustomBook> expected = new ArrayList<>();
        expected.add(expectedBook);
        assertEquals(actual, expected);
    }

    @Test
    public void findByAuthorTestNegative() {
        String author = "Charles Dickens";
        assertThrows(DaoException.class, () -> bookDao.findByAuthor(author));
    }

    @Test
    public void findOldBookTestPositive() throws DaoException{
        List<CustomBook> actual = bookDao.findOldBook();
        long bookId = 5L;
        String name = "Notre-Dame de Paris";
        List<String> author = new ArrayList<>();
        author.add("Victor Hugo");
        String edition= "Oxford University Press, Reissue edition";
        int year = 2009;
        int page = 592;
        CustomBook book = new CustomBook(bookId, name,
                author, edition, year, page);
        List<CustomBook> expected = new ArrayList<>();
        expected.add(book);
        bookId = 25L;
        name = "Titian Tintoretto Veronese";
        author = new ArrayList<>();
        author.add("Frederick Ilchman");
        author.add("Linda Borean");
        author.add("Patricia Fortini Brown");
        edition = "Lund Humphries";
        year = 2009;
        page = 304;
        book = new CustomBook(bookId, name, author, edition, year, page);
        expected.add(book);
        assertEquals(actual, expected);
    }
}