package com.buyalskaya.bookstorage.controller;

import com.buyalskaya.bookstorage.controller.command.Command;

import java.util.Map;

public class BookController {
    private static BookController instance;

    private BookController() {
    }

    public static BookController getInstance() {
        if (instance == null) {
            instance = new BookController();
        }
        return instance;
    }

    public Response processRequest(String commandName, Map<String, String> parameters) {
        Command command = ActionProvider.defineCommand(commandName);
        Response response = command.execute(parameters);
        return response;
    }
}