package com.buyalskaya.bookstorage.model.entity;

import java.util.List;
import java.util.StringJoiner;

public class CustomBook extends CustomEntity {
    private long bookId;
    private String name;
    private List<String> author;
    private String edition;
    private int publishingYear;
    private int numberOfPages;

    public CustomBook() {
    }

    public CustomBook(String name, List<String> author, String edition,
                      int publishingYear, int numberOfPages) {
        this.name = name;
        this.author = author;
        this.edition = edition;
        this.publishingYear = publishingYear;
        this.numberOfPages = numberOfPages;
    }

    public CustomBook(long bookId, String name, List<String> author, String edition,
                      int publishingYear, int numberOfPages) {
        this.bookId = bookId;
        this.name = name;
        this.author = author;
        this.edition = edition;
        this.publishingYear = publishingYear;
        this.numberOfPages = numberOfPages;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public long getBookId() {
        return bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getAuthor() {
        return author;
    }

    public void setAuthor(List<String> author) {
        this.author = author;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public int getPublishingYear() {
        return publishingYear;
    }

    public void setPublishingYear(int publishingYear) {
        this.publishingYear = publishingYear;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj == null) || (getClass() != obj.getClass())) {
            return false;
        }
        CustomBook book = (CustomBook) obj;
        if ((name == null ^ book.name == null) ||
                (name != null && book.name != null && !name.equals(book.name))) {
            return false;
        }
        if ((author == null ^ book.author == null) ||
                (author != null && book.author != null && !author.equals(book.author))) {
            return false;
        }
        if ((edition == null ^ book.edition == null) ||
                (edition != null && book.edition != null && !edition.equals(book.edition))) {
            return false;
        }
        return (bookId == book.bookId) && (publishingYear == book.publishingYear) && (numberOfPages == book.numberOfPages);
    }

    @Override
    public int hashCode() {
        return (int) bookId + name.hashCode() + author.hashCode() + edition.hashCode() + publishingYear + numberOfPages * 31;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CustomBook.class.getSimpleName() + "[", "]")
                .add("bookId=" + bookId)
                .add("name='" + name + "'")
                .add("author=" + author)
                .add("edition='" + edition + "'")
                .add("year=" + publishingYear)
                .add("page=" + numberOfPages)
                .toString();
    }
}