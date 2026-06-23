package com.example.estudoFila.controller;


import com.example.estudoFila.DTO.ApiResponse;
import com.example.estudoFila.DTO.ProdutoRequestDTO;
import com.example.estudoFila.DTO.ProdutoResponseDTO;
import com.example.estudoFila.service.ProdutoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/produto")
@RequiredArgsConstructor
@RestController
public class ProdutoController {

    private final ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<ApiResponse<ProdutoResponseDTO>> cadastrar(@RequestBody @Valid ProdutoRequestDTO dto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(201, "Produto cadastrado!", produtoService.cadastrar(dto)));


    }


    @GetMapping
    public ResponseEntity<ApiResponse<Page<ProdutoResponseDTO>>> listar(@PageableDefault(size = 10, sort = "id")Pageable pageable) {

        return ResponseEntity.ok(ApiResponse.success(200, "Lista de progdutos gerada.", produtoService.listar(pageable)));

    }

    @PostMapping("/{codProduto}")
    public ResponseEntity<ApiResponse<ProdutoResponseDTO>> buscar(@PathVariable String codProduto) {

        return ResponseEntity.ok(ApiResponse.success(200, "Produto encontrado!", produtoService.buscar(codProduto)));

    }


}
