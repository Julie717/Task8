package com.buyalskaya.bookstorage.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataParser {
    private static final String REGEX_COMMA = ",";

    public List<String> authorParser(String author) {
        List<String> authors = new ArrayList<>();
        if (author != null && !author.isEmpty()) {
            authors = Stream.of(author.split(REGEX_COMMA))
                    .map(p -> p.trim())
                    .collect(Collectors.toList());
        }
        return authors;
    }

    public int[] yearParserToInt(String year) {
        int[] years = new int[]{};
        if (year != null && !year.isEmpty()) {
            years = Arrays.stream(year.split(REGEX_COMMA))
                    .mapToInt(y -> Integer.parseInt(y.trim()))
                    .sorted()
                    .toArray();
        }
        return years;
    }

    public int[] pageParserToInt(String page) {
        int[] pages = new int[]{};
        if (page != null && !page.isEmpty()) {
            pages = Arrays.stream(page.split(REGEX_COMMA))
                    .mapToInt(y -> Integer.parseInt(y.trim()))
                    .sorted()
                    .toArray();
        }
        return pages;
    }
}