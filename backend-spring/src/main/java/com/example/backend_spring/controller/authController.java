package com.example.backend_spring.controller;

import com.example.backend_spring.dto.loginRequest;
import com.example.backend_spring.dto.loginResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class authController {

    @PostMapping("/login")
    public ResponseEntity<loginResponse> login(@RequestBody loginRequest req) {

        if ("teste@teste.com".equalsIgnoreCase(req.getEmail())
                && "123456".equals(req.getSenha())) {

            loginResponse ok = new loginResponse(1L, "Login realizado!", null);
            return ResponseEntity.ok(ok);
        }

        loginResponse erro = new loginResponse(null, null, "Credenciais inválidas");
        return ResponseEntity.status(401).body(erro);
    }
}
