/**
 * ========================================
 * SEMEAR - DTO de Criação de Pedido
 * ========================================
 *
 * Descrição: estrutura de dados usada para criar um novo pedido via API.
 * Contém informações sobre itens, comprador e dados de entrega/pagamento.
 *
 * Dependências: consumido por `PedidoController` e `PedidoService`.
 * ========================================
 */
package com.example.backend_spring.dto;

import lombok.Data;
import java.util.List;

@Data
public class PedidoCreateDTO {

    private Long usuarioId;
    private String status;
    private List<ItemPedidoDTO> itens;

    @Data
    public static class ItemPedidoDTO {
        private Long produtoId;
        private Integer quantidade;
        private Double preco_unitario;
    }
}
