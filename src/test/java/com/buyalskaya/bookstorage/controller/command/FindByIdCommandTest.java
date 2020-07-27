package com.buyalskaya.bookstorage.controller.command;

import com.buyalskaya.bookstorage.controller.command.impl.FindByIdCommand;
import com.buyalskaya.bookstorage.model.entity.CustomBook;
import com.buyalskaya.bookstorage.controller.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;

import static org.testng.Assert.assertEquals;

public class FindByIdCommandTest {
    FindByIdCommand findByIdCommand;

    @BeforeClass
    public void setUp() {
        findByIdCommand = new FindByIdCommand();
    }

    @DataProvider(name = "dataForFindByIdCommand")
    public Object[][] dataForFindByIdCommand() {
        Map<String, String> parameters1 = new HashMap<>();
        parameters1.put("id", "17");
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
        response1.setBooks(books);
        Map<String, String> parameters2 = new HashMap<>();
        parameters2.put("name", "Notre-Dam");
        Response response2 = new Response(false, "Incorrect book id");
        Map<String, String> parameters3 = new HashMap<>();
        parameters3.put("id", "1547");
        Response response3 = new Response(false, "DaoException was found: The book isn't found");
        Map<String, String> parameters4 = new HashMap<>();
        parameters4.put("id", "");
        Response response4 = new Response(false, "Incorrect book id");
        Map<String, String> parameters5 = null;
        Response response5 = new Response(false, "Incorrect book id");
        return new Object[][]{
                {parameters1, response1},
                {parameters2, response2},
                {parameters3, response3},
                {parameters4, response4},
                {parameters5, response5}
        };
    }

    @Test(dataProvider = "dataForFindByIdCommand")
    public void findByIdCommandTestParams(Map<String, String> parameters, Response expected) {
        Response actual = findByIdCommand.execute(parameters);
        assertEquals(actual, expected);
    }
}