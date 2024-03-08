package com.example.springapi.rest.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartResponseDTO {
    
    private Integer id;
    private String clientCpf;
    private String clientName;
    private BigDecimal total;
    private String createdAt;
    private List<ItemResponseDTO> items;

}
