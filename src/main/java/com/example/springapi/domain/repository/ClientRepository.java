package com.example.springapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.example.springapi.domain.entity.Client;
import java.util.List;


public interface ClientRepository extends JpaRepository<Client, Integer> {
    
    List<Client> findByNameLike(String name);

    List<Client> findByNameOrId(String name, Integer id);

    Client findOneByName(String name);

    boolean existsByName(String name);

    @Modifying
    void deleteByName(String name);

}
