/**
 * ========================================
 * SEMEAR - Entidade de Categoria de Produto
 * ========================================
 *
 * Descrição: entidade JPA que mapeia a tabela "categorias_produto" no banco de dados.
 * Representa uma categoria taxonômica de produtos (hortaliças, frutas, etc.).
 *
 * Campos principais:
 * - id_categoria: identificador único
 * - nome_categoria: nome da categoria
 *
 * Dependências: JPA, Lombok
 * ========================================
 */
package com.example.backend_spring.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "categorias_produto")
public class CategoriaProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_categoria;

    private String nome_categoria;
}
