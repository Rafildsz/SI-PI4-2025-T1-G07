package com.example.backend_spring.controller;

import com.example.backend_spring.entity.Produto;
import com.example.backend_spring.service.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Produto> listar() {
        return service.listar();
    }

    @PostMapping("/{propriedadeId}")
    public ResponseEntity<Produto> criar(@RequestBody Produto produto, @PathVariable Long propriedadeId) {
        Produto criado = service.criar(produto, propriedadeId);
        return ResponseEntity.created(URI.create("/produtos/" + criado.getId_produto())).body(criado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscar(@PathVariable Long id) {
        Produto produto = service.buscar(id);
        return produto != null ? ResponseEntity.ok(produto) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizar(@PathVariable Long id, @RequestBody Produto produto) {
        return ResponseEntity.ok(service.atualizar(id, produto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
