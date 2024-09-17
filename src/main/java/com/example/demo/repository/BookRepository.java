package com.example.demo.repository;

import com.example.demo.xml.school.book.Book;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class BookRepository {
    private static final Map<String, Book> books = new HashMap<>(); // Change variable name to 'books'

    @PostConstruct
    public void initData() {
        // Initialize some sample books
        Book book = new Book();
        book.setName("Effective Java");
        book.setAuthor("Joshua Bloch");
        book.setPublishYear(2018);
        books.put(book.getName(), book);

        book = new Book();
        book.setName("Clean Code");
        book.setAuthor("Robert C. Martin");
        book.setPublishYear(2008);
        books.put(book.getName(), book);

        book = new Book();
        book.setName("Design Patterns");
        book.setAuthor("Erich Gamma");
        book.setPublishYear(1994);
        books.put(book.getName(), book);
    }

    public Collection<Book> listAll() {
        return books.values(); // Return all books
    }
}