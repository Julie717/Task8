package com.buyalskaya.bookstorage.controller.command.impl;

import com.buyalskaya.bookstorage.controller.command.Command;
import com.buyalskaya.bookstorage.exception.ServiceException;
import com.buyalskaya.bookstorage.model.service.BookService;
import com.buyalskaya.bookstorage.controller.Response;

import java.util.Map;

public class AddCommand implements Command {
    private static final String PARAM_NAME = "name";
    private static final String PARAM_AUTHOR = "author";
    private static final String PARAM_EDITION = "edition";
    private static final String PARAM_YEAR = "year";
    private static final String PARAM_PAGE = "page";
    private static final String CORRECT_MESSAGE = "The book was added";
    private static final String INCORRECT_MESSAGE = "Incorrect parameters";

    @Override
    public Response execute(Map<String, String> parameters) {
        Response response = new Response();
        if (parameters != null) {
            String name = parameters.get(PARAM_NAME);
            String author = parameters.get(PARAM_AUTHOR);
            String edition = parameters.get(PARAM_EDITION);
            String year = parameters.get(PARAM_YEAR);
            String page = parameters.get(PARAM_PAGE);
            BookService bookService = new BookService();
            try {
                bookService.bookAdd(name, author, edition, year, page);
                response.setCompletedSuccess(true);
                response.setMessage(CORRECT_MESSAGE);
            } catch (ServiceException ex) {
                String errorMessage = ex.getMessage();
                if (ex.getCause() != null) {
                    errorMessage = errorMessage + ex.getCause().getMessage();
                }
                response.setMessage(errorMessage);
            }
        } else {
            response.setMessage(INCORRECT_MESSAGE);
        }
        return response;
    }
}