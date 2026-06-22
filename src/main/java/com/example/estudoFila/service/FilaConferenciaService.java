package com.example.estudoFila.service;

import com.example.estudoFila.DTO.FilaConferenciaRequestDTO;
import com.example.estudoFila.DTO.FilaConferenciaResponseDTO;
import com.example.estudoFila.DTO.NotaPedidoResponseDTO;
import com.example.estudoFila.enums.StatusConferencia;
import com.example.estudoFila.exception.RecursoNaoEncontrado;
import com.example.estudoFila.mapper.FilaConferenciaMapper;
import com.example.estudoFila.mapper.NotaPedidoMapper;
import com.example.estudoFila.model.FilaConfencia;
import com.example.estudoFila.model.ItemNota;
import com.example.estudoFila.model.NotaPedido;
import com.example.estudoFila.repository.FilaConferenciaRepository;
import com.example.estudoFila.repository.NotaPedidoRepository;
import com.example.estudoFila.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class FilaConferenciaService {

    private final FilaConferenciaRepository filaConferenciaRepository;

    private final FilaConferenciaMapper filaConferenciaMapper;

    private final NotaPedidoRepository notaPedidoRepository;

    private final ProdutoRepository produtoRepository;

    private final NotaPedidoMapper notaPedidoMapper;


    public FilaConferenciaResponseDTO criar(FilaConferenciaRequestDTO dto) {

        log.info("DTO PASSADO: {}", dto);

        FilaConfencia confencia = filaConferenciaMapper.toEntity(dto);

        log.info("DTO CONVERTIDO: {}", confencia);

        FilaConfencia conferenciaSalva = filaConferenciaRepository.save(confencia);

        log.info("Conferencia: {}", conferenciaSalva);

        return filaConferenciaMapper.toDTO(conferenciaSalva);

    }

    public List<FilaConferenciaResponseDTO> listar() {

        return filaConferenciaRepository.findAll()
                .stream()
                .map(filaConferenciaMapper::toDTO)
                .toList();

    }

    public NotaPedidoResponseDTO conferirItem(String id, String codProduto, double qtdConferida) {

        NotaPedido nota = notaPedidoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontrado("Nota não encontrada para nunota: " + id));

        produtoRepository.findByCodProduto(codProduto)
                .orElseThrow(() -> new RecursoNaoEncontrado("Produto não encontrado!"));

        boolean prodEncontrado = false;

        for (ItemNota item : nota.getItem()) {


            if (item.getCodProduto().equals(codProduto)) {

                prodEncontrado = true;

                item.setQtdConf(qtdConferida);

            if (item.getQtdConf() < item.getQtdNeg()) {
                nota.setStatusConferencia(StatusConferencia.DIVERGENTE);

            } else {
                nota.setStatusConferencia(StatusConferencia.CONFERIDO);
            }

                break;

            }


        }

        if (!prodEncontrado) {

            throw new RecursoNaoEncontrado("Produto não encontrado na nota: " + codProduto);
        }

        NotaPedido notaSalva = notaPedidoRepository.save(nota);

        return notaPedidoMapper.toDTO(notaSalva);

    }


}
