package com.example.estudoFila.repository;

import com.example.estudoFila.DTO.NotaPedidoResponseDTO;
import com.example.estudoFila.model.NotaPedido;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotaPedidoRepository extends MongoRepository<NotaPedido, String > {
    Optional<NotaPedido> findByNuNota(String nuNota);
    List<NotaPedido> findByCodParceiro(String codParceiro);

}
