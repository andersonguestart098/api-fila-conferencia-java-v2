package com.example.estudoFila.controller;


import com.example.estudoFila.DTO.ApiResponse;
import com.example.estudoFila.DTO.NotaPedidoRequestDTO;
import com.example.estudoFila.DTO.NotaPedidoResponseDTO;
import com.example.estudoFila.service.NotaPedidoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/nota")
@RequiredArgsConstructor
@RestController
public class NotaPedidoController {

    private final NotaPedidoService notaPedidoService;

    @PostMapping
    public ResponseEntity<ApiResponse<NotaPedidoResponseDTO>> criar(@RequestBody @Valid NotaPedidoRequestDTO dto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(201, "Nota criada!", notaPedidoService.criar(dto)));


    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<NotaPedidoResponseDTO>>> listar() {

        return ResponseEntity.ok(ApiResponse.success(200, "Lista de notas gerada.", notaPedidoService.listar()));

    }

    @GetMapping("/{codParceiro}")
    public ResponseEntity<ApiResponse<List<NotaPedidoResponseDTO>>> buscar(@PathVariable String codParceiro){

        return ResponseEntity.ok(ApiResponse.success(200, "Notas retornadas para o parceiro.", notaPedidoService.buscar(codParceiro)));

    }

}
