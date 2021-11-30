package com.test.warehouses.controller;

import com.test.warehouses.exception.NotFoundException;
import com.test.warehouses.model.RestErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class ControllerExceptionHandler {

    public static final String NOT_FOUND_ENTITY_MESSAGE = "Not found entity";

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<RestErrorMessage> handleNotFoundException(NotFoundException ex) {
        List<String> errorMessages = Collections.singletonList(ex.getMessage());
        return new ResponseEntity<>(RestErrorMessage.builder()
                .message(NOT_FOUND_ENTITY_MESSAGE)
                .errors(errorMessages).build(), HttpStatus.BAD_REQUEST);
    }
}
