package com.buyalskaya.bookstorage.util;

import java.util.List;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataParser {
    private static final String REGEX_COMMA = ",";

    public List<String> authorParser(String author) {
        String[] authorsArray = author.split(REGEX_COMMA);
        return Stream.of(authorsArray).map(p -> p.trim()).collect(Collectors.toList());
    }
}