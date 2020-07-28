package com.buyalskaya.bookstorage.controller.command;

import com.buyalskaya.bookstorage.controller.Response;
import com.buyalskaya.bookstorage.controller.command.impl.FindOldBookCommand;
import com.buyalskaya.bookstorage.model.entity.CustomBook;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class FindOldBookCommandTest {
    FindOldBookCommand findOldBook = new FindOldBookCommand();

    @Test
    public void findOldBookTestPositive() {
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
        CustomBook book = new CustomBook(bookId, name,
                author, edition, year, page);
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
        Response actual = findOldBook.execute(parameters);
        assertEquals(actual, expected);
    }
}