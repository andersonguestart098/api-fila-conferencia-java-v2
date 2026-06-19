package com.example.estudoFila.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ItemNota {

    @NotBlank(message = "Código do produto inválido!")
    private String codProduto;

    @NotBlank(message = "Descrição do produto inválida!")
    private String descProduto;

    @Positive(message = "Quantidade inválida!")
    private double qtdNeg;

    private double qtdConf;

}
