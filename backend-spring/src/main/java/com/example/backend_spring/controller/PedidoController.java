package com.example.backend_spring.controller;

import com.example.backend_spring.dto.PedidoCreateDTO;
import com.example.backend_spring.entity.Pedido;
import com.example.backend_spring.service.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Pedido> listar() {
        return service.listar();
    }

    @PostMapping
    public ResponseEntity<Pedido> criar(@RequestBody PedidoCreateDTO pedidoDTO) {
        Pedido criado = service.criarComItens(pedidoDTO);
        return ResponseEntity.created(URI.create("/api/pedidos/" + criado.getId_pedido())).body(criado);
    }

    @PostMapping("/{restauranteId}/{produtorId}")
    public ResponseEntity<Pedido> criar(
            @RequestBody Pedido pedido,
            @PathVariable Long restauranteId,
            @PathVariable Long produtorId) {

        Pedido criado = service.criar(pedido, restauranteId, produtorId);
        return ResponseEntity.created(URI.create("/api/pedidos/" + criado.getId_pedido())).body(criado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscar(@PathVariable Long id) {
        Pedido pedido = service.buscar(id);
        return pedido != null ? ResponseEntity.ok(pedido) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> atualizar(@PathVariable Long id, @RequestBody Pedido pedido) {
        return ResponseEntity.ok(service.atualizar(id, pedido));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
