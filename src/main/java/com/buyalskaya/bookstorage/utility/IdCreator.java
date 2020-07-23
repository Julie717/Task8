package com.buyalskaya.bookstorage.utility;

import java.util.UUID;

public class IdCreator {
    public static UUID createId() {
        return UUID.randomUUID();
    }
}