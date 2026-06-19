package com.example.estudoFila.repository;

import com.example.estudoFila.DTO.ParceiroResponseDTO;
import com.example.estudoFila.model.Parceiro;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParceiroRepository extends MongoRepository<Parceiro, String> {
    Optional<Parceiro> findByCodParceiro(String codParceiro);

}
