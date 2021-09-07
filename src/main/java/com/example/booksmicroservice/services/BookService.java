package com.example.booksmicroservice.services;

import com.example.booksmicroservice.dto.BookDTO;
import com.example.booksmicroservice.entity.Book;
import com.example.booksmicroservice.exceptions.DuplicateTitleNameException;
import com.example.booksmicroservice.model.response.BookDetailsResponseModel;
import com.example.booksmicroservice.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;


    //get all books
    public List<BookDTO> getAllBooks()
    {
        List<BookDTO> returnBookDto = new ArrayList<>();

        List<Book> books = bookRepository.findAll();

        for (Book book : books)
        {
            BookDTO bookDto = new BookDTO();
            BeanUtils.copyProperties(book, bookDto);
            returnBookDto.add(bookDto);
        }

        return  returnBookDto;


    }

    //get single book by ID
    public BookDTO getBookById(int id)
    {

        BookDTO bookDTO = new BookDTO();
        Book book = bookRepository.findById(id);
        BeanUtils.copyProperties(book, bookDTO);

        return bookDTO;
    }

    //to post a book
    public BookDTO addBook(BookDTO bookDTO)
    {

        if (bookRepository.existsBookByTitle(bookDTO.getTitle()))
            throw new DuplicateTitleNameException();
//        bookDTO.setName("Emon");
//        return bookRepository.save(bookDTO);
        Book book = new Book();
        BeanUtils.copyProperties(bookDTO, book);

        Book createdBook = bookRepository.save(book);

        BookDTO returnDto = new BookDTO();
        BeanUtils.copyProperties(createdBook, returnDto);

        return returnDto;

    }


    //to delete a book
    public void deleteBook(int bookId) {

        this.bookRepository.deleteById(bookId);

    }

    //to update a book
    public BookDTO updateBook(BookDTO bookDTO , int bookId) {

        Book book = new Book();
        BeanUtils.copyProperties(bookDTO, book);

        Book returnBook = new Book();

        book.setId(bookId);

        returnBook = bookRepository.save(book);

        BookDTO returnDto = new BookDTO();

        BeanUtils.copyProperties(returnBook, returnDto);

        return returnDto;

    }
}
