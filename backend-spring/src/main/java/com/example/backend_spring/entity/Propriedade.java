/**
 * ========================================
 * SEMEAR - Entidade de Propriedade
 * ========================================
 *
 * Descrição: entidade JPA que mapeia a tabela "propriedades" no banco de dados.
 * Representa uma propriedade/fazenda pertencente a um produtor.
 *
 * Campos principais:
 * - id_propriedade: identificador único
 * - nome_propriedade: nome da propriedade
 * - cpf_cnpj: documento fiscal da propriedade
 * - endereco, cep: localização
 * - descricao: descrição detalhada
 * - selo_certificacao: certificações agropecuárias
 * - usuario: referência ao produtor proprietário (ManyToOne)
 * - produtos: lista de produtos produzidos (OneToMany)
 *
 * Dependências: JPA, Lombok, Jackson, Usuario, Produto
 * ========================================
 */
package com.example.backend_spring.entity;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@Entity
@Table(name = "propriedades")
@Data
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
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @JsonIgnoreProperties({"senha", "propriedades"})
    private Usuario usuario;

    @JsonIgnore
    @OneToMany(mappedBy = "propriedade", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Produto> produtos;
}
