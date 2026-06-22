package com.example.estudoFila.model;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "Conferencia")
public class FilaConfencia {

    @Id
    private String id;

    private String idNota;

    @NotEmpty(message = "Item não conferido!")
    private List<ItemNota> itensConf;

}