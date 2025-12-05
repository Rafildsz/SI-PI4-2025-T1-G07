# semear
# 🌱 SEMEAR – Sistema Web de Vendas para Pequenos Produtores

Projeto Integrador IV – Sistemas de Informação – 2025  
Pontifícia Universidade Católica de Campinas  
Orientador: Prof. Dr. Luã Marcelo Muriana


## Descrição Sobre o Projeto 

Nossa aplicação SEMEAR é uma plataforma web desenvolvida com o objetivo de conectar pequenos produtores rurais diretamente a restaurantes, mercados e estabelecimentos alimentícios, eliminando a necessidade de intermediários.

A proposta do sistema é facilitar a *comercialização* de alimentos frescos, locais e sustentáveis, promovendo:

- Valorização da agricultura familiar;
- Redução de custos para estabelecimentos;
- Negociação direta entre produtor e comprador;
- Fortalecimento da economia local;
- Sustentabilidade ambiental.

## Equipe de Desenvolvedores
- Eduardo de Almeida Freitas – 24008082  
- Guilherme Silveira Alves – 24008428  
- Isabelly Letícia Horschutz – 24008480  
- Júlia Damária Lupi – 24002058  
- Maysa Gabrielle Rodrigues de Melo – 24009119  
- Rafael Rodrigues Cespedes – 24013307
## Como Executar o Projeto no VSCode Rodando Localmente 

     1. Clonar o Repositório no Vscode 
     https://github.com/Rafildsz/SI-PI4-2025-T1-G07.git     

     2. Rodar o Back-end (Spring Boot – Porta 8081)  
     
     No terminal do vscode: 
     
     Passo 1 : cd backend-spring 
     Passo 2 : .\mvnw spring-boot:run

     3. Rodar o Servidor Java (MainServer - Porta 8080)
     
     Passo 1: cd proxy-server 
     Passo 2: javac -d out src/server/MainServer.java src/server/handler/*.java
     Passo 3: java -cp out server.MainServer



## Dependencias Necessárias

Pré-requisitos para executar o projeto:

- Java JDK 17
- Maven
- MySQL Server 8.0 ou superior
- Navegador Web (Google Chrome, Edge, Firefox)
- Visual Studio Code (opcional, recomendado)