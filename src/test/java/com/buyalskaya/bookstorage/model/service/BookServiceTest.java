package com.buyalskaya.bookstorage.model.service;

import com.buyalskaya.bookstorage.exception.DaoException;
import com.buyalskaya.bookstorage.exception.ServiceException;
import com.buyalskaya.bookstorage.model.entity.CustomBook;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;

import static org.testng.Assert.*;

public class BookServiceTest {
    BookService bookService;

    @BeforeClass
    public void setUp() {
        bookService = new BookService();
    }

    @Test
    public void findByIdTestPositive() throws ServiceException {
        CustomBook actual = bookService.findById("21");
        long bookId = 21L;
        String name = "Les Miserables";
        List<String> author = new ArrayList<>();
        author.add("Victor Hugo");
        String edition = "Canterbury Classics";
        int year = 2015;
        int page = 1264;
        CustomBook expected = new CustomBook(bookId, name, author, edition, year, page);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "dataForFindById")
    public Object[][] dataForFindById() {
        return new Object[][]{
                {"-5"},
                {"0"},
                {"152748"},
                {""}
        };
    }

    @Test(dataProvider = "dataForFindById")
    public void findByIdTestParams(String id) {
        assertThrows(ServiceException.class, () -> bookService.findById(id));
    }

    @DataProvider(name = "dataForFindByName")
    public Object[][] dataForFindByName() {
        return new Object[][]{
                {"The Master and Margarita"},
                {"147"},
                {""}
        };
    }

    @Test(dataProvider = "dataForFindByName")
    public void findByNameTestParams(String name) {
        assertThrows(ServiceException.class, () -> bookService.findByName(name));
    }

    @Test
    public void findByNameTestPositive() throws ServiceException {
        List<CustomBook> actual = bookService.findByName("Ninety-three");
        long bookId = 22L;
        String name = "Ninety-three";
        List<String> author = new ArrayList<>();
        author.add("Victor Hugo");
        String edition = "Peerless Press";
        int year = 2011;
        int page = 362;
        CustomBook book = new CustomBook(bookId, name, author, edition, year, page);
        List<CustomBook> expected = new ArrayList<>();
        expected.add(book);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "dataForFindByAuthor")
    public Object[][] dataForFindByAuthor() {
        return new Object[][]{
                {"Daniel Defo"},
                {"147"},
                {""}
        };
    }

    @Test(dataProvider = "dataForFindByAuthor")
    public void findByAuthorTestParams(String author) {
        assertThrows(ServiceException.class, () -> bookService.findByAuthor(author));
    }

    @Test
    public void findByAuthorTestPositive() throws ServiceException {
        List<CustomBook> actual = bookService.findByAuthor("Jane Healey");
        long bookId = 20L;
        String name = "The Animals at Lockwood Manor";
        List<String> author = new ArrayList<>();
        author.add("Jane Healey");
        String edition = "Houghton Mifflin Harcourt";
        int year = 2020;
        int page = 352;
        CustomBook book = new CustomBook(bookId, name, author, edition, year, page);
        List<CustomBook> expected = new ArrayList<>();
        expected.add(book);
        assertEquals(actual, expected);
    }
    @Test
    public void findOldBookTestPositive() throws ServiceException {
        List<CustomBook> actual = bookService.findOldBook();
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