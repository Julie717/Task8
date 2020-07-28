package com.buyalskaya.bookstorage.controller.command;

import com.buyalskaya.bookstorage.controller.command.impl.*;

public enum CommandType {
    ADD(new AddCommand()),
    REMOVE_BY_ID(new RemoveByIdCommand()),
    REMOVE_BY_NAME(new RemoveByNameCommand()),
    FIND_ALL(new FindAllCommand()),
    FIND_BY_ID(new FindByIdCommand()),
    FIND_BY_NAME(new FindByNameCommand()),
    FIND_BY_AUTHOR(new FindByAuthorCommand()),
    FIND_BY_EDITION(new FindByEditionCommand()),
    FIND_BY_YEAR(new FindByYearCommand()),
    FIND_BY_PAGE(new FindByPageCommand()),
    FIND_OLD_BOOK(new FindOldBookCommand()),
    SORT_BY_TAG(new SortByTagCommand());

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}