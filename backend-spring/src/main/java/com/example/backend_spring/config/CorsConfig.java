/**
 * ========================================
 * SEMEAR - Configuração CORS
 * ========================================
 * 
 * Descrição: Classe de configuração que ativa e configura CORS (Cross-Origin Resource Sharing)
 * para permitir requisições de diferentes origens ao backend da aplicação.
 * 
 * Responsabilidades:
 * - Configurar permissões de CORS para requisições HTTP
 * - Permitir requisições de localhost:8080, localhost:5500 e seus equivalentes 127.0.0.1
 * - Habilitar métodos HTTP (GET, POST, PUT, DELETE, OPTIONS)
 * - Permitir headers customizados e credenciais
 * 
 * Dependências: Spring Web MVC, WebMvcConfigurer
 * ========================================
 */
package com.example.backend_spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:8080", "http://127.0.0.1:8080", "http://localhost:5500", "http://127.0.0.1:5500")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}
