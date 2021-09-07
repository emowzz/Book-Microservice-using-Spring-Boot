package com.example.booksmicroservice.repository;

import com.example.booksmicroservice.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    public Book findById(int id);

//    public Book findByTitle(String title);

    boolean existsBookByTitle(String title);
}
