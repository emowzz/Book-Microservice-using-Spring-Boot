package com.example.booksmicroservice.validators;

import com.example.booksmicroservice.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class BookIdMustExistValidator implements ConstraintValidator<BookIdMustExist, Integer> {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {

        return this.bookRepository.existsById(integer);
//        return this.bookRepository.findById(integer).isPresent();
    }
}
