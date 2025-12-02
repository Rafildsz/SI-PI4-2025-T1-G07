// URL correta do backend (porta 8081)
const API_CARRINHO = "http://localhost:8081/carrinho";

// Função para adicionar ao carrinho
function adicionarCarrinho(produto) {
    const id = produto.id_produto; // pega o ID certo

    fetch(`${API_CARRINHO}/adicionar?produtoId=${id}&quantidade=1`, {
        method: "POST"
    })
    .then(res => {
        if (!res.ok) throw new Error("Erro ao adicionar ao carrinho");
        return res.json();
    })
    .then(data => {
        alert("Produto adicionado ao carrinho!");
        console.log("Carrinho atualizado:", data);
    })
    .catch(err => {
        console.error("ERRO NO CARRINHO:", err);
        alert("Falha ao comunicar com o servidor do carrinho (backend).");
    });
}
