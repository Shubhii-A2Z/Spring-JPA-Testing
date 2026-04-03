package org.example.book_application.service;

import lombok.extern.slf4j.Slf4j;
import org.example.book_application.models.Book;
import org.example.book_application.repositories.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("bookService")
@Slf4j // automatically creating a logger for your class

public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository){
        this.bookRepository=bookRepository;
    }

    @Transactional
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
