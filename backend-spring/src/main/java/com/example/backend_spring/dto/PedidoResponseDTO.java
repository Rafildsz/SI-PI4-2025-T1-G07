/**
 * ========================================
 * SEMEAR - DTO de Resposta de Pedido
 * ========================================
 *
 * Descrição: DTO retornado ao cliente com detalhes de um pedido criado ou consultado.
 * Contém resumo de itens, valores, status e identificadores.
 *
 * Dependências: produzido por `PedidoService` e retornado por controladores.
 * ========================================
 */
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
