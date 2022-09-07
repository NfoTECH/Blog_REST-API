package com.fortunate.blogrestfulapi.controller;

import com.fortunate.blogrestfulapi.exception.PostAlreadyLikedException;
import com.fortunate.blogrestfulapi.exception.PostNotFoundException;
import com.fortunate.blogrestfulapi.exception.UserNotFoundException;
import com.fortunate.blogrestfulapi.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> UserNotFoundException(UserNotFoundException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<?> PostNotFoundException(PostNotFoundException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PostAlreadyLikedException.class)
    public ResponseEntity<?> PostAlreadyLikedException(PostAlreadyLikedException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
    }
}
