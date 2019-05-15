package com.example.exceptions;


import com.example.model.Error;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    //Constraint Exception Handling
    @org.springframework.web.bind.annotation.ExceptionHandler({ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleConstraintViolation(
            ConstraintViolationException constraintViolationException, WebRequest request) {

        List<String> errors = new ArrayList<>();

        for (ConstraintViolation<?> violation :
                constraintViolationException.getConstraintViolations()) {

            errors.add(violation.getRootBeanClass().getName() + " " +
                    violation.getPropertyPath() + ": " + violation.getMessage());
        }

        Error err =
                new Error(new Date(),HttpStatus.BAD_REQUEST, errors);

        return new ResponseEntity<Object>(
                err, new HttpHeaders(), err.getStatus());

    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        List<String> errors = new ArrayList<>();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }

        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }

        Error apiError =
                new Error(new Date(),HttpStatus.BAD_REQUEST, errors);

        return handleExceptionInternal(
                ex, apiError, headers, apiError.getStatus(), request);


    }
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            NoHandlerFoundException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        List<String> errors = new ArrayList<>();

        Error apiError =
                new Error(new Date(),HttpStatus.NOT_FOUND, errors);

        return handleExceptionInternal(ex, apiError, headers, status, request);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({ContactNotFoundException.class})

    protected ResponseEntity<Error> handleContactNotFoundException(
            ContactNotFoundException ex, WebRequest request) {

        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());

        Error error= new Error(new Date(),HttpStatus.NOT_FOUND,errors);
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({RequestNotFoundException.class})

    protected ResponseEntity<Error> handleRequestNotFoundException(
            ContactNotFoundException ex, WebRequest request) {

        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());

        Error error= new Error(new Date(),HttpStatus.NOT_FOUND,errors);
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

}
