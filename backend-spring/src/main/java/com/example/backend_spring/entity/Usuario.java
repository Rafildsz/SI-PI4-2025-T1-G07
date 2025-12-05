/**
 * ========================================
 * SEMEAR - Entidade de Usuário
 * ========================================
 *
 * Descrição: entidade JPA que mapeia a tabela "usuarios" no banco de dados.
 * Representa um usuário da plataforma (produtor ou restaurante).
 *
 * Campos principais:
 * - id_usuario: identificador único
 * - nome_completo, email, telefone: dados pessoais
 * - tipo_usuario: produtor ou restaurante
 * - endereco: localização do usuário
 * - propriedades: relação 1:N com propriedades (apenas para produtores)
 *
 * Dependências: JPA, Lombok, Jackson
 * ========================================
 */
package com.example.backend_spring.entity;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@Entity
@Table(name = "usuarios")
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_usuario;

    private String nome_completo;
    private String email;
    
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String senha;
    
    private String telefone;
    private String tipo_usuario;
    private String endereco;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Propriedade> propriedades;
}
