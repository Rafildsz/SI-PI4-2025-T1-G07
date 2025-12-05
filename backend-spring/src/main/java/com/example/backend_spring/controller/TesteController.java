/**
 * ========================================
 * SEMEAR - Controlador de Testes
 * ========================================
 * 
 * Descrição: Controlador de testes para verificar se o backend está em funcionamento.
 * Fornece endpoints simples para validação de disponibilidade do servidor.
 * 
 * Responsabilidades:
 * - Endpoint GET /: retornar mensagem inicial do backend
 * - Endpoint GET /teste: validar se API está funcionando
 * - Servir como health check básico do servidor
 * 
 * Dependências: Spring Web MVC
 * ========================================
 */
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
