package com.luzhajka.tasktracker.controller;

import com.luzhajka.tasktracker.exceptions.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.WebRequest;


@ControllerAdvice
public class ErrorController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorController.class);

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handleException(EntityNotFoundException e) {
        LOGGER.error("Получено исключение", e);
        return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @InitBinder
    private void initBinder(WebDataBinder binder, WebRequest webRequest) {
        LOGGER.info("Входящий запрос: ", webRequest);
    }
}
