package org.example.book_application.service;

import org.example.book_application.models.Book;
import org.example.book_application.repositories.BookRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*; // Static Imports
import static org.mockito.Mockito.*; // Static Imports

@ExtendWith(MockitoExtension.class)

public class BookServiceTest {

    @Mock // Mocking all the dependencies
    BookRepository bookRepository;

    @InjectMocks // Injects all required dependencies inside (Repository will be injected inside of Service)
    BookService bookService;

    @Test
    void addBookShouldAddSuccessfully(){

        /* Data Preparation */
        Book book=new Book();
        book.setId(1);
        book.setTitle("Harry Potter");
        book.setAuthor("JK Rowlings");
        book.setGenre("Fantasy");

        /* Mocking the calls if any */
        when(bookRepository.save(book)).thenReturn(book);

        /* Calling the actual methods */
        Book addedBook=bookService.add(book);

        /* Assertions */
        assertNotNull(addedBook);
        assertEquals(book.getId(),addedBook.getId());
        assertEquals(book.getTitle(),addedBook.getTitle());

    }

    @Test
    void deleteBookShouldDeleteSuccessfully(){

        Book book = new Book();
        book.setId(1);

        /* Mock findById */
        when(bookRepository.findById(1)).thenReturn(Optional.of(book));

        /* Mocking deleteById */
        doNothing().when(bookRepository).deleteById(1);

        /* Calling actual method */
        bookService.deleteBookById(1);

        /* Verification: repository methods should be called only once */
        verify(bookRepository,times(1)).deleteById(1);

    }

    @Test
    void deleteBookShouldReturnEmptyWhenNotFound(){

        /* Mocking findById to return empty in case id not exists */
        when(bookRepository.findById(1)).thenReturn(Optional.empty());

        /* Calling actual method */
        Optional<Book> result=bookService.deleteBookById(1);

        /* Assertion: result should be empty in case id not exists */
        assertTrue(result.isEmpty());

    }
}
