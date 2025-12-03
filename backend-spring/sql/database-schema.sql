-- Tabela: usuarios
CREATE TABLE usuarios (
    id_usuario BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome_completo VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    telefone VARCHAR(20),
    tipo_usuario VARCHAR(50) NOT NULL,
    endereco VARCHAR(255)
);

-- Tabela: propriedades
CREATE TABLE propriedades (
    id_propriedade BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome_propriedade VARCHAR(100) NOT NULL,
    cpf_cnpj VARCHAR(20),
    cep VARCHAR(10),
    endereco VARCHAR(255),
    descricao TEXT,
    selo_certificacao VARCHAR(100),
    id_usuario BIGINT NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario)
);

-- Tabela: categorias_produto
CREATE TABLE categorias_produto (
    id_categoria BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome_categoria VARCHAR(50) NOT NULL
);

-- Tabela: produtos
CREATE TABLE produtos (
    id_produto BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome_produto VARCHAR(100) NOT NULL,
    descricao TEXT,
    preco DOUBLE,
    unidade_medida VARCHAR(20),
    estoque INT,
    imagem_url VARCHAR(255),
    id_propriedade BIGINT NOT NULL,
    id_categoria BIGINT,
    FOREIGN KEY (id_propriedade) REFERENCES propriedades(id_propriedade),
    FOREIGN KEY (id_categoria) REFERENCES categorias_produto(id_categoria)
);

-- Tabela: pedidos
CREATE TABLE pedidos (
    id_pedido BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_restaurante BIGINT NOT NULL,
    id_produtor BIGINT NOT NULL,
    data_pedido TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    valor_total DOUBLE DEFAULT 0.0,
    status VARCHAR(50) DEFAULT 'pendente',
    tipo_entrega VARCHAR(50) DEFAULT 'retirada',
    observacoes TEXT,
    FOREIGN KEY (id_restaurante) REFERENCES usuarios(id_usuario),
    FOREIGN KEY (id_produtor) REFERENCES usuarios(id_usuario)
);

-- Tabela: itens_pedido
CREATE TABLE itens_pedido (
    id_item BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_pedido BIGINT NOT NULL,
    id_produto BIGINT NOT NULL,
    quantidade INT NOT NULL,
    preco_unitario DOUBLE NOT NULL,
    FOREIGN KEY (id_pedido) REFERENCES pedidos(id_pedido),
    FOREIGN KEY (id_produto) REFERENCES produtos(id_produto)
);
