/**
 * ========================================
 * SEMEAR - Repositório de Item de Pedido
 * ========================================
 *
 * Descrição: interface que estende JpaRepository, fornecendo acesso aos dados
 * de itens de pedidos no banco de dados.
 *
 * Dependências: Spring Data JPA, ItemPedido
 * ========================================
 */
package com.example.backend_spring.repository;

import com.example.backend_spring.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {
}
