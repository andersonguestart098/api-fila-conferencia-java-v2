package com.example.estudoFila.service;

import com.example.estudoFila.DTO.ParceiroRequestDTO;
import com.example.estudoFila.DTO.ParceiroResponseDTO;
import com.example.estudoFila.exception.RecursoNaoEncontrado;
import com.example.estudoFila.mapper.ParceiroMapper;
import com.example.estudoFila.model.Parceiro;
import com.example.estudoFila.repository.ParceiroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ParceiroService {

    private final ParceiroRepository parceiroRepository;

    private final ParceiroMapper parceiroMapper;

    public ParceiroResponseDTO cadastrar(ParceiroRequestDTO dto) {

        Parceiro parceiro = parceiroMapper.toEntity(dto);

        Parceiro salvo = parceiroRepository.save(parceiro);

        return parceiroMapper.toDTO(salvo);

    }

    public List<ParceiroResponseDTO> listar() {

         return parceiroRepository.findAll().stream()
                 .map(parceiroMapper::toDTO)
                 .toList();

    }

    public ParceiroResponseDTO buscar(String codParceiro) {

        return parceiroRepository.findByCodParceiro(codParceiro)
                .map(parceiroMapper::toDTO)
                .orElseThrow(() -> new RecursoNaoEncontrado("Parceiro não encontrado!"));

    }

}
