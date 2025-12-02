package com.example.backend_spring.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private Usuario restaurante; // pode ser null

    @ManyToOne
    @JoinColumn(name = "id_produtor")
    private Usuario produtor; // pode ser null

    private LocalDateTime data_pedido = LocalDateTime.now();

    private Double valor_total = 0.0;

    // compatível com ENUM do banco
    private String status = "pendente";

    private String tipo_entrega = "retirada";

    private String observacoes;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemPedido> itens = new ArrayList<>();
}
