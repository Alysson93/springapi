package com.example.springapi.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.springapi.domain.entity.Cart;
import com.example.springapi.rest.dto.CartDTO;
import com.example.springapi.service.CartService;

@RestController
@RequestMapping("/carts")
public class CartController {
    
    @Autowired
    private CartService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer post(@RequestBody CartDTO dto) {
        Cart cart = service.save(dto);
        return cart.getId();
    }

}
