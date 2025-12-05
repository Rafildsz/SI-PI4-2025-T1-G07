/**
 * ========================================
 * SEMEAR - DTO de Atualização de Pedido
 * ========================================
 *
 * Descrição: campos permitidos para atualização de um pedido (status, informações de rastreio, etc.).
 *
 * Dependências: usado por `PedidoController` ao atualizar pedidos via `PedidoService`.
 * ========================================
 */
package com.example.backend_spring.dto;

import lombok.Data;

@Data
public class PedidoUpdateDTO {

    private Double valor_total;
    private String status;
    private String tipo_entrega;
    private String observacoes;
}
