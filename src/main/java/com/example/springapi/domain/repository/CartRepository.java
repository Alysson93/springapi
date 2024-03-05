package com.example.springapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springapi.domain.entity.Cart;
import com.example.springapi.domain.entity.Client;

import java.util.List;


public interface CartRepository extends JpaRepository<Cart, Integer> {


    List<Cart> findByClient(Client client);

}
