package com.buyalskaya.bookstorage.controller.command.impl;

import com.buyalskaya.bookstorage.controller.Response;
import com.buyalskaya.bookstorage.controller.command.Command;
import com.buyalskaya.bookstorage.exception.ServiceException;
import com.buyalskaya.bookstorage.model.entity.CustomBook;
import com.buyalskaya.bookstorage.model.service.BookService;

import java.util.List;
import java.util.Map;

public class FindByPageCommand  implements Command {
    private static final String PARAM_PAGE = "page";
    private static final String INCORRECT_MESSAGE = "Incorrect book page";

    @Override
    public Response execute(Map<String, String> parameters) {
        Response response = new Response();
        if (parameters != null) {
            String page = parameters.get(PARAM_PAGE);
            BookService bookService = new BookService();
            try {
                List<CustomBook> books = bookService.findByPage(page);
                response.setCompletedSuccess(true);
                response.setBooks(books);
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