package com.example.estudoFila.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Parceiro")
public class Parceiro {

    @Id
    private String id;

    @NotBlank(message = "Código do parceiro inválido!")
    private String codParceiro;

    @NotBlank(message = "Razao social inválida!")
    private String razaoSocial;

    private double limiteCredito;

}
