package com.example.estudoFila.service;

import com.example.estudoFila.DTO.ProdutoRequestDTO;
import com.example.estudoFila.DTO.ProdutoResponseDTO;
import com.example.estudoFila.exception.RecursoNaoEncontrado;
import com.example.estudoFila.mapper.ProdutoMapper;
import com.example.estudoFila.model.Produto;
import com.example.estudoFila.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    private final ProdutoMapper produtoMapper;

    public ProdutoResponseDTO cadastrar(ProdutoRequestDTO dto) {
        Produto produto = produtoMapper.toEntity(dto);

        Produto salvo = produtoRepository.save(produto);
        log.info("PRODUTO: {}", salvo);

        return produtoMapper.toDTO(salvo);

    }

    public Page<ProdutoResponseDTO> listar(Pageable pageable) {

        return produtoRepository.findAll(pageable)
                .map(produtoMapper::toDTO);

    }

    public ProdutoResponseDTO buscar(String codProduto) {

        return produtoRepository.findByCodProduto(codProduto)
                .map(produtoMapper::toDTO)
                .orElseThrow(() -> new RecursoNaoEncontrado("Produto não encontrado!"));

    }


}
