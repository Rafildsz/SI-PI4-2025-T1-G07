package com.example.backend_spring.entity;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "produtos")
@Data
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_produto;

    private String nome_produto;
    private String descricao;
    private Double preco;

    private String unidade_medida;
    private Integer estoque;
    private String imagem_url;

    @ManyToOne
    @JoinColumn(name = "id_propriedade", referencedColumnName = "id_propriedade")
    private Propriedade propriedade;

    @ManyToOne
    @JoinColumn(name = "id_categoria", referencedColumnName = "id_categoria")
    private CategoriaProduto categoria;
}
