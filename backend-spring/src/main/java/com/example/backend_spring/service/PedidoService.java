package com.example.backend_spring.service;

import com.example.backend_spring.entity.Pedido;
import com.example.backend_spring.entity.Usuario;
import com.example.backend_spring.repository.PedidoRepository;
import com.example.backend_spring.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository repository;
    private final UsuarioRepository usuarioRepository;

    public PedidoService(PedidoRepository repository, UsuarioRepository usuarioRepository) {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<Pedido> listar() {
        return repository.findAll();
    }

    public Pedido buscar(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Pedido criar(Pedido pedido, Long restauranteId, Long produtorId) {

        Usuario restaurante = usuarioRepository.findById(restauranteId).orElse(null);
        Usuario produtor = usuarioRepository.findById(produtorId).orElse(null);
        if (restaurante == null || produtor == null) return null;

        pedido.setRestaurante(restaurante);
        pedido.setProdutor(produtor);
        pedido.setData_pedido(LocalDateTime.now());

        return repository.save(pedido);
    }

    public Pedido atualizar(Long id, Pedido dados) {
        Pedido p = buscar(id);
        if (p == null) return null;

        p.setStatus(dados.getStatus());
        p.setTipo_entrega(dados.getTipo_entrega());
        p.setObservacoes(dados.getObservacoes());
        p.setValor_total(dados.getValor_total());

        return repository.save(p);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
