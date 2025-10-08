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

// Função para mostrar uma etapa n (1,2,3...)
function showStep(n) {
  // 1) ativa o conteúdo das etapas
  document.querySelectorAll('.step').forEach(el => {
    el.classList.toggle('active', el.classList.contains('step-' + n));
  });

  // 2) atualiza os círculos (com fallback se não houver data-step)
  const steps = Array.from(document.querySelectorAll('.progress-step'));
  steps.forEach((step, i) => {
    const s = Number(step.dataset.step) || (i + 1);
    step.dataset.step = String(s); // persiste para não voltar a 0

    step.classList.remove('active', 'completed');
    if (s < n) step.classList.add('completed');
    if (s === n) step.classList.add('active');

    const circle = step.querySelector('.circle');
    if (circle) circle.textContent = String(s);
  });

  // 3) pinta as linhas entre círculos (linha i conecta step i -> i+1)
  const lines = Array.from(document.querySelectorAll('.line'));
  lines.forEach((line, idx) => {
    line.classList.toggle('completed', idx < (n - 1));
  });

  console.log('Mostrando step', n);
}



// Exemplo: liga botões next/back (ajuste IDs conforme seu HTML)
document.getElementById('next1')?.addEventListener('click', () => {
  // validações do step1...
  showStep(2);
});
document.getElementById('back2')?.addEventListener('click', () => showStep(1));
document.getElementById('next2')?.addEventListener('click', () => {
  // validações do step2...
  showStep(3);
});
document.getElementById('back3')?.addEventListener('click', () => showStep(2));

// Inicializa mostrando a primeira etapa ao carregar
showStep(1);
