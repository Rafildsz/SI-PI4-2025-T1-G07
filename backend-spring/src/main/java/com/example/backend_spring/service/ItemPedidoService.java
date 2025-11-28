package com.example.backend_spring.service;

import com.example.backend_spring.entity.ItemPedido;
import com.example.backend_spring.entity.Pedido;
import com.example.backend_spring.entity.Produto;
import com.example.backend_spring.repository.ItemPedidoRepository;
import com.example.backend_spring.repository.PedidoRepository;
import com.example.backend_spring.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

@Service
public class ItemPedidoService {

    private final ItemPedidoRepository repository;
    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;

    public ItemPedidoService(ItemPedidoRepository repository, PedidoRepository pedidoRepository, ProdutoRepository produtoRepository) {
        this.repository = repository;
        this.pedidoRepository = pedidoRepository;
        this.produtoRepository = produtoRepository;
    }

    public ItemPedido criar(ItemPedido item, Long pedidoId, Long produtoId) {

        Pedido pedido = pedidoRepository.findById(pedidoId).orElse(null);
        Produto produto = produtoRepository.findById(produtoId).orElse(null);

        if (pedido == null || produto == null) return null;

        item.setPedido(pedido);
        item.setProduto(produto);

        return repository.save(item);
    }
}
