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
import java.util.Optional;

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

    public Optional<Book> deleteBookById(Integer id){
        Optional<Book> bookToBeDeleted=bookRepository.findById(id);
        if(bookToBeDeleted.isEmpty()) return Optional.empty();
        bookRepository.deleteById(id);
        return bookToBeDeleted;
    }
}
