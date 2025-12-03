package com.example.backend_spring.controller;

import com.example.backend_spring.dto.loginRequest;
import com.example.backend_spring.dto.loginResponse;
import com.example.backend_spring.entity.Usuario;
import com.example.backend_spring.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class authController {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public authController(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<loginResponse> login(@RequestBody loginRequest req) {
        
        // Busca usuário pelo email
        Usuario usuario = usuarioRepository.findByEmail(req.getEmail());
        
        if (usuario == null) {
            loginResponse erro = new loginResponse(null, null, "E-mail não cadastrado");
            return ResponseEntity.status(401).body(erro);
        }
        
        // Verifica senha
        if (!passwordEncoder.matches(req.getSenha(), usuario.getSenha())) {
            loginResponse erro = new loginResponse(null, null, "Senha incorreta");
            return ResponseEntity.status(401).body(erro);
        }
        
        // Login bem-sucedido
        loginResponse ok = new loginResponse(
            usuario.getId_usuario(), 
            "Login realizado com sucesso!", 
            null
        );
        return ResponseEntity.ok(ok);
    }
}
