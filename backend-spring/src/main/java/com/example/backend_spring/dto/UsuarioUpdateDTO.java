/**
 * ========================================
 * SEMEAR - DTO de Atualização de Usuário
 * ========================================
 *
 * Descrição: campos que podem ser atualizados em um perfil de usuário
 * (nome, telefone, endereço, etc.).
 *
 * Dependências: usado por `UsuarioController` para operações de atualização.
 * ========================================
 */
package com.example.backend_spring.dto;

import lombok.Data;

@Data
public class UsuarioUpdateDTO {
    private String nome_completo;
    private String telefone;
    private String endereco;
}
