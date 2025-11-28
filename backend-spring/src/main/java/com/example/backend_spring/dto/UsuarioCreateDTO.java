package com.example.backend_spring.dto;

import lombok.Data;

@Data
public class UsuarioCreateDTO {
    private String nome_completo;
    private String email;
    private String senha;
    private String telefone;
    private String tipo_usuario;
    private String endereco;
}
