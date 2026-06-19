package com.example.estudoFila.service;

import com.example.estudoFila.DTO.NotaPedidoRequestDTO;
import com.example.estudoFila.DTO.NotaPedidoResponseDTO;
import com.example.estudoFila.enums.StatusConferencia;
import com.example.estudoFila.exception.RecursoNaoEncontrado;
import com.example.estudoFila.mapper.NotaPedidoMapper;
import com.example.estudoFila.model.ItemNota;
import com.example.estudoFila.model.NotaPedido;
import com.example.estudoFila.model.Parceiro;
import com.example.estudoFila.model.Produto;
import com.example.estudoFila.repository.NotaPedidoRepository;
import com.example.estudoFila.repository.ParceiroRepository;
import com.example.estudoFila.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class NotaPedidoService {

    private final NotaPedidoRepository notaPedidoRepository;

    private final NotaPedidoMapper notaPedidoMapper;

    private final ParceiroRepository parceiroRepository;

    private final ProdutoRepository produtoRepository;


    public NotaPedidoResponseDTO criar(NotaPedidoRequestDTO dto) {

        Parceiro parceiro = parceiroRepository.findByCodParceiro(dto.codParceiro())
                .orElseThrow(() -> new RecursoNaoEncontrado("Parceiro não encontrado!"));


        NotaPedido nota = notaPedidoMapper.toEntity(dto);

        nota.setRazaoSocial(parceiro.getRazaoSocial());
        nota.setStatusConferencia(StatusConferencia.PENDENTE);

        for (ItemNota item : nota.getItens()) {

        Produto produto = produtoRepository.findByCodProduto(item.getCodProduto())
                .orElseThrow(() -> new RecursoNaoEncontrado("Produto não encontrado!"));

        item.setDescProduto(produto.getDescProduto());

        }

        NotaPedido notaSalva = notaPedidoRepository.save(nota);

        return notaPedidoMapper.toDTO(notaSalva);


    }

    public List<NotaPedidoResponseDTO> listar() {

        return notaPedidoRepository.findAll()
                .stream()
                .map(notaPedidoMapper::toDTO)
                .toList();


    }

    public List<NotaPedidoResponseDTO> buscar(String codParceiro) {

        return notaPedidoRepository.findByCodParceiro(codParceiro).stream()
                .map(notaPedidoMapper::toDTO)
                .toList();


    }


}