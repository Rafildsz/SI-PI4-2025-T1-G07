//Autor: Rafael Cespedes
/**
 * ========================================
 * SEMEAR - Menu Dashboard (Produtor)
 * ========================================
 * 
 * Descrição: Componente de menu/navegação específico para o dashboard
 * do produtor com acesso a vendas, produtos e mensagens.
 * 
 * Funcionalidades:
 * - Renderização de menu sidebar lateral
 * - Exibição de perfil do usuário logado
 * - Navegação entre seções do produtor
 * - Gestão de logout e limpeza de sessão
 * - Carregamento automático ao abrir página
 * 
 * Dependências: localStorage (dados do usuário logado)
 * ========================================
 */

// Componente de Menu para páginas do Produtor
function carregarMenuProdutor() {
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
      <a class="sb-item" href="minhas-vendas.html">📦 Minhas Vendas</a>
      <a class="sb-item" href="meus-produtos.html">🌾 Meus Produtos</a>
      <a class="sb-item" href="mensagens-produtor.html">✉️ Mensagens</a>
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
}

// Carrega automaticamente quando o DOM estiver pronto
if (document.readyState === 'loading') {
  document.addEventListener('DOMContentLoaded', carregarMenuProdutor);
} else {
  carregarMenuProdutor();
}
