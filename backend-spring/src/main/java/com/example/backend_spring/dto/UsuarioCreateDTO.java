/**
 * ========================================
 * SEMEAR - DTO de Criação de Usuário
 * ========================================
 *
 * Descrição: dados enviados pelo cliente para criar um novo usuário (produtor ou restaurante).
 *
 * Campos principais: nome, email, senha, telefone, tipo_usuario, endereco
 *
 * Dependências: consumido por `UsuarioController` e `UsuarioService`.
 * ========================================
 */
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
