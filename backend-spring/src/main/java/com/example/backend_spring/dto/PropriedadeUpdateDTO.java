package com.example.backend_spring.dto;

import lombok.Data;

@Data
public class PropriedadeUpdateDTO {

    private String nome_propriedade;
    private String descricao;
    private String endereco;
    private String cep;
    private String cpf_cnpj;
    private String selo_certificacao;
}
