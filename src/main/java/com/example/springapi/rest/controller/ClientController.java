package com.example.springapi.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.springapi.domain.entity.Client;
import com.example.springapi.domain.repository.ClientRepository;


@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientRepository repository;
    
    @GetMapping
    public List<Client> get() {
        List<Client> clients = repository.findAll();
        return clients;
    }

    @GetMapping("/{id}")
    public Client getById(@PathVariable Integer id) {
        Optional<Client> client = repository.findById(id);
        if (client.isPresent()) return client.get();
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client post(@RequestBody Client client) {
        Client savedClient = repository.save(client);
        return savedClient;
    }

   

}
