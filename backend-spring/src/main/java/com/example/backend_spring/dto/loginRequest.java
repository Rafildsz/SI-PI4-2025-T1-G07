/**
 * ========================================
 * SEMEAR - DTO de Requisição de Login
 * ========================================
 *
 * Descrição: objeto de transferência de dados usado para receber
 * os dados de login (email, senha e tipo de usuário) nas requisições
 * de autenticação.
 *
 * Campos principais:
 * - email: identificador do usuário
 * - senha: senha em texto recebido do cliente (será verificada pelo backend)
 * - tipoUsuario: opcional, indica se o login é para produtor ou restaurante
 *
 * Dependências: usado pelo controlador de autenticação (`authController`).
 * ========================================
 */
package com.example.backend_spring.dto;

public class loginRequest {
    private String email;
    private String senha;
    private String tipoUsuario;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;   // ✔ agora está certo
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}
