package com.example.backend_spring.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "propriedades")
public class Propriedade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_propriedade;

    private String nome_propriedade;
    private String cpf_cnpj;
    private String cep;
    private String endereco;
    private String descricao;
    private String selo_certificacao;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @OneToMany(mappedBy = "propriedade", cascade = CascadeType.ALL)
    private List<Produto> produtos;
}

