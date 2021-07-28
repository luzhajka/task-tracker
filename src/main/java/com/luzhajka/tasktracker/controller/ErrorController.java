package com.luzhajka.tasktracker.controller;

import com.luzhajka.tasktracker.exceptions.EntityNotFoundExceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception e) {

        if (e instanceof EntityNotFoundExceptions) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
