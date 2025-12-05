/**
 * ========================================
 * SEMEAR - Entidade de Pedido
 * ========================================
 *
 * Descrição: entidade JPA que mapeia a tabela "pedidos" no banco de dados.
 * Representa um pedido de compra realizado por um restaurante a um produtor.
 *
 * Campos principais:
 * - id_pedido: identificador único
 * - restaurante: usuário restaurante que fez o pedido (ManyToOne)
 * - produtor: usuário produtor que receberá o pedido (ManyToOne)
 * - data_pedido: data/hora de criação
 * - valor_total: soma dos valores dos itens
 * - status: estado do pedido (pendente, confirmado, entregue, etc.)
 * - tipo_entrega: retirada ou entrega
 * - observacoes: notas especiais
 * - itens: lista de produtos/quantidades no pedido (OneToMany)
 *
 * Dependências: JPA, Lombok, Jackson, Usuario, ItemPedido
 * ========================================
 */
package com.example.backend_spring.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedidos")
@Data
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_pedido;

    @ManyToOne
    @JoinColumn(name = "id_restaurante", referencedColumnName = "id_usuario")
    @JsonIgnoreProperties({"senha", "pedidosComoRestaurante", "pedidosComoProdutor"})
    private Usuario restaurante;

    @ManyToOne
    @JoinColumn(name = "id_produtor", referencedColumnName = "id_usuario")
    @JsonIgnoreProperties({"senha", "pedidosComoRestaurante", "pedidosComoProdutor"})
    private Usuario produtor;

    private LocalDateTime data_pedido = LocalDateTime.now();

    private Double valor_total = 0.0;

    private String status = "pendente";
    private String tipo_entrega = "retirada";
    private String observacoes;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedido> itens = new ArrayList<>();
}
