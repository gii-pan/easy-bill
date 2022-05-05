package br.com.alura.oobj.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeProduto;
    private String urlProduto;
    private String descricaoProduto;
    private BigDecimal precoProduto;
    private BigDecimal precoPromocionalProduto;
    private String classeFiscalProduto;
}
