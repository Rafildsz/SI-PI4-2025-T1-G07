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

// Login com integração backend
document.getElementById('loginForm').addEventListener('submit', function(e){
  e.preventDefault();
  
  const email = document.getElementById('loginEmail');
  const pass  = document.getElementById('loginPassword');

  // Validação básica
  if (!email.checkValidity()) { 
    alert('Informe um e-mail válido.'); 
    email.focus(); 
    return; 
  }
  if (pass.value.length < 6) { 
    alert('A senha deve ter ao menos 6 caracteres.'); 
    pass.focus();  
    return; 
  }

  // Envia login para o backend
  fetch('http://localhost:8081/api/login', {
    method: 'POST',
    headers: { 
      'Content-Type': 'application/json',
      'Accept': 'application/json'
    },
    body: JSON.stringify({
      email: email.value,
      senha: pass.value,
      tipoUsuario: 'PRODUTOR'
    })
  })
  .then(response => {
    if (!response.ok) {
      return response.json().then(data => {
        throw new Error(data.erro || 'Erro ao fazer login');
      });
    }
    return response.json();
  })
  .then(data => {
    // Salva informações do usuário logado
    localStorage.setItem('usuario_id', data.id);
    localStorage.setItem('usuario_logado', data.nome || 'Usuário');
    localStorage.setItem('tipo_usuario', 'PRODUTOR');
    
    // Redireciona para área do produtor
    window.location.href = 'minhas-vendas.html';
  })
  .catch(error => {
    alert(error.message || 'Erro ao fazer login. Verifique suas credenciais.');
  });
});
