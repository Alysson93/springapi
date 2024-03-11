package com.example.springapi.rest.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.springapi.exceptions.CartNotFoundException;
import com.example.springapi.exceptions.RegraNegocioException;
import com.example.springapi.rest.APIErrors;

@RestControllerAdvice
public class AppControllerAdvice {
    
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public APIErrors handleRegraNegocioException(RegraNegocioException exception) {
        String message = exception.getMessage();
        return new APIErrors(message);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public APIErrors handleCartNotFoundException(CartNotFoundException exception) {
        return new APIErrors(exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public APIErrors handleNotValidException(MethodArgumentNotValidException exception) {
        List<String> errors = exception.getBindingResult().getAllErrors()
        .stream().map(e -> e.getDefaultMessage())
        .collect(Collectors.toList());
        return new APIErrors(errors);
    }

}
