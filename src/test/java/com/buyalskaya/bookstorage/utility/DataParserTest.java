package com.buyalskaya.bookstorage.utility;

import com.buyalskaya.bookstorage.util.DataParser;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


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
                {"", List.of("")}
        };
    }

    @Test(dataProvider = "dataForAuthorParser")
    public void authorParserTestParams(String author, List<String> expected) {
        List<String> actual = dataParser.authorParser(author);
        assertEquals(actual, expected);
    }
}