package com.buyalskaya.bookstorage.parser;

import java.util.Arrays;
import java.util.List;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataParser {
    private static final String REGEX_COMMA = ",";

    public List<String> authorParser(String author) {
        String[] authorsArray = author.split(REGEX_COMMA);
        return Stream.of(authorsArray).map(p -> p.trim()).collect(Collectors.toList());
    }

    public int[] yearParserToInt(String year) {
        int[] years = Arrays.stream(year.split(REGEX_COMMA))
                .mapToInt(y -> Integer.parseInt(y.trim())).sorted().toArray();
        return years;
    }

    public int[] pageParserToInt(String page) {
        int[] years = Arrays.stream(page.split(REGEX_COMMA))
                .mapToInt(y -> Integer.parseInt(y.trim())).sorted().toArray();
        return years;
    }
}