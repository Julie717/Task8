package com.buyalskaya.bookstorage.validator;

import com.buyalskaya.bookstorage.model.dao.SortTag;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class DataValidator {
    private final static String REGEX_LETTER = ".*\\pL.*";
    private final static String REGEX_AUTHOR = "(\\pL[\\pL\\s]+)|(\\pL\\.(\\s)*(\\pL\\.(\\s)*)?\\pL+)";
    private final static String REGEX_YEAR = "(\\s*)\\d{4}(\\s*)(,(\\s*)\\d{4}(\\s*))?";
    private final static String REGEX_PAGE = "(\\s*)\\d{1,5}(\\s*)(,(\\s*)\\d{1,5}(\\s*))?";
    private final static long MIN_ID = 1;
    private final static long MAX_ID = Long.MAX_VALUE;
    private final static int MIN_YEAR = 1400;
    private final static int MAX_YEAR = LocalDate.now().getYear();
    private final static int MIN_PAGE = 1;
    private final static int MAX_PAGE = 10000;
    private final static String CHECK_NUMBER = "\\d+";
    private final static String COMMA = ",";

    private boolean isPositiveIntegerNumber(String number) {
        boolean isValid = false;
        if (number != null) {
            isValid = Pattern.matches(CHECK_NUMBER, number);
        }
        return isValid;
    }

    public boolean isIdValid(String id) {
        boolean isValid = isPositiveIntegerNumber(id);
        if (isValid) {
            long idLong = Long.parseLong(id);
            isValid = (idLong > MIN_ID) && (idLong <= MAX_ID);
        }
        return isValid;
    }

    public boolean isNameValid(String name) {
        boolean isValid = false;
        if (name != null && !name.isEmpty()) {
            isValid = Pattern.matches(REGEX_LETTER, name);
        }
        return isValid;
    }

    public boolean isAuthorValid(List<String> authors) {
        boolean isValid = false;
        if (authors != null) {
            isValid = authors.stream().allMatch(a -> isAuthorValid(a));
        }
        return isValid;
    }

    public boolean isAuthorValid(String author) {
        boolean isValid = false;
        if (author != null && !author.isEmpty()) {
            isValid = Pattern.matches(REGEX_AUTHOR, author);
        }
        return isValid;
    }

    public boolean isEditionValid(String edition) {
        boolean isValid = false;
        if (edition != null && !edition.isEmpty()) {
            isValid = Pattern.matches(REGEX_LETTER, edition);
        }
        return isValid;
    }

    public boolean isYearValid(String year) {
        boolean isValid = false;
        if (year != null && !year.isEmpty()) {
            isValid = Pattern.matches(REGEX_YEAR, year);
            if (isValid) {
                isValid = Arrays.stream(year.split(COMMA))
                        .map(y -> Integer.parseInt(y.trim()))
                        .allMatch(y -> y >= MIN_YEAR && y <= MAX_YEAR);
            }
        }
        return isValid;
    }

    public boolean isPageValid(String page) {
        boolean isValid = false;
        if (page != null && !page.isEmpty()) {
            isValid = Pattern.matches(REGEX_PAGE, page);
            if (isValid) {
                isValid = Arrays.stream(page.split(COMMA))
                        .map(p -> Integer.parseInt(p.trim()))
                        .allMatch(p -> p >= MIN_PAGE && p <= MAX_PAGE);
            }
        }
        return isValid;
    }

    public boolean isSortFieldValid(String sortField) {
        boolean isValid = false;
        if (sortField != null) {
            isValid = Arrays.stream(SortTag.values())
                    .anyMatch(p -> p.name().equals(sortField.toUpperCase()));
        }
        return isValid;
    }
}