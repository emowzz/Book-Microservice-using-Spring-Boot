package com.example.booksmicroservice.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BookIdMustExistValidator.class)
public @interface BookIdMustExist {
    String message() default "Book ID is invalid.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default{};
}
