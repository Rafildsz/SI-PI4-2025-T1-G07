package com.example.backend_spring.controller;

import com.example.backend_spring.dto.ProdutoCreateDTO;
import com.example.backend_spring.entity.Produto;
import com.example.backend_spring.service.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Produto> listar() {
        return service.listar();
    }

    @PostMapping
    public ResponseEntity<Produto> criar(@RequestBody Produto produto) {
        // Tenta pegar o id_propriedade do objeto propriedade ou do campo direto
        Long propriedadeId = null;
        if (produto.getPropriedade() != null && produto.getPropriedade().getId_propriedade() != null) {
            propriedadeId = produto.getPropriedade().getId_propriedade();
        }
        
        if (propriedadeId == null) {
            return ResponseEntity.badRequest().build();
        }
        
        Produto criado = service.criar(produto, propriedadeId);
        return ResponseEntity.created(URI.create("/api/produtos/" + criado.getId_produto())).body(criado);
    }

    @PostMapping("/{propriedadeId}")
    public ResponseEntity<Produto> criar(@RequestBody Produto produto, @PathVariable Long propriedadeId) {
        Produto criado = service.criar(produto, propriedadeId);
        return ResponseEntity.created(URI.create("/api/produtos/" + criado.getId_produto())).body(criado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscar(@PathVariable Long id) {
        Produto produto = service.buscar(id);
        return produto != null ? ResponseEntity.ok(produto) : ResponseEntity.notFound().build();
    }

    @GetMapping("/propriedade/{propriedadeId}")
    public ResponseEntity<List<Produto>> listarPorPropriedade(@PathVariable Long propriedadeId) {
        List<Produto> produtos = service.listarPorPropriedade(propriedadeId);
        return ResponseEntity.ok(produtos);
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
