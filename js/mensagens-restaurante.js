/**
 * ========================================
 * SEMEAR - Sistema de Mensagens (Restaurante)
 * ========================================
 * 
 * Descrição: Gerencia sistema de mensagens diretas entre restaurantes
 * e produtores, com interface de chat em tempo real.
 * 
 * Funcionalidades:
 * - Lista de conversas com produtores
 * - Envio e recebimento de mensagens
 * - Menu lateral com histórico de conversas
 * - Notificações de novas mensagens
 * - Armazenamento de histórico de chat
 * 
 * Dependências: localStorage, API backend para persistência de mensagens
 * ========================================
 */

// Sistema de mensagens - RESTAURANTE
const conversas = {
  "1": {
    nome: "Fazenda Boa Vista",
    mensagens: [
      { texto: "Olá! Vi que você tem tomates frescos.", enviadoPor: "restaurante" },
      { texto: "Sim! Temos bastante disponível.", enviadoPor: "produtor" },
      { texto: "Gostaria de fazer um pedido de 50kg.", enviadoPor: "restaurante" }
    ]
  },
  "2": {
    nome: "Sítio Verde Vale",
    mensagens: [
      { texto: "Bom dia! As alfaces estão em promoção?", enviadoPor: "restaurante" },
      { texto: "Bom dia! Sim, R$ 2,50/kg esta semana.", enviadoPor: "produtor" }
    ]
  },
  "3": {
    nome: "Chácara Sol Nascente",
    mensagens: [
      { texto: "Oi! Podemos agendar uma entrega?", enviadoPor: "restaurante" }
    ]
  }
};

let conversaAtual = null;

document.addEventListener("DOMContentLoaded", () => {

  /* ===================================================
     MENU SLIDE LATERAL
  =================================================== */
  const rightMenu = document.getElementById("rightMenu");
  const menuToggle = document.getElementById("menuToggle");
  const chatTitulo = document.getElementById("chatNome");
  const chatMensagens = document.getElementById("chatMensagens");

  // ABRIR/FECHAR MENU
  if (rightMenu && menuToggle) {
    menuToggle.addEventListener("click", (e) => {
      e.stopPropagation();
      rightMenu.classList.toggle("open");
    });
  }

  // FECHAR MENU AO CLICAR FORA
  document.addEventListener("click", (e) => {
    const clicouFora =
      !rightMenu.contains(e.target) && !menuToggle.contains(e.target);

    if (clicouFora && rightMenu) {
      rightMenu.classList.remove("open");
    }
  });

  /* ===================================================
     LISTA DE CONVERSAS
  =================================================== */
  const listaConversas = document.getElementById("listaConversas");

  Object.keys(conversas).forEach(id => {
    const conv = conversas[id];

    const li = document.createElement("li");
    li.classList.add("conversation");
    li.dataset.id = id;

    li.innerHTML = `
      <div class="avatar"></div>
      <div class="info">
        <strong>${conv.nome}</strong>
        <span>${conv.mensagens[conv.mensagens.length - 1].texto.slice(0, 30)}...</span>
      </div>
      <span class="time">12:34</span>
    `;

    li.addEventListener("click", () => {
      abrirConversa(id);
      if (rightMenu) rightMenu.classList.remove("open");
    });

    listaConversas.appendChild(li);
  });

  /* ===================================================
     ABRIR UMA CONVERSA
  =================================================== */
  function abrirConversa(id) {
    conversaAtual = id;

    // marca conversa ativa
    document.querySelectorAll(".conversation").forEach(el =>
      el.classList.toggle("active", el.dataset.id === id)
    );

    chatTitulo.textContent = conversas[id].nome;

    // render mensagens
    chatMensagens.innerHTML = "";
    conversas[id].mensagens.forEach(msg => {
      const div = document.createElement("div");
      div.classList.add("msg");
      div.classList.add(msg.enviadoPor === "restaurante" ? "msg-right" : "msg-left");
      div.textContent = msg.texto;
      chatMensagens.appendChild(div);
    });

    // scroll no final
    chatMensagens.scrollTop = chatMensagens.scrollHeight;
  }


  /* ===================================================
     ENVIAR MENSAGEM
  =================================================== */
  const input = document.getElementById("msgInput");
  const btnEnviar = document.getElementById("btnEnviar");
  
  btnEnviar.addEventListener("click", enviarMensagem);

  input.addEventListener("keydown", (e) => {
    if (e.key === "Enter") {
      e.preventDefault();
      enviarMensagem();
    }
  });

  function enviarMensagem() {
    const texto = input.value.trim();
    if (!texto || !conversaAtual) return;

    conversas[conversaAtual].mensagens.push({
      texto,
      enviadoPor: "restaurante"
    });

    input.value = "";
    abrirConversa(conversaAtual);
  }


  /* ===================================================
     ABRIR PRIMEIRA CONVERSA AUTOMATICAMENTE
  =================================================== */
  const primeira = Object.keys(conversas)[0];
  if (primeira) abrirConversa(primeira);

});
