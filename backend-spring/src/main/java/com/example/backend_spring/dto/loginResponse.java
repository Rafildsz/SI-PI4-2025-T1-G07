/**
 * ========================================
 * SEMEAR - DTO de Resposta de Login
 * ========================================
 *
 * Descrição: DTO usado para enviar informações de resultado do processo
 * de autenticação ao cliente (id, nome, mensagem ou erro).
 *
 * Campos principais:
 * - id: identificador do usuário autenticado
 * - nome: nome completo do usuário
 * - message: mensagem de sucesso
 * - erro: mensagem de erro quando houver falha na autenticação
 *
 * Dependências: retornado pelo `authController` após validação.
 * ========================================
 */
package com.example.backend_spring.dto;

public class loginResponse {
    private Long id;
    private String nome;
    private String message;
    private String erro;

    public loginResponse(Long id, String nome, String message, String erro) {
        this.id = id;
        this.nome = nome;
        this.message = message;
        this.erro = erro;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getMessage() {
        return message;
    }

    public String getErro() {
        return erro;
    }
}
