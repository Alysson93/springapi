package com.example.springapi.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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

}
