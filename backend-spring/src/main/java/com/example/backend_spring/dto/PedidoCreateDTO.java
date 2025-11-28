package com.example.backend_spring.dto;

import lombok.Data;

@Data
public class PedidoCreateDTO {

    private Double valor_total;
    private String status;
    private String tipo_entrega;
    private String observacoes;

    private Long restauranteId;
    private Long produtorId;
}
