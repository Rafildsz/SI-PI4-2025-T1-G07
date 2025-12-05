/**
 * ========================================
 * SEMEAR - DTO de Resposta de Propriedade
 * ========================================
 *
 * Descrição: DTO que representa os dados de uma propriedade (endereço, contato, etc.)
 * retornados pelas APIs ao consultar informações do produtor.
 *
 * Dependências: produzido por `PropriedadeService` e retornado por `PropriedadeController`.
 * ========================================
 */
package com.example.backend_spring.dto;

import lombok.Data;

@Data
public class PropriedadeResponseDTO {

    private Long id;
    private String nome_propriedade;
    private String cpf_cnpj;
    private String cep;
    private String endereco;
    private String descricao;
    private String selo_certificacao;
    private Long usuarioId;
}
