package com.buyalskaya.bookstorage.controller.command;

import com.buyalskaya.bookstorage.controller.Response;
import com.buyalskaya.bookstorage.controller.command.impl.FindByEditionCommand;
import com.buyalskaya.bookstorage.model.entity.CustomBook;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class FindByEditionCommandTest {
    FindByEditionCommand findByEditionCommand;

    @BeforeClass
    public void setUp() {
        findByEditionCommand = new FindByEditionCommand();
    }

    @DataProvider(name = "dataForFindByEditionCommand")
    public Object[][] dataForFindByEditionCommand() {
        Map<String, String> parameters1 = new HashMap<>();
        parameters1.put("edition", "Азбука");
        Response response1 = new Response();
        response1.setCompletedSuccess(true);
        List<CustomBook> books = new ArrayList<>();
        long bookId = 28L;
        String name = "Горе от ума";
        List<String> author = new ArrayList<>();
        author.add("Александр Грибоедов");
        String edition = "Азбука";
        int year = 2018;
        int page = 256;
        CustomBook book = new CustomBook(bookId, name, author, edition, year, page);
        books.add(book);
        bookId = 31L;
        name = "Портрет Дориана Грея";
        author = new ArrayList<>();
        author.add("Оскар Уайльд");
        edition = "Азбука";
        year = 2017;
        page = 320;
        book = new CustomBook(bookId, name, author, edition, year, page);
        books.add(book);
        response1.setBooks(books);
        Map<String, String> parameters2 = new HashMap<>();
        parameters2.put("id", "78");
        Response response2 = new Response(false, "Incorrect book edition");
        Map<String, String> parameters3 = new HashMap<>();
        parameters3.put("edition", "Black moon");
        Response response3 = new Response(false, "DaoException was found: The book isn't found");
        Map<String, String> parameters4 = new HashMap<>();
        parameters4.put("edition", "");
        Response response4 = new Response(false, "Incorrect book edition");
        Map<String, String> parameters5 = new HashMap<>();
        parameters5.put("edition", "Classic");
        Response response5 = new Response();
        response5.setCompletedSuccess(true);
        books = new ArrayList<>();
        bookId = 21L;
        name = "Les Miserables";
        author = new ArrayList<>();
        author.add("Victor Hugo");
        edition = "Canterbury Classics";
        year = 2015;
        page = 1264;
        book = new CustomBook(bookId, name, author, edition, year, page);
        books.add(book);
        response5.setBooks(books);
        Map<String, String> parameters6 = null;
        Response response6 = new Response(false, "Incorrect book edition");
        return new Object[][]{
                {parameters1, response1},
                {parameters2, response2},
                {parameters3, response3},
                {parameters4, response4},
                {parameters5, response5},
                {parameters6, response6}
        };
    }

    @Test(dataProvider = "dataForFindByEditionCommand")
    public void findByEditionCommandTestParams(Map<String, String> parameters, Response expected) {
        Response actual = findByEditionCommand.execute(parameters);
        assertEquals(actual, expected);
    }
}