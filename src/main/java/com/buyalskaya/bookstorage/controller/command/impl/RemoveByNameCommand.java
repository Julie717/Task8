package com.buyalskaya.bookstorage.controller.command.impl;

import com.buyalskaya.bookstorage.controller.command.Command;
import com.buyalskaya.bookstorage.exception.ServiceException;
import com.buyalskaya.bookstorage.model.service.BookService;
import com.buyalskaya.bookstorage.controller.Response;

import java.util.Map;

public class RemoveByNameCommand implements Command {
    private static final String PARAM_NAME = "name";
    private static final String CORRECT_MESSAGE = "The book(-s) was removed";
    private static final String INCORRECT_MESSAGE = "Incorrect book name";

    @Override
    public Response execute(Map<String, String> parameters) {
        Response response = new Response();
        if (parameters != null) {
            String name = parameters.get(PARAM_NAME);
            BookService bookService = new BookService();
            try {
                bookService.removeByName(name);
                response.setCompletedSuccess(true);
                response.setMessage(CORRECT_MESSAGE);
            } catch (ServiceException ex) {
                response.setMessage(ex.getMessage() + ex.getCause().getMessage());
            }
        } else {
            response.setMessage(INCORRECT_MESSAGE);
        }
        return response;
    }
}