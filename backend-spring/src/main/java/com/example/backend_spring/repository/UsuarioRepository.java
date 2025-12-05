/**
 * ========================================
 * SEMEAR - Repositório de Usuário
 * ========================================
 *
 * Descrição: interface que estende JpaRepository, fornecendo métodos CRUD padrão
 * e customizados para acesso à entidade Usuario no banco de dados.
 *
 * Métodos customizados:
 * - findByEmail(): busca usuário por email (usado na autenticação)
 *
 * Dependências: Spring Data JPA, Usuario
 * ========================================
 */
package com.example.backend_spring.repository;

import com.example.backend_spring.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByEmail(String email);
}
