package com.buyalskaya.bookstorage.controller.command;

import com.buyalskaya.bookstorage.controller.Response;

import java.util.Map;

public interface Command {
    Response execute(Map<String, String> parameters);
}