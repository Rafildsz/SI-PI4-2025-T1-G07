/**
 * ========================================
 * SEMEAR - DTO de Criação de Propriedade
 * ========================================
 *
 * Descrição: dados necessários para cadastrar uma propriedade vinculada a um produtor.
 *
 * Dependências: usado por `PropriedadeController` e `PropriedadeService`.
 * ========================================
 */
package com.example.backend_spring.dto;

import lombok.Data;

@Data
public class PropriedadeCreateDTO {

    private String nome_propriedade;
    private String cpf_cnpj;
    private String cep;
    private String endereco;
    private String descricao;
    private String selo_certificacao;
    private Long usuarioId;
}
