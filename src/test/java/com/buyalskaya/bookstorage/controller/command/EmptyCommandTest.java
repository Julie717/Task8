package com.buyalskaya.bookstorage.controller.command;

import com.buyalskaya.bookstorage.controller.command.impl.EmptyCommand;
import com.buyalskaya.bookstorage.controller.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;

import static org.testng.Assert.*;

public class EmptyCommandTest {
    EmptyCommand emptyCommand;

    @BeforeClass
    public void setUp() {
        emptyCommand = new EmptyCommand();
    }

    @Test
    public void emptyCommandTest() {
        Response actual = emptyCommand.execute(new HashMap<>());
        Response expected = new Response(false, "Incorrect command");
        assertEquals(actual, expected);
    }
}