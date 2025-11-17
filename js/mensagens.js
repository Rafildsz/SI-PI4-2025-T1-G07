// Dados simulados de conversas
const conversas = {
  prod1: {
    nome: "Produtor 1",
    mensagens: [
      { texto: "Olá! Obrigado pela compra!", enviadoPor: "produtor" },
      { texto: "Qualquer dúvida estou à disposição.", enviadoPor: "produtor" }
    ]
  },
  prod2: {
    nome: "Produtor 2",
    mensagens: [
      { texto: "Pedido recebido!", enviadoPor: "produtor" },
      { texto: "Obrigado!", enviadoPor: "restaurante" }
    ]
  },
  prod3: {
    nome: "Produtor 3",
    mensagens: [
      { texto: "Seu pedido sairá amanhã cedo.", enviadoPor: "produtor" }
    ]
  },
  prod4: {
    nome: "Produtor 4",
    mensagens: [
      { texto: "Em separação no estoque.", enviadoPor: "produtor" }
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

  // ABRIR/FECHAR MENU
  if (rightMenu && menuToggle) {
    menuToggle.addEventListener("click", (e) => {
      e.stopPropagation();  // impede clique de fechar
      rightMenu.classList.toggle("open");
    });
  }

  // FECHAR MENU AO CLICAR FORA
  document.addEventListener("click", (e) => {
    const clicouFora =
      !rightMenu.contains(e.target) && !menuToggle.contains(e.target);

    if (clicouFora) {
      rightMenu.classList.remove("open");
    }
  });


  /* ===================================================
     ELEMENTOS DO CHAT
  =================================================== */
  const listaConversas = document.getElementById("listaConversas");
  const chatTitulo = document.getElementById("chatNome");
  const chatMensagens = document.getElementById("chatMensagens");
  const input = document.getElementById("msgInput");
  const btnEnviar = document.getElementById("btnEnviar");

  if (!listaConversas || !chatTitulo || !chatMensagens || !input || !btnEnviar) {
    console.warn("Elementos do chat não encontrados na página.");
    return;
  }

  /* ===================================================
     LISTA DE CONVERSAS (ESQUERDA)
  =================================================== */
  Object.keys(conversas).forEach(id => {
    const conv = conversas[id];

    const li = document.createElement("li");
    li.classList.add("conversation");
    li.dataset.id = id;

    li.innerHTML = `
      <div class="avatar"></div>
      <div class="info">
        <strong>${conv.nome}</strong>
        <span>Ver mensagens...</span>
      </div>
      <span class="time">Agora</span>
    `;

    li.addEventListener("click", () => {
      abrirConversa(id);
      rightMenu.classList.remove("open"); // fecha menu ao clicar em conversa
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
