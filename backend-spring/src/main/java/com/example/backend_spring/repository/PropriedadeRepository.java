package com.example.backend_spring.repository;

import com.example.backend_spring.entity.Propriedade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropriedadeRepository extends JpaRepository<Propriedade, Long> {
    List<Propriedade> findByUsuario_Id_usuario(Long usuarioId);
}
