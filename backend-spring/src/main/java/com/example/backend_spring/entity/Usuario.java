package com.example.backend_spring.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_usuario;

    private String nome_completo;
    private String email;
    private String senha;
    private String telefone;
    private String tipo_usuario;
    private String endereco;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Propriedade> propriedades;
}
