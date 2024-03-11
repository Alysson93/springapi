package com.example.springapi.rest.dto;

import java.math.BigDecimal;
import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CartDTO {
    
    @NotNull(message = "Informe o código do cliente.")
    private Integer client;

    @NotNull(message = "Total do pedido é um campo obrigatório.")
    private BigDecimal total;

    private List<ItemDTO> items;

}
