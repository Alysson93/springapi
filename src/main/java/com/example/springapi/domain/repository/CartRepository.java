package com.example.springapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.springapi.domain.entity.Cart;
import com.example.springapi.domain.entity.Client;

import java.util.List;
import java.util.Optional;


public interface CartRepository extends JpaRepository<Cart, Integer> {

    // List<Cart> findByClient(Client client);

    @Query("select c from Cart c left join fetch c.items where c.id = :id")
    Optional<Cart> findByIdFetchItems(@Param("id") Integer id);

}
