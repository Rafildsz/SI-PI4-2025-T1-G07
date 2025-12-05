/**
 * ========================================
 * SEMEAR - DTO de Criação de Item de Pedido
 * ========================================
 * 
 * Descrição: Data Transfer Object para requisição de criação de itens em um pedido.
 * Transporta dados necessários para adicionar um produto ao pedido.
 * 
 * Responsabilidades:
 * - Receber quantidade do produto
 * - Receber preço unitário no momento do pedido
 * - Receber ID do pedido
 * - Receber ID do produto
 * 
 * Dependências: Lombok @Data
 * ========================================
 */
package com.example.backend_spring.dto;

import lombok.Data;

@Data
public class ItemPedidoCreateDTO {

    private Integer quantidade;
    private Double preco_unitario;

    private Long pedidoId;
    private Long produtoId;
}
