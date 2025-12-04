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
