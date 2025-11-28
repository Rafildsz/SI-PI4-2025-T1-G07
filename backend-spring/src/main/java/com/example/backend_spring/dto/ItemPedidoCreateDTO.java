package com.example.backend_spring.dto;

import lombok.Data;

@Data
public class ItemPedidoCreateDTO {

    private Integer quantidade;
    private Double preco_unitario;

    private Long pedidoId;
    private Long produtoId;
}
