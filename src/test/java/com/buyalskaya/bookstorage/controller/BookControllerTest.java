package com.buyalskaya.bookstorage.controller;

import com.buyalskaya.bookstorage.model.entity.CustomBook;
import org.testng.annotations.Test;

import java.util.*;

import static org.testng.Assert.*;

public class BookControllerTest {

    @Test
    public void findByIdTest() {
        String commandName = "FIND_BY_ID";
        Map<String, String> parameters = new HashMap<>();
        parameters.put("id", "17");
        Response expected = new Response();
        expected.setCompletedSuccess(true);
        List<CustomBook> books = new ArrayList<>();
        long bookId = 17L;
        String name = "Harry Potter and the Chamber of Secrets";
        List<String> author = new ArrayList<>();
        author.add("J.K.Rowling");
        String edition = "Bloomsbury";
        int year = 2014;
        int page = 384;
        CustomBook book = new CustomBook(bookId, name, author, edition, year, page);
        books.add(book);
        expected.setBooks(books);
        Response actual = BookController.getInstance().processRequest(commandName, parameters);
        assertEquals(actual, expected);
    }

    @Test
    public void findByNameTest() {
        String commandName = "FIND_BY_NAME";
        Map<String, String> parameters = new HashMap<>();
        parameters.put("name", "Paris");
        Response expected = new Response();
        expected.setCompletedSuccess(true);
        List<CustomBook> books = new ArrayList<>();
        long bookId = 5L;
        String name = "Notre-Dame de Paris";
        List<String> author = new ArrayList<>();
        author.add("Victor Hugo");
        String edition = "Oxford University Press, Reissue edition";
        int year = 2009;
        int page = 592;
        CustomBook book = new CustomBook(bookId, name, author, edition, year, page);
        books.add(book);
        expected.setBooks(books);
        Response actual = BookController.getInstance().processRequest(commandName, parameters);
        assertEquals(actual, expected);
    }

    @Test
    public void findByAuthorTest() {
        String commandName = "find_by_author";
        Map<String, String> parameters = new HashMap<>();
        parameters.put("author", "Susan Meissner");
        Response expected = new Response();
        expected.setCompletedSuccess(true);
        List<CustomBook> books = new ArrayList<>();
        long bookId = 24L;
        String name = "A Fall of Marigolds";
        List<String> author = new ArrayList<>();
        author.add("Susan Meissner");
        String edition = "Houghton Mifflin Harcourt";
        int year = 2014;
        int page = 400;
        CustomBook book = new CustomBook(bookId, name, author, edition, year, page);
        books.add(book);
        expected.setBooks(books);
        Response actual = BookController.getInstance().processRequest(commandName, parameters);
        assertEquals(actual, expected);
    }

    @Test
    public void findByEditionTest() {
        String commandName = "FIND_BY_EDITION";
        Map<String, String> parameters = new HashMap<>();
        parameters.put("edition", "Classic");
        Response expected = new Response();
        expected.setCompletedSuccess(true);
        List<CustomBook> books = new ArrayList<>();
        long bookId = 21L;
        String name = "Les Miserables";
        List<String> author = new ArrayList<>();
        author.add("Victor Hugo");
        String edition = "Canterbury Classics";
        int year = 2015;
        int page = 1264;
        CustomBook book = new CustomBook(bookId, name, author, edition, year, page);
        books.add(book);
        expected.setBooks(books);
        Response actual = BookController.getInstance().processRequest(commandName, parameters);
        assertEquals(actual, expected);
    }

    @Test
    public void findByYearTest() {
        String commandName = "FIND_BY_year";
        Map<String, String> parameters = new HashMap<>();
        parameters.put("year", "2015");
        Response expected = new Response();
        expected.setCompletedSuccess(true);
        List<CustomBook> books = new ArrayList<>();
        long bookId = 21L;
        String name = "Les Miserables";
        List<String> author = new ArrayList<>();
        author.add("Victor Hugo");
        String edition = "Canterbury Classics";
        int year = 2015;
        int page = 1264;
        CustomBook book = new CustomBook(bookId, name, author, edition, year, page);
        books.add(book);
        expected.setBooks(books);
        Response actual = BookController.getInstance().processRequest(commandName, parameters);
        assertEquals(actual, expected);
    }

    @Test
    public void findByPageTest() {
        String commandName = "FIND_BY_Page";
        Map<String, String> parameters = new HashMap<>();
        parameters.put("page", "272");
        Response expected = new Response();
        expected.setCompletedSuccess(true);
        List<CustomBook> books = new ArrayList<>();
        long bookId = 15L;
        String name = "Memoirs and Misinformation";
        List<String> author = new ArrayList<>();
        author.add("Jim Carrey");
        author.add("Dana Vachon");
        String edition = "Knopf";
        int year = 2020;
        int page = 272;
        CustomBook book = new CustomBook(bookId, name, author, edition, year, page);
        books.add(book);
        expected.setBooks(books);
        Response actual = BookController.getInstance().processRequest(commandName, parameters);
        assertEquals(actual, expected);
    }

    @Test
    public void findOldBookTest() {
        String commandName = "find_old_book";
        Map<String, String> parameters = new HashMap<>();
        Response expected = new Response();
        expected.setCompletedSuccess(true);
        long bookId = 5L;
        String name = "Notre-Dame de Paris";
        List<String> author = new ArrayList<>();
        author.add("Victor Hugo");
        String edition = "Oxford University Press, Reissue edition";
        int year = 2009;
        int page = 592;
        CustomBook book = new CustomBook(bookId, name, author, edition, year, page);
        List<CustomBook> books = new ArrayList<>();
        books.add(book);
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
        books.add(book);
        expected.setBooks(books);
        Response actual = BookController.getInstance().processRequest(commandName, parameters);
        assertEquals(actual, expected);
    }

    @Test
    public void processRequestTestNegativeCommand() {
        String commandName = "findByMaterial";
        Map<String, String> parameters = new HashMap<>();
        parameters.put("page", "400");
        Response expected = new Response();
        expected.setCompletedSuccess(false);
        expected.setMessage("Incorrect command");
        Response actual = BookController.getInstance().processRequest(commandName, parameters);
        assertEquals(actual, expected);
    }
}