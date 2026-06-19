package com.example.estudoFila.controller;

import com.example.estudoFila.DTO.ApiResponse;
import com.example.estudoFila.DTO.FilaConferenciaRequestDTO;
import com.example.estudoFila.DTO.FilaConferenciaResponseDTO;
import com.example.estudoFila.DTO.NotaPedidoResponseDTO;
import com.example.estudoFila.service.FilaConferenciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/conferencia")
@RequiredArgsConstructor
@RestController
public class FilaConferenciaController {

    private final FilaConferenciaService conferenciaService;

    @PostMapping
    public ResponseEntity<ApiResponse<FilaConferenciaResponseDTO>> criar(@RequestBody FilaConferenciaRequestDTO dto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(201, "Conferencia criada!", conferenciaService.criar(dto)));


    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<FilaConferenciaResponseDTO>>> listar() {

        return ResponseEntity.ok(ApiResponse.success(200, "Notas retornadas", conferenciaService.listar()));

    }

    /*
    @GetMapping("/{nuNota}")
    public ResponseEntity<ApiResponse<FilaConferenciaResponseDTO>> buscar() {


    }
     */

    @PatchMapping("/{nuNota}")
    public ResponseEntity<ApiResponse<NotaPedidoResponseDTO>> conferir(@PathVariable String nuNota,
                                                                       @RequestParam String codProduto,
                                                                       @RequestParam double qtdConf) {

        return ResponseEntity.ok(ApiResponse.success(200, "Item conferido!", conferenciaService.conferirItem(nuNota, codProduto, qtdConf)));

    }

}
