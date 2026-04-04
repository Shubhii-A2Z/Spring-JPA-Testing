package org.example.book_application.controllers;

import lombok.extern.slf4j.Slf4j;
import org.example.book_application.models.Book;
import org.example.book_application.service.BookService;
import org.example.book_application.utils.ResponseHandler;
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
    public ResponseEntity<?> addBook(@RequestBody Book book){
        Book savedBook=bookService.add(book);
        return ResponseHandler.generateResponse("Successfully added book",HttpStatus.CREATED,savedBook);
    }

    @GetMapping("/book/{name}")
    public ResponseEntity<?> getBookByName(@PathVariable("name") String name){
        Book bookByName=bookService.getBookByName(name);
        if(bookByName==null){
            log.warn("No such book exists!");
            return ResponseHandler.generateResponse("No such book exists!",HttpStatus.NOT_FOUND,null);
        }
        return ResponseHandler.generateResponse("Success",HttpStatus.OK,bookByName);
    }

    @GetMapping("/books")
    public ResponseEntity<?> getBooks(){
        List<Book> books=bookService.getAllBooks();
        if(books.isEmpty()){
            log.warn("No content");
            return ResponseHandler.generateResponse("No such book exists!",HttpStatus.NO_CONTENT,null);
        }
        return ResponseHandler.generateResponse("Fetched All Books",HttpStatus.OK,books);
    }
}
