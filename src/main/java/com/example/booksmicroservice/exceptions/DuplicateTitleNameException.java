package com.example.booksmicroservice.exceptions;

public class DuplicateTitleNameException extends RuntimeException{

    public DuplicateTitleNameException()
    {
        super("Book with same title already exists. Please try with another name.");
    }

}
