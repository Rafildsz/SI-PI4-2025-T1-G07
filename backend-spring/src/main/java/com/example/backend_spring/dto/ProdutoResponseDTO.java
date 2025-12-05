/**
 * ========================================
 * SEMEAR - DTO de Resposta de Produto
 * ========================================
 *
 * Descrição: DTO utilizado para serializar informações de produto que são
 * retornadas pelas APIs para o cliente (detalhes exibidos no catálogo/ dashboard).
 *
 * Campos principais: id, nome, descrição, preço, unidade, estoque, imagem e propriedadeId
 *
 * Dependências: produzido por `ProdutoService` e retornado por `ProdutoController`.
 * ========================================
 */
package com.example.backend_spring.dto;

import lombok.Data;

@Data
public class ProdutoResponseDTO {

    private Long id;
    private String nome_produto;
    private String descricao;
    private Double preco;
    private String unidade_medida;
    private Integer estoque;
    private String imagem_url;
    private Long propriedadeId;
}
