package com.example.estudoFila.mapper;

import com.example.estudoFila.DTO.ParceiroRequestDTO;
import com.example.estudoFila.DTO.ParceiroResponseDTO;
import com.example.estudoFila.model.Parceiro;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface ParceiroMapper {

    Parceiro toEntity(ParceiroRequestDTO dto);

    ParceiroResponseDTO toDTO(Parceiro parceiro);

}
