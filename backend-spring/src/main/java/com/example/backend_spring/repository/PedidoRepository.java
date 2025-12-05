/**
 * ========================================
 * SEMEAR - Repositório de Pedido
 * ========================================
 *
 * Descrição: interface que estende JpaRepository, fornecendo acesso aos dados
 * de pedidos no banco de dados, com buscas customizadas.
 *
 * Métodos customizados:
 * - findByStatus(): busca pedidos por um status específico
 *
 * Dependências: Spring Data JPA, Pedido
 * ========================================
 */
package com.example.backend_spring.repository;

import com.example.backend_spring.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    Optional<Pedido> findByStatus(String status);
}
