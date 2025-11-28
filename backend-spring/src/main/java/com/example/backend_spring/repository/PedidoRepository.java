package com.example.backend_spring.repository;

import com.example.backend_spring.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> { }

