package com.example.backend_spring.repository;

import com.example.backend_spring.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findByNomeProdutoContainingIgnoreCase(String nome);
}

