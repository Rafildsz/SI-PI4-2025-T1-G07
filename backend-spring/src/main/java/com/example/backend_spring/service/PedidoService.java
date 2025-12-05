/**
 * ========================================
 * SEMEAR - Serviço de Pedido
 * ========================================
 *
 * Descrição: classe de serviço que implementa lógica de negócio de pedidos.
 * Gerencia criação, atualização e consulta de pedidos com seus itens associados.
 *
 * Responsabilidades:
 * - Listar todos os pedidos
 * - Buscar pedido por ID
 * - Criar pedido com múltiplos itens (criarComItens)
 * - Criar pedido simples (criar)
 * - Atualizar dados do pedido
 * - Deletar pedido
 * - Gerenciar estoque de produtos conforme pedidos são criados
 *
 * Dependências: PedidoRepository, UsuarioRepository, ProdutoRepository, ItemPedidoRepository
 * ========================================
 */
package com.example.backend_spring.service;

import com.example.backend_spring.dto.PedidoCreateDTO;
import com.example.backend_spring.entity.ItemPedido;
import com.example.backend_spring.entity.Pedido;
import com.example.backend_spring.entity.Produto;
import com.example.backend_spring.entity.Usuario;
import com.example.backend_spring.repository.ItemPedidoRepository;
import com.example.backend_spring.repository.PedidoRepository;
import com.example.backend_spring.repository.ProdutoRepository;
import com.example.backend_spring.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final ProdutoRepository produtoRepository;
    private final ItemPedidoRepository itemPedidoRepository;

    public PedidoService(PedidoRepository repository, UsuarioRepository usuarioRepository, 
                        ProdutoRepository produtoRepository, ItemPedidoRepository itemPedidoRepository) {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
        this.produtoRepository = produtoRepository;
        this.itemPedidoRepository = itemPedidoRepository;
    }

    public List<Pedido> listar() {
        return repository.findAll();
    }

    public Pedido buscar(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Pedido criarComItens(PedidoCreateDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId()).orElse(null);
        if (usuario == null) return null;

        // Busca o primeiro produto para obter o produtor
        if (dto.getItens() == null || dto.getItens().isEmpty()) return null;
        
        Produto primeiroProduto = produtoRepository.findById(dto.getItens().get(0).getProdutoId()).orElse(null);
        if (primeiroProduto == null || primeiroProduto.getPropriedade() == null) return null;
        
        Usuario produtor = primeiroProduto.getPropriedade().getUsuario();
        if (produtor == null) return null;

        // Calcula valor total
        double valorTotal = 0;
        for (PedidoCreateDTO.ItemPedidoDTO itemDTO : dto.getItens()) {
            valorTotal += itemDTO.getPreco_unitario() * itemDTO.getQuantidade();
        }

        // Cria pedido
        Pedido pedido = new Pedido();
        pedido.setRestaurante(usuario);
        pedido.setProdutor(produtor);
        pedido.setStatus(dto.getStatus());
        pedido.setData_pedido(LocalDateTime.now());
        pedido.setValor_total(valorTotal);
        
        Pedido pedidoSalvo = repository.save(pedido);

        // Cria itens do pedido
        for (PedidoCreateDTO.ItemPedidoDTO itemDTO : dto.getItens()) {
            Produto produto = produtoRepository.findById(itemDTO.getProdutoId()).orElse(null);
            if (produto == null) continue;

            ItemPedido item = new ItemPedido();
            item.setPedido(pedidoSalvo);
            item.setProduto(produto);
            item.setQuantidade(itemDTO.getQuantidade());
            item.setPrecoUnitario(itemDTO.getPreco_unitario());
            
            itemPedidoRepository.save(item);

            // Atualiza estoque
            produto.setEstoque(produto.getEstoque() - itemDTO.getQuantidade());
            produtoRepository.save(produto);
        }

        return pedidoSalvo;
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

        // Atualiza apenas os campos que não são nulos e válidos
        if (dados.getStatus() != null) {
            p.setStatus(dados.getStatus());
        }
        if (dados.getTipo_entrega() != null) {
            p.setTipo_entrega(dados.getTipo_entrega());
        }
        if (dados.getObservacoes() != null) {
            p.setObservacoes(dados.getObservacoes());
        }
        // Só atualiza valor_total se for maior que 0
        if (dados.getValor_total() != null && dados.getValor_total() > 0) {
            p.setValor_total(dados.getValor_total());
        }

        return repository.save(p);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
