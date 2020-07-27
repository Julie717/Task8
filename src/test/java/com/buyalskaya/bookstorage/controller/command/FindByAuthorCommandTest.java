package com.buyalskaya.bookstorage.controller.command;

import com.buyalskaya.bookstorage.controller.command.impl.FindByAuthorCommand;
import com.buyalskaya.bookstorage.model.entity.CustomBook;
import com.buyalskaya.bookstorage.controller.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;

import static org.testng.Assert.assertEquals;

public class FindByAuthorCommandTest {
    FindByAuthorCommand findByAuthorCommand;

    @BeforeClass
    public void setUp(){
        findByAuthorCommand = new FindByAuthorCommand();
    }

    @DataProvider(name = "dataForFindByAuthorCommand")
    public Object[][] dataForFindByAuthorCommand() {
        Map<String, String> parameters1 = new HashMap<>();
        parameters1.put("author", "Rowling");
        Response response1 = new Response();
        response1.setCompletedSuccess(true);
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
        bookId = 19L;
        name = "Harry Potter and the Goblet of Fire";
        author = new ArrayList<>();
        author.add("J.K.Rowling");
        edition = "Bloomsbury";
        year = 2014;
        page = 640;
        book = new CustomBook(bookId, name, author, edition, year, page);
        books.add(book);
        bookId = 16L;
        name = "Harry Potter and the Philosopher's Stone";
        author = new ArrayList<>();
        author.add("J.K.Rowling");
        edition = "Bloomsbury";
        year = 2014;
        page = 352;
        book = new CustomBook(bookId, name, author, edition, year, page);
        books.add(book);
        bookId = 18L;
        name = "Harry Potter and the Prisoner of Azkaban";
        author = new ArrayList<>();
        author.add("J.K.Rowling");
        edition = "Bloomsbury";
        year = 2014;
        page = 480;
        book = new CustomBook(bookId, name, author, edition, year, page);
        books.add(book);
        response1.setBooks(books);
        Map<String, String> parameters2 = new HashMap<>();
        parameters2.put("name", "Notre-Dam");
        Response response2 = new Response(false, "Incorrect book author");
        Map<String, String> parameters3 = new HashMap<>();
        parameters3.put("author", "Daniel Defo");
        Response response3 = new Response(false, "DaoException was found: The book isn't found");
        Map<String, String> parameters4 = new HashMap<>();
        parameters4.put("name", "");
        Response response4 = new Response(false, "Incorrect book author");
        Map<String, String> parameters5 = new HashMap<>();
        parameters5.put("author", "DuBois");
        Response response5 = new Response();
        response5.setCompletedSuccess(true);
        books = new ArrayList<>();
        bookId = 23L;
        name = "The Summer House";
        author = new ArrayList<>();
        author.add("James Patterson");
        author.add("Brendan DuBois");
        edition = "Little, Brown and Company";
        year = 2020;
        page = 448;
        book = new CustomBook(bookId, name, author, edition, year, page);
        books.add(book);
        response5.setBooks(books);
        Map<String, String> parameters6 = null;
        Response response6 = new Response(false, "Incorrect author");
        return new Object[][]{
                {parameters1, response1},
                {parameters2, response2},
                {parameters3, response3},
                {parameters4, response4},
                {parameters5, response5},
                {parameters6, response6}
        };
    }

    @Test(dataProvider = "dataForFindByAuthorCommand")
    public void findByAuthorCommandTestParams(Map<String, String> parameters, Response expected) {
        Response actual = findByAuthorCommand.execute(parameters);
        assertEquals(actual, expected);
    }
}