package com.example.backend_spring.repository;

import com.example.backend_spring.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    @Query("SELECT p FROM Produto p WHERE p.propriedade.id_propriedade = :propriedadeId")
    List<Produto> findByPropriedadeId(@Param("propriedadeId") Long propriedadeId);
}
