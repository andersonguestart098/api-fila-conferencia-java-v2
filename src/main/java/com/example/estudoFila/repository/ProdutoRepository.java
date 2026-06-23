package com.example.estudoFila.repository;

import com.example.estudoFila.model.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoRepository extends MongoRepository<Produto, String> {
    Optional<Produto> findByCodProduto(String codProduto);
    Page<Produto> findAll(Pageable pageable);

}
