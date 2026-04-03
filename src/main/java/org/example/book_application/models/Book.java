package org.example.book_application.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE) // auto-increment
    private Integer id;

//    @Column(nullable = false)
    private String title;

//    @Column
    private String author;

//    @Column
    private String genre;

}
