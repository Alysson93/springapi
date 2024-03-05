package com.example.springapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.springapi.domain.entity.Client;
import java.util.List;


public interface ClientRepository extends JpaRepository<Client, Integer> {
    
    List<Client> findByNameLike(String name);

    List<Client> findByNameOrId(String name, Integer id);

    Client findOneByName(String name);

    boolean existsByName(String name);

    @Modifying
    void deleteByName(String name);

    @Query("select c from Client c left join fetch c.carts where c.id = :id")
    Client findClientFetchCarts(@Param("id") Integer id);

}
