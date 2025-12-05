/**
 * ========================================
 * SEMEAR - Carrinho de Compras
 * ========================================
 * 
 * Descrição: Gerencia a lógica do carrinho de compras de produtos agrícolas,
 * incluindo adição/remoção de itens e processamento de pagamento.
 * 
 * Funcionalidades:
 * - Adição e remoção de produtos ao carrinho
 * - Verificação de estoque disponível
 * - Armazenamento de carrinho no localStorage
 * - Processamento de pedidos no backend
 * - Cálculo de totais e aplicação de descontos
 * 
 * Dependências: Backend Spring Boot (API REST em http://localhost:8081/api)
 * ========================================
 */

// Backend Spring Boot
const API_BASE = "http://localhost:8081/api";

// Obtém o carrinho do localStorage (temporário até criar pedido)
function obterCarrinho() {
    const carrinho = localStorage.getItem('semear_carrinho');
    return carrinho ? JSON.parse(carrinho) : [];
}

// Salva carrinho no localStorage
function salvarCarrinho(carrinho) {
    localStorage.setItem('semear_carrinho', JSON.stringify(carrinho));
}

// Função para adicionar ao carrinho
function adicionarCarrinho(produtoId) {
    const usuarioId = localStorage.getItem('usuario_id');
    
    if (!usuarioId) {
        alert('Você precisa estar logado para adicionar produtos ao carrinho!');
        window.location.href = 'login-restaurante.html';
        return;
    }

    // Busca o produto completo
    fetch(`${API_BASE}/produtos/${produtoId}`)
        .then(res => {
            if (!res.ok) throw new Error('Produto não encontrado');
            return res.json();
        })
        .then(produto => {
            // Verifica estoque
            if (produto.estoque <= 0) {
                alert('Produto sem estoque!');
                return;
            }

            // Adiciona ao carrinho local
            let carrinho = obterCarrinho();
            const itemExistente = carrinho.find(item => item.produto.id_produto === produtoId);
            
            if (itemExistente) {
                // Verifica se não ultrapassa o estoque
                if (itemExistente.quantidade >= produto.estoque) {
                    alert(`Estoque máximo: ${produto.estoque} ${produto.unidade_medida}`);
                    return;
                }
                itemExistente.quantidade += 1;
            } else {
                carrinho.push({
                    produto: produto,
                    quantidade: 1
                });
            }
            
            salvarCarrinho(carrinho);
            atualizarContadorCarrinho();
            alert(`${produto.nome_produto} adicionado ao carrinho!`);
        })
        .catch(err => {
            console.error('Erro ao adicionar ao carrinho:', err);
            alert('Erro ao adicionar produto ao carrinho.');
        });
}

// Atualiza contador do carrinho (se existir na interface)
function atualizarContadorCarrinho() {
    const carrinho = obterCarrinho();
    const totalItens = carrinho.reduce((total, item) => total + item.quantidade, 0);
    
    const contador = document.getElementById('carrinho-contador');
    if (contador) {
        contador.textContent = totalItens;
        contador.style.display = totalItens > 0 ? 'inline' : 'none';
    }
}

// Inicializa contador ao carregar a página
document.addEventListener('DOMContentLoaded', () => {
    atualizarContadorCarrinho();
});
