package com.example.booksmicroservice.exceptions;

import com.example.booksmicroservice.model.ApiResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestControllerAdvice
public class GlobalRestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        final List<String> errors = new ArrayList<String>();

        for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + " " + error.getDefaultMessage());
        }

        for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + " " + error.getDefaultMessage());
        }


//        return handleExceptionInternal(ex, errorMap, headers, HttpStatus.BAD_REQUEST, request);

        return new ResponseEntity<>(new ApiResponse("BAD REQUEST", errors), HttpStatus.BAD_REQUEST);

    }



    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest webRequest)
    {

        List<String> errors = new ArrayList<>();

        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();

        for (ConstraintViolation<?> constraintViolation : constraintViolations)
        {
            errors.add(constraintViolation.getMessage());
        }

        return new ResponseEntity<>(new ApiResponse("BAD REQUEST", errors), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({DuplicateTitleNameException.class})
    public ResponseEntity<Object> handleDuplicateTitleNameException(DuplicateTitleNameException ex, WebRequest webRequest)
    {

        List<String> errors = new ArrayList<>();

        errors.add(ex.getMessage());

        return new ResponseEntity<>(new ApiResponse("DUPLICATE DATA", errors), HttpStatus.BAD_REQUEST);
    }
}
