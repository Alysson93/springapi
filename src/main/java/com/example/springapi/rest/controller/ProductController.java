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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.springapi.domain.entity.Product;
import com.example.springapi.domain.repository.ProductRepository;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/products")
public class ProductController {
    
    @Autowired
    private ProductRepository repository;

    @GetMapping
    public List<Product> get(Product filter) {
        ExampleMatcher matcher = ExampleMatcher.matching()
        .withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING);
        @SuppressWarnings("rawtypes")
        Example example = Example.of(filter, matcher);
        @SuppressWarnings("unchecked")
        List<Product> products = repository.findAll(example);
        return products;
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable Integer id) {
        Optional<Product> product = repository.findById(id);
        if (product.isPresent()) return product.get();
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product post(@RequestBody @Valid Product product) {
        Product p = repository.save(product);
        return p;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        Optional<Product> product = repository.findById(id);
        if (product.isPresent()) repository.delete(product.get()); 
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void put(@PathVariable Integer id, @RequestBody @Valid Product product) {
        Optional<Product> p = repository.findById(id);
        if (p.isPresent()) {
            product.setId(p.get().getId());
            repository.save(product);
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

}
