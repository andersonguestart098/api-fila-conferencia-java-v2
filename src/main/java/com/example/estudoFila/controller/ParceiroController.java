package com.example.estudoFila.controller;

import com.example.estudoFila.DTO.ApiResponse;
import com.example.estudoFila.DTO.ParceiroRequestDTO;
import com.example.estudoFila.DTO.ParceiroResponseDTO;
import com.example.estudoFila.service.ParceiroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/parceiro")
@RequiredArgsConstructor
@RestController
public class ParceiroController {


    private final ParceiroService parceiroService;

    @PostMapping
    public ResponseEntity<ApiResponse<ParceiroResponseDTO>> cadastrar(@RequestBody @Valid ParceiroRequestDTO dto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(201, "Parceiro cadastrado!", parceiroService.cadastrar(dto)));


    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ParceiroResponseDTO>>> listar(@PageableDefault(size = 10, sort = "id") Pageable pageable) {

        return ResponseEntity.ok(ApiResponse.success(200, "Lista retornada com sucesso!", parceiroService.listar(pageable)));

    }

    @GetMapping("/{codParceiro}")
    public ResponseEntity<ApiResponse<ParceiroResponseDTO>> buscar(@PathVariable String codParceiro) {

        return ResponseEntity.ok(ApiResponse.success(200, "Parceiro encontrado!", parceiroService.buscar(codParceiro)));



    }

}
