/**
 * ========================================
 * SEMEAR - DTO de Resposta de Usuário
 * ========================================
 *
 * Descrição: DTO com informações públicas do usuário (sem campos sensíveis como senha)
 * retornado em operações de consulta ou após autenticação.
 *
 * Dependências: produzido por `UsuarioService` e retornado por controladores.
 * ========================================
 */
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
