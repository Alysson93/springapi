package com.example.springapi.service;

import com.example.springapi.domain.entity.Cart;
import com.example.springapi.rest.dto.CartDTO;

public interface CartService {
    
    Cart save(CartDTO dto);

}
