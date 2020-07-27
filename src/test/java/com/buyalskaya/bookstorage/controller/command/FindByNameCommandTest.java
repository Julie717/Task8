package com.buyalskaya.bookstorage.controller.command;

import com.buyalskaya.bookstorage.controller.command.impl.FindByNameCommand;
import com.buyalskaya.bookstorage.model.entity.CustomBook;
import com.buyalskaya.bookstorage.controller.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;

import static org.testng.Assert.assertEquals;

public class FindByNameCommandTest {
    FindByNameCommand findByNameCommand;

    @BeforeClass
    public void setUp() {
        findByNameCommand = new FindByNameCommand();
    }

    @DataProvider(name = "dataForFindByNameCommand")
    public Object[][] dataForFindByNameCommand() {
        Map<String, String> parameters1 = new HashMap<>();
        parameters1.put("name", "Harry Potter");
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
        parameters2.put("id", "ffffffff-de3f-445d-88d0-c77319426c36");
        Response response2 = new Response(false, "Incorrect book name");
        Map<String, String> parameters3 = new HashMap<>();
        parameters3.put("name", "Black moon");
        Response response3 = new Response(false, "DaoException was found: The book isn't found");
        Map<String, String> parameters4 = new HashMap<>();
        parameters4.put("name", "");
        Response response4 = new Response(false, "Incorrect book name");
        Map<String, String> parameters5 = new HashMap<>();
        parameters5.put("name", "Paris");
        Response response5 = new Response();
        response5.setCompletedSuccess(true);
        books = new ArrayList<>();
        bookId =5L;
        name = "Notre-Dame de Paris";
        author = new ArrayList<>();
        author.add("Victor Hugo");
        edition = "Oxford University Press, Reissue edition";
        year = 2009;
        page = 592;
        book = new CustomBook(bookId, name, author, edition, year, page);
        books.add(book);
        response5.setBooks(books);
        Map<String, String> parameters6 = null;
        Response response6 = new Response(false, "Incorrect book name");
        return new Object[][]{
                {parameters1, response1},
                {parameters2, response2},
                {parameters3, response3},
                {parameters4, response4},
                {parameters5, response5},
                {parameters6, response6}
        };
    }

    @Test(dataProvider = "dataForFindByNameCommand")
    public void findByNameCommandTestParams(Map<String, String> parameters, Response expected) {
        Response actual = findByNameCommand.execute(parameters);
        assertEquals(actual, expected);
    }
}