package com.example.estudoFila.mapper;


import com.example.estudoFila.DTO.NotaPedidoRequestDTO;
import com.example.estudoFila.DTO.NotaPedidoResponseDTO;
import com.example.estudoFila.model.NotaPedido;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface NotaPedidoMapper {

    NotaPedido toEntity(NotaPedidoRequestDTO dto);

    NotaPedidoResponseDTO toDTO(NotaPedido notaPedido);

}
