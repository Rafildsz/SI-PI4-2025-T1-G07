package com.example.backend_spring.repository;

import com.example.backend_spring.entity.Propriedade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PropriedadeRepository extends JpaRepository<Propriedade, Long> {
    @Query("SELECT p FROM Propriedade p WHERE p.usuario.id_usuario = :idUsuario")
    List<Propriedade> findByUsuarioId(@Param("idUsuario") Long idUsuario);
}
