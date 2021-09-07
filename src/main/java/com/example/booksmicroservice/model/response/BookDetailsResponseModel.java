package com.example.booksmicroservice.model.response;

import com.example.booksmicroservice.entity.Author;

public class BookDetailsResponseModel {

//    private String name;
    private int id;
    private String title;
    private Author author;

//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
