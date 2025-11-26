package com.example.backend_spring.dto;

public class loginResponse {
    private Long id;
    private String message;
    private String erro;

    public loginResponse(Long id, String message, String erro) {
        this.id = id;
        this.message = message;
        this.erro = erro;
    }

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public String getErro() {
        return erro;
    }
}
