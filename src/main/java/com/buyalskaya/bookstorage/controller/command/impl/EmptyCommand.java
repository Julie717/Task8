package com.buyalskaya.bookstorage.controller.command.impl;

import com.buyalskaya.bookstorage.controller.command.Command;
import com.buyalskaya.bookstorage.controller.Response;

import java.util.Map;

public class EmptyCommand implements Command {
    private static final String ERROR_MESSAGE = "Incorrect command";

    @Override
    public Response execute(Map<String, String> parameters) {
        Response response = new Response();
        response.setCompletedSuccess(false);
        response.setMessage(ERROR_MESSAGE);
        return response;
    }
}