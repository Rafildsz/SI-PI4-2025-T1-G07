/**
 * ========================================
 * SEMEAR - Script Global
 * ========================================
 * 
 * Descrição: Arquivo JavaScript global que contém funcionalidades reutilizáveis
 *            em toda a plataforma.
 * 
 * Funcionalidades:
 * - Gerenciamento do menu mobile (toggle)
 * - Atualização dinâmica do ano do footer
 * - Interatividades gerais
 * 
 * Dependências: Nenhuma
 * ========================================
 */

// Menu mobile simples e ano do rodapé
document.getElementById('year').textContent = new Date().getFullYear();

const toggle = document.getElementById('mobile-toggle');
const nav = document.getElementById('main-nav');
toggle && toggle.addEventListener('click', () => {
  if (!nav) return;
  const isVisible = nav.style.display === 'flex';
  nav.style.display = isVisible ? 'none' : 'flex';
  nav.style.flexDirection = 'column';
  nav.style.gap = '12px';
  nav.style.position = 'absolute';
  nav.style.top = '70px';
  nav.style.right = '24px';
  nav.style.background = '#fff';
  nav.style.padding = '12px';
  nav.style.borderRadius = '10px';
  nav.style.boxShadow = '0 8px 30px rgba(0,0,0,0.08)';
});
