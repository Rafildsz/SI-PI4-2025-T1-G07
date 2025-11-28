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
