package com.example.estudoFila.model;

import com.example.estudoFila.enums.StatusConferencia;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "Produto")
public class NotaPedido {

    @Id
    private String id;

    private String nuNota;

    @NotBlank(message = "Código do parceiro inválido!")
    private String codParceiro;

    private String razaoSocial;

    @NotBlank(message = "Tipo de faturamento inválido!")
    private String tipoFat;

    private double valorNota;

    private StatusConferencia statusConferencia;

    private List<ItemNota> item;

}
