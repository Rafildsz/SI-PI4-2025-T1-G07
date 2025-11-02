console.log("🟢 Rodando o arquivo:", __filename);

const express = require('express');
const pool = require('./config/db');
require('dotenv').config({ path: './src/.env' });

const app = express();
app.use(express.json());

// 🔹 Rota inicial
app.get('/', (req, res) => {
  res.send('Servidor funcionando ✅');
});

// 🔹 Rota de teste do banco
app.get('/db-check', async (req, res) => {
  try {
    const [rows] = await pool.query('SELECT NOW() AS data;');
    res.json({ status: 'Conectado com sucesso!', data: rows[0] });
  } catch (error) {
    console.error('Erro no /db-check:', error);
    res.status(500).json({ status: 'Erro na conexão', message: error.message });
  }
});

// 🔹 Rota GET - listar usuários
app.get('/usuarios', async (req, res) => {
  try {
    console.log('➡️ Requisição recebida em /usuarios');
    const [rows] = await pool.query('SELECT * FROM usuarios');
    res.json(rows);
  } catch (error) {
    console.error('Erro no /usuarios:', error);
    res.status(500).json({ status: 'Erro ao buscar usuários', message: error.message });
  }
});

// 🔹 Rota POST - cadastrar novo usuário
app.post('/usuarios', async (req, res) => {
  try {
    const { nome_completo, email, senha, telefone, tipo_usuario } = req.body;

    if (!nome_completo || !email || !senha || !tipo_usuario) {
      return res.status(400).json({
        status: 'Erro',
        message: 'Campos obrigatórios não preenchidos.'
      });
    }

    const sql = `
      INSERT INTO usuarios (nome_completo, email, senha, telefone, tipo_usuario)
      VALUES (?, ?, ?, ?, ?)
    `;

    const [result] = await pool.query(sql, [
      nome_completo,
      email,
      senha,
      telefone || null,
      tipo_usuario
    ]);

    res.status(201).json({
      status: 'Sucesso',
      message: 'Usuário cadastrado com sucesso!',
      id: result.insertId
    });

  } catch (error) {
    console.error('Erro no POST /usuarios:', error);
    res.status(500).json({
      status: 'Erro',
      message: error.message
    });
  }
});

// 🔹 Inicia o servidor por último
const PORT = process.env.PORT || 4000;
app.listen(PORT, () => console.log(`🚀 Servidor rodando em http://localhost:${PORT}`));
