package com.example.springapi.service;

import org.springframework.stereotype.Service;

import com.example.springapi.domain.repository.CartRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {
    
    private final CartRepository repository;

}
