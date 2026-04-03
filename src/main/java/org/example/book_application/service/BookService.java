package org.example.book_application.service;

import org.example.book_application.models.Book;
import org.example.book_application.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("bookService")
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository){
        this.bookRepository=bookRepository;
    }

    public Book add(@Qualifier("Book") Book book) {
        Book content=bookRepository.save(book);
        return content;
    }

    public Book getBookByName(String name) {
        Book content=bookRepository.findByTitle(name);
        return content;
    }

    public List<Book> getAllBooks() {
        List<Book> books=bookRepository.getAll();
        return books;
    }
}
