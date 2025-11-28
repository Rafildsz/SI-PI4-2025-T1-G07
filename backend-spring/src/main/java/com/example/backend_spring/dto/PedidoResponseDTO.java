package com.example.backend_spring.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class PedidoResponseDTO {

    private Long id;
    private LocalDateTime data_pedido;
    private Double valor_total;
    private String status;
    private String tipo_entrega;
    private String observacoes;

    private Long restauranteId;
    private Long produtorId;
}
