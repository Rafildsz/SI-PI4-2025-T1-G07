package com.example.backend_spring.dto;

import lombok.Data;

@Data
public class UsuarioResponseDTO {
    private Long id;
    private String nome_completo;
    private String email;
    private String telefone;
    private String tipo_usuario;
    private String endereco;
}
