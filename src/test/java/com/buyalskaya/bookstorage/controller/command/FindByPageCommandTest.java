package com.buyalskaya.bookstorage.controller.command;

import com.buyalskaya.bookstorage.controller.Response;
import com.buyalskaya.bookstorage.controller.command.impl.FindByPageCommand;
import com.buyalskaya.bookstorage.model.entity.CustomBook;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class FindByPageCommandTest {
    FindByPageCommand findByPageCommand;

    @BeforeClass
    public void setUp() {
        findByPageCommand = new FindByPageCommand();
    }

    @DataProvider(name = "dataForFindByPageCommand")
    public Object[][] dataForFindByPageCommand() {
        Map<String, String> parameters1 = new HashMap<>();
        parameters1.put("page", "300, 250");
        Response response1 = new Response();
        response1.setCompletedSuccess(true);
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
        bookId = 28L;
        name = "Горе от ума";
        author = new ArrayList<>();
        author.add("Александр Грибоедов");
        edition = "Азбука";
        year = 2018;
        page = 256;
        book = new CustomBook(bookId, name, author, edition, year, page);
        books.add(book);
        response1.setBooks(books);
        Map<String, String> parameters2 = new HashMap<>();
        parameters2.put("id", "78");
        Response response2 = new Response(false, "Incorrect book page");
        Map<String, String> parameters3 = new HashMap<>();
        parameters3.put("page", "15, 100");
        Response response3 = new Response(false, "DaoException was found: The book isn't found");
        Map<String, String> parameters4 = new HashMap<>();
        parameters4.put("page", "");
        Response response4 = new Response(false, "Incorrect book page");
        Map<String, String> parameters5 = new HashMap<>();
        parameters5.put("page", "608");
        Response response5 = new Response();
        response5.setCompletedSuccess(true);
        books = new ArrayList<>();
        bookId = 27L;
        name = "Поющие в терновнике";
        author = new ArrayList<>();
        author.add("Колин Маккалоу");
        edition = "АСТ";
        year = 2019;
        page = 608;
        book = new CustomBook(bookId, name, author, edition, year, page);
        books.add(book);
        response5.setBooks(books);
        Map<String, String> parameters6 = null;
        Response response6 = new Response(false, "Incorrect book page");
        return new Object[][]{
                {parameters1, response1},
                {parameters2, response2},
                {parameters3, response3},
                {parameters4, response4},
                {parameters5, response5},
                {parameters6, response6}
        };
    }

    @Test(dataProvider = "dataForFindByPageCommand")
    public void findByYearCommandTestParams(Map<String, String> parameters, Response expected) {
        Response actual = findByPageCommand.execute(parameters);
        assertEquals(actual, expected);
    }
}