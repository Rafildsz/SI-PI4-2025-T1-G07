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
