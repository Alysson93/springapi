package com.example.springapi.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public List<Client> get(Client filter) {
        ExampleMatcher matcher = ExampleMatcher
        .matching().withIgnoreCase()
        .withStringMatcher(StringMatcher.CONTAINING);
        @SuppressWarnings("rawtypes")
        Example example = Example.of(filter, matcher);
        @SuppressWarnings("unchecked")
        List<Client> clients = repository.findAll(example);
        return clients;
    }

    @GetMapping("/{id}")
    public Client getById(@PathVariable Integer id) {
        Optional<Client> client = repository.findById(id);
        if (client.isPresent()) return client.get();
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado.");
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client post(@RequestBody Client client) {
        Client savedClient = repository.save(client);
        return savedClient;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        Optional<Client> client = repository.findById(id);
        if (client.isPresent()) {
            repository.delete(client.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado.");
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void put(@PathVariable Integer id, @RequestBody Client client) {
        Optional<Client> c = repository.findById(id);
        if (c.isPresent()) {
            client.setId(c.get().getId());
            repository.save(client);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado.");
        }
    }

   

}
