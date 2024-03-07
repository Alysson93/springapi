package com.example.springapi.rest.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

@Data
public class CartDTO {
    
    private Integer client;
    private BigDecimal total;
    private List<ItemDTO> items;

}
