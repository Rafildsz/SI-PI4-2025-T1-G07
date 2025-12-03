package com.example.backend_spring.dto;

import lombok.Data;
import java.util.List;

@Data
public class PedidoCreateDTO {

    private Long usuarioId;
    private String status;
    private List<ItemPedidoDTO> itens;

    @Data
    public static class ItemPedidoDTO {
        private Long produtoId;
        private Integer quantidade;
        private Double preco_unitario;
    }
}
