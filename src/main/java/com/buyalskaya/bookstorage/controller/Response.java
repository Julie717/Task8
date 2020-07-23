package com.buyalskaya.bookstorage.controller;

import com.buyalskaya.bookstorage.model.entity.CustomBook;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Response {
    private boolean isCompletedSuccess;
    private String message = new String();
    private List<CustomBook> books = new ArrayList<>();

    public Response() {
    }

    public Response(boolean isCompletedSuccess, String message) {
        this.isCompletedSuccess = isCompletedSuccess;
        this.message = message;
    }

    public Response(boolean isCompletedSuccess, List<CustomBook> books) {
        this.isCompletedSuccess = isCompletedSuccess;
        this.books = books;
    }

    public Response(boolean isCompletedSuccess, String message, List<CustomBook> books) {
        this.isCompletedSuccess = isCompletedSuccess;
        this.message = message;
        this.books = books;
    }

    public boolean isCompletedSuccess() {
        return isCompletedSuccess;
    }

    public void setCompletedSuccess(boolean completedSuccess) {
        isCompletedSuccess = completedSuccess;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<CustomBook> getBooks() {
        return books;
    }

    public void setBooks(List<CustomBook> books) {
        this.books = books;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj == null) || (getClass() != obj.getClass())) {
            return false;
        }
        Response response = (Response) obj;
        if ((message == null ^ response.message == null) ||
                (message != null && response.message != null && !message.equals(response.message))) {
            return false;
        }
        if ((books == null ^ response.books == null) ||
                (books != null && response.books != null && !books.equals(response.books))) {
            return false;
        }
        return isCompletedSuccess == response.isCompletedSuccess;
    }

    @Override
    public int hashCode() {
        return (isCompletedSuccess ? 1 : 0) + message.hashCode() * 31 + books.hashCode();
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Response.class.getSimpleName() + "[", "]")
                .add("isCompletedSuccess=" + isCompletedSuccess)
                .add("message='" + message + "'")
                .add("books=" + books)
                .toString();
    }
}