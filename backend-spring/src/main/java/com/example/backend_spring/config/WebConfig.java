/**
 * ========================================
 * SEMEAR - Configuração Web
 * ========================================
 * 
 * Descrição: Classe de configuração que define beans e configurações gerais da aplicação web.
 * Responsável por providenciar componentes reutilizáveis em toda a aplicação.
 * 
 * Responsabilidades:
 * - Configurar encoder de senhas usando BCrypt
 * - Providenciar bean PasswordEncoder para uso em serviços de autenticação
 * - Garantir segurança de senhas com algoritmo de hash bcrypt
 * 
 * Dependências: Spring Security, BCryptPasswordEncoder
 * ========================================
 */
package com.example.backend_spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
