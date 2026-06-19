package com.example.estudoFila.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Produto")
public class Produto {

    @Id
    private String id;

    @NotBlank(message = "Código do produto inválido!")
    private String codProduto;

    @NotBlank(message = "Descrição do produto inválida!")
    private String descProduto;

    private double preco;

}
