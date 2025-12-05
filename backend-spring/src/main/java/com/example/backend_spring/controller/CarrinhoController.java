/**
 * ========================================
 * SEMEAR - Controlador de Carrinho
 * ========================================
 * 
 * Descrição: Controlador REST para gerenciar carrinho de compras da aplicação.
 * Permite adicionar/remover produtos ao carrinho e gerenciar pedidos pendentes.
 * 
 * Responsabilidades:
 * - Endpoint GET /carrinho: recuperar carrinho atual (pedido pendente)
 * - Endpoint POST /carrinho/adicionar: adicionar produto ao carrinho
 * - Endpoint DELETE /carrinho/{itemId}: remover item do carrinho
 * - Gerenciar quantidade de produtos
 * - Validar disponibilidade de estoque
 * 
 * Dependências: PedidoRepository, ProdutoRepository, ItemPedidoRepository
 * ========================================
 */
package com.example.backend_spring.controller;

import com.example.backend_spring.entity.ItemPedido;
import com.example.backend_spring.entity.Pedido;
import com.example.backend_spring.entity.Produto;
import com.example.backend_spring.repository.ItemPedidoRepository;
import com.example.backend_spring.repository.PedidoRepository;
import com.example.backend_spring.repository.ProdutoRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carrinho")
@CrossOrigin(origins = "*")
public class CarrinhoController {

    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;
    private final ItemPedidoRepository itemPedidoRepository;

    public CarrinhoController(
            PedidoRepository pedidoRepository,
            ProdutoRepository produtoRepository,
            ItemPedidoRepository itemPedidoRepository
    ) {
        this.pedidoRepository = pedidoRepository;
        this.produtoRepository = produtoRepository;
        this.itemPedidoRepository = itemPedidoRepository;
    }

    // Buscar carrinho atual
    @GetMapping
    public Pedido getCarrinho() {
        return pedidoRepository.findByStatus("pendente").orElse(null);
    }

    // Adicionar produto ao carrinho
    @PostMapping("/adicionar")
    public Pedido adicionar(@RequestParam Long produtoId,
                            @RequestParam(defaultValue = "1") Integer quantidade) {

        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        // Buscar pedido em andamento ou criar um novo
        Pedido pedido = pedidoRepository.findByStatus("pendente")
                .orElseGet(() -> pedidoRepository.save(new Pedido()));

        // Criar item
        ItemPedido item = new ItemPedido();
        item.setPedido(pedido);
        item.setProduto(produto);
        item.setQuantidade(quantidade);
        item.setPrecoUnitario(produto.getPreco());   // <-- CORRETO
        itemPedidoRepository.save(item);

        pedido.getItens().add(item);

        // Recalcular total
        pedido.setValor_total(
                pedido.getItens().stream()
                        .mapToDouble(i -> i.getPrecoUnitario() * i.getQuantidade())
                        .sum()
        );

        return pedidoRepository.save(pedido);
    }

    // Remover item
    @DeleteMapping("/remover/{id}")
    public void remover(@PathVariable Long id) {
        itemPedidoRepository.deleteById(id);
    }

    // Atualizar quantidade do item
    @PutMapping("/quantidade")
    public Pedido atualizarQtd(@RequestParam Long itemId,
                               @RequestParam Integer quantidade) {

        ItemPedido item = itemPedidoRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item não encontrado"));

        item.setQuantidade(quantidade);
        itemPedidoRepository.save(item);

        Pedido pedido = item.getPedido();
        pedido.setValor_total(
                pedido.getItens().stream()
                        .mapToDouble(i -> i.getPrecoUnitario() * i.getQuantidade())
                        .sum()
        );

        return pedidoRepository.save(pedido);
    }

    // Finalizar pedido
    @PostMapping("/finalizar")
    public Pedido finalizar() {
        Pedido pedido = pedidoRepository.findByStatus("pendente")
                .orElseThrow(() -> new RuntimeException("Não há pedido em andamento"));

        pedido.setStatus("aceito");
        return pedidoRepository.save(pedido);
    }

    // Limpar carrinho
    @PostMapping("/limpar")
    public void limpar() {
        pedidoRepository.findByStatus("pendente").ifPresent(pedido -> {
            itemPedidoRepository.deleteAll(pedido.getItens());
            pedidoRepository.delete(pedido);
        });
    }
}
