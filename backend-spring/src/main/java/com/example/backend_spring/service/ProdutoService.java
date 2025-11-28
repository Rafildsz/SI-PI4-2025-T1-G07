package com.example.backend_spring.service;

import com.example.backend_spring.entity.Produto;
import com.example.backend_spring.entity.Propriedade;
import com.example.backend_spring.repository.ProdutoRepository;
import com.example.backend_spring.repository.PropriedadeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;
    private final PropriedadeRepository propriedadeRepository;

    public ProdutoService(ProdutoRepository repository, PropriedadeRepository propriedadeRepository) {
        this.repository = repository;
        this.propriedadeRepository = propriedadeRepository;
    }

    public List<Produto> listar() {
        return repository.findAll();
    }

    public Produto buscar(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Produto criar(Produto produto, Long propriedadeId) {
        Propriedade prop = propriedadeRepository.findById(propriedadeId).orElse(null);
        if (prop == null) return null;

        produto.setPropriedade(prop);
        return repository.save(produto);
    }

    public Produto atualizar(Long id, Produto dados) {
        Produto p = buscar(id);
        if (p == null) return null;

        p.setNome_produto(dados.getNome_produto());
        p.setDescricao(dados.getDescricao());
        p.setPreco(dados.getPreco());
        p.setEstoque(dados.getEstoque());
        p.setUnidade_medida(dados.getUnidade_medida());
        p.setImagem_url(dados.getImagem_url());

        return repository.save(p);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
