/**
 * ========================================
 * SEMEAR - DTO de Resposta de Item de Pedido
 * ========================================
 * 
 * Descrição: Data Transfer Object para resposta de consulta de itens de pedido.
 * Retorna informações completas do item adicionado ao pedido.
 * 
 * Responsabilidades:
 * - Retornar ID do item de pedido
 * - Retornar quantidade do produto
 * - Retornar preço unitário
 * - Retornar referência do pedido
 * - Retornar referência do produto
 * 
 * Dependências: Lombok @Data
 * ========================================
 */
package com.example.backend_spring.dto;

import lombok.Data;

@Data
public class ItemPedidoResponseDTO {

    private Long id;
    private Integer quantidade;
    private Double preco_unitario;

    private Long pedidoId;
    private Long produtoId;
}
