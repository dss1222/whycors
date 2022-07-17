package com.example.whycors.global.exceptionhandler;

import com.example.whycors.global.exceptionhandler.ex.PostException;
import com.example.whycors.global.exceptionhandler.ex.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ErrorResponse> handlerUserException(UserException e) {
        return new ResponseEntity<>(new ErrorResponse("USER", e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PostException.class)
    public ResponseEntity<ErrorResponse> handlerPostException(UserException e) {
        return new ResponseEntity<>(new ErrorResponse("POST", e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
