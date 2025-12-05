/**
 * ========================================
 * SEMEAR - Controlador de Usuários
 * ========================================
 * 
 * Descrição: Controlador REST para gerenciar usuários da plataforma SEMEAR.
 * Implementa operações CRUD de usuários (restaurantes e produtores).
 * 
 * Responsabilidades:
 * - Endpoint GET /api/usuarios: listar todos os usuários
 * - Endpoint GET /api/usuarios/{id}: buscar usuário específico
 * - Endpoint POST /api/usuarios: criar novo usuário
 * - Endpoint PUT /api/usuarios/{id}: atualizar informações do usuário
 * - Endpoint DELETE /api/usuarios/{id}: deletar usuário
 * - Gerenciar dados de produtores e restaurantes
 * 
 * Dependências: UsuarioService
 * ========================================
 */
package com.example.backend_spring.controller;

import com.example.backend_spring.entity.Usuario;
import com.example.backend_spring.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping
    public List<Usuario> listar() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscar(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Usuario> criar(@RequestBody Usuario usuario) {
        Usuario criado = service.criarUsuario(usuario);
        return ResponseEntity.created(URI.create("/api/usuarios/" + criado.getId_usuario())).body(criado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
        Usuario atualizado = service.atualizarUsuario(id, usuario);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

