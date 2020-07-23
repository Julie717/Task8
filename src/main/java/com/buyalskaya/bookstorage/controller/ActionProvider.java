package com.buyalskaya.bookstorage.controller;

import com.buyalskaya.bookstorage.controller.command.Command;
import com.buyalskaya.bookstorage.controller.command.CommandType;
import com.buyalskaya.bookstorage.controller.command.impl.EmptyCommand;

import java.util.Arrays;

public class ActionProvider {
    public static Command defineCommand(String commandName) {
        Command command = new EmptyCommand();
        if (commandName != null) {
            command = Arrays.stream(CommandType.values())
                    .filter(p -> p.name().equals(commandName.toUpperCase()))
                    .findFirst()
                    .map(CommandType::getCommand).orElse(command);
        }
        return command;
    }
}