package com.example.springapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springapi.domain.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    
}
