package com.buyalskaya.bookstorage.validator;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;

public class DataValidatorTest {
    DataValidator dataValidator;

    @BeforeClass
    public void setUp() {
        dataValidator = new DataValidator();
    }

    @DataProvider(name = "dataForIsIdValid")
    public Object[][] dataForIsIdValid() {
        return new Object[][]{
                {"1789", true},
                {"19", true},
                {"", false},
                {"0", false},
                {"-17", false},
                {null, false}
        };
    }

    @Test(dataProvider = "dataForIsIdValid")
    public void isIdValidTestParams(String id, boolean expected) {
        boolean actual = dataValidator.isIdValid(id);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "dataForIsStringValid")
    public Object[][] dataForIsStringValid() {
        return new Object[][]{
                {"World", true},
                {".", false},
                {"", false},
                {"4", false},
                {null, false}
        };
    }

    @Test(dataProvider = "dataForIsStringValid")
    public void isNameValidTestParams(String name, boolean expected) {
        boolean actual = dataValidator.isNameValid(name);
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "dataForIsStringValid")
    public void isEditionValidTestParams(String edition, boolean expected) {
        boolean actual = dataValidator.isEditionValid(edition);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "dataForIsListAuthorValid")
    public Object[][] dataForIsListAuthorValid() {
        return new Object[][]{
                {Arrays.asList("J.K.Rowling", "Victor Hugo", "Александр Сергеевич Пушкин", "S. King"), true},
                {Arrays.asList("Антуан де Сент-Экзюпери"), true},
                {Arrays.asList("А. дe Сент-Экзюпери"), true},
                {Arrays.asList("S.S.S.King", "Victor Hugo"), false},
                {Arrays.asList("Victor Hugo", ""), false},
                {Arrays.asList("Victor Hugo", " "), false},
                {Arrays.asList("Victor Hugo", "4"), false},
                {Arrays.asList("Victor Hugo", null), false},
                {new ArrayList<>(), false},
                {null, false}
        };
    }

    @Test(dataProvider = "dataForIsListAuthorValid")
    public void isListAuthorValidTestParams(List<String> author, boolean expected) {
        boolean actual = dataValidator.isAuthorValid(author);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "dataForIsAuthorValid")
    public Object[][] dataForIsAuthorValid() {
        return new Object[][]{
                {"J.K.Rowling", true},
                {"Victor Hugo", true},
                {"V.Hugo", true},
                {"S.S.S.King", false},
                {"", false},
                {"9", false},
                {null, false}
        };
    }

    @Test(dataProvider = "dataForIsAuthorValid")
    public void isAuthorValidTestParams(String author, boolean expected) {
        boolean actual = dataValidator.isAuthorValid(author);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "dataForIsYearValid")
    public Object[][] dataForIsYearValid() {
        return new Object[][]{
                {"1996", true},
                {"1996,1587", true},
                {"1876", true},
                {"2007, 2020", true},
                {"0", false},
                {"-15", false},
                {"1300", false},
                {"2015, 2016, 2017", false},
                {"2015, 2016, ", false},
                {"0015, 7810", false},
                {"2025", false},
                {"one", false},
                {"", false},
                {null, false}
        };
    }

    @Test(dataProvider = "dataForIsYearValid")
    public void isYearValidTestParams(String year, boolean expected) {
        boolean actual = dataValidator.isYearValid(year);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "dataForIsPageValid")
    public Object[][] dataForIsPageValid() {
        return new Object[][]{
                {"195", true},
                {"987", true},
                {"115,175", true},
                {"325, 175", true},
                {"0", false},
                {"-15", false},
                {"13000", false},
                {"125,130,135", false},
                {"125, 13000", false},
                {", 13000", false},
                {"15o8", false},
                {"", false},
                {null, false}
        };
    }

    @Test(dataProvider = "dataForIsPageValid")
    public void isPageValidTestParams(String page, boolean expected) {
        boolean actual = dataValidator.isPageValid(page);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "dataForIsSortFieldValid")
    public Object[][] dataForIsSortFieldValid() {
        return new Object[][]{
                {"year", true},
                {"Name", true},
                {"id", true},
                {"price", false},
                {"15", false},
                {"", false},
                {null, false}
        };
    }

    @Test(dataProvider = "dataForIsSortFieldValid")
    public void isSortFieldValidTestParams(String page, boolean expected) {
        boolean actual = dataValidator.isSortFieldValid(page);
        assertEquals(actual, expected);
    }
}