/**
 * ========================================
 * SEMEAR - DTO de Atualização de Produto
 * ========================================
 *
 * Descrição: dados usados para atualizar um produto existente.
 *
 * Campos principais: campos editáveis do produto (nome, descrição, preço, unidade, estoque, imagem)
 *
 * Dependências: usado por `ProdutoController` ao chamar `ProdutoService`.
 * ========================================
 */
package com.example.backend_spring.dto;

import lombok.Data;

@Data
public class ProdutoUpdateDTO {

    private String nome_produto;
    private String descricao;
    private Double preco;
    private String unidade_medida;
    private Integer estoque;
    private String imagem_url;
}
