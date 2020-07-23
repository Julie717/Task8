package com.buyalskaya.bookstorage.controller.command;

import com.buyalskaya.bookstorage.controller.command.impl.*;

public enum CommandType {
    ADD(new AddCommand()),
    REMOVE_BY_ID(new RemoveByIdCommand()),
    REMOVE_BY_NAME(new RemoveByNameCommand()),
    FIND_ALL(new FindAllCommand()),
    FIND_BY_ID(new FindByIdCommand()),
    FIND_BY_NAME(new FindByNameCommand()),
    FIND_BY_AUTHOR(new FindByAuthorCommand());

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}