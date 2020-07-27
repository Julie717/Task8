package com.buyalskaya.bookstorage.validator;

import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;

public class DataValidator {
    private final static String REGEX_LETTER = ".*\\pL.*";
    private final static String REGEX_AUTHOR = "(\\pL[\\pL\\s]+)|(\\pL\\.(\\s)*(\\pL\\.(\\s)*)?\\pL+)";
    private final static long MIN_ID = 1;
    private final static long MAX_ID = Long.MAX_VALUE;
    private final static int MIN_YEAR = 1400;
    private final static int MAX_YEAR = LocalDate.now().getYear();
    private final static int MIN_PAGE = 1;
    private final static int MAX_PAGE = 10000;
    private final static String CHECK_NUMBER = "\\d+";

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
        if (isPositiveIntegerNumber(year)) {
            int yearNumber = Integer.parseInt(year);
            isValid = (yearNumber >= MIN_YEAR && yearNumber <= MAX_YEAR);
        }
        return isValid;
    }

    public boolean isPageValid(String page) {
        boolean isValid = false;
        if (isPositiveIntegerNumber(page)) {
            int pageNumber = Integer.parseInt(page);
            isValid = (pageNumber >= MIN_PAGE && pageNumber <= MAX_PAGE);
        }
        return isValid;
    }
}