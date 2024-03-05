package com.example.springapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springapi.domain.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {
    
}
