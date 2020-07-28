package com.buyalskaya.bookstorage.parser;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class DataParserTest {
    DataParser dataParser;

    @BeforeClass
    public void setUp() {
        dataParser = new DataParser();
    }

    @DataProvider(name = "dataForAuthorParser")
    public Object[][] dataForAuthorParser() {
        return new Object[][]{
                {"Mikhail Bulgakov", List.of("Mikhail Bulgakov")},
                {"James Kurose, Keith Ross", List.of("James Kurose", "Keith Ross")},
                {"J.Kurose, K.Ross", List.of("J.Kurose", "K.Ross")},
                {"S.L.Garfinkel, R.H.Grunspan", List.of("S.L.Garfinkel", "R.H.Grunspan")},
                {"", new ArrayList<>()},
                {null, new ArrayList<>()}
        };
    }

    @Test(dataProvider = "dataForAuthorParser")
    public void authorParserTestParams(String author, List<String> expected) {
        List<String> actual = dataParser.authorParser(author);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "dataForYearParserToInt")
    public Object[][] dataForYearParserToInt() {
        return new Object[][]{
                {"2017,2020", new int[]{2017, 2020}},
                {"2020, 2014", new int[]{2014, 2020}},
                {"2000", new int[]{2000}},
                {"  1987", new int[]{1987}},
                {"", new int[]{}},
                {null, new int[]{}}
        };
    }

    @Test(dataProvider = "dataForYearParserToInt")
    public void yearParserToIntTestParams(String year, int[] expected) {
        int[] actual = dataParser.yearParserToInt(year);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "dataForPageParserToInt")
    public Object[][] dataForPageParserToInt() {
        return new Object[][]{
                {"117,445", new int[]{117, 445}},
                {"1251, 145", new int[]{145, 1251}},
                {"610", new int[]{610}},
                {"  378", new int[]{378}},
                {"", new int[]{}},
                {null, new int[]{}}
        };
    }

    @Test(dataProvider = "dataForPageParserToInt")
    public void pageParserToIntTestParams(String page, int[] expected) {
        int[] actual = dataParser.yearParserToInt(page);
        assertEquals(actual, expected);
    }
}