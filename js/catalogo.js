// Ano rodapé
document.getElementById('year').textContent = new Date().getFullYear();

// Saudação
try {
  const usuarioLogado = JSON.parse(localStorage.getItem('usuario_logado') || '{}');
  if (usuarioLogado && usuarioLogado.nome) {
    document.getElementById('nomeRest').textContent = usuarioLogado.nome.split(' ')[0];
  }
} catch (e) {}

// Variável global de produtos
let produtos = [];
const grid = document.getElementById('gridProdutos');

function fmtPreco(v){
  return v.toLocaleString('pt-BR',{ style:'currency', currency:'BRL' });
}

// Mapeamento de categorias
const categorias = {
  1: 'Hortaliças', 2: 'Frutas', 3: 'Legumes', 4: 'Verduras',
  5: 'Grãos', 6: 'Laticínios', 7: 'Ovos', 8: 'Outros'
};

// Card do produto
function card(p){
  const nomeProdutor = p.propriedade?.nome_propriedade || 'Produtor';
  const categoria = p.categoria ? categorias[p.categoria.id_categoria] : '';
  
  return `
    <article class="card">
      ${p.imagem_url ? `<img src="${p.imagem_url}" alt="${p.nome_produto}" style="width:100%; height:200px; object-fit:cover;">` : ''}
      <div class="card-body">
        <h3>${p.nome_produto}</h3>
        ${categoria ? `<span style="font-size: 12px; color: #666; display: block; margin-bottom: 4px;">${categoria}</span>` : ''}
        ${p.descricao ? `<p style="font-size: 13px; color: #777; margin: 4px 0;">${p.descricao}</p>` : ''}
        <div class="meta">
          <div class="price">${fmtPreco(p.preco)} <small>/ ${p.unidade_medida}</small></div>
          <div>Por ${nomeProdutor}</div>
          <div style="color: ${p.estoque > 0 ? '#28a745' : '#dc3545'};">
            ${p.estoque > 0 ? `${p.estoque} ${p.unidade_medida} disponível` : 'Sem estoque'}
          </div>
        </div>

        <!-- Botão Adicionar ao Carrinho -->
        <button class="btn-add-carrinho"
          onclick="adicionarCarrinho(${p.id_produto})"
          ${p.estoque <= 0 ? 'disabled style="opacity: 0.5; cursor: not-allowed;"' : ''}>
          🛒 ${p.estoque > 0 ? 'Adicionar ao Carrinho' : 'Indisponível'}
        </button>

      </div>
    </article>
  `;
}

function render(lista){
  if (lista.length === 0) {
    grid.innerHTML = '<p style="text-align: center; padding: 40px; color: #666;">Nenhum produto disponível no momento.</p>';
    return;
  }
  grid.innerHTML = lista.map(card).join('');
}

// Carrega produtos do backend
async function carregarProdutos() {
  try {
    const response = await fetch('http://localhost:8080/api/produtos');
    if (!response.ok) throw new Error('Erro ao buscar produtos');
    
    produtos = await response.json();
    render(produtos);
  } catch (error) {
    console.error('Erro ao carregar produtos:', error);
    grid.innerHTML = '<p style="text-align: center; padding: 40px; color: #dc3545;">Erro ao carregar produtos. Verifique se o servidor está ativo.</p>';
  }
}

// Carrega produtos ao iniciar
carregarProdutos();

// Ordenação
document.getElementById('ordem').addEventListener('change', (e) => {
  const v = e.target.value;
  const arr = [...produtos];
  if (v === 'preco_asc') arr.sort((a,b)=>a.preco-b.preco);
  if (v === 'preco_desc') arr.sort((a,b)=>b.preco-a.preco);
  // distancia e avaliacao não existem no backend ainda
  render(arr);
});

// Busca
const searchInput = document.querySelector('.searchbar input');
searchInput.addEventListener('input', (e) => {
  const termo = e.target.value.toLowerCase();
  const filtrados = produtos.filter(p => 
    p.nome_produto.toLowerCase().includes(termo) ||
    (p.descricao && p.descricao.toLowerCase().includes(termo)) ||
    (p.propriedade?.nome_propriedade && p.propriedade.nome_propriedade.toLowerCase().includes(termo))
  );
  render(filtrados);
});

// Chips
const chips = document.getElementById('chips');
document.getElementById('btnAdd').addEventListener('click', () => {
  const c = document.createElement('span');
  c.className = 'chip';
  c.innerHTML = 'Orgânico <button class="chip-x">×</button>';
  c.querySelector('.chip-x').onclick = () => c.remove();
  chips.appendChild(c);
});
