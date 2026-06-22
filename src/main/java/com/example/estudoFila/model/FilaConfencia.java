package com.example.estudoFila.model;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Conferencia")
public class FilaConfencia {

    @Id
    private String id;

    private String idNota;

}