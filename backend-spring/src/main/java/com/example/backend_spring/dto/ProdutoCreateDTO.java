/**
 * ========================================
 * SEMEAR - DTO de Criação de Produto
 * ========================================
 *
 * Descrição: representação dos dados necessários para criar um novo produto
 * no sistema (enviado pelas APIs REST quando um produtor cadastra um produto).
 *
 * Campos principais: nome, descrição, preço, unidade, estoque, imagem e propriedadeId
 *
 * Dependências: consumido por `ProdutoController` e `ProdutoService`.
 * ========================================
 */
package com.example.backend_spring.dto;

import lombok.Data;

@Data
public class ProdutoCreateDTO {

    private String nome_produto;
    private String descricao;
    private Double preco;
    private String unidade_medida;
    private Integer estoque;
    private String imagem_url;
    private Long propriedadeId;
}
