package com.example.estudoFila.mapper;

import com.example.estudoFila.DTO.ProdutoRequestDTO;
import com.example.estudoFila.DTO.ProdutoResponseDTO;
import com.example.estudoFila.model.Produto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface ProdutoMapper {

    Produto toEntity(ProdutoRequestDTO dto);

    ProdutoResponseDTO toDTO(Produto produto);

}
