package com.buyalskaya.bookstorage.controller.command.impl;

import com.buyalskaya.bookstorage.controller.command.Command;
import com.buyalskaya.bookstorage.exception.ServiceException;
import com.buyalskaya.bookstorage.model.entity.CustomBook;
import com.buyalskaya.bookstorage.model.service.BookService;
import com.buyalskaya.bookstorage.controller.Response;

import java.util.List;
import java.util.Map;

public class FindByAuthorCommand implements Command {
    private static final String PARAM_AUTHOR = "author";
    private static final String INCORRECT_MESSAGE = "Incorrect author";

    @Override
    public Response execute(Map<String, String> parameters) {
        Response response = new Response();
        if (parameters != null) {
            String author = parameters.get(PARAM_AUTHOR);
            BookService bookService = new BookService();
            try {
                List<CustomBook> books = bookService.findByAuthor(author);
                response.setCompletedSuccess(true);
                response.setBooks(books);
            } catch (ServiceException ex) {
                response.setMessage(ex.getMessage() + ex.getCause().getMessage());
            }
        } else {
            response.setMessage(INCORRECT_MESSAGE);
        }
        return response;
    }
}