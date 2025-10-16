// Ano no rodapé
document.getElementById('year').textContent = new Date().getFullYear();

// Menu mobile simples
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

// Validação e "login" demo
document.getElementById('loginForm').addEventListener('submit', function(e){
  e.preventDefault();
  const email = document.getElementById('loginEmail');
  const pass  = document.getElementById('loginPassword');

  if (!email.checkValidity()) { alert('Informe um e-mail válido.'); email.focus(); return; }
  if (pass.value.length < 6)  { alert('A senha deve ter ao menos 6 caracteres.'); pass.focus();  return; }

  // (Demo) autenticação baseada no cadastro salvo em localStorage (ajuste para sua API real)
  try {
    const cad = JSON.parse(localStorage.getItem('semear_cadastro_demo') || '{}');
    if (cad.email && cad.senha && cad.email === email.value && cad.senha === pass.value) {
      window.location.href = 'catalogo.html'; // sucesso
      return;
    }
  } catch (err) {}

  // fallback (remova em produção; aqui só para navegar)
  window.location.href = 'catalogo.html';
});
