package com.example.backend_spring.dto;

import lombok.Data;

@Data
public class UsuarioUpdateDTO {
    private String nome_completo;
    private String telefone;
    private String endereco;
}
