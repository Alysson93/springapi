package com.example.springapi.service;

import java.util.Optional;

import com.example.springapi.domain.entity.Cart;
import com.example.springapi.domain.enums.Status;
import com.example.springapi.rest.dto.CartDTO;

public interface CartService {
    
    Cart save(CartDTO dto);

    Optional<Cart> getCart(Integer id);

    void updateStatus(Integer id, Status status);

}
