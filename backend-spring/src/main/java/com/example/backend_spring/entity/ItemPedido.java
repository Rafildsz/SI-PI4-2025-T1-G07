/**
 * ========================================
 * SEMEAR - Entidade de Item de Pedido
 * ========================================
 *
 * Descrição: entidade JPA que mapeia a tabela "itens_pedido" no banco de dados.
 * Representa um item (linha) dentro de um pedido, ligando um produto a uma quantidade.
 *
 * Campos principais:
 * - id_item: identificador único
 * - pedido: referência ao pedido pai (ManyToOne)
 * - produto: referência ao produto específico (ManyToOne)
 * - quantidade: quantos itens deste produto
 * - precoUnitario: preço do produto no momento do pedido
 *
 * Dependências: JPA, Lombok, Jackson, Pedido, Produto
 * ========================================
 */
package com.example.backend_spring.entity;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "itens_pedido")
@Data
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_item;

    @ManyToOne
    @JoinColumn(name = "id_pedido", referencedColumnName = "id_pedido")
    @JsonIgnore
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "id_produto", referencedColumnName = "id_produto")
    private Produto produto;

    private Integer quantidade;

    @Column(name = "preco_unitario")
    @JsonProperty("preco_unitario")
    private Double precoUnitario;
}
