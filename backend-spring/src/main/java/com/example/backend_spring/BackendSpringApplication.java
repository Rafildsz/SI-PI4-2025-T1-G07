/**
 * ========================================
 * SEMEAR - Aplicação Principal Backend Spring
 * ========================================
 * 
 * Descrição: Classe principal de inicialização da aplicação Spring Boot SEMEAR.
 * Responsável por configurar e iniciar o servidor backend da plataforma de
 * integração entre produtores e restaurantes.
 * 
 * Responsabilidades:
 * - Inicializar a aplicação Spring Boot
 * - Configurar scanning de componentes da aplicação
 * - Ativar repositórios JPA
 * - Escanear entidades JPA
 * 
 * Dependências: Spring Boot Framework, JPA, Component Scanning
 * ========================================
 */
package com.example.backend_spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.backend_spring")
@EntityScan(basePackages = "com.example.backend_spring.entity")
@EnableJpaRepositories(basePackages = "com.example.backend_spring.repository")
public class BackendSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendSpringApplication.class, args);
    }
}
