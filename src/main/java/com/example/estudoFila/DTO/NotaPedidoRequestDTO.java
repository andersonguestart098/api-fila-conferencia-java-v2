package com.example.estudoFila.DTO;

import com.example.estudoFila.model.ItemNota;

import java.util.List;

public record NotaPedidoRequestDTO(
        String codParceiro,

        String tipoFat,

        double valorNota,

        List<ItemNota> item
) {}