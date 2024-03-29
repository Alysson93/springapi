package com.example.springapi.rest;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

public class APIErrors {
    
    @Getter
    private List<String> errors;

    public APIErrors(String message) {
        this.errors = Arrays.asList(message);
    }

    public APIErrors(List<String> errors) {
        this.errors = errors;
    }

}
