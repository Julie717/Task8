package com.buyalskaya.bookstorage.controller.command.impl;

import com.buyalskaya.bookstorage.controller.Response;
import com.buyalskaya.bookstorage.controller.command.Command;
import com.buyalskaya.bookstorage.exception.ServiceException;
import com.buyalskaya.bookstorage.model.entity.CustomBook;
import com.buyalskaya.bookstorage.model.service.BookService;

import java.util.List;
import java.util.Map;

public class FindOldBookCommand implements Command {
    @Override
    public Response execute(Map<String, String> parameters) {
        BookService bookService = new BookService();
        Response response = new Response();
        try {
            List<CustomBook> books = bookService.findOldBook();
            response.setCompletedSuccess(true);
            response.setBooks(books);
        } catch (ServiceException ex) {
            String errorMessage = ex.getMessage();
            if (ex.getCause() != null) {
                errorMessage = errorMessage + ex.getCause().getMessage();
            }
            response.setMessage(errorMessage);
        }
        return response;
    }
}