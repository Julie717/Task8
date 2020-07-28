package com.buyalskaya.bookstorage.model.dao;

import com.buyalskaya.bookstorage.exception.DaoException;
import com.buyalskaya.bookstorage.model.dao.impl.BookDaoImpl;
import com.buyalskaya.bookstorage.model.entity.CustomBook;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class BookDaoTest {
    BookDaoImpl bookDao;

    @BeforeClass
    public void setUp() throws DaoException {
        bookDao = new BookDaoImpl();
    }

    @Test
    public void addTestNegative() {
        String name = "Портрет Дориана Грея"; //This book is already in database
        List<String> author = new ArrayList<>();
        author.add("Оскар Уайльд");
        String edition = "Азбука";
        int year = 2017;
        int page = 320;
        CustomBook book = new CustomBook(name, author, edition, year, page);
        assertThrows(DaoException.class, () -> bookDao.add(book));
    }

    @Test
    public void removeByIdTestNegative() {
        long bookId = 1785L; //This book is absent in database
        assertThrows(DaoException.class, () -> bookDao.removeById(bookId));
    }

    @Test
    public void removeByNameTestNegative() {
        String name = "Маленький принц"; //This book is absent in database
        assertThrows(DaoException.class, () -> bookDao.removeByName(name));
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
    public void findByAuthorTestPositive() throws DaoException {
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
    public void findByEditionTestPositive() throws DaoException {
        String edition = "Азбука";
        List<CustomBook> actual = bookDao.findByEdition(edition);
        List<CustomBook> expected = new ArrayList<>();
        long bookId = 28L;
        String name = "Горе от ума";
        List<String> author = new ArrayList<>();
        author.add("Александр Грибоедов");
        edition = "Азбука";
        int year = 2018;
        int page = 256;
        CustomBook book = new CustomBook(bookId, name, author, edition, year, page);
        expected.add(book);
        bookId = 31L;
        name = "Портрет Дориана Грея";
        author = new ArrayList<>();
        author.add("Оскар Уайльд");
        edition = "Азбука";
        year = 2017;
        page = 320;
        book = new CustomBook(bookId, name, author, edition, year, page);
        expected.add(book);
        assertEquals(actual, expected);
    }

    @Test
    public void findByEditionTestNegative() {
        String edition = "Sun";
        assertThrows(DaoException.class, () -> bookDao.findByEdition(edition));
    }

    @Test
    public void findByYearTestPositive() throws DaoException {
        int[] years = new int[]{2017, 2018};
        List<CustomBook> actual = bookDao.findByYear(years);
        List<CustomBook> expected = new ArrayList<>();
        long bookId = 28L;
        String name = "Горе от ума";
        List<String> author = new ArrayList<>();
        author.add("Александр Грибоедов");
        String edition = "Азбука";
        int year = 2018;
        int page = 256;
        CustomBook book = new CustomBook(bookId, name, author, edition, year, page);
        expected.add(book);
        bookId = 31L;
        name = "Портрет Дориана Грея";
        author = new ArrayList<>();
        author.add("Оскар Уайльд");
        edition = "Азбука";
        year = 2017;
        page = 320;
        book = new CustomBook(bookId, name, author, edition, year, page);
        expected.add(book);
        assertEquals(actual, expected);
    }

    @Test
    public void findByYearTestNegative() {
        int[] years = new int[]{1999, 2000};//Books of such period publishing years is absent in database
        assertThrows(DaoException.class, () -> bookDao.findByYear(years));
    }

    @Test
    public void findByPageTestPositive() throws DaoException {
        int[] pages = new int[]{250, 300};
        List<CustomBook> actual = bookDao.findByPage(pages);
        List<CustomBook> expected = new ArrayList<>();
        long bookId = 15L;
        String name = "Memoirs and Misinformation";
        List<String> author = new ArrayList<>();
        author.add("Jim Carrey");
        author.add("Dana Vachon");
        String edition = "Knopf";
        int year = 2020;
        int page = 272;
        CustomBook book = new CustomBook(bookId, name, author, edition, year, page);
        expected.add(book);
        bookId = 28L;
        name = "Горе от ума";
        author = new ArrayList<>();
        author.add("Александр Грибоедов");
        edition = "Азбука";
        year = 2018;
        page = 256;
        book = new CustomBook(bookId, name, author, edition, year, page);
        expected.add(book);
        assertEquals(actual, expected);
    }

    @Test
    public void findByPageTestNegative() {
        int[] pages = new int[]{15, 120};//Books with pages from this interval is absent in database
        assertThrows(DaoException.class, () -> bookDao.findByPage(pages));
    }

    @Test
    public void findOldBookTestPositive() throws DaoException {
        List<CustomBook> actual = bookDao.findOldBook();
        long bookId = 5L;
        String name = "Notre-Dame de Paris";
        List<String> author = new ArrayList<>();
        author.add("Victor Hugo");
        String edition = "Oxford University Press, Reissue edition";
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