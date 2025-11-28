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
