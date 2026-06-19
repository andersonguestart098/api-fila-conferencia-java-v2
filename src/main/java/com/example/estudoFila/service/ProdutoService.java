package com.example.estudoFila.service;

import com.example.estudoFila.DTO.ProdutoRequestDTO;
import com.example.estudoFila.DTO.ProdutoResponseDTO;
import com.example.estudoFila.exception.RecursoNaoEncontrado;
import com.example.estudoFila.mapper.ProdutoMapper;
import com.example.estudoFila.model.Produto;
import com.example.estudoFila.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    private final ProdutoMapper produtoMapper;

    public ProdutoResponseDTO cadastrar(ProdutoRequestDTO dto) {
        Produto produto = produtoMapper.toEntity(dto);

        Produto salvo = produtoRepository.save(produto);

        return produtoMapper.toDTO(salvo);

    }

    public List<ProdutoResponseDTO> listar() {

        return produtoRepository.findAll()
                .stream()
                .map(produtoMapper::toDTO)
                .toList();

    }

    public ProdutoResponseDTO buscar(String codProduto) {

        return produtoRepository.findByCodProduto(codProduto)
                .map(produtoMapper::toDTO)
                .orElseThrow(() -> new RecursoNaoEncontrado("Produto não encontrado!"));

    }


}
