package com.example.springapi.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springapi.service.CartService;

@RestController
@RequestMapping("/carts")
public class CartController {
    
    @Autowired
    private CartService service;

}
