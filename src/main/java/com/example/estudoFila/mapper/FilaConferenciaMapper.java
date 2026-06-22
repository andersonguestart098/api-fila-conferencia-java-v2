package com.example.estudoFila.mapper;

import com.example.estudoFila.DTO.FilaConferenciaRequestDTO;
import com.example.estudoFila.DTO.FilaConferenciaResponseDTO;
import com.example.estudoFila.model.FilaConfencia;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FilaConferenciaMapper {

    FilaConfencia toEntity(FilaConferenciaRequestDTO dto);

    FilaConferenciaResponseDTO toDTO(FilaConfencia confencia);

}
