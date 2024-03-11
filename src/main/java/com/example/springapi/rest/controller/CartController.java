package com.example.springapi.rest.controller;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.springapi.domain.entity.Cart;
import com.example.springapi.domain.entity.Item;
import com.example.springapi.domain.enums.Status;
import com.example.springapi.rest.dto.CartDTO;
import com.example.springapi.rest.dto.CartResponseDTO;
import com.example.springapi.rest.dto.CartStatusDTO;
import com.example.springapi.rest.dto.ItemResponseDTO;
import com.example.springapi.service.CartService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/carts")
public class CartController {
    
    @Autowired
    private CartService service;

    @GetMapping("/{id}")
    public CartResponseDTO getById(@PathVariable Integer id) {
        return service.getCart(id)
            .map(c -> convert(c))
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado."));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer post(@RequestBody @Valid CartDTO dto) {
        Cart cart = service.save(dto);
        return cart.getId();
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStatus(@PathVariable Integer id, @RequestBody CartStatusDTO dto) {
        String status = dto.getStatus();
        service.updateStatus(id, Status.valueOf(status));
    }


    private CartResponseDTO convert(Cart cart) {
        return CartResponseDTO.builder()
        .id(cart.getId())
        .createdAt(cart.getCreatedAt().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
        .clientCpf(cart.getClient().getCpf())
        .clientName(cart.getClient().getName())
        .total(cart.getTotal())
        .status(cart.getStatus().name())
        .items(convertItems(cart.getItems()))
        .build();
    }

    private List<ItemResponseDTO> convertItems(List<Item> items) {
        if (CollectionUtils.isEmpty(items)) {
            return Collections.emptyList();
        }
        return items.stream().map(
            item -> ItemResponseDTO.builder()
            .productDescription(item.getProduct().getDescription())
            .productPrice(item.getProduct().getPrice())
            .qtd(item.getQtd())
            .build()
        ).collect(Collectors.toList());
    }
    

}
