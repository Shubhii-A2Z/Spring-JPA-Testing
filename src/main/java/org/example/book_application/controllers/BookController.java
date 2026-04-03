package org.example.book_application.controllers;

import lombok.extern.slf4j.Slf4j;
import org.example.book_application.models.Book;
import org.example.book_application.service.BookService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Slf4j // automatically creating a logger for your class

public class BookController {

    private final BookService bookService;

    public BookController(@Qualifier("bookService") BookService bookService){
        this.bookService=bookService;
    }

    @PostMapping("/add")
    public ResponseEntity<Book> addBook(@RequestBody Book book){
        Book savedBook=bookService.add(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
    }

    @GetMapping("/book/{name}")
    public ResponseEntity<Book> getBookByName(@PathVariable("name") String name){
        Book bookByName=bookService.getBookByName(name);
        if(bookByName==null){
            log.warn("No such book exists!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(bookByName);
    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooks(){
        List<Book> books=bookService.getAllBooks();
        if(books.isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return ResponseEntity.status(HttpStatus.OK).body(books);
    }
}
