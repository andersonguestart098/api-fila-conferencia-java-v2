package com.example.estudoFila.repository;

import com.example.estudoFila.model.FilaConfencia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilaConferenciaRepository extends MongoRepository<FilaConfencia, String> {
    Page<FilaConfencia> findAll(Pageable pageable);

}
