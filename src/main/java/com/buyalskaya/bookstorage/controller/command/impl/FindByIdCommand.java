package com.buyalskaya.bookstorage.controller.command.impl;

import com.buyalskaya.bookstorage.controller.command.Command;
import com.buyalskaya.bookstorage.exception.ServiceException;
import com.buyalskaya.bookstorage.model.entity.CustomBook;
import com.buyalskaya.bookstorage.model.service.BookService;
import com.buyalskaya.bookstorage.controller.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FindByIdCommand implements Command {
    private static final String PARAM_ID = "id";
    private static final String INCORRECT_MESSAGE = "Incorrect book id";

    @Override
    public Response execute(Map<String, String> parameters) {
        Response response = new Response();
        if (parameters != null) {
            String id = parameters.get(PARAM_ID);
            BookService bookService = new BookService();
            try {
                List<CustomBook> books = new ArrayList<>();
                books.add(bookService.findById(id));
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