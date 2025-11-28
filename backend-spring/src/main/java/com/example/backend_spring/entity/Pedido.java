package com.example.backend_spring.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_pedido;

    @ManyToOne
    @JoinColumn(name = "id_restaurante")
    private Usuario restaurante;

    @ManyToOne
    @JoinColumn(name = "id_produtor")
    private Usuario produtor;

    private LocalDateTime data_pedido;
    private Double valor_total;
    private String status;
    private String tipo_entrega;
    private String observacoes;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemPedido> itens;
}

