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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class NotaPedidoService {

    private final NotaPedidoRepository notaPedidoRepository;

    private final NotaPedidoMapper notaPedidoMapper;

    private final ParceiroRepository parceiroRepository;

    private final ProdutoRepository produtoRepository;


    @Transactional
    public NotaPedidoResponseDTO criar(NotaPedidoRequestDTO dto) {

        Parceiro parceiro = parceiroRepository.findByCodParceiro(dto.codParceiro())
                .orElseThrow(() -> new RecursoNaoEncontrado("Parceiro não encontrado!"));


        NotaPedido nota = notaPedidoMapper.toEntity(dto);

        log.info("DTO CONVERTIDO: {}", nota);

        nota.setRazaoSocial(parceiro.getRazaoSocial());
        log.info("Razao social setada na nota: {}", nota);

        nota.setStatusConferencia(StatusConferencia.PENDENTE);
        log.info("Status da conferencia setada para PENDENTE {}", nota);

        log.info("Itens da nota antes do for: {}", nota.getItem());

        for (ItemNota item : nota.getItem()) {

            log.info("Entrou no for {}", item);

        Produto produto = produtoRepository.findByCodProduto(item.getCodProduto())
                .orElseThrow(() -> new RecursoNaoEncontrado("Produto não encontrado!"));

        item.setDescProduto(produto.getDescProduto());

        log.info("Produto setado: {}", item);


        log.info("itens: {}", item);

        nota.setValorNota(nota.getValorNota() + produto.getPreco() * item.getQtdNeg());


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