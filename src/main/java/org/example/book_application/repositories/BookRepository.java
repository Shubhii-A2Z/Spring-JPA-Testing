package org.example.book_application.repositories;

import org.example.book_application.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
    public Book findByTitle(String name); // This will automatically write internal query

    @Query("Select b from Book b")
    List<Book> getAll();
}
