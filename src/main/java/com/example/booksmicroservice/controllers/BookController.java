package com.example.booksmicroservice.controllers;

import com.example.booksmicroservice.dto.BookDTO;
import com.example.booksmicroservice.entity.Book;
import com.example.booksmicroservice.model.ApiResponse;
import com.example.booksmicroservice.model.request.BookDetailsRequestModel;
import com.example.booksmicroservice.model.response.BookDetailsResponseModel;
import com.example.booksmicroservice.services.BookService;
import com.example.booksmicroservice.validators.BookIdMustExist;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@Validated
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping(value = "/books")
    public ResponseEntity<ApiResponse> getBooks()
    {


        List<BookDetailsResponseModel> books = new ArrayList<>();
        List<BookDTO> bookDTO = bookService.getAllBooks();

        for (BookDTO bookDTO1 : bookDTO)
        {
            BookDetailsResponseModel book = new BookDetailsResponseModel();
            BeanUtils.copyProperties(bookDTO1, book);
            books.add(book);
        }

        return new ResponseEntity<>(new ApiResponse("Showing data successfully", books), HttpStatus.OK);
    }

    @GetMapping(value = "/books/{id}", produces = "application/json")
    public ResponseEntity<ApiResponse> getBook(@PathVariable("id") @BookIdMustExist int id)
    {
        BookDTO bookDTO = bookService.getBookById(id);

        BookDetailsResponseModel bookDetailsResponseModel = new BookDetailsResponseModel();

        BeanUtils.copyProperties(bookDTO, bookDetailsResponseModel);

        return new ResponseEntity<>(new ApiResponse("Showing data successfully", bookDetailsResponseModel), HttpStatus.OK);
    }

    @PostMapping(value = "/books", consumes = "application/json")
    public ResponseEntity<ApiResponse> addBook(@RequestBody @Valid BookDetailsRequestModel bookDetailsRequestModel)
    {
        BookDTO bookDTO = new BookDTO();

        BeanUtils.copyProperties(bookDetailsRequestModel, bookDTO);

        BookDTO responseService = bookService.addBook(bookDTO);

        BookDetailsResponseModel bookDetailsResponseModel = new BookDetailsResponseModel();

        BeanUtils.copyProperties(responseService, bookDetailsResponseModel);

        return new ResponseEntity<>(new ApiResponse("Posted Successfully", bookDetailsResponseModel), HttpStatus.OK);
    }

    @DeleteMapping(value = "/books/{bookId}")
    public ResponseEntity<ApiResponse> deleteBook(@PathVariable("bookId") @BookIdMustExist int bookId)
    {
        this.bookService.deleteBook(bookId);

        return new ResponseEntity(new ApiResponse("Deleted Successfully"), HttpStatus.OK);
    }

    @PutMapping(value = "/books/{bookId}")
    public ResponseEntity<ApiResponse> updateBook(@RequestBody @Valid BookDetailsRequestModel bookDetailsRequestModel, @PathVariable("bookId") @BookIdMustExist int bookId)
    {
        BookDTO bookDTO = new BookDTO();
        BeanUtils.copyProperties(bookDetailsRequestModel, bookDTO);

        BookDTO responseService = bookService.updateBook(bookDTO, bookId);

        BookDetailsResponseModel bookDetailsResponseModel = new BookDetailsResponseModel();

        BeanUtils.copyProperties(responseService, bookDetailsResponseModel);


        return new ResponseEntity<>(new ApiResponse("Updated Successfully", bookDetailsResponseModel), HttpStatus.OK);
    }

}
