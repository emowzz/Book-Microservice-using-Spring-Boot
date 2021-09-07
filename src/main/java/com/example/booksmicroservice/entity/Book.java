package com.example.booksmicroservice.entity;

import com.example.booksmicroservice.dto.BookDTO;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "books")
public class Book extends BookDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private int id;

    private String name;

    @NotEmpty(message = "Title can not be empty")
    private String title;

    @OneToOne(cascade = CascadeType.ALL)
    private Author author;

    public Book(int id, String name, String title, Author author) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.author = author;
    }

    public Book()
    {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
