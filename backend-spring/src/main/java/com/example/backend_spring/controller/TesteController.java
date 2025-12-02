package com.example.backend_spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TesteController {

    @GetMapping("/")
    public String home() {
        return "Backend Semear rodando!";
    }

    @GetMapping("/teste")
    public String teste() {
        return "API funcionando com sucesso!";
    }
}
