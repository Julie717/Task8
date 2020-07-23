package com.buyalskaya.bookstorage.model.entity;

import com.buyalskaya.bookstorage.exception.LibraryException;

import java.util.*;
import java.util.stream.Collectors;

public class Library {
    private static Library instance;
    private static List<CustomBook> books;

    private Library() {
        books = new ArrayList<>();
    }

    public static Library getInstance() {
        if (instance == null) {
            instance = new Library();
        }
        return instance;
    }

    public void setBooks(List<CustomBook> books) throws LibraryException {
        //TODO It uses just for initialization of library
        if (!Library.books.isEmpty()) {
            throw new LibraryException("Library is full");
        }
        if (!isDifferentBooks(books)) {
            throw new LibraryException("Incorrect book list, which contains the same books");
        }
        Library.books = books;
    }

    private boolean isDifferentBooks(List<CustomBook> books) {
        boolean isDifferentId = books.stream()
                .collect(Collectors.groupingBy(b -> b.getBookId(), Collectors.counting()))
                .values().stream().allMatch(v -> v == 1);
        boolean isDifferentBook = isDifferentId;
        if (isDifferentId) {
            long amountDifferentBooksExceptId = books.stream()
                    .map(b -> List.of(b.getName(),
                            b.getAuthor(),
                            b.getEdition(),
                            b.getYear(),
                            b.getPage()))
                    .distinct()
                    .count();
            isDifferentBook = amountDifferentBooksExceptId == books.size();
        }
        return isDifferentBook;
    }

    public List<CustomBook> getBooks() {
        return Collections.unmodifiableList(books);
    }

    public void add(CustomBook book) throws LibraryException {
        if (books.contains(book)) {
            throw new LibraryException("This book is already in storage");
        }
        books.add(book);
    }

    public void remove(CustomBook book) throws LibraryException {
        if (!books.contains(book)) {
            throw new LibraryException("This book is absent in storage");
        }
        books.remove(book);
    }
}