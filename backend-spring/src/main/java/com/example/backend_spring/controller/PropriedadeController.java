package com.example.backend_spring.controller;

import com.example.backend_spring.entity.Propriedade;
import com.example.backend_spring.service.PropriedadeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/propriedades")
public class PropriedadeController {

    private final PropriedadeService service;

    public PropriedadeController(PropriedadeService service) {
        this.service = service;
    }

    @GetMapping
    public List<Propriedade> listar() {
        return service.listar();
    }

    @PostMapping("/{usuarioId}")
    public ResponseEntity<Propriedade> criar(@RequestBody Propriedade propriedade, @PathVariable Long usuarioId) {
        Propriedade criada = service.criar(propriedade, usuarioId);
        return ResponseEntity.created(URI.create("/propriedades/" + criada.getId_propriedade())).body(criada);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Propriedade> buscar(@PathVariable Long id) {
        Propriedade p = service.buscar(id);
        return p != null ? ResponseEntity.ok(p) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Propriedade> atualizar(@PathVariable Long id, @RequestBody Propriedade propriedade) {
        return ResponseEntity.ok(service.atualizar(id, propriedade));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Propriedade>> buscarPorUsuario(@PathVariable Long usuarioId) {
        List<Propriedade> propriedades = service.buscarPorUsuario(usuarioId);
        return ResponseEntity.ok(propriedades);
    }
}
