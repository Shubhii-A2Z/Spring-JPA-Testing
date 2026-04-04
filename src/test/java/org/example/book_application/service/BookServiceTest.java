package org.example.book_application.service;

import org.example.book_application.models.Book;
import org.example.book_application.repositories.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
}
