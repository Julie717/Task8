package com.buyalskaya.bookstorage.model.dao;

public enum SortTag {
    ID(ColumnName.BOOK_ID),
    NAME(ColumnName.BOOK_NAME),
    AUTHOR(ColumnName.BOOK_AUTHOR),
    EDITION(ColumnName.BOOK_EDITION),
    YEAR(ColumnName.BOOK_YEAR),
    PAGE(ColumnName.BOOK_PAGE);

    private String columnName;

    SortTag(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }
}