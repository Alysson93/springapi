package com.example.springapi.exceptions;

public class CartNotFoundException extends RuntimeException {
    
    public CartNotFoundException() {
        super("Pedido não encontrado.");
    }

}
