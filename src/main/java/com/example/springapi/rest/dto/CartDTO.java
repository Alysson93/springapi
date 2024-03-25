package com.example.springapi.rest.dto;

import java.math.BigDecimal;
import java.util.List;

import com.example.springapi.validation.NotEmptyList;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CartDTO {
    
    @NotNull(message = "Informe o código do cliente.")
    private Integer client;

    @NotNull(message = "Total do pedido é um campo obrigatório.")
    private BigDecimal total;

    @NotEmptyList(message = "Pedido não pode ser finalizado sem itens.")
    private List<ItemDTO> items;

}
