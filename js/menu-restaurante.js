// Componente de Menu para páginas do Restaurante
function carregarMenuRestaurante() {
  const sidebar = document.getElementById('sidebar');
  if (!sidebar) return;

  const usuarioNome = localStorage.getItem('usuario_logado') || 'Usuário';
  
  sidebar.innerHTML = `
    <div class="sb-header">
      <div class="sb-avatar">${usuarioNome.charAt(0).toUpperCase()}</div>
      <div>
        <div class="sb-title">${usuarioNome}</div>
      </div>
    </div>

    <nav class="sb-nav">
      <a class="sb-item" href="catalogo.html">📦 Catálogo</a>
      <a class="sb-item" href="carrinho.html" style="position: relative;">
        🛒 Meu Carrinho
        <span id="carrinho-contador" style="display: none; position: absolute; top: 8px; right: 12px; background: #dc3545; color: white; border-radius: 50%; width: 20px; height: 20px; font-size: 11px; font-weight: 700; display: flex; align-items: center; justify-content: center;">0</span>
      </a>
      <a class="sb-item" href="meus-pedidos.html">🧾 Meus Pedidos</a>
      <a class="sb-item" href="mensagens-restaurante.html">✉️ Mensagens</a>
      <div class="sb-sep"></div>
      <a class="sb-item sb-logout" href="index.html">↩️ Sair</a>
    </nav>
  `;

  // Configura logout
  const logoutBtn = sidebar.querySelector('.sb-logout');
  if (logoutBtn) {
    logoutBtn.addEventListener('click', (e) => {
      e.preventDefault();
      localStorage.removeItem('usuario_id');
      localStorage.removeItem('usuario_logado');
      localStorage.removeItem('semear_carrinho');
      window.location.href = 'index.html';
    });
  }

  // Atualiza contador do carrinho
  atualizarContadorCarrinhoMenu();
}

function atualizarContadorCarrinhoMenu() {
  const carrinho = localStorage.getItem('semear_carrinho');
  const itens = carrinho ? JSON.parse(carrinho) : [];
  const totalItens = itens.reduce((total, item) => total + item.quantidade, 0);
  
  const contador = document.getElementById('carrinho-contador');
  if (contador) {
    contador.textContent = totalItens;
    contador.style.display = totalItens > 0 ? 'flex' : 'none';
  }
}

// Carrega automaticamente quando o DOM estiver pronto
if (document.readyState === 'loading') {
  document.addEventListener('DOMContentLoaded', carregarMenuRestaurante);
} else {
  carregarMenuRestaurante();
}
