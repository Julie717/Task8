package com.buyalskaya.bookstorage.controller;

import com.buyalskaya.bookstorage.controller.command.Command;
import com.buyalskaya.bookstorage.controller.command.impl.AddCommand;
import com.buyalskaya.bookstorage.controller.command.impl.EmptyCommand;
import com.buyalskaya.bookstorage.controller.command.impl.FindByIdCommand;
import com.buyalskaya.bookstorage.controller.command.impl.FindByNameCommand;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ActionProviderTest {

    @DataProvider(name = "dataForDefineCommand")
    public Object[][] dataForDefineCommand() {
        return new Object[][]{
                {"Add", new AddCommand()},
                {"find_by_name", new FindByNameCommand()},
                {"FIND_by_ID", new FindByIdCommand()},
                {"addition", new EmptyCommand()},
                {"", new EmptyCommand()},
                {null, new EmptyCommand()}
        };
    }

    @Test(dataProvider = "dataForDefineCommand")
    public void defineCommandTestParams(String commandName, Command expected) {
        Command actual = ActionProvider.defineCommand(commandName);
        assertEquals(actual.getClass().getSimpleName(), expected.getClass().getSimpleName());
    }
}