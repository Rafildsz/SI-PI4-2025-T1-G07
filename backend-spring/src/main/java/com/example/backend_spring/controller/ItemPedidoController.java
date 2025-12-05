/**
 * ========================================
 * SEMEAR - Controlador de Itens de Pedido
 * ========================================
 * 
 * Descrição: Controlador REST para gerenciar itens individuais dentro de um pedido.
 * Cada item representa um produto específico com quantidade e preço unitário.
 * 
 * Responsabilidades:
 * - Endpoint POST /itens-pedido/{pedidoId}/{produtoId}: criar novo item de pedido
 * - Associar item a um pedido específico
 * - Associar item a um produto específico
 * - Registrar quantidade e preço unitário
 * 
 * Dependências: ItemPedidoService
 * ========================================
 */
package com.example.backend_spring.controller;

import com.example.backend_spring.entity.ItemPedido;
import com.example.backend_spring.service.ItemPedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/itens-pedido")
public class ItemPedidoController {

    private final ItemPedidoService service;

    public ItemPedidoController(ItemPedidoService service) {
        this.service = service;
    }

    @PostMapping("/{pedidoId}/{produtoId}")
    public ResponseEntity<ItemPedido> criar(
            @RequestBody ItemPedido item,
            @PathVariable Long pedidoId,
            @PathVariable Long produtoId) {

        ItemPedido criado = service.criar(item, pedidoId, produtoId);
        return ResponseEntity.ok(criado);
    }
}
